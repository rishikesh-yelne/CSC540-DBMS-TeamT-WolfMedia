package edu.ncsu.csc540.s23.backend.constants;

public class OperationQuery {
    public static final String GET_ALL_USERS = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User";
    public static final String GET_ALL_ARTISTS = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, rlabel_id as recordLabelId, primary_genre_id, status, type, artist_country FROM User u JOIN Artist a ON u.user_id=a.user_id;";
    public static final String GET_ALL_PODCAST_HOSTS = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, city FROM User u JOIN Podcast_Host p ON u.user_id=p.user_id;";
    public static final String GET_ALL_GENRES = "SELECT genre_id, name, gtype as genreType FROM Genre";
    public static final String GET_ALL_RECORD_LABELS = "SELECT rlabel_id as recordLabelId, rlabel_name as recordLabelName FROM Record_Label";
    public static final String INSERT_USER = "INSERT INTO User (first_name, last_name, email_id, phone_num, reg_date) VALUES(?, ?, ?, ?, ?)";
    public static final String INSERT_ARTIST = "INSERT INTO Artist (user_id, rlabel_id, primary_genre_id, status, type, artist_country) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PODCAST_HOST = "INSERT INTO Podcast_Host (user_id, city) VALUES(?, ?)";
    public static final String GET_USER_BY_ID = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User WHERE user_id=?;";
    public static final String GET_ARTIST_BY_ID = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, rlabel_id as recordLabelId, primary_genre_id, status, type, artist_country FROM User u JOIN Artist a ON u.user_id=a.user_id WHERE u.user_id=?;";
    public static final String GET_PODCAST_HOST_BY_ID = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, city FROM User u JOIN Podcast_Host p ON u.user_id=p.user_id WHERE u.user_id=?;";
    public static final String INSERT_GENRE = "INSERT INTO Genre (name, gtype) VALUES (?, ?)";
    public static final String INSERT_RECORD_LABEL = "INSERT INTO Record_Label (rlabel_name) VALUES (?)";
    public static final String GET_PAYMENT_TO_RL_BY_ID =
            "SELECT sum(acc.amount)*0.3 FROM Accounts acc JOIN pays_record pr ON acc.transac_id=pr.transac_id " +
                    "WHERE pr.rlabel_id = ? AND month(acc.payment_date) = ? AND year(acc.payment_date) = ?;";
    public static final String GET_PAYMENT_TO_ARTIST_BY_ID =
            "SELECT sum(acc.amount) FROM Accounts acc JOIN pays_artist pa ON acc.transac_id=pa.transac_id " +
                    "WHERE pa.user_id = ? AND month(acc.payment_date) = ? AND year(acc.payment_date) = ?;";
    public static final String GET_PAYMENT_TO_PH_BY_ID =
            "SELECT sum(acc.amount) FROM Accounts acc JOIN pays_ph pp ON acc.transac_id=pp.transac_id " +
                    "WHERE pp.user_id = ? AND month(acc.payment_date) = ? AND year(acc.payment_date) = ?;";
    public static final String GET_PAYMENTS_TO_RECORD_LABELS =
            "SELECT rl.rlabel_name as RecordLabel, sum(acc.amount)*0.3 as Amount, acc.payment_date as PaymentDate " +
                    "FROM Accounts acc JOIN pays_record pr ON acc.transac_id=pr.transac_id " +
                    "JOIN Record_Label rl ON pr.rlabel_id=rl.rlabel_id " +
                    "GROUP BY rl.rlabel_id, acc.payment_date ORDER BY rl.rlabel_id, acc.payment_date ASC";
    public static final String GET_PAYMENTS_TO_ARTISTS =
            "SELECT concat(artist.first_name, ' ', artist.last_name) as Artist, sum(acc.amount) as Amount, acc.payment_date as PaymentDate " +
                    "FROM Accounts acc JOIN pays_artist pa ON acc.transac_id=pa.transac_id " +
                    "JOIN User artist ON pa.user_id=artist.user_id " +
                    "GROUP BY artist.user_id, acc.payment_date ORDER BY artist.user_id, acc.payment_date ASC";
    public static final String GET_PAYMENTS_TO_PODCAST_HOSTS =
            "SELECT concat(phost.first_name, ' ', phost.last_name) as PodcastHost, sum(acc.amount) as Amount, acc.payment_date as PaymentDate " +
                    "FROM Accounts acc JOIN pays_ph pp ON acc.transac_id=pp.transac_id " +
                    "JOIN User phost ON pp.user_id=phost.user_id " +
                    "GROUP BY phost.user_id, acc.payment_date ORDER BY phost.user_id, acc.payment_date ASC";

}
