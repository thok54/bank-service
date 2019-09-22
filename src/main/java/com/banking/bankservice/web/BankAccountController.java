package com.banking.bankservice.web;

import com.banking.bankservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Account get() {
        ResponseEntity<Account> response = restTemplate.getForEntity("http://localhost:8090/account", Account.class);

        return response.getBody();
    }
}
