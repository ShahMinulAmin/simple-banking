package com.mns.banking.web.e2e.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mns.banking.SimpleBankingApplication;
import com.mns.banking.web.model.AccountDto;
import com.mns.banking.web.model.TransferDto;
import com.mns.banking.web.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SimpleBankingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class BankingE2E {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String ACCOUNT_NUMBER_1 = "121-123-323";
    private final String ACCOUNT_NUMBER_2 = "121-123-876";
    private final Double AMOUNT_TO_TRANSFER = 500.0;

    @Test
    public void GivenTwoAccounts_WhenTransferAmount_ThenVerifyBalance() throws Exception {

        // Get the initial balance of account one
        ResultActions accOneResultActions = mockMvc.perform(get("/api/v1/accounts/{accountNumber}",
                ACCOUNT_NUMBER_1)
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult accOneResult = accOneResultActions.andReturn();
        String accOneContent = accOneResult.getResponse().getContentAsString();
        AccountDto accOneDto = objectMapper.readValue(accOneContent, AccountDto.class);

        // Calculate the expected balance of account one by subtracting the transfer amount
        Double expectedBalance1 = accOneDto.getBalance() - AMOUNT_TO_TRANSFER;
        System.out.println(expectedBalance1);

        // Get the initial balance of account two
        ResultActions accTwoResultActions = mockMvc.perform(get("/api/v1/accounts/{accountNumber}",
                ACCOUNT_NUMBER_2)
                .contentType(MediaType.APPLICATION_JSON));
        MvcResult accTwoResult = accTwoResultActions.andReturn();
        String accTwoContent = accTwoResult.getResponse().getContentAsString();
        AccountDto accTwoDto = objectMapper.readValue(accTwoContent, AccountDto.class);

        // Calculate the expected balance of account two by adding the transfer amount
        Double expectedBalance2 = accTwoDto.getBalance() + AMOUNT_TO_TRANSFER;
        System.out.println(expectedBalance2);


        /** Transfer the amount from account one to account two **/
        TransferDto transferDto = new TransferDto(ACCOUNT_NUMBER_1, ACCOUNT_NUMBER_2, AMOUNT_TO_TRANSFER);
        ResultActions transferResult = mockMvc.perform(post("/api/v1/transactions/transfer")
                .content(JsonUtil.toJson(transferDto))
                .contentType(MediaType.APPLICATION_JSON));
        // Verify the transferred amount
        transferResult.andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount", is(AMOUNT_TO_TRANSFER)));


        /** Get the updated balance of account one **/
        accOneResultActions = mockMvc.perform(get("/api/v1/accounts/{accountNumber}",
                ACCOUNT_NUMBER_1)
                .contentType(MediaType.APPLICATION_JSON));
        // Verify the updated balance of account one
        accOneResultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(expectedBalance1)));


        /** Get the updated balance of account two **/
        accTwoResultActions = mockMvc.perform(get("/api/v1/accounts/{accountNumber}",
                ACCOUNT_NUMBER_2)
                .contentType(MediaType.APPLICATION_JSON));
        // Verify the updated balance of account two
        accTwoResultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(expectedBalance2)));
    }

}
