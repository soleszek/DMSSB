package com.oleszeksylwester.dmssb.DMSSB.repository;

import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findAllByUsernameContains(String tag);
}
