package com.example.banking3.Account;

import com.example.banking3.User.User;
import com.example.banking3.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account addAccountToUser(Long userId, Account account) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            account.setUser(user);
            Account savedAccount = accountRepository.save(account);
            return savedAccount;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<Account> findAccountsByUsername(String username){
        return accountRepository.findByUserUsername(username);
    }

    public Account deposit(Long accountId, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.deposit(amount);
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public Account withdraw(Long accountId, Double amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.withdraw(amount);
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public void transfer(Integer userId, Long fromAccountId, Long toAccountId, Double amount) throws Exception{
        
        Account fromAccount = accountRepository.findById(fromAccountId)
        .orElseThrow(() -> new Exception("From account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
        .orElseThrow(() -> new Exception("From account not found"));

        if(fromAccount.getUser().getId() == toAccount.getUser().getId()){
            if(fromAccount.getBalance() >= amount){
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                accountRepository.save(fromAccount);
                accountRepository.save(toAccount);
            }
        }
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}



