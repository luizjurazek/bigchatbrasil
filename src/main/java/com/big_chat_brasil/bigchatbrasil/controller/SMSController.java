package com.big_chat_brasil.bigchatbrasil.controller;

import com.big_chat_brasil.bigchatbrasil.model.SMS;
import com.big_chat_brasil.bigchatbrasil.service.SMSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/sms")
@Tag(name = "SMS", description = "Endpoints to manage SMS messages")
public class SMSController {

    @Autowired
    private SMSService smsService;

    @Operation(
        summary = "Send an SMS message",
        description = "This endpoint allows you to send an SMS message to a specified phone number. The request body must contain the phone number, message content, and whether it is sent via WhatsApp."
    )
    @PostMapping
    public SMS enviarSMS(
        @Parameter(description = "SMS object containing the phone number, message, and WhatsApp flag", required = true) 
        @RequestBody SMS sms) {
        return smsService.sendMessage(sms);
    }

    @Operation(
        summary = "Get all SMS messages",
        description = "This endpoint retrieves all SMS messages stored in the system."
    )
    @GetMapping("/all")
    public List<SMS> getAllMessages() {
        return smsService.getAllMessages();
    }

    @Operation(
        summary = "Get SMS message by ID",
        description = "This endpoint retrieves an SMS message by its unique ID."
    )
    @GetMapping("/{smsId}")
    public SMS getMessageById(
        @Parameter(description = "The ID of the SMS message to be retrieved", required = true) 
        @PathVariable Long smsId) {
        return smsService.getMessageById(smsId);
    }

    @Operation(
        summary = "Get SMS messages by client ID",
        description = "This endpoint retrieves all SMS messages associated with a specific client."
    )
    @GetMapping("/client/{clientId}")
    public List<SMS> getMessageByClient(
        @Parameter(description = "The ID of the client whose SMS messages are to be retrieved", required = true) 
        @PathVariable Long clientId) {
        return smsService.getMessageByClient(clientId);
    }

    @Operation(
        summary = "Delete an SMS message",
        description = "This endpoint allows you to delete an SMS message by its unique ID."
    )
    @DeleteMapping("/delete/{smsId}")
    public SMS deleteMessage(
        @Parameter(description = "The ID of the SMS message to be deleted", required = true) 
        @PathVariable Long smsId) {
        return smsService.deleteMessage(smsId);
    }
}