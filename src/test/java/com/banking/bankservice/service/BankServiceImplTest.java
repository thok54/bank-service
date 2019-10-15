package com.banking.bankservice.service;

import com.banking.bankservice.dto.Bank;
import com.banking.bankservice.repository.MySqlBankRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceImplTest {

    private static final String BANK_NAME = "name";
    private static final int BANK_ID = 1;
    private Bank expectedBank = new Bank();

    @Mock
    private MySqlBankRepository repository;

    @InjectMocks
    private BankServiceImpl bankService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void testFindAllCallsFindAllFromRepository() {
        bankService.findAll();
        verify(repository).findAll();
    }


    @Test
    public void testFindCallsFindFromRepository() {
        bankService.find(BANK_ID);
        verify(repository).find(BANK_ID);
    }

    @Test
    public void testFindBanksByNameCallsFindByNameFromRepository() {
        bankService.findAllByName(BANK_NAME);
        verify(repository).findAllByName(BANK_NAME);
    }

    @Test
    public void testStoreBankCallsStoreFromRepository() {
        bankService.store(expectedBank);
        verify(repository).store(expectedBank);
    }

    @Test
    public void testUpdateBankCallsUpdateFromRepository() {
        bankService.update(BANK_ID, expectedBank);
        verify(repository).update(BANK_ID, expectedBank);
    }

    @Test
    public void testDeleteBankCallsDeleteFromRepository() {
        bankService.delete(BANK_ID);
        verify(repository).delete(BANK_ID);
    }
}