package com.banking.bankservice.web;

import com.banking.bankservice.dto.Account;
import com.banking.bankservice.service.BankAccountService;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    //TODO: BankAccountService, BankService and AccountService tests.

    private String basePath = "/account";

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping
    public List<Account> getAll() {
        ResponseEntity<Account[]> response = bankAccountService.findAll();
        return Arrays.asList(response.getBody());
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Integer id) {
        ResponseEntity<Account> response = bankAccountService.find(id);
        return response.getBody();
    }

    @GetMapping("/byName/{name}")
    public List<Account> getByName(@PathVariable String name) {
        ResponseEntity<Account[]> response = bankAccountService.findAllByName(name);
        return Arrays.asList(response.getBody());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void post(@RequestBody Account acc) {
        bankAccountService.store(acc);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody Account acc) {
        bankAccountService.update(id, acc);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        bankAccountService.delete(id);
    }

    @PutMapping("/reset")
    @ResponseStatus(NO_CONTENT)
    public void reset(@RequestBody Account acc) {
        bankAccountService.reset(acc);
    }
}
