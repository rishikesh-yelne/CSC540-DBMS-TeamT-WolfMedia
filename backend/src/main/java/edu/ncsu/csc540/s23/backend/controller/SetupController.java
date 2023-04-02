package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.constants.SetupQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/init")
    public void initializeDatabase() {
        try {
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_HOSTED_BY);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_SPONSORS);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_RATES);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_CONTRIBUTED_TO);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PERFORMED_BY);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_LISTENS_TO);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_SUBSCRIBES_TO);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_LISTENS_TO_P);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_HAS);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_HAS_GENRE);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_EPISODE_HAS);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_USER_PAYS);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PAYS_RECORD);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PAYS_PH);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PAYS_ARTIST);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_ARTIST);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PODCAST_HOST);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_SONG);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_GENRE);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_RECORD_LABEL);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_USER);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_ALBUM);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PODCAST_EPISODE);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PODCAST);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_GUEST_SPEAKER);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_SPONSOR);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_ACCOUNTS);

            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_USER);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_GENRE);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_RECORD_LABEL);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_ARTIST);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PODCAST_HOST);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_ALBUM);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_SONG);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PODCAST);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PODCAST_EPISODE);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_GUEST_SPEAKER);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_SPONSOR);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_ACCOUNTS);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_HOSTED_BY);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_SPONSORS);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_RATES);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_CONTRIBUTED_TO);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PERFORMED_BY);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_LISTENS_TO);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_SUBSCRIBES_TO);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_LISTENS_TO_P);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_HAS);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_HAS_GENRE);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_EPISODE_HAS);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_USER_PAYS);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PAYS_RECORD);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PAYS_PH);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_PAYS_ARTIST);

            jdbcTemplate.execute(SetupQuery.INSERT_USER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_2);
            jdbcTemplate.execute(SetupQuery.INSERT_ARTIST_USER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ARTIST_USER_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_HOST_USER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ALBUM_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ALBUM_2);
            jdbcTemplate.execute(SetupQuery.INSERT_RECORD_LABEL_1);
            jdbcTemplate.execute(SetupQuery.INSERT_RECORD_LABEL_2);
            jdbcTemplate.execute(SetupQuery.INSERT_GENRE_1);
            jdbcTemplate.execute(SetupQuery.INSERT_GENRE_2);
            jdbcTemplate.execute(SetupQuery.INSERT_ARTIST_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ARTIST_2);
            jdbcTemplate.execute(SetupQuery.INSERT_SONG_1);
            jdbcTemplate.execute(SetupQuery.INSERT_SONG_2);
            jdbcTemplate.execute(SetupQuery.INSERT_SONG_3);
            jdbcTemplate.execute(SetupQuery.INSERT_SONG_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_HOST_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_EPISODE_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_EPISODE_2);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
