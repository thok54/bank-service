package com.banking.bankservice.web;

import com.banking.bankservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    //TODO:BankAccount Controller + BankAccountService.

    private String basePath = "/account";

    @Autowired
    private RestTemplate accountRestTemplate;

    @GetMapping
    public List<Account> getAll() {
        ResponseEntity<Account[]> response = accountRestTemplate.getForEntity(basePath, Account[].class);
        return Arrays.asList(response.getBody());
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Integer id) {
        ResponseEntity<Account> response = accountRestTemplate.getForEntity(basePath + "/" + id, Account.class);
        return response.getBody();
    }

    @GetMapping("/byName/{name}")
    public Account getByName(@PathVariable String name) {
        ResponseEntity<Account> response = accountRestTemplate.getForEntity(basePath + "/byName/" + name, Account.class);
        return response.getBody();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void post(@RequestBody Account acc) {
        accountRestTemplate.postForEntity(basePath, acc, Account.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody Account acc) {
        accountRestTemplate.put(basePath + "/" + id, acc);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        accountRestTemplate.delete(basePath + "/" + id);
    }

    @PutMapping("/reset")
    @ResponseStatus(NO_CONTENT)
    public void reset(@RequestBody Account acc) {
        accountRestTemplate.put(basePath + "/reset", acc);
    }
}
