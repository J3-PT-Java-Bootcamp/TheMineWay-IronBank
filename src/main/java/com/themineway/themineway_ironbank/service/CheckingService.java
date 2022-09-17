package com.themineway.themineway_ironbank.service;

import com.themineway.themineway_ironbank.model.accounts.Checking;
import com.themineway.themineway_ironbank.model.accounts.Money;
import com.themineway.themineway_ironbank.model.accounts.StudentChecking;
import com.themineway.themineway_ironbank.repository.accounts.CheckingRepository;
import com.themineway.themineway_ironbank.repository.accounts.StudentCheckingRepository;
import com.themineway.themineway_ironbank.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    UserRepository userRepository;


    public List<Checking> getAllCheckings() {
        return checkingRepository.findAll();
    }

    public void createChecking(Checking checking) {
        final var primaryOwnerFetch = userRepository.findById(checking.getPrimaryOwner().getId());
        if(primaryOwnerFetch.isEmpty()) return; // TODO: throw exception

        final var primaryOwner = primaryOwnerFetch.get();
        final var age = Period.between(primaryOwner.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();

        if(age < 24) {
            final var studentChecking = new StudentChecking();

            studentChecking.setAccountStatus(checking.getAccountStatus());
            studentChecking.setBalance(checking.getBalance());
            studentChecking.setPenaltyFee(checking.getPenaltyFee());
            studentChecking.setSecretKey(checking.getSecretKey());
            studentChecking.setPrimaryOwner(checking.getPrimaryOwner());

            studentCheckingRepository.save(studentChecking);
        } else {
            checkingRepository.save(checking);
        }
    }

    public void updateAccountBalance(int id, Money balance) {
        final var _account = checkingRepository.findById(id);
        if(_account.isEmpty()) return; // TODO: throw error
        final var account = _account.get();
        account.setBalance(balance);
        checkingRepository.save(account);
    }

    public Checking getById(int id) {
        final var _checking = checkingRepository.findById(id);
        if(_checking.isEmpty()) return null; //TODO: except
        return _checking.get();
    }

    public List<Checking> getAccountsByKeycloakUser(String userId) {
        return checkingRepository.getAccountsByKeycloakUserId(userId);
    }

    public Optional<Checking> getAccountByKeycloakUser(String userId, int accountId) {
        return checkingRepository.getAccountByKeycloakUserId(userId, accountId);
    }
}