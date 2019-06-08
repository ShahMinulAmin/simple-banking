package com.mns.banking.web.unit.test;

import com.mns.banking.SimpleBankingApplication;
import com.mns.banking.entity.Account;
import com.mns.banking.entity.Transaction;
import com.mns.banking.service.AccountService;
import com.mns.banking.service.TransactionService;
import com.mns.banking.web.model.TransferDto;
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
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SimpleBankingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    private Date dateObj = new Date();

    @Before
    public void setup() throws Exception {
        Account account1 = new Account(1, "121-123-323", "John Doe",
                "Mirpur, Dhaka", new BigDecimal(30000));
        Account account2 = new Account(2, "121-123-876", "Paul Alen",
                "Banani, Dhaka", new BigDecimal(20000));
        BigDecimal amount = new BigDecimal(500.0);
        Transaction transaction = new Transaction(3, account1, account2, amount, 3, dateObj);

        when(accountService.getAccountByAccountNumber("121-123-323")).thenReturn(account1);
        when(accountService.getAccountByAccountNumber("121-123-876")).thenReturn(account2);
        when(transactionService.transferAmount(account1, account2, amount)).thenReturn(transaction);
    }

    @Test
    public void GivenTwoAccounts_WhenTransferAmount_ThenReturnTransaction() throws Exception {
        TransferDto transferDto = new TransferDto("121-123-323", "121-123-876", 500.0);

        ResultActions result = mockMvc.perform(post("/api/v1/transactions/transfer")
                .content(JsonUtil.toJson(transferDto))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount", is(500.0)));
    }
}
