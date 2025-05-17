package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(int messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public Integer deleteMessage(int messageId) {
        messageRepository.count();
        if(messageRepository.findById(messageId).orElse(null) != null){
            messageRepository.deleteById(messageId);
            if(messageRepository.findById(messageId).orElse(null) == null){
                return 1;
            }
        }
        return 0;
    }

    public Integer patchMessage(int messageId, String messageText) {
        if(messageText == null || messageText.length() == 0 || messageText.length() >= 256){
            return 0;
        }
        Message m = messageRepository.findById(messageId).orElse(null);
        if(m == null){
            return 0;
        }
        m.setMessageText(messageText);
        messageRepository.save(m);
        return 1;
    }

    public List<Message> getMessagesByAccount(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
