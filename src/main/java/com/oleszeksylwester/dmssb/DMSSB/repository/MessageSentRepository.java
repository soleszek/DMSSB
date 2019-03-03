package com.oleszeksylwester.dmssb.DMSSB.repository;

import com.oleszeksylwester.dmssb.DMSSB.model.MessageSent;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageSentRepository extends JpaRepository<MessageSent, Long> {
    List<MessageSent> findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(User receiver);
    List<MessageSent> findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(User receiver);
    List<MessageSent> findAllBySenderAndIsDeletedIsFalse(User sender);
    List<MessageSent> findAllBySenderOrReceiverAndIsDeletedIsTrue(User sender, User receiver);
    Long countMessagesByReceiverAndIsReadIsFalse(User receiver);
}
