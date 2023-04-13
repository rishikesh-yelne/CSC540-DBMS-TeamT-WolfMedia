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
            // drop existing tables
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
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_SONG);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_GENRE);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_RECORD_LABEL);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PODCAST_EPISODE);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PODCAST);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_PODCAST_HOST);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_USER);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_ALBUM);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_GUEST_SPEAKER);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_SPONSOR);
            jdbcTemplate.execute(SetupQuery.DROP_TABLE_ACCOUNTS);

            // create tables if not present
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
//            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_HOSTED_BY);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_SPONSORS);
            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_RATES);
//            jdbcTemplate.execute(SetupQuery.CREATE_TABLE_CONTRIBUTED_TO);
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

            // insert data
            jdbcTemplate.execute(SetupQuery.INSERT_USER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_2);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_3);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_4);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_5);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_6);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_7);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_8);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_9);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_10);
            jdbcTemplate.execute(SetupQuery.INSERT_ARTIST_USER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ARTIST_USER_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_HOST_USER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ALBUM_1);
            jdbcTemplate.execute(SetupQuery.INSERT_ALBUM_2);
            jdbcTemplate.execute(SetupQuery.INSERT_RECORD_LABEL_1);
            jdbcTemplate.execute(SetupQuery.INSERT_RECORD_LABEL_2);
            jdbcTemplate.execute(SetupQuery.INSERT_GENRE_1);
            jdbcTemplate.execute(SetupQuery.INSERT_GENRE_2);
            jdbcTemplate.execute(SetupQuery.INSERT_GENRE_3);
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
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_EPISODE_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_EPISODE_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PODCAST_EPISODE_5);
            jdbcTemplate.execute(SetupQuery.INSERT_GUEST_SPEAKER_1);
            jdbcTemplate.execute(SetupQuery.INSERT_GUEST_SPEAKER_2);
            jdbcTemplate.execute(SetupQuery.INSERT_GUEST_SPEAKER_3);
            jdbcTemplate.execute(SetupQuery.INSERT_SPONSOR_1);
            jdbcTemplate.execute(SetupQuery.INSERT_SPONSOR_2);


            // insert payment data
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_1);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_2);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_3);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_4);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_5);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_6);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_7);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_8);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_9);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_10);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_11);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_12);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_13);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_14);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_15);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_16);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_17);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_JAN_18);


            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_1);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_2);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_3);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_4);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_5);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_6);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_7);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_8);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_9);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_10);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_11);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_12);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_13);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_14);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_15);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_16);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_17);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_18);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_19);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_FEB_20);

            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_1);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_2);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_3);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_4);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_5);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_6);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_7);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_8);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_9);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_10);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_11);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_12);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_13);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_14);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_15);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_16);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_17);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_18);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_19);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_20);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_21);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_MAR_22);

            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_1);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_2);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_3);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_4);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_5);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_6);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_7);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_8);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_9);
            jdbcTemplate.execute(SetupQuery.INSERT_TRANSACTION_APR_10);

            // insert relationship data
//            jdbcTemplate.execute(SetupQuery.INSERT_HOSTED_BY);
            jdbcTemplate.execute(SetupQuery.INSERT_RATED_1);
            jdbcTemplate.execute(SetupQuery.INSERT_RATED_2);
//            jdbcTemplate.execute(SetupQuery.INSERT_CONTRIBUTED_TO_1);
//            jdbcTemplate.execute(SetupQuery.INSERT_CONTRIBUTED_TO_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PERFORMED_BY_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PERFORMED_BY_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PERFORMED_BY_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PERFORMED_BY_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PERFORMED_BY_5);
            jdbcTemplate.execute(SetupQuery.INSERT_HAS_1);
            jdbcTemplate.execute(SetupQuery.INSERT_HAS_2);
            jdbcTemplate.execute(SetupQuery.INSERT_HAS_3);
            jdbcTemplate.execute(SetupQuery.INSERT_HAS_4);
            jdbcTemplate.execute(SetupQuery.INSERT_HAS_GENRE_1);

            // insert payment relationship data
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_JAN_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_JAN_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_JAN_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_JAN_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_JAN_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_JAN_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_JAN_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_JAN_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_JAN_5);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_JAN_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_JAN_2);

            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_FEB_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_FEB_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_FEB_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_FEB_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_FEB_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_FEB_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_FEB_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_FEB_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_FEB_5);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_FEB_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_FEB_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_FEB_3);

            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_MAR_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_MAR_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_MAR_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_RECORD_MAR_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_MAR_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_MAR_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_MAR_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_MAR_4);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_ARTIST_MAR_5);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_MAR_1);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_MAR_2);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_MAR_3);
            jdbcTemplate.execute(SetupQuery.INSERT_PAYS_PODCAST_HOST_MAR_4);

            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_1);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_2);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_3);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_4);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_5);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_6);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_7);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_8);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_9);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_10);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_11);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_12);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_13);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_14);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_15);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_16);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_17);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_18);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_19);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_20);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_21);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_22);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_23);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_24);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_25);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_26);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_27);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_28);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_29);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_30);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_31);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_32);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_33);
            jdbcTemplate.execute(SetupQuery.INSERT_USER_PAYS_34);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
