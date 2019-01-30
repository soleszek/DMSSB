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
    private NameFactory nameFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, NameFactory nameFactory) {
        this.userRepository = userRepository;
        this.nameFactory = nameFactory;
    }

    /*@Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }*/


    @Transactional
    public void saveOrUpdate(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("VIEWER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        Long userId = user.getUser_id();
        nameFactory.createName(userId, ObjectTypes.USER.getObjectType());

        userRepository.save(user);
    }

   /* @Transactional
    public void saveOrUpdateContributor(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("CONTRIBUTOR");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        Long userId = user.getUser_id();
        nameFactory.createName(userId, ObjectTypes.USER.getObjectType());

        userRepository.save(user);
    }

    @Transactional
    public void saveOrUpdateManager(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("MANAGER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        Long userId = user.getUser_id();
        nameFactory.createName(userId, ObjectTypes.USER.getObjectType());

        userRepository.save(user);
    }

    @Transactional
    public void saveOrUpdateAdmin(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        Long userId = user.getUser_id();
        nameFactory.createName(userId, ObjectTypes.USER.getObjectType());

        userRepository.save(user);
    }*/

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
