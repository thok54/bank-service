package com.banking.bankservice.web;

import com.banking.bankservice.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Account getAll() {
        ResponseEntity<Account> response = restTemplate.getForEntity("http://localhost:8085/account", Account.class);
        return response.getBody();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Integer id) {
        ResponseEntity<Account> response = restTemplate.getForEntity("http://localhost:8085/account/" + id, Account.class);
        return response.getBody();
    }

    @GetMapping("/byName/{name}")
    public Account getByName(@PathVariable String name) {
        ResponseEntity<Account> response = restTemplate.getForEntity("http://localhost:8085/account/byName/" + name, Account.class);
        return response.getBody();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody Account acc) {
        restTemplate.postForEntity("http://localhost:8085/account", acc, Account.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody Account acc) {
        restTemplate.put("http://localhost:8085/account/" + id, acc);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        restTemplate.delete("http://localhost:8085/account/" + id);
    }

    @PutMapping("/reset")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reset(@RequestBody Account acc) {
        restTemplate.put("http://localhost:8085/account/reset", acc);
    }
}
