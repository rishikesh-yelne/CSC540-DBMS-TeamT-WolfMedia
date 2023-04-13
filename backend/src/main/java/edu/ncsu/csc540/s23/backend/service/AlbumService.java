package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Album;
import edu.ncsu.csc540.s23.backend.model.Genre;
import edu.ncsu.csc540.s23.backend.model.Song;
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
public class AlbumService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<Album> getAllAlbums() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_ALBUMS, BeanPropertyRowMapper.newInstance(Album.class));
    }

    public Album getAlbum(Long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(OperationQuery.GET_ALBUM_BY_ID);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Album album = new Album();
            while (result.next()) {
                album.setAlbumId(result.getLong(1));
                album.setAlbumName(result.getString(2));
                album.setReleaseYear(result.getInt(3));
                album.setEdition(result.getString(4));
            }
            result.close();
            statement.close();

            return album;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Long createNewAlbum(Album album){
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "album_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_ALBUM, generatedColumns);
            statement.setString(1, album.getAlbumName());
            statement.setInt(2, album.getReleaseYear());
            statement.setString(3, album.getEdition());

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

    public boolean assignArtistToAlbum(Long albumId, Long artistId){
        return jdbcTemplate.update(OperationQuery.ASSIGN_ARTIST_TO_ALBUM, albumId, artistId)>0;
    }
}
