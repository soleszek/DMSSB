package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.message.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.message.MessageSent;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.logging.Logger;

@Service
public class MessageSentService {
    private static final Logger LOGGER = Logger.getLogger(MessageReceivedService.class.getName());

    private UserService userService;
    private MessageRepository messageRepository;
    private NameFactory nameFactory;

    @Autowired
    public MessageSentService(UserService userService, MessageRepository messageRepository, NameFactory nameFactory) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.nameFactory = nameFactory;
    }

    public Message SaveOrUpdate(Message message, Long userId, String username, String content){

        Message messageSent = new MessageSent();
        messageSent.setTitle(message.getTitle());
        messageSent.setContent(content);
        User sender = userService.findById(userId);
        messageSent.setSender(sender);

        User receiver = userService.findByUsername(username);
        messageSent.setReceiver(receiver);
        messageSent.setSendingDate(LocalDate.now());
        messageSent.setIsRead(false);

        messageRepository.save(messageSent);

        Long messageReceivedId = messageSent.getMessage_id();
        String name = nameFactory.createName(messageReceivedId, ObjectTypes.MESSAGE.getObjectType());
        messageSent.setName(name);

        messageRepository.save(messageSent);

        return messageSent;
    }
}
