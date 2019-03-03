package com.oleszeksylwester.dmssb.DMSSB.repository;

import com.oleszeksylwester.dmssb.DMSSB.model.MessageReceived;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageReceivedRepository extends JpaRepository<MessageReceived, Long> {
    List<MessageReceived> findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(User receiver);
    List<MessageReceived> findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(User receiver);
    List<MessageReceived> findAllBySenderAndIsDeletedIsFalse(User sender);
    List<MessageReceived> findAllBySenderOrReceiverAndIsDeletedIsTrue(User sender, User receiver);
    Long countMessagesByReceiverAndIsReadIsFalse(User receiver);
}
