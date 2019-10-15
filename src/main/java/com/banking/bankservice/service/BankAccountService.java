package com.banking.bankservice.service;

import com.banking.bankservice.dto.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankAccountService {

    ResponseEntity<Account[]> findAll();

    ResponseEntity<Account> find(int accountId);

    ResponseEntity<Account[]> findAllByName(String name);

    void store(Account acc);

    void update(int id, Account acc);

    void delete(int id);

    void reset(Account acc);
}
