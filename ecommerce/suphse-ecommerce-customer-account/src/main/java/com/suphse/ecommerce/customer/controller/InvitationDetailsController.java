package com.suphse.ecommerce.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suphse.ecommerce.customer.entity.InvitationDetails;
import com.suphse.ecommerce.customer.model.BusinessAccount;
import com.suphse.ecommerce.customer.model.SubAccountActivity;
import com.suphse.ecommerce.customer.service.InvitationDetailsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.UUID;

@Tag(name = "Invitation Details")
@RestController
@RequestMapping("/invitation/subaccount")
public class InvitationDetailsController {

	@Autowired
    private InvitationDetailsService invitationDetailsService;

	@Operation(method = "Create Invitation", summary = "Invite other Account holder to join")
    @PostMapping
    public ResponseEntity<String> createInvitation(@Valid @RequestBody BusinessAccount businessaccountreq) {
    	String message = invitationDetailsService.createInvitation(businessaccountreq);
       // String message = "Invitation send successfully !!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    
	@Operation(method = "Invitation Activity", summary = "Action against the invitation for Sub-Account can be ACCEPT/REJECT/UNLINK")
    @PutMapping
    public ResponseEntity<Void> processInvitationAction(@Valid @RequestBody SubAccountActivity request) {
        invitationDetailsService.processInvitation(request.getBisAccountId(), request.getAccountId(), request.getAction());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

