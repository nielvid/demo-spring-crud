package com.example.demo.dao;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("pg")
public class UserDaoImpl implements UserDao{

    private final JdbcTemplate jdbcTemplate;
    private static List<User> DB = new ArrayList<>();

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


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
        final String sql = "INSERT INTO users (id, name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, id, user.getName());
//        DB.add(new User(id, user.getName()));
//        System.out.println(user.toString() + " saved to the database");
//        return 1;
    }

    @Override
    public int delete(UUID id) {final String sql = "delete from users where id=?";
        return jdbcTemplate.update(sql, id);
//       Optional<User> user = findById(id);
//       if(user.isEmpty()) {
//           return 0;
//       }
//       DB.remove(user.get());
//       return 1;
    }

    @Override
    public int update(UUID id, User userData) {

        final String sql = "UPDATE users SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, userData.getName(), id);
//        Optional<User> user = findById(id);
//        if(user.isPresent()) {
//            int index = DB.indexOf(user.get());
//            DB.set(index, new User(id, userData.getName()));
//            return 1;
//        }
//        return 0;
    }

    @Override
    public Optional<User> findById(UUID id) {
        String sql = "SELECT id, name, email FROM users WHERE id=?";
        try {
        User user = jdbcTemplate.queryForObject (sql, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new User(personId, name);
        }, id);
        return Optional.ofNullable(user);
//        return DB.stream().filter(user -> user.getId().equals(id)).findFirst();
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    }
    }

    @Override
    public List<User> selectAllUsers() {
        String sql = "SELECT id, name FROM users";
     return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
          return new User(id, name);
        });

    }
}
