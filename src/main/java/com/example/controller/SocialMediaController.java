package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    @Autowired 
    AccountService accountService;
    @Autowired
    MessageService messageService;
    //Usecase1
    @PostMapping(value = "/register")
    public ResponseEntity postRegister(@RequestBody Account account){
        if(!accountService.verifyAccount(account)){
            return ResponseEntity.status(400).build();
        }
        if(!accountService.verifyName(account)){
            return ResponseEntity.status(409).build();
        }
        Account nac = accountService.registerAccount(account);
        if(nac != null){
            return ResponseEntity.status(200).body(nac);
        } else {
            return ResponseEntity.status(400).build();
        }
    }
    //Usecase2
    @PostMapping(value = "/login")
    public ResponseEntity postLogin(@RequestBody Account account){
        Account nac = accountService.login(account); 
        if(nac != null){
            return ResponseEntity.status(200).body(nac);
        }
        return ResponseEntity.status(401).build();
    }
    //Usecase3 
    @PostMapping(value = "/messages")
    public ResponseEntity postMessage(@RequestBody Message message){
        Message nm = messageService.registerMessage(message);
        if(nm != null){
            return ResponseEntity.status(200).body(nm);
        }   
        return ResponseEntity.status(400).build();
    }
    //Usecase4
    @GetMapping(value = "/messages")
    public ResponseEntity getMessages(){
        return ResponseEntity.status(200).body(messageService.getMessages());
    }
    //Usecase5
    @GetMapping(value = "/messages/{messageId}")
    public ResponseEntity getMessage(@PathVariable int messageId){
        Message message = messageService.getMessage(messageId);
        if(message == null){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(200).body(message);
    }
    //Usecase6
    @DeleteMapping(value = "/messages/{messageId}")
    public ResponseEntity deleteMessage(@PathVariable int messageId){
        int updated = messageService.deleteMessage(messageId);
        if(updated >0){
            return ResponseEntity.status(200).body(updated);
        }
        return ResponseEntity.status(200).build();
    }
    //Usecase7
    @PatchMapping(value = "/messages/{messageId}")
    public ResponseEntity patchMessage(@PathVariable int messageId, @RequestBody Message message){
        int updated = messageService.patchMessage(messageId, message.getMessageText()); 
        if(updated>0){
            return ResponseEntity.status(200).body(updated);
        }
        return ResponseEntity.status(400).build();
    }
    //Usecase8
    @GetMapping(value ="/accounts/{accountId}/messages")
    public ResponseEntity getMessagesByAccount(@PathVariable int accountId){
        return ResponseEntity.status(200).body(messageService.getMessagesByAccount(accountId));
    }
}
