package com.banking.bankservice.repository;

import com.banking.bankservice.dto.Bank;

import java.util.List;

public interface BankRepository {

    List<Bank> findAll();

    Bank find(int id);

    List<Bank> findAllByName(String name);

    void store(Bank bank);

    void update(int id, Bank bank);

    void delete(int id);
}

