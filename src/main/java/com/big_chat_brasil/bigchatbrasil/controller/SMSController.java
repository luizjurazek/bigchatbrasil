package com.big_chat_brasil.bigchatbrasil.controller;

import com.big_chat_brasil.bigchatbrasil.model.SMS;
import com.big_chat_brasil.bigchatbrasil.service.SMSService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/sms")
public class SMSController {
    @Autowired
    private SMSService smsService;

    @PostMapping
    public SMS enviarSMS(@RequestBody SMS sms) {
        return smsService.sendMessage(sms);
    }

    @GetMapping("/all")
    public List<SMS> getAllMessages(){
        return smsService.getAllMessages();
    }

    @GetMapping("/{smsId}")
    public SMS getMessageById(@PathVariable Long smsId) {
        return smsService.getMessageById(smsId);
    }

    @DeleteMapping("/{smsId}")
    public SMS deleteMessage(@PathVariable Long smsId) {
        return smsService.deleteMessage(smsId);
    }

    @GetMapping("/client/{clientId}")
    public List<SMS> getMessageByClient(@PathVariable Long clientId) {
        return smsService.getMessageByClient(clientId);
    }
}

