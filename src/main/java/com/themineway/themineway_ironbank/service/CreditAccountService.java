package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.dto.accounts.TransferenceDTO;
import com.themineway.themineway_ironbank.model.accounts.*;
import com.themineway.themineway_ironbank.repository.accounts.CreditAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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
        final var r = creditAccountRepository.getAccountByKeycloakUserId(userId, accountId);
        if(r.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        applyInterest(r.get());
        return r;
    }

    public void transfer(TransferenceDTO transferenceDTO, String owner) {
        final var fromAccount = creditAccountRepository.getAccountByKeycloakUserId(owner, transferenceDTO.from);
        final var toAccount = creditAccountRepository.findById(transferenceDTO.to);

        if(fromAccount.isEmpty() || toAccount.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        final var from = fromAccount.get();
        final var to = toAccount.get();

        applyInterest(from);
        applyInterest(to);

        if(from.getBalance().getAmount().compareTo(transferenceDTO.amount) < 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        from.getBalance().decreaseAmount(transferenceDTO.amount);
        to.getBalance().increaseAmount(transferenceDTO.amount);

        creditAccountRepository.saveAll(Arrays.stream(new CreditAccount[] { from, to }).toList());
        applyPenalty(from);
    }

    public void applyPenalty(CreditAccount account) {
        if(new BigDecimal("0").compareTo(account.getBalance().getAmount()) > 0) {
            account.getBalance().decreaseAmount(new BigDecimal(account.getPenaltyFee()));
            creditAccountRepository.save(account);
        }
    }

    public void applyInterest(CreditAccount account) {
        final var m = creditAccountRepository.getMonthsSinceLastInterest(account.getId());
        if(m.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        account.getBalance().increaseAmount(new BigDecimal(m.get().get("months").toString()).multiply(BigDecimal.valueOf(account.getInterestRate()).divide(new BigDecimal("12"))));
        account.setLastInterest(new Date());
        creditAccountRepository.save(account);
    }
}