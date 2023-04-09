package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Genre;
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
public class GenreService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<Genre> getAllGenres() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_GENRES, BeanPropertyRowMapper.newInstance(Genre.class));
    }

    public Long createNewGenre(Genre genre){
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "genre_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_GENRE, generatedColumns);
            statement.setString(1, genre.getName());
            statement.setString(2, genre.getGenreType());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) throw new SQLException("Genre creation failed");

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getLong(1);
            }
            return -1L;
        }catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Genre getGenre(Long id) {
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "genre_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_GENRE_BY_ID, generatedColumns);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Genre genre = new Genre();
            while (result.next()) {
                genre.setGenreId(result.getLong(1));
                genre.setName(result.getString(2));
                genre.setGenreType(result.getString(3));
            }
            result.close();
            statement.close();

            return genre;
        }catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
