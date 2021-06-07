package com.library.services;

import com.library.entities.User;
import com.library.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository, PasswordEncoder passwordEncoder){
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUserName(String username){
        return iUserRepository.findByUsername(username);
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        iUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return iUserRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    public void deleteById(Long id) {
        iUserRepository.deleteById(id);
    }
}
