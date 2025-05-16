package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;

@Service
public class AccountService {

    public static boolean verifyAccount(Account account) {
        // TODO Auto-generated method stub
        return true;
    }

    public static boolean verifyName(Account account) {
        // TODO Auto-generated method stub
        return false;
    }

    public static Account registerAccount(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerAccount'");
    }
}
