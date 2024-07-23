package com.example.banking3.Account;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAccounts(@RequestParam String username) {
        try {
            List<Account> accounts = accountService.findAccountsByUsername(username);
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving accounts");
        }
    }

    @PostMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<Account> addAccountToUser(
        @PathVariable Long userId,
        @RequestBody Account account){
        Account savedAccount = accountService.addAccountToUser(userId, account);
        return ResponseEntity.ok(savedAccount);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Integer userId, @RequestBody TransferRequest transferRequest){
        try{
            accountService.transfer(userId, transferRequest.getFromAccountId(), transferRequest.getToAccountId(), transferRequest.getAmount());
            return ResponseEntity.ok("Transfer successful.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{accountId}/deposit")
    @ResponseBody
    public ResponseEntity<Account> deposit(
        @PathVariable Long accountId,
        @RequestBody Double amount){
        Account updatedAccount = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping("/{accountId}/withdraw")
    @ResponseBody
    public ResponseEntity<Account> withdraw(
        @PathVariable Long accountId,
        @RequestBody Double amount) {
        Account updatedAccount = accountService.withdraw(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("account deleted successfully.");
    }
    
}
    


