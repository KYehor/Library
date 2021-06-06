package com.library.services;

import com.library.entities.User;
import com.library.entities.UserRole;
import com.library.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final IUserRepository IUserRepository;

    @Autowired
    public UserService(IUserRepository IUserRepository, PasswordEncoder passwordEncoder){
        this.IUserRepository = IUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUserName(String username){
        return IUserRepository.findByUsername(username);
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.READER));
        IUserRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return IUserRepository.findByUsername(username);
    }
}
