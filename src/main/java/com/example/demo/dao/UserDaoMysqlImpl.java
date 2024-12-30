package com.example.demo.dao;

import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysql")
public class UserDaoMysqlImpl implements UserDao{

    private static List<User> DB = new ArrayList<>();

    //    <User>public List<User> selectAllUsers() {
//        return DB;
//    }
//    <User>public Optional<User> selectUserById(UUID id) {
//        return DB.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst();
//    }
    @Override
    public int save(UUID id, User user) {
        DB.add(new User(id, user.getName()));
        System.out.println(user.toString() + " saved to the database");
        return 1;
    }

    @Override
    public int delete(UUID id) {
        Optional<User> user = findById(id);
        if(user.isEmpty()) {
            return 0;
        }
        DB.remove(user.get());
        return 1;
    }

    @Override
    public int update(UUID id, User userData) {
        Optional<User> user = findById(id);
        if(user.isPresent()) {
            int index = DB.indexOf(user.get());
            DB.set(index, new User(id, userData.getName()));
            return 1;
        }
        return 0;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return DB.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> selectAllUsers() {
        return List.of(new User(UUID.randomUUID(), "FROM MYSQL DB"));
    }
}

