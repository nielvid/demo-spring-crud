package com.example.demo.dao;

import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    int save(UUID id, User user);

    default int save(User user) {
        UUID id = UUID.randomUUID();
        return save(id, user);
    }
 int delete(UUID id);

 int update(UUID id, User user);

 Optional<User> findById(UUID id);

 List<User> selectAllUsers();
}
