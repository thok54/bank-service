package com.banking.bankservice.web;

import com.banking.bankservice.dto.Bank;
import com.banking.bankservice.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping
    public List<Bank> getAll() {
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public Bank getById(@PathVariable Integer id) {
        return bankService.find(id);
    }

    @GetMapping("/byName/{name}")
    public List<Bank> getByName(@PathVariable String name) {
        return bankService.findAllByName(name);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void store(@RequestBody Bank bank) {
        bankService.store(bank);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Bank bank) {
        bankService.update(id, bank);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        bankService.delete(id);
    }
}
