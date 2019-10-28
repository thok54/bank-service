package com.banking.bankservice.service;

import com.banking.bankservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private RestTemplate accountRestTemplate;

    private String basePath = "/account";

    @Override
    public ResponseEntity<Account[]> findAll() {
        return accountRestTemplate.getForEntity(basePath, Account[].class);
    }

    @Override
    public ResponseEntity<Account> find(int id) {
        return accountRestTemplate.getForEntity(basePath + "/" + id, Account.class);
    }

    @Override
    public ResponseEntity<Account[]> findAllByName(String name) {
        return accountRestTemplate.getForEntity(basePath + "/byName/" + name, Account[].class);
    }

    @Override
    public void store(Account acc) {
        accountRestTemplate.postForEntity(basePath, acc, Account.class);
    }

    @Override
    public void update(int id, Account acc) {
        accountRestTemplate.put(basePath + "/" + id, acc);
    }

    @Override
    public void delete(int id) {
        accountRestTemplate.delete(basePath + "/" + id);
    }

    @Override
    public void reset(Account acc) {
        accountRestTemplate.put(basePath + "/reset", acc);
    }

}
