package com.suphse.ecommerce.customer.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suphse.ecommerce.customer.entity.AccountDetails;
import com.suphse.ecommerce.customer.model.AccountUpgrade;
import com.suphse.ecommerce.customer.service.AccountDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Tag(name = "Account Details")
@RestController
@RequestMapping("/account")
public class AccountDetailsController {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private AccountDetailsService accountDetailsService;
    
    @Operation(method = "Create Account", summary = "Create Customer Account")
    @PostMapping
    public ResponseEntity<AccountDetails> saveAccountDetails(@Valid @RequestBody AccountDetails accountDetails) {
        AccountDetails savedAccountDetails = accountDetailsService.saveAccountDetails(accountDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountDetails);
    }
    
    @Operation(method = "Update Account", summary = "Update Customer Account details")
    @PutMapping
    public ResponseEntity<AccountDetails> updateAccountDetails(@Valid @RequestBody AccountDetails accountDetails) {
        AccountDetails updatedAccountDetails = accountDetailsService.updateAccountDetails(accountDetails);
        return ResponseEntity.ok(updatedAccountDetails);
    }
    
    @Operation(method = "Fetch Account by Account Id", summary = "Fetch Customer Account details using Account Id")
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDetails> fetchAccountDetails(@PathVariable("accountId") UUID accountId) {
        AccountDetails accountDetails = accountDetailsService.fetchAccountDetails(accountId);
        return ResponseEntity.ok(accountDetails);
    }
    
    @Operation(method = "Upgrade Account", summary = "Upgrade the Person Account to Business Acount")
    @PutMapping("bisaccount/{accountId}")
    public ResponseEntity<AccountDetails> upgradeAccount(@PathVariable("accountId") UUID accountId) {
        AccountDetails upgradedAccountDetails = accountDetailsService.upgradeAccount(accountId);
        return ResponseEntity.ok(upgradedAccountDetails);
    }
}
