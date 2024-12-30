package com.example.demo.services;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.UserValidationException;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
private final UserDao userDao;

@Autowired
    public UserService(@Qualifier("pg") UserDao userDao) {
        this.userDao = userDao;
    }
    public int createUser(User user) throws UserValidationException {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new UserValidationException("User name cannot be empty");
        }
      return userDao.save(user);

    }
    public User getUserById(User user) {
        return user;
    }
    public List<User> getUsers() {
        return userDao.selectAllUsers();
    }
    public int updateUser(UUID id, User user) {
        return userDao.update(id, user);
    }
    public Optional<User> findUserById(UUID id) {
        return userDao.findById(id);
    }
    public int deleteUserById(UUID id) {
        return userDao.delete(id);
    }
}
