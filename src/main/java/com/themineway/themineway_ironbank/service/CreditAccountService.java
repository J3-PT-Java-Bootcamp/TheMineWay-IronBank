package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.dto.accounts.TransferenceDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.CreditAccount;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.CreditAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CreditAccountService {

    @Autowired
    CreditAccountRepository creditAccountRepository;

    public List<CreditAccount> getAllCreditAccounts() {
        return creditAccountRepository.findAll();
    }

    public void createCreditAccount(CreditAccount creditAccount) {

        BigDecimal creditLimit = creditAccount.getCreditLimit() == null ? new BigDecimal("100") : creditAccount.getCreditLimit();
        float interestRate = creditAccount.getInterestRate() == null ? 0.2f : creditAccount.getInterestRate();

        creditAccount.setCreditLimit(creditLimit);
        creditAccount.setInterestRate(interestRate);

        creditAccountRepository.save(creditAccount);
    }

    public void updateAccountBalance(int id, Money balance) {
        final var _account = creditAccountRepository.findById(id);
        if(_account.isEmpty()) return; // TODO: throw error
        final var account = _account.get();
        account.setBalance(balance);
        creditAccountRepository.save(account);
    }

    public CreditAccount getById(int id) {
        final var _credit = creditAccountRepository.findById(id);
        if(_credit.isEmpty()) return null; //TODO: except
        return _credit.get();
    }

    public List<CreditAccount> getAccountsByKeycloakUser(String userId) {
        return creditAccountRepository.getAccountsByKeycloakUserId(userId);
    }

    public Optional<CreditAccount> getAccountByKeycloakUser(String userId, int accountId) {
        return creditAccountRepository.getAccountByKeycloakUserId(userId, accountId);
    }

    public void transfer(TransferenceDTO transferenceDTO, String owner) {
        final var fromAccount = creditAccountRepository.getAccountByKeycloakUserId(owner, transferenceDTO.from);
        final var toAccount = creditAccountRepository.findById(transferenceDTO.to);

        if(fromAccount.isEmpty() || toAccount.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        final var from = fromAccount.get();
        final var to = toAccount.get();

        if(from.getBalance().getAmount().compareTo(transferenceDTO.amount) < 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        from.getBalance().decreaseAmount(transferenceDTO.amount);
        to.getBalance().increaseAmount(transferenceDTO.amount);

        creditAccountRepository.saveAll(Arrays.stream(new CreditAccount[] { from, to }).toList());
    }
}