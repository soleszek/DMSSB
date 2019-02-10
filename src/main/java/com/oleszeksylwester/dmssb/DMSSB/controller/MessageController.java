package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.MessageService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    MessageRepository messageRepository;
    MessageService messageService;
    UserService userService;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/messages")
    private ModelAndView messages(){
        ModelAndView mov = new ModelAndView();

        List<Message> messages = messageRepository.findAll();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);

        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages");
        return mov;
    }

    @PostMapping("new/message/{userId}")
    private ModelAndView sendMessage(@ModelAttribute("message") Message message, @RequestParam("content") String content, @RequestParam("username") String username, @PathVariable("userId") Long userId){
        ModelAndView mov = new ModelAndView();

        Message sentMessage = messageService.SaveOrUpdate(message, userId, username);

        mov.addObject("sentMessage", sentMessage);
        mov.setViewName("message");
        return mov;
    }

    @GetMapping("/message/{messageId}")
    private ModelAndView message(@RequestParam("messageId") Long messageId){
        ModelAndView mov = new ModelAndView();

        Message sentMessage = messageService.findById(messageId);

        mov.addObject("sentMessage", sentMessage);
        mov.setViewName("message");
        return mov;
    }
}
