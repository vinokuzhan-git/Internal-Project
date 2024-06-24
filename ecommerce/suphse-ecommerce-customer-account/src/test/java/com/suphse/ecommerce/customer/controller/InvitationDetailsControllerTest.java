package com.suphse.ecommerce.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suphse.ecommerce.customer.controller.InvitationDetailsController;
import com.suphse.ecommerce.customer.model.BusinessAccount;
import com.suphse.ecommerce.customer.model.SubAccountActivity;
import com.suphse.ecommerce.customer.service.InvitationDetailsService;

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

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InvitationDetailsController.class)
@AutoConfigureMockMvc
public class InvitationDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvitationDetailsService invitationDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    private BusinessAccount createTestBusinessAccount() {
        BusinessAccount businessAccount = new BusinessAccount();
        businessAccount.setBisAccountId(UUID.randomUUID());
        BusinessAccount.Account account = new BusinessAccount.Account();
        account.setAccountId(UUID.randomUUID());
        businessAccount.setAccounts(Arrays.asList(account));
        return businessAccount;
    }

    private SubAccountActivity createTestSubAccountActivity() {
        return new SubAccountActivity(UUID.randomUUID(), UUID.randomUUID(), "ACCEPT");
    }

    @Test
    public void testCreateInvitation() throws Exception {
        BusinessAccount businessAccount = createTestBusinessAccount();
        String expectedMessage = "Invitation send successfully !!";

        when(invitationDetailsService.createInvitation(any(BusinessAccount.class))).thenReturn(expectedMessage);

        mockMvc.perform(post("/invitation/subaccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(businessAccount)))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedMessage));
    }

    @Test
    public void testProcessInvitationAction() throws Exception {
        SubAccountActivity subAccountActivity = createTestSubAccountActivity();

        doNothing().when(invitationDetailsService).processInvitation(any(UUID.class), any(UUID.class), any(String.class));

        mockMvc.perform(put("/invitation/subaccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subAccountActivity)))
                .andExpect(status().isNoContent());
    }
}
