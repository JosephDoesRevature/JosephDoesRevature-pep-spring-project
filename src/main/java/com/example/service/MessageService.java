package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired AccountService accountService;
    public Message registerMessage(Message message) {
        if(message != null && accountService.isReal(message.getPostedBy()) && (message.getMessageText().length() < 256) && (message.getMessageText().length()>0)){
            return messageRepository.save(message);
        } 
        return null;
    }

    public ResponseEntity getMessages() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessages'");
    }

    public Object getMessage(int messageID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }

    public Object deleteMessage(int messageID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessage'");
    }

    public int patchMessage(int messageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchMessage'");
    }

    public Object getMessagesByAccount(int accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessagesByAccount'");
    }
}
