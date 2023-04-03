package edu.ncsu.csc540.s23.backend.constants;

public class SetupQuery {

    // region Drop Tables
    public static final String DROP_TABLE_HOSTED_BY = "DROP TABLE IF EXISTS hosted_by;";
    public static final String DROP_TABLE_SPONSORS = "DROP TABLE IF EXISTS sponsors;";
    public static final String DROP_TABLE_RATES = "DROP TABLE IF EXISTS rates;";
    public static final String DROP_TABLE_CONTRIBUTED_TO = "DROP TABLE IF EXISTS contributed_to;";
    public static final String DROP_TABLE_PERFORMED_BY = "DROP TABLE IF EXISTS performed_by;";
    public static final String DROP_TABLE_LISTENS_TO = "DROP TABLE IF EXISTS listens_to;";
    public static final String DROP_TABLE_SUBSCRIBES_TO = "DROP TABLE IF EXISTS subscribes_to;";
    public static final String DROP_TABLE_LISTENS_TO_P = "DROP TABLE IF EXISTS listens_to_p;";
    public static final String DROP_TABLE_HAS = "DROP TABLE IF EXISTS has;";
    public static final String DROP_TABLE_HAS_GENRE = "DROP TABLE IF EXISTS has_genre;";
    public static final String DROP_TABLE_EPISODE_HAS = "DROP TABLE IF EXISTS episode_has;";
    public static final String DROP_TABLE_USER_PAYS = "DROP TABLE IF EXISTS user_pays;";
    public static final String DROP_TABLE_PAYS_RECORD = "DROP TABLE IF EXISTS pays_record;";
    public static final String DROP_TABLE_PAYS_PH = "DROP TABLE IF EXISTS pays_ph;";
    public static final String DROP_TABLE_PAYS_ARTIST = "DROP TABLE IF EXISTS pays_artist;";
    public static final String DROP_TABLE_ARTIST = "DROP TABLE IF EXISTS Artist;";
    public static final String DROP_TABLE_PODCAST_HOST = "DROP TABLE IF EXISTS Podcast_Host;";
    public static final String DROP_TABLE_SONG = "DROP TABLE IF EXISTS Song;";
    public static final String DROP_TABLE_GENRE = "DROP TABLE IF EXISTS Genre;";
    public static final String DROP_TABLE_RECORD_LABEL = "DROP TABLE IF EXISTS Record_Label;";
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS User;";
    public static final String DROP_TABLE_ALBUM = "DROP TABLE IF EXISTS Album;";
    public static final String DROP_TABLE_PODCAST_EPISODE = "DROP TABLE IF EXISTS Podcast_Episode;";
    public static final String DROP_TABLE_PODCAST = "DROP TABLE IF EXISTS Podcast;";
    public static final String DROP_TABLE_GUEST_SPEAKER = "DROP TABLE IF EXISTS Guest_Speaker;";
    public static final String DROP_TABLE_SPONSOR = "DROP TABLE IF EXISTS Sponsor;";
    public static final String DROP_TABLE_ACCOUNTS = "DROP TABLE IF EXISTS Accounts;";
    // endregion
    // region Create Tables
    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS User (user_id int NOT NULL AUTO_INCREMENT,first_name varchar(64) NOT NULL,last_name varchar(64) NOT NULL,phone_num varchar(16) NOT NULL UNIQUE,email_id varchar(64) NOT NULL UNIQUE,reg_date datetime NOT NULL,PRIMARY KEY (user_id));";
    public static final String CREATE_TABLE_GENRE = "CREATE TABLE IF NOT EXISTS Genre (genre_id int NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL,gtype varchar(30) NOT NULL,PRIMARY KEY (genre_id));";
    public static final String CREATE_TABLE_RECORD_LABEL = "CREATE TABLE IF NOT EXISTS Record_Label (rlabel_id int NOT NULL AUTO_INCREMENT,rlabel_name varchar(64) NOT NULL,PRIMARY KEY (rlabel_id));";
    public static final String CREATE_TABLE_ARTIST = "CREATE TABLE IF NOT EXISTS Artist (user_id int,rlabel_id int NOT NULL,primary_genre_id int,status varchar(16) NOT NULL,`type` varchar(16) NOT NULL,artist_country varchar(64),PRIMARY KEY(user_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`rlabel_id`) REFERENCES `Record_Label` (`rlabel_id`) ON DELETE CASCADE,FOREIGN KEY (`primary_genre_id`) REFERENCES `Genre` (`genre_id`) ON DELETE SET NULL);";
    public static final String CREATE_TABLE_PODCAST_HOST = "CREATE TABLE IF NOT EXISTS Podcast_Host (user_id int,city varchar(64),PRIMARY KEY(user_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_ALBUM = "CREATE TABLE IF NOT EXISTS Album (album_id int NOT NULL AUTO_INCREMENT,album_name varchar(64) NOT NULL,release_year datetime NOT NULL,edition varchar(30) NOT NULL,PRIMARY KEY (album_id));";
    public static final String CREATE_TABLE_SONG = "CREATE TABLE IF NOT EXISTS Song (song_id int NOT NULL AUTO_INCREMENT,album_id int,title varchar(64) NOT NULL,duration time NOT NULL,track_no int NOT NULL,release_date DATETIME NOT NULL,release_country varchar(64) NOT NULL,language varchar(30) NOT NULL,royalty_rate DECIMAL NOT NULL,PRIMARY KEY (song_id, album_id),UNIQUE(album_id, title),FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PODCAST = "CREATE TABLE IF NOT EXISTS Podcast (podcast_id int NOT NULL AUTO_INCREMENT,pname varchar(64) NOT NULL,planguage varchar(30) NOT NULL,country varchar(64) NOT NULL,PRIMARY KEY(podcast_id));";
    public static final String CREATE_TABLE_PODCAST_EPISODE = "CREATE TABLE IF NOT EXISTS Podcast_Episode(pepi_id int NOT NULL AUTO_INCREMENT,podcast_id int,epi_title varchar(128) NOT NULL,prelease_date DATETIME NOT NULL,pduration time NOT NULL,adv_count int NOT NULL,episode_no int NOT NULL,PRIMARY KEY (pepi_id, podcast_id),UNIQUE(podcast_id, epi_title),FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_GUEST_SPEAKER = "CREATE TABLE IF NOT EXISTS Guest_Speaker (gspeaker_id int NOT NULL AUTO_INCREMENT,gfirst_name varchar(64) NOT NULL,glast_name varchar(64) NOT NULL,gphone_num varchar(16) NOT NULL UNIQUE,gemail varchar(64) NOT NULL UNIQUE,gcity varchar(64) NOT NULL,PRIMARY KEY(gspeaker_id));";
    public static final String CREATE_TABLE_SPONSOR = "CREATE TABLE IF NOT EXISTS Sponsor (sponsor_id int NOT NULL AUTO_INCREMENT,sfirst_name varchar(64) NOT NULL,slast_name varchar(64) NOT NULL,organization varchar(128) NOT NULL,semail varchar(64) NOT NULL UNIQUE,scity varchar(64) NOT NULL,PRIMARY KEY(sponsor_id));";
    public static final String CREATE_TABLE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS Accounts (transac_id int NOT NULL AUTO_INCREMENT,amount DECIMAL NOT NULL,payee varchar(128) NOT NULL,payer varchar(128) NOT NULL,payment_date DATETIME NOT NULL,PRIMARY KEY(transac_id));";
    public static final String CREATE_TABLE_HOSTED_BY = "CREATE TABLE IF NOT EXISTS hosted_by (podcast_id int NOT NULL,user_id int NOT NULL,PRIMARY KEY(podcast_id, user_id),FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_SPONSORS = "CREATE TABLE IF NOT EXISTS sponsors (sponsor_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY(podcast_id, sponsor_id),FOREIGN KEY (`sponsor_id`) REFERENCES `Sponsor` (`sponsor_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_RATES = "CREATE TABLE IF NOT EXISTS rates (user_id int NOT NULL,podcast_id int NOT NULL,rating DECIMAL NOT NULL,PRIMARY KEY(podcast_id, user_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE, FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_CONTRIBUTED_TO = "CREATE TABLE IF NOT EXISTS contributed_to (album_id int,user_id int,PRIMARY KEY(album_id, user_id),FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON UPDATE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE CASCADE);";
    public static final String CREATE_TABLE_PERFORMED_BY = "CREATE TABLE IF NOT EXISTS performed_by (song_id int NOT NULL,album_id int NOT NULL,user_id int NOT NULL,is_main BOOLEAN NOT NULL,PRIMARY KEY (song_id, album_id, user_id),FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_LISTENS_TO = "CREATE TABLE IF NOT EXISTS listens_to (song_id int NOT NULL,album_id int NOT NULL,user_id int NOT NULL,`timestamp` TIMESTAMP NOT NULL,PRIMARY KEY(song_id, album_id, user_id, `timestamp`),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_SUBSCRIBES_TO = "CREATE TABLE IF NOT EXISTS subscribes_to (user_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY(user_id, podcast_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_LISTENS_TO_P = "CREATE TABLE IF NOT EXISTS listens_to_p(user_id int NOT NULL,pepi_id int NOT NULL,podcast_id int NOT NULL,ptimestamp TIMESTAMP NOT NULL,PRIMARY KEY(user_id, pepi_id, podcast_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`pepi_id`) REFERENCES `Podcast_Episode` (`pepi_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_HAS = "CREATE TABLE IF NOT EXISTS has(genre_id int NOT NULL,song_id int NOT NULL,album_id int NOT NULL,PRIMARY KEY(genre_id, song_id, album_id),FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`genre_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_HAS_GENRE = "CREATE TABLE IF NOT EXISTS has_genre(genre_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY (genre_id, podcast_id),FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`genre_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_EPISODE_HAS = "CREATE TABLE IF NOT EXISTS episode_has(gspeaker_id int NOT NULL,pepi_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY (gspeaker_id, pepi_id, podcast_id),FOREIGN KEY (`gspeaker_id`) REFERENCES `Guest_Speaker` (`gspeaker_id`) ON DELETE CASCADE,FOREIGN KEY (`pepi_id`) REFERENCES `Podcast_Episode` (`pepi_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_USER_PAYS = "CREATE TABLE IF NOT EXISTS user_pays(user_id int NOT NULL,transac_id int NOT NULL,plan varchar(20) NOT NULL,amount DECIMAL NOT NULL,num_months int NOT NULL,start_date DATETIME NOT NULL,PRIMARY KEY (user_id, transac_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PAYS_RECORD = "CREATE TABLE IF NOT EXISTS pays_record(rlabel_id int NOT NULL,transac_id int NOT NULL,song_id int NOT NULL,album_id int NOT NULL,PRIMARY KEY (rlabel_id, transac_id, song_id, album_id),FOREIGN KEY (`rlabel_id`) REFERENCES `Record_Label` (`rlabel_id`) ON DELETE CASCADE,FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PAYS_PH = "CREATE TABLE IF NOT EXISTS pays_ph(transac_id int NOT NULL,pepi_id int NOT NULL,podcast_id int NOT NULL,user_id int NOT NULL,PRIMARY KEY (transac_id, pepi_id, podcast_id, user_id),FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE,FOREIGN KEY (`pepi_id`) REFERENCES `Podcast_Episode` (`pepi_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PAYS_ARTIST = "CREATE TABLE IF NOT EXISTS pays_artist(rlabel_id int NOT NULL,transac_id int NOT NULL,user_id int NOT NULL,song_id int NOT NULL,album_id int NOT NULL,PRIMARY KEY (rlabel_id, transac_id, user_id, song_id, album_id),FOREIGN KEY (`rlabel_id`) REFERENCES `Record_Label` (`rlabel_id`) ON DELETE CASCADE,FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    // endregion
    // region Insert Data
    public static final String INSERT_USER_1 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8001, 'Alex', 'A', 'alex.a@ncsu.edu', '+1(919)000-0001', '2023-04-02')";
    public static final String INSERT_USER_2 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8002, 'John', 'J', 'john.j@ncsu.edu', '+1(919)000-0002', '2023-04-03')";
    public static final String INSERT_ARTIST_USER_1 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(2001, 'Forest', 'F', 'forest.f@ncsu.edu', '+1(919)000-1001', '2023-01-01')";
    public static final String INSERT_ARTIST_USER_2 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(2002, 'Rain', 'R', 'rain.r@ncsu.edu', '+1(919)000-1002', '2023-01-02')";
    public static final String INSERT_PODCAST_HOST_USER_1 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(6001, 'Matthew', 'Wilson', 'matthew.wilson@ncsu.edu', '+1(919)000-2001', '2023-01-03')";
    public static final String INSERT_ALBUM_1 = "INSERT INTO Album (album_id, album_name, release_year, edition) VALUES(4001, 'Electric Oasis', 2023, 'Special')";
    public static final String INSERT_ALBUM_2 = "INSERT INTO Album (album_id, album_name, release_year, edition) VALUES(4002, 'Lost in the Echoes', 2023, 'Limited')";
    public static final String INSERT_RECORD_LABEL_1 = "INSERT INTO Record_Label (rlabel_id, rlabel_name) VALUES(3001, 'Elevate Records')";
    public static final String INSERT_RECORD_LABEL_2 = "INSERT INTO Record_Label (rlabel_id, rlabel_name) VALUES(3002, 'Melodic Avenue Music')";
    public static final String INSERT_GENRE_1 = "INSERT INTO Genre (genre_id, name, gtype) VALUES(1, 'Country Music', 'Song')";
    public static final String INSERT_GENRE_2 = "INSERT INTO Genre (genre_id, name, gtype) VALUES(2, 'Instrumental', 'Song')";
    public static final String INSERT_GENRE_3 = "INSERT INTO Genre (genre_id, name, gtype) VALUES(3, 'Informative', 'Podcast')";
    public static final String INSERT_ARTIST_1 = "INSERT INTO Artist (user_id, rlabel_id, primary_genre_id, status, type, artist_country) VALUES(2001, 3001, 1, 'Active', 'Band', 'United States')";
    public static final String INSERT_ARTIST_2 = "INSERT INTO Artist (user_id, rlabel_id, primary_genre_id, status, type, artist_country) VALUES(2002, 3002, 2, 'Active', 'Composer', 'United States')";
    public static final String INSERT_SONG_1 = "INSERT INTO Song (song_id,  album_id, title, duration, track_no, release_date, release_country, language, royalty_rate) VALUES(1001, 4001, 'Electric Dreamscape', '04:11:00', 1, '2023-03-27', 'United States', 'English', 0.1)";
    public static final String INSERT_SONG_2 = "INSERT INTO Song (song_id,  album_id, title, duration, track_no, release_date, release_country, language, royalty_rate) VALUES(1002, 4001, 'Midnight Mirage', '03:58:00', 2, '2023-03-31', 'United States', 'English', 0.1)";
    public static final String INSERT_SONG_3 = "INSERT INTO Song (song_id,  album_id, title, duration, track_no, release_date, release_country, language, royalty_rate) VALUES(1003, 4002, 'Echoes of You', '04:05:00', 1, '2023-04-01', 'United States', 'English', 0.1)";
    public static final String INSERT_SONG_4 = "INSERT INTO Song (song_id,  album_id, title, duration, track_no, release_date, release_country, language, royalty_rate) VALUES(1004, 4002, 'Rainy Nights', '03:25:00', 2, '2023-04-02', 'United States', 'English', 0.1)";
    public static final String INSERT_PODCAST_HOST_1 = "INSERT INTO Podcast_Host (user_id, city) VALUES(6001, 'Raleigh')";
    public static final String INSERT_PODCAST = "INSERT INTO Podcast (podcast_id, pname, planguage, country) VALUES(5001, 'Mind Over Matter: Exploring the Power of the Human Mind', 'English', 'United States')";
    public static final String INSERT_PODCAST_EPISODE_1 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no) VALUES(7001, 5001, 'The Science of Mindfulness', '2023-04-02', '55:12:00', 0, 1)";
    public static final String INSERT_PODCAST_EPISODE_2 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no) VALUES(7002, 5001, 'Unlocking Your Potential', '2023-04-03', '41:52:00', 0, 2)";
    // endregion
    // region Insert Relationship Data
    //TODO: setup listens_to
    //TODO: setup subscribes_to
    //TODO: setup listens_to_p
    //TODO: setup user_pays
    //TODO: setup pays_record
    //TODO: setup pays_ph
    //TODO: setup pays_artist
    public static final String INSERT_HOSTED_BY = "INSERT INTO hosted_by (podcast_id, user_id) VALUES(5001, 6001);";
    public static final String INSERT_RATED_1 = "INSERT INTO rates (user_id, podcast_id, rating) VALUES(8001, 5001, 5);";
    public static final String INSERT_RATED_2 = "INSERT INTO rates (user_id, podcast_id, rating) VALUES(8002, 5001, 4);";
    public static final String INSERT_CONTRIBUTED_TO_1 = "INSERT INTO contributed_to (album_id, user_id) VALUES(4001, 2001);";
    public static final String INSERT_CONTRIBUTED_TO_2 = "INSERT INTO contributed_to (album_id, user_id) VALUES(4002, 2002);";
    public static final String INSERT_PERFORMED_BY_1 = "INSERT INTO performed_by (song_id, album_id, user_id, is_main) VALUES(1001, 4001, 2001, 1);";
    public static final String INSERT_PERFORMED_BY_2 = "INSERT INTO performed_by (song_id, album_id, user_id, is_main) VALUES(1002, 4001, 2001, 1);";
    public static final String INSERT_PERFORMED_BY_3 = "INSERT INTO performed_by (song_id, album_id, user_id, is_main) VALUES(1002, 4001, 2002, 0);";
    public static final String INSERT_PERFORMED_BY_4 = "INSERT INTO performed_by (song_id, album_id, user_id, is_main) VALUES(1003, 4002, 2002, 1);";
    public static final String INSERT_PERFORMED_BY_5 = "INSERT INTO performed_by (song_id, album_id, user_id, is_main) VALUES(1004, 4002, 2002, 1);";
    public static final String INSERT_HAS_1 = "INSERT INTO has (genre_id, song_id, album_id) VALUES(1, 1001, 4001);";
    public static final String INSERT_HAS_2 = "INSERT INTO has (genre_id, song_id, album_id) VALUES(2, 1002, 4001);";
    public static final String INSERT_HAS_3 = "INSERT INTO has (genre_id, song_id, album_id) VALUES(1, 1003, 4002);";
    public static final String INSERT_HAS_4 = "INSERT INTO has (genre_id, song_id, album_id) VALUES(2, 1004, 4002);";
    public static final String INSERT_HAS_GENRE_1 = "INSERT INTO has_genre (genre_id, podcast_id) VALUES(3, 5001);";
    // no setup data required for sponsors and episode_has
    // endregion
}
