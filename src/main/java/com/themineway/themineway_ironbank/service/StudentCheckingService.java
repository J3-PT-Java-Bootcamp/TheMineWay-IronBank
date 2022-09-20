package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.dto.accounts.TransferenceDTO;
import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public List<StudentChecking> getAllStudentCheckings() {
        return studentCheckingRepository.findAll();
    }

    public void updateAccountBalance(int id, Money balance) {
        final var _account = studentCheckingRepository.findById(id);
        if(_account.isEmpty()) return; // TODO: throw error
        final var account = _account.get();
        account.setBalance(balance);
        studentCheckingRepository.save(account);
    }

    public StudentChecking getById(int id) {
        final var _checking = studentCheckingRepository.findById(id);
        if(_checking.isEmpty()) return null; //TODO: except
        return _checking.get();
    }

    public List<StudentChecking> getAccountsByKeycloakUser(String userId) {
        return studentCheckingRepository.getAccountsByKeycloakUserId(userId);
    }

    public Optional<StudentChecking> getAccountByKeycloakUser(String userId, int accountId) {
        return studentCheckingRepository.getAccountByKeycloakUserId(userId, accountId);
    }

    public void transfer(TransferenceDTO transferenceDTO, String owner) {
        final var fromAccount = studentCheckingRepository.getAccountByKeycloakUserId(owner, transferenceDTO.from);
        final var toAccount = studentCheckingRepository.findById(transferenceDTO.to);

        if(fromAccount.isEmpty() || toAccount.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        final var from = fromAccount.get();
        final var to = toAccount.get();

        if(from.getBalance().getAmount().compareTo(transferenceDTO.amount) < 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        from.getBalance().decreaseAmount(transferenceDTO.amount);
        to.getBalance().increaseAmount(transferenceDTO.amount);

        studentCheckingRepository.saveAll(Arrays.stream(new StudentChecking[] { from, to }).toList());
        applyPenalty(from);
    }

    public void applyPenalty(StudentChecking account) {
        if(new BigDecimal("0").compareTo(account.getBalance().getAmount()) > 0) {
            account.getBalance().decreaseAmount(new BigDecimal(account.getPenaltyFee()));
            studentCheckingRepository.save(account);
        }
    }
}
