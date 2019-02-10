package com.oleszeksylwester.dmssb.DMSSB.service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class.getName());

    UserService userService;
    MessageRepository messageRepository;
    NameFactory nameFactory;

    @Autowired
    public MessageService(UserService userService, MessageRepository messageRepository, NameFactory nameFactory) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.nameFactory = nameFactory;
    }

    @Transactional
    public Message SaveOrUpdate(Message message, Long userId, String username){

        User sender = userService.findById(userId);
        message.setSender(sender);

        User receiver = userService.findByUsername(username);
        message.setReceiver(receiver);
        message.setSendingDate(LocalDate.now());
        message.setIsRead(false);

        messageRepository.save(message);

        Long messageId = message.getMessage_id();
        String name = nameFactory.createName(messageId, ObjectTypes.MESSAGE.getObjectType());
        message.setName(name);

        messageRepository.save(message);

        return message;
    }

    /*@Transactional
    public Message openMessage(Long messageId){
        *//*Message message = messageRepository.findById(messageId);*//*

    }*/

    @Transactional(readOnly = true)
    public Message findById(Long id){
        return null;
    }

    @Transactional(readOnly = true)
    public List<Message> findAll(){
        return null;
    }

    @Transactional
    public void deleteById(Long id){

    }
}
