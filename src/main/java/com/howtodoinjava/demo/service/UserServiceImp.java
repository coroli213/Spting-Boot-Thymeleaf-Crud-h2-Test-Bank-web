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
public class UserServiceImp implements UserService {

    public UserRepository userRepository;
    public RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // Encode plaintext password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        // Set Role to ROLE_USER
        user.setRoles(Collections.singletonList(roleRepository.findByRole("ROLE_ADMIN")));
        return userRepository.saveAndFlush(user);
    }

    public List<User> getAllUsers()
    {
        List<User> result = (List<User>) userRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<User>();
        }
    }

    public User getUserById(Long id) throws RecordNotFoundException
    {
        User User = userRepository.getOne(id);

        if(User!=null) {
            return User;
        } else {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }

    public User createOrUpdateUser(User entity)
    {
        entity = userRepository.saveAndFlush(entity);

        return entity;

    }

    public User AdminUser(User entity)
    {
        String i =entity.getRoles().iterator().next().getRole();

        if (i.equals("ROLE_USER"))
        {
            //roleRepository.
            entity.setRoles(Collections.singletonList(roleRepository.findByRole("ROLE_ADMIN")));

        }
        else
            {
                entity.setRoles(Collections.singletonList(roleRepository.findByRole(USER_ROLE)));
        }

        return userRepository.saveAndFlush(entity);
    }

    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<User> User1 = userRepository.findById(id);

        if(User1.isPresent())
        {
            userRepository.deleteById(id);
        }
        else
            {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }




}
