package edu.ncsu.csc540.s23.backend.constants;

public class OperationQuery {
    public static final String GET_ALL_USERS = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User";
    public static final String GET_ALL_ARTISTS = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, rlabel_id as recordLabelId, primary_genre_id, status, type, artist_country FROM User u JOIN Artist a ON u.user_id=a.user_id;";
    public static final String GET_ALL_PODCAST_HOSTS = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, city FROM User u JOIN Podcast_Host p ON u.user_id=p.user_id;";
    public static final String GET_ALL_GENRES = "SELECT genre_id, name, gtype as genreType FROM Genre";
    public static final String GET_ALL_RECORD_LABELS = "SELECT rlabel_id, rlabel_name FROM Record_Label";
    public static final String INSERT_USER = "INSERT INTO User (first_name, last_name, email_id, phone_num, reg_date) VALUES(?, ?, ?, ?, ?)";
    public static final String INSERT_ARTIST = "INSERT INTO Artist (user_id, rlabel_id, primary_genre_id, status, type, artist_country) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PODCAST_HOST = "INSERT INTO Podcast_Host (user_id, city) VALUES(?, ?)";
    public static final String GET_USER_BY_ID = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User WHERE user_id=?;";
    public static final String GET_ARTIST_BY_ID = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, rlabel_id as recordLabelId, primary_genre_id, status, type, artist_country FROM User u JOIN Artist a ON u.user_id=a.user_id WHERE u.user_id=?;";
    public static final String GET_PODCAST_HOST_BY_ID = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, city FROM User u JOIN Podcast_Host p ON u.user_id=p.user_id WHERE u.user_id=?;";
    public static final String INSERT_GENRE = "INSERT INTO Genre (name, gtype) VALUES (?, ?)";
    public static final String INSERT_RECORD_LABEL = "INSERT INTO Record_Label (rlabel_name) VALUES (?)";

}
