package com.banking.bankservice.web;

import com.banking.bankservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Account get() {
        ResponseEntity<Account> response = restTemplate.getForEntity("http://localhost:8085/account", Account.class);
        return response.getBody();
    }

    @PostMapping
    public void post() {
        HttpEntity<Account> request = new HttpEntity<>(new Account());
        restTemplate.postForEntity("http://localhost:8085/account", request, Account.class);
    }

    @PutMapping
    public void put() {/*
        Account updatedInstance = new Account();
        updatedInstance.setId(createResponse.getBody().getId());
        String resourceUrl = "http://localhost:8085/account";
        HttpEntity<Account> requestUpdate = new HttpEntity<>(updatedInstance, headers);
        template.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
        return response.getBody();
    */
    }

    @DeleteMapping
    public void delete() {
        restTemplate.delete("http://localhost:8085/account");
    }
}
