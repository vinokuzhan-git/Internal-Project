package com.suphse.ecommerce.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suphse.ecommerce.customer.controller.AccountDetailsController;
import com.suphse.ecommerce.customer.entity.AccountDetails;
import com.suphse.ecommerce.customer.service.AccountDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountDetailsController.class)
@AutoConfigureMockMvc
public class AccountDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountDetailsService accountDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccountDetails createTestAccountDetails() {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(UUID.randomUUID());
        accountDetails.setAccountName("John Doe");
        accountDetails.setEmail("john.doe@example.com");
        accountDetails.setFirstName("John");
        accountDetails.setLastName("Doe");
        accountDetails.setAccountType("Personal Account");
        return accountDetails;
    }

    @Test
    public void testSaveAccountDetails() throws Exception {
        AccountDetails accountDetails = createTestAccountDetails();

        when(accountDetailsService.saveAccountDetails(any(AccountDetails.class))).thenReturn(accountDetails);

        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDetails)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(accountDetails.getId().toString()))
                .andExpect(jsonPath("$.accountName").value(accountDetails.getAccountName()))
                .andExpect(jsonPath("$.email").value(accountDetails.getEmail()))
                .andExpect(jsonPath("$.firstName").value(accountDetails.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(accountDetails.getLastName()))
                .andExpect(jsonPath("$.accountType").value(accountDetails.getAccountType()));
    }

    @Test
    public void testUpdateAccountDetails() throws Exception {
        AccountDetails accountDetails = createTestAccountDetails();
        accountDetails.setAccountName("Updated Name");

        when(accountDetailsService.updateAccountDetails(any(AccountDetails.class))).thenReturn(accountDetails);

        mockMvc.perform(put("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountDetails.getId().toString()))
                .andExpect(jsonPath("$.accountName").value(accountDetails.getAccountName()))
                .andExpect(jsonPath("$.email").value(accountDetails.getEmail()))
                .andExpect(jsonPath("$.firstName").value(accountDetails.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(accountDetails.getLastName()))
                .andExpect(jsonPath("$.accountType").value(accountDetails.getAccountType()));
    }

    @Test
    public void testFetchAccountDetails() throws Exception {
        UUID accountId = UUID.randomUUID();
        AccountDetails accountDetails = createTestAccountDetails();
        accountDetails.setId(accountId);

        when(accountDetailsService.fetchAccountDetails(accountId)).thenReturn(accountDetails);

        mockMvc.perform(get("/account/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountDetails.getId().toString()))
                .andExpect(jsonPath("$.accountName").value(accountDetails.getAccountName()))
                .andExpect(jsonPath("$.email").value(accountDetails.getEmail()))
                .andExpect(jsonPath("$.firstName").value(accountDetails.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(accountDetails.getLastName()))
                .andExpect(jsonPath("$.accountType").value(accountDetails.getAccountType()));
    }

    @Test
    public void testUpgradeAccount() throws Exception {
        UUID accountId = UUID.randomUUID();
        AccountDetails upgradedAccountDetails = createTestAccountDetails();
        upgradedAccountDetails.setId(accountId);
        upgradedAccountDetails.setAccountType("Business Account");

        when(accountDetailsService.upgradeAccount(accountId)).thenReturn(upgradedAccountDetails);

        mockMvc.perform(put("/account/bisaccount/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(upgradedAccountDetails.getId().toString()))
                .andExpect(jsonPath("$.accountName").value(upgradedAccountDetails.getAccountName()))
                .andExpect(jsonPath("$.email").value(upgradedAccountDetails.getEmail()))
                .andExpect(jsonPath("$.firstName").value(upgradedAccountDetails.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(upgradedAccountDetails.getLastName()))
                .andExpect(jsonPath("$.accountType").value(upgradedAccountDetails.getAccountType()));
    }
}
