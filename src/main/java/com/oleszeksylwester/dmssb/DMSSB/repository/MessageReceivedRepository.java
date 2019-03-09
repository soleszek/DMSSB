package com.oleszeksylwester.dmssb.DMSSB.repository;

import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.MessageReceived;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageReceivedRepository extends JpaRepository<MessageReceived, Long> {
    List<Message> findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(User receiver);
    List<Message> findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(User receiver);
    List<Message> findAllBySenderAndIsDeletedIsFalse(User sender);
    List<Message> findAllBySenderOrReceiverAndIsDeletedIsTrue(User sender, User receiver);
    Long countMessagesByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(User receiver);
}
