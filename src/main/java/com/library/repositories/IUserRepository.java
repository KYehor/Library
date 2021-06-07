package com.library.repositories;

import com.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
}
