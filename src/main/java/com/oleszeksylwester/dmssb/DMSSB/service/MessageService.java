package com.oleszeksylwester.dmssb.DMSSB.service;

import java.util.List;
import java.util.logging.Logger;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class.getName());

    @Transactional
    public Message SaveOrUpdate(Message message){

        return null;
    }

    @Transactional(readOnly = true)
    public Document findById(Long id){
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
