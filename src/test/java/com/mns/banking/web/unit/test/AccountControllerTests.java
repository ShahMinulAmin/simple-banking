package com.mns.banking.web.unit.test;

import com.mns.banking.SimpleBankingApplication;
import com.mns.banking.entity.Account;
import com.mns.banking.service.AccountService;
import com.mns.banking.web.model.AccountDto;
import com.mns.banking.web.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SimpleBankingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Before
    public void setup() throws Exception {
        Account account1 = new Account(1, "121-123-323", "John Doe",
                "Mirpur, Dhaka", new BigDecimal(30000));
        Account account2 = new Account(2, "121-123-876", "Paul Alen",
                "Banani, Dhaka", new BigDecimal(20000));

        when(accountService.getAccountByAccountNumber("121-123-323")).thenReturn(account1);
        when(accountService.saveOrUpdateAccount(any(Account.class))).thenReturn(account2);
    }

    @Test
    public void GivenAccountNumber_WhenGetAccount_ThenReturnAccount() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/accounts/{accountNumber}", "121-123-323").
                contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    public void GivenAccountToAdd_WhenAddAccount_ThenReturnAddedAccount() throws Exception {
        AccountDto accountToAdd = new AccountDto(2, "121-123-876", "Paul Alen",
                "Banani, Dhaka", 20000.0);

        ResultActions result = mockMvc.perform(post("/api/v1/accounts")
                .content(JsonUtil.toJson(accountToAdd))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Paul Alen")));
    }

}
