package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.model.Artist;
import edu.ncsu.csc540.s23.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String query = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class));
    }

    public boolean createNewUser(User user) {
        String query = "INSERT INTO User (first_name, last_name, email_id, phone_num, reg_date) VALUES(?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPhoneNum(), user.getRegDate());
        return rowsAffected > 0;
    }
}
