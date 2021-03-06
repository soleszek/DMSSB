package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.message.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.message.MessageReceived;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.notification.NotificationMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Logger;

@Service
public class MessageReceivedService {
    private static final Logger LOGGER = Logger.getLogger(MessageReceivedService.class.getName());

    private UserService userService;
    private MessageRepository messageRepository;
    private NameFactory nameFactory;
    private NotificationMessageService notificationMessageService;

    @Autowired
    public MessageReceivedService(UserService userService,
                                  MessageRepository messageRepository,
                                  NameFactory nameFactory,
                                  NotificationMessageService notificationMessageService) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.nameFactory = nameFactory;
        this.notificationMessageService = notificationMessageService;
    }

    public Message SaveOrUpdate(Message message, Long userId, String username, String content){

        Message messageReceived = new MessageReceived();
        messageReceived.setTitle(message.getTitle());
        messageReceived.setContent(content);
        User sender = userService.findById(userId);
        messageReceived.setSender(sender);

        User receiver = userService.findByUsername(username);
        messageReceived.setReceiver(receiver);
        messageReceived.setSendingDate(LocalDate.now());
        messageReceived.setIsRead(false);

        messageRepository.save(messageReceived);

        Long messageReceivedId = messageReceived.getMessage_id();
        String name = nameFactory.createName(messageReceivedId, ObjectTypes.MESSAGE.getObjectType());
        messageReceived.setName(name);

        messageRepository.save(messageReceived);

        notificationMessageService.saveOrUpdate(messageReceived, messageReceived.getReceiver());

        return messageReceived;
    }
}
