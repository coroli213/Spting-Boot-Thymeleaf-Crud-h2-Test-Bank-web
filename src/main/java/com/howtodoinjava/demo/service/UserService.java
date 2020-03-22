package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.User;
import com.howtodoinjava.demo.repository.RoleRepository;
import com.howtodoinjava.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        // Encode plaintext password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        // Set Role to ROLE_USER
        user.setRoles(Collections.singletonList(roleRepository.findByRole(USER_ROLE)));
        return userRepository.saveAndFlush(user);
    }

    public List<User> getAllUsers()
    {
        List<User> result = (List<User>) userRepository.findAll();

        if(result.size() > 0) {
            return result;
        }
        else {
            return new ArrayList<User>();
        }
    }

    public void deleteUserById(Long id) throws  RecordNotFoundException
    {
        Optional<User> User = userRepository.findById(id);

        if(User.isPresent())
        {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Terminal record exist for given id");
        }
    }

    public User AdminUser(User user) {

        if(user.getRoles().size()==1)
        {
            user.addRole(Collections.singletonList(roleRepository.findByRole("ROLE_ADMIN")).get(0));
        }
        else {
            user.clearRole();
            user.addRole(Collections.singletonList(roleRepository.findByRole("ROLE_USER")).get(0));
        }

        return userRepository.saveAndFlush(user);
    }


    public User createOrUpdateUser(User entity) {

        Optional<User> User = userRepository.findById(entity.getId());

        if (User.isPresent()) {
            User newEntity = User.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setUsername(entity.getUsername());

            newEntity = userRepository.save(newEntity);

            return newEntity;
        }
        else {
            entity = userRepository.save(entity);

            return entity;
        }
    }
}
