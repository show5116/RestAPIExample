package com.example.restapi.controller;

import com.example.restapi.entity.Account;
import com.example.restapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    //생성
    @PostMapping("/account")
    public ResponseEntity<?> saveAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    //전체조회
    @GetMapping("/account")
    public List<Account> all() {
        return accountRepository.findAll();
    }

    //단건조회
    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    //수정
    @PutMapping("/account/{id}")
    public void updateAccount(@PathVariable String id, @RequestBody Account newAccount){
        accountRepository.findById(id)
                .map(account -> {
                    account.setName(newAccount.getName());
                    account.setPassword(newAccount.getPassword());
                    return accountRepository.save(account);
                })
                .orElseGet(() ->{
                    newAccount.setId(id);
                    return accountRepository.save(newAccount);
                });
    }

    //삭제
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountRepository.deleteById(id);
    }
}
