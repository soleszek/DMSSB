package com.oleszeksylwester.dmssb.DMSSB.serviceimpl;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Role;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.RoleRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private NameFactory nameFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, NameFactory nameFactory) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.nameFactory = nameFactory;
    }

    @Transactional
    public void saveOrUpdate(User user, String role){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(role);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnabled("1");

        userRepository.save(user);

        Long userId = user.getUser_id();
        String name = nameFactory.createName(userId, ObjectTypes.USER.getObjectType());
        user.setName(name);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no user with this id"));
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }
}
