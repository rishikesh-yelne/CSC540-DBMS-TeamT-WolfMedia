package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Genre;
import edu.ncsu.csc540.s23.backend.model.GuestSpeaker;
import edu.ncsu.csc540.s23.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class GuestSpeakerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<GuestSpeaker> getAllGuestSpeakers() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_GUESTSPEAKERS, BeanPropertyRowMapper.newInstance(GuestSpeaker.class));
    }

    public GuestSpeaker getGuestSpeaker(Long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_GUEST_SPEAKER_BY_ID);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            GuestSpeaker guestSpeaker = new GuestSpeaker();
            while (result.next()) {
                guestSpeaker.setGuestSpeakerId(result.getLong(1));
                guestSpeaker.setGuestSpeakerFirstName(result.getString(2));
                guestSpeaker.setGuestSpeakerLastName(result.getString(3));
                guestSpeaker.setGuestSpeakerPhoneNum(result.getString(4));
                guestSpeaker.setGuestSpeakerEmail(result.getString(5));
                guestSpeaker.setGuestSpeakerCity(result.getString(6));
            }
            result.close();
            statement.close();

            return guestSpeaker;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Long createNewGuestSpeaker(GuestSpeaker guest){
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "gspeaker_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_GUESTSPEAKER, generatedColumns);
            statement.setString(1, guest.getGuestSpeakerFirstName());
            statement.setString(2, guest.getGuestSpeakerLastName());
            statement.setString(3, guest.getGuestSpeakerPhoneNum());
            statement.setString(4, guest.getGuestSpeakerEmail());
            statement.setString(5, guest.getGuestSpeakerCity());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) throw new SQLException("Guest speaker creation failed");

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            }
            return -1L;
        }catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
