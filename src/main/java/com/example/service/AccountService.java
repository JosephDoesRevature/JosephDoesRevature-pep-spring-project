package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired 
    AccountRepository accountRepository;
    public boolean verifyAccount(Account account) {
        if(account == null){
            return false;
        }
        if(account.getUsername().length() == 0){
            return false;
        }
        if(account.getPassword().length() < 4){
            return false;
        }
        return true;
    }

    public boolean verifyName(Account account) {
        return (accountRepository.findByUsername(account.getUsername()) == null);
    }

    public Account registerAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account login(Account account) {
        Account desired = accountRepository.findByUsername(account.getUsername());
        if(desired != null && desired.getPassword().equals(account.getPassword())){
            return desired;
        }
        else{
            return null;
        }
    }
}
