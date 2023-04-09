package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.GuestSpeaker;
import edu.ncsu.csc540.s23.backend.model.Sponsor;
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
public class SponsorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<Sponsor> getAllSponsors() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_SPONSORS, BeanPropertyRowMapper.newInstance(Sponsor.class));
    }

    public Sponsor getSponsor(Long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_SPONSOR_BY_ID);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Sponsor sponsor = new Sponsor();
            while (result.next()) {
                sponsor.setSponsorId(result.getLong(1));
                sponsor.setSponsorFirstName(result.getString(2));
                sponsor.setSponsorLastName(result.getString(3));
                sponsor.setOrganization(result.getString(4));
                sponsor.setSponsorEmail(result.getString(5));
                sponsor.setSponsorCity(result.getString(6));
            }
            result.close();
            statement.close();

            return sponsor;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Long createNewSponsor(Sponsor sponsor){
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "sponsor_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_SPONSOR, generatedColumns);
            statement.setString(1, sponsor.getSponsorFirstName());
            statement.setString(2, sponsor.getSponsorLastName());
            statement.setString(3, sponsor.getOrganization());
            statement.setString(4, sponsor.getSponsorEmail());
            statement.setString(5, sponsor.getSponsorCity());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) throw new SQLException("sponsor creation failed");

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
