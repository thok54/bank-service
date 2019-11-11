package com.banking.bankservice.service;

import com.banking.bankservice.dto.Account;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceImplTest {

    private String basePath = "/account";
    private static final String ACCOUNT_NAME = "name";
    private static final int ACCOUNT_ID = 1;
    private Account expectedAccount = new Account();

    @Mock
    private RestTemplate accountRestTemplate;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void findAllCallsSuccesfully() {
        bankAccountService.findAll();
        verify(accountRestTemplate).getForEntity(basePath, Account[].class);
    }

    @Test
    public void findCallsSuccesfully() {
        bankAccountService.find(ACCOUNT_ID);
        verify(accountRestTemplate).getForEntity(basePath + "/" + ACCOUNT_ID, Account.class);
    }

    @Test
    public void findAllByNameCallsSuccesfully() {
        bankAccountService.findAllByName(ACCOUNT_NAME);
        verify(accountRestTemplate).getForEntity(basePath + "/byName/" + ACCOUNT_NAME, Account[].class);
    }

    @Test
    public void storeCallsSuccesfully() {
        bankAccountService.store(expectedAccount);
        verify(accountRestTemplate).postForEntity(basePath, expectedAccount, Account.class);
    }

    @Test
    public void updateCallsSuccesfully() {
        bankAccountService.update(ACCOUNT_ID, expectedAccount);
        verify(accountRestTemplate).put(basePath + "/" + ACCOUNT_ID, expectedAccount);
    }

    @Test
    public void deleteCallsSuccesfully() {
        bankAccountService.delete(ACCOUNT_ID);
        verify(accountRestTemplate).delete(basePath + "/" + ACCOUNT_ID);
    }

    @Test
    public void resetCallsSuccesfully() {
        bankAccountService.reset(expectedAccount);
        verify(accountRestTemplate).put(basePath + "/reset", expectedAccount);
    }
}