package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.*;
import com.oleszeksylwester.dmssb.DMSSB.model.message.Message;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageReceivedRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageSentRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.MessageReceivedService;
import com.oleszeksylwester.dmssb.DMSSB.service.MessageSentService;
import com.oleszeksylwester.dmssb.DMSSB.service.MessageService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import com.oleszeksylwester.dmssb.DMSSB.utils.UserJsonSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    private MessageRepository messageRepository;
    private MessageReceivedRepository messageReceivedRepository;
    private MessageSentRepository messageSentRepository;
    private MessageReceivedService messageReceivedService;
    private MessageSentService messageSentService;
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageReceivedRepository messageReceivedRepository, MessageSentRepository messageSentRepository, MessageService messageService, MessageReceivedService messageReceivedService, MessageSentService messageSentService, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageReceivedRepository = messageReceivedRepository;
        this.messageSentRepository = messageSentRepository;
        this.messageReceivedService = messageReceivedService;
        this.messageSentService = messageSentService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/messages/unread")
    private ModelAndView unreadMessages() {
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageReceivedRepository.findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-unread");
        return mov;
    }

    @GetMapping("/messages/received")
    private ModelAndView allMessages() {
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageReceivedRepository.findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(user);
        Long newMessagesCount = messageReceivedRepository.countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-received");
        return mov;
    }

    @GetMapping("/messages/sent")
    private ModelAndView sentMessages() {
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageSentRepository.findAllBySenderAndIsDeletedIsFalse(user);
        Long newMessagesCount = messageReceivedRepository.countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-sent");
        return mov;
    }

    @GetMapping("/messages/deleted")
    private ModelAndView deletedMessages() {
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);

        List<Message> messagesReceived = messageReceivedRepository.findAllBySenderOrReceiverAndIsDeletedIsTrue(user, user);
        List<Message> messagesSent = messageSentRepository.findAllBySenderOrReceiverAndIsDeletedIsTrue(user, user);
        List<Message> messages = new ArrayList<>();
        messages.addAll(messagesReceived);
        messages.addAll(messagesSent);

        Long newMessagesCount = messageReceivedRepository.countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("messages", messages);
        mov.setViewName("/messages-deleted");
        return mov;
    }

    @GetMapping("/message/{message_id}/{view-name}")
    private ModelAndView messageReceived(@PathVariable("message_id") Long message_id, @PathVariable("view-name") String viewName) {
        ModelAndView mov = new ModelAndView();

        String originView = viewName;

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);

        Message oneMessage = messageRepository.getOne(message_id);

        if(!oneMessage.getIsRead()){
            messageService.markAsRead(message_id);
        }

        Long newMessagesCount = messageReceivedRepository.countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("user", user);
        mov.addObject("originView", originView);
        mov.addObject("oneMessage", oneMessage);
        mov.addObject("message", new Message());
        mov.addObject("newMessagesCount", newMessagesCount);
        mov.setViewName("message");
        return mov;
    }

    @PostMapping("/new/{originView}/{userId}")
    private ModelAndView sendMessage(@ModelAttribute("message") Message message, @RequestParam("content") String content, @RequestParam("username") String username, @PathVariable("originView") String originView,  @PathVariable("userId") Long userId) {
        ModelAndView mov = new ModelAndView();

        messageReceivedService.SaveOrUpdate(message, userId, username, content);
        Message oneMessage = messageSentService.SaveOrUpdate(message, userId, username, content);

        String currentUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails) principal).getUsername();
        } else {
            currentUser = principal.toString();
        }
        User user = userService.findByUsername(currentUser);
        Long newMessagesCount = messageReceivedRepository.countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("user", user);
        mov.addObject("originView", originView);
        mov.addObject("oneMessage", oneMessage);
        mov.addObject("message", new Message());
        mov.addObject("newMessagesCount", newMessagesCount);
        mov.setViewName("message");
        return mov;
    }

    @PostMapping("/trash/messages")
    private ModelAndView deleteUnreadReceivedMessages(@RequestParam(required = false, name = "messagesChecked") List<Long> messagesChecked, @RequestParam String view) {
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        Long newMessagesCount = messageReceivedRepository.countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);
        List<Message> messages = new ArrayList<>();

        switch (view) {
            case "/messages-unread":
                messageService.moveManyToTrash(messagesChecked);
                messages = messageRepository.findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);
                break;
            case "/messages-received":
                messageService.moveManyToTrash(messagesChecked);
                messages = messageRepository.findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(user);
                break;
            case "/messages-sent":
                messageService.moveManyToTrash(messagesChecked);
                messages = messageRepository.findAllBySenderAndIsDeletedIsFalse(user);
                break;
            case "/messages-deleted":
                messageService.deleteManyMessages(messagesChecked);
                messages = messageRepository.findAllBySenderOrReceiverAndIsDeletedIsTrue(user, user);
                break;
        }

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName(view);
        return mov;
    }

    @GetMapping("/trash/{originView}/{message_id}")
    private ModelAndView deleteMessage(@PathVariable("originView") String originView, @PathVariable("message_id") Long message_id) {
        ModelAndView mov = new ModelAndView();

        Message unwantedMessage = messageRepository.getOne(message_id);

        if(unwantedMessage.getIsDeleted()){
            messageRepository.delete(unwantedMessage);
        } else {
            messageService.moveToTrash(message_id);
        }

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(user);

        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("messages-" + originView);
        return mov;
    }

    @GetMapping("/receiver")
    public @ResponseBody
    List<UserJsonSearch> getUser(@RequestParam String tag) {

        return userService.searchDynamically(tag);
    }
}
