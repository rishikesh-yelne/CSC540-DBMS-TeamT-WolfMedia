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
    public static final String DROP_TABLE_HISTORICAL_SONG_COUNT = "DROP TABLE IF EXISTS Historical_Song_Count;";
    public static final String DROP_TABLE_SONG = "DROP TABLE IF EXISTS Song;";
    public static final String DROP_TABLE_GENRE = "DROP TABLE IF EXISTS Genre;";
    public static final String DROP_TABLE_RECORD_LABEL = "DROP TABLE IF EXISTS Record_Label;";
    public static final String DROP_TABLE_PODCAST_EPISODE = "DROP TABLE IF EXISTS Podcast_Episode;";
    public static final String DROP_TABLE_PODCAST = "DROP TABLE IF EXISTS Podcast;";
    public static final String DROP_TABLE_PODCAST_HOST = "DROP TABLE IF EXISTS Podcast_Host;";
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS User;";
    public static final String DROP_TABLE_ALBUM = "DROP TABLE IF EXISTS Album;";
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
    public static final String CREATE_TABLE_ALBUM = "CREATE TABLE IF NOT EXISTS Album (album_id int NOT NULL AUTO_INCREMENT,album_name varchar(64) NOT NULL,release_year int NOT NULL,edition varchar(30) NOT NULL,PRIMARY KEY (album_id));";
    public static final String CREATE_TABLE_SONG = "CREATE TABLE IF NOT EXISTS Song (song_id int NOT NULL AUTO_INCREMENT,album_id int,title varchar(64) NOT NULL,duration time NOT NULL,track_no int NOT NULL,release_date DATETIME NOT NULL,release_country varchar(64) NOT NULL,language varchar(30) NOT NULL,royalty_rate DECIMAL(10,2) NOT NULL,PRIMARY KEY (song_id, album_id),UNIQUE(album_id, title),FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PODCAST = "CREATE TABLE IF NOT EXISTS Podcast (podcast_id int NOT NULL AUTO_INCREMENT,user_id int, pname varchar(64) NOT NULL,planguage varchar(30) NOT NULL,country varchar(64) NOT NULL,flat_fee DECIMAL(10,2) NOT NULL,PRIMARY KEY(podcast_id), FOREIGN KEY (`user_id`) REFERENCES `Podcast_Host` (`user_id`) ON DELETE SET NULL);";
    public static final String CREATE_TABLE_PODCAST_EPISODE = "CREATE TABLE IF NOT EXISTS Podcast_Episode(pepi_id int NOT NULL AUTO_INCREMENT,podcast_id int,epi_title varchar(128) NOT NULL,prelease_date DATETIME NOT NULL,pduration time NOT NULL,adv_count int NOT NULL,bonus_rate DECIMAL(10,2) NOT NULL,episode_no int NOT NULL,PRIMARY KEY (pepi_id, podcast_id),UNIQUE(podcast_id, epi_title),FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_GUEST_SPEAKER = "CREATE TABLE IF NOT EXISTS Guest_Speaker (gspeaker_id int NOT NULL AUTO_INCREMENT,gfirst_name varchar(64) NOT NULL,glast_name varchar(64) NOT NULL,gphone_num varchar(16) NOT NULL UNIQUE,gemail varchar(64) NOT NULL UNIQUE,gcity varchar(64) NOT NULL,PRIMARY KEY(gspeaker_id));";
    public static final String CREATE_TABLE_SPONSOR = "CREATE TABLE IF NOT EXISTS Sponsor (sponsor_id int NOT NULL AUTO_INCREMENT,sfirst_name varchar(64) NOT NULL,slast_name varchar(64) NOT NULL,organization varchar(128) NOT NULL,semail varchar(64) NOT NULL UNIQUE,scity varchar(64) NOT NULL,PRIMARY KEY(sponsor_id));";
    public static final String CREATE_TABLE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS Accounts (transac_id int NOT NULL AUTO_INCREMENT,amount DECIMAL(10,2) NOT NULL,payee varchar(128) NOT NULL,payer varchar(128) NOT NULL,payment_date DATETIME NOT NULL,PRIMARY KEY(transac_id));";
    //    public static final String CREATE_TABLE_HOSTED_BY = "CREATE TABLE IF NOT EXISTS hosted_by (podcast_id int NOT NULL,user_id int NOT NULL,PRIMARY KEY(podcast_id, user_id),FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_SPONSORS = "CREATE TABLE IF NOT EXISTS sponsors (sponsor_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY(podcast_id, sponsor_id),FOREIGN KEY (`sponsor_id`) REFERENCES `Sponsor` (`sponsor_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_RATES = "CREATE TABLE IF NOT EXISTS rates (user_id int NOT NULL,podcast_id int NOT NULL,rating DECIMAL(10,2) NOT NULL,PRIMARY KEY(podcast_id, user_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE, FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
//    public static final String CREATE_TABLE_CONTRIBUTED_TO = "CREATE TABLE IF NOT EXISTS contributed_to (album_id int,user_id int,PRIMARY KEY(album_id, user_id),FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON UPDATE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE CASCADE);";
public static final String CREATE_TABLE_PERFORMED_BY = "CREATE TABLE IF NOT EXISTS performed_by (song_id int NOT NULL,album_id int NOT NULL,user_id int NOT NULL,is_main BOOLEAN NOT NULL,PRIMARY KEY (song_id, album_id, user_id),FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_LISTENS_TO = "CREATE TABLE IF NOT EXISTS listens_to (song_id int NOT NULL,album_id int NOT NULL,user_id int NOT NULL,`timestamp` TIMESTAMP NOT NULL,PRIMARY KEY(song_id, album_id, user_id, `timestamp`),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_SUBSCRIBES_TO = "CREATE TABLE IF NOT EXISTS subscribes_to (user_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY(user_id, podcast_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_LISTENS_TO_P = "CREATE TABLE IF NOT EXISTS listens_to_p(user_id int NOT NULL,pepi_id int NOT NULL,podcast_id int NOT NULL,ptimestamp TIMESTAMP NOT NULL,PRIMARY KEY(user_id, pepi_id, podcast_id, ptimestamp),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`pepi_id`) REFERENCES `Podcast_Episode` (`pepi_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_HAS = "CREATE TABLE IF NOT EXISTS has(genre_id int NOT NULL,song_id int NOT NULL,album_id int NOT NULL,PRIMARY KEY(genre_id, song_id, album_id),FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`genre_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_HAS_GENRE = "CREATE TABLE IF NOT EXISTS has_genre(genre_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY (genre_id, podcast_id),FOREIGN KEY (`genre_id`) REFERENCES `Genre` (`genre_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_EPISODE_HAS = "CREATE TABLE IF NOT EXISTS episode_has(gspeaker_id int NOT NULL,pepi_id int NOT NULL,podcast_id int NOT NULL,PRIMARY KEY (gspeaker_id, pepi_id, podcast_id),FOREIGN KEY (`gspeaker_id`) REFERENCES `Guest_Speaker` (`gspeaker_id`) ON DELETE CASCADE,FOREIGN KEY (`pepi_id`) REFERENCES `Podcast_Episode` (`pepi_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_USER_PAYS = "CREATE TABLE IF NOT EXISTS user_pays(user_id int NOT NULL,transac_id int NOT NULL,plan varchar(20) NOT NULL,amount DECIMAL(10,2) NOT NULL,num_months int NOT NULL,start_date DATETIME NOT NULL,PRIMARY KEY (user_id, transac_id),FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PAYS_RECORD = "CREATE TABLE IF NOT EXISTS pays_record(rlabel_id int NOT NULL,transac_id int NOT NULL,song_id int NOT NULL,album_id int NOT NULL,PRIMARY KEY (rlabel_id, transac_id, song_id, album_id),FOREIGN KEY (`rlabel_id`) REFERENCES `Record_Label` (`rlabel_id`) ON DELETE CASCADE,FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PAYS_PH = "CREATE TABLE IF NOT EXISTS pays_ph(transac_id int NOT NULL,pepi_id int NOT NULL,podcast_id int NOT NULL,user_id int NOT NULL,PRIMARY KEY (transac_id, pepi_id, podcast_id, user_id),FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE,FOREIGN KEY (`pepi_id`) REFERENCES `Podcast_Episode` (`pepi_id`) ON DELETE CASCADE,FOREIGN KEY (`podcast_id`) REFERENCES `Podcast` (`podcast_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_PAYS_ARTIST = "CREATE TABLE IF NOT EXISTS pays_artist(rlabel_id int NOT NULL,transac_id int NOT NULL,user_id int NOT NULL,song_id int NOT NULL,album_id int NOT NULL,PRIMARY KEY (rlabel_id, transac_id, user_id, song_id, album_id),FOREIGN KEY (`rlabel_id`) REFERENCES `Record_Label` (`rlabel_id`) ON DELETE CASCADE,FOREIGN KEY (`transac_id`) REFERENCES `Accounts` (`transac_id`) ON DELETE CASCADE,FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE,FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_HISTORICAL_SONG_COUNT = "CREATE TABLE Historical_Song_Count (song_id int NOT NULL, album_id int NOT NULL, play_count int NOT NULL, month DATE NOT NULL, PRIMARY KEY (`song_id`, `album_id`, `month`), FOREIGN KEY (`song_id`) REFERENCES `Song` (`song_id`) ON DELETE CASCADE, FOREIGN KEY (`album_id`) REFERENCES `Album` (`album_id`) ON DELETE CASCADE);";
    // endregion
    // region Insert Data
    public static final String INSERT_USER_1 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8001, 'Alex', 'A', 'alex.a@ncsu.edu', '+1(919)000-0001', '2023-01-01')";
    public static final String INSERT_USER_2 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8002, 'John', 'J', 'john.j@ncsu.edu', '+1(919)000-0002', '2023-01-02')";
    public static final String INSERT_USER_3 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8003, 'Test1', 'User1', 'test1.user1@ncsu.edu', '+1(919)000-0003', '2023-01-03')";
    public static final String INSERT_USER_4 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8004, 'Test2', 'User2', 'test2.user2@ncsu.edu', '+1(919)000-0004', '2023-01-04')";
    public static final String INSERT_USER_5 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8005, 'Test3', 'User3', 'test3.user3@ncsu.edu', '+1(919)000-0005', '2023-01-05')";
    public static final String INSERT_USER_6 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8006, 'Test4', 'User4', 'test4.user4@ncsu.edu', '+1(919)000-0006', '2023-01-06')";
    public static final String INSERT_USER_7 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8007, 'Test5', 'User5', 'test5.user5@ncsu.edu', '+1(919)000-0007', '2023-01-07')";
    public static final String INSERT_USER_8 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8008, 'Test6', 'User6', 'test6.user6@ncsu.edu', '+1(919)000-0008', '2023-02-01')";
    public static final String INSERT_USER_9 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8009, 'Test7', 'User7', 'test7.user7@ncsu.edu', '+1(919)000-0009', '2023-03-01')";
    public static final String INSERT_USER_10 = "INSERT INTO User (user_id, first_name, last_name, email_id, phone_num, reg_date) VALUES(8010, 'Test8', 'User8', 'test8.user8@ncsu.edu', '+1(919)000-0010', '2023-04-01')";
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
    public static final String INSERT_PODCAST = "INSERT INTO Podcast (podcast_id, user_id, pname, planguage, country, flat_fee) VALUES(5001, 6001, 'Mind Over Matter: Exploring the Power of the Human Mind', 'English', 'United States', 10)";
    public static final String INSERT_PODCAST_EPISODE_1 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no, bonus_rate) VALUES(7001, 5001, 'The Science of Mindfulness', '2023-01-14', '00:55:12', 0, 1, 10)";
    public static final String INSERT_PODCAST_EPISODE_2 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no, bonus_rate) VALUES(7002, 5001, 'Unlocking Your Potential', '2023-01-21', '00:41:52', 0, 2, 10)";
    public static final String INSERT_PODCAST_EPISODE_3 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no, bonus_rate) VALUES(7003, 5001, 'Being Successful', '2023-02-07', '00:43:25', 0, 2, 10)";
    public static final String INSERT_PODCAST_EPISODE_4 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no, bonus_rate) VALUES(7004, 5001, 'Achieving Greater Heights', '2023-03-15', '00:51:07', 0, 2, 10)";
    public static final String INSERT_PODCAST_EPISODE_5 = "INSERT INTO Podcast_Episode (pepi_id, podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no, bonus_rate) VALUES(7005, 5001, 'Challenge Yourself', '2023-04-10', '00:48:16', 0, 2, 10)";
    public static final String INSERT_GUEST_SPEAKER_1 = "INSERT INTO Guest_Speaker (gspeaker_id, gfirst_name, glast_name, gphone_num, gemail, gcity) VALUES(9001, 'Sam', 'S', '+1(919)123-0001', 'sam.s@ncsu.edu', 'Chicago')";
    public static final String INSERT_GUEST_SPEAKER_2 = "INSERT INTO Guest_Speaker (gspeaker_id, gfirst_name, glast_name, gphone_num, gemail, gcity) VALUES(9002, 'Justin', 'J', '+1(919)123-0002', 'justin.j@ncsu.edu', 'Denver')";
    public static final String INSERT_GUEST_SPEAKER_3 = "INSERT INTO Guest_Speaker (gspeaker_id, gfirst_name, glast_name, gphone_num, gemail, gcity) VALUES(9003, 'Kamal', 'K', '+1(919)123-0003', 'kamal.k@ncsu.edu', 'Atlanta')";
    public static final String INSERT_SPONSOR_1 = "INSERT INTO Sponsor (sponsor_id, sfirst_name, slast_name, organization, semail, scity) VALUES(10001, 'Christine', 'C', '+1(919)456-0001', 'christine.c@ncsu.edu', 'Dallas')";
    public static final String INSERT_SPONSOR_2 = "INSERT INTO Sponsor (sponsor_id, sfirst_name, slast_name, organization, semail, scity) VALUES(10002, 'Crystal', 'C', '+1(919)456-0002', 'crystal.c@ncsu.edu', 'Austin')";

    // Transactions in January 2023
    public static final String INSERT_TRANSACTION_JAN_1 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(1, 1, 'WolfMedia', 'Elevate Records', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_2 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(2, 10, 'WolfMedia', 'Elevate Records', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_3 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(3, 100, 'WolfMedia', 'Melodic Avenue Music', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_4 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(4, 1000, 'WolfMedia', 'Melodic Avenue Music', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_5 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(5, 0.7, 'Elevate Records', 'Forest F', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_6 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(6, 3.5, 'Elevate Records', 'Forest F', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_7 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(7, 3.5, 'Elevate Records', 'Rain R', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_8 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(8, 70, 'Melodic Avenue Music', 'Rain R', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_9 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(9, 700, 'Melodic Avenue Music', 'Rain R', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_10 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(10, 10, 'WolfMedia', 'Matthew Wilson', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_11 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(11, 10, 'WolfMedia', 'Matthew Wilson', '2023-01-31');";
    public static final String INSERT_TRANSACTION_JAN_12 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(37, 10, 'Alex A', 'WolfMedia', '2023-01-01');";
    public static final String INSERT_TRANSACTION_JAN_13 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(38, 10, 'John J', 'WolfMedia', '2023-01-01');";
    public static final String INSERT_TRANSACTION_JAN_14 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(39, 500, 'Test1 User1', 'WolfMedia', '2023-01-01');";
    public static final String INSERT_TRANSACTION_JAN_15 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(40, 500, 'Test2 User2', 'WolfMedia', '2023-01-01');";
    public static final String INSERT_TRANSACTION_JAN_16 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(41, 50, 'Test3 User3', 'WolfMedia', '2023-01-01');";
    public static final String INSERT_TRANSACTION_JAN_17 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(42, 30, 'Test4 User4', 'WolfMedia', '2023-01-01');";
    public static final String INSERT_TRANSACTION_JAN_18 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(43, 11, 'Test5 User5', 'WolfMedia', '2023-01-01');";

    // Transactions in February 2023
    public static final String INSERT_TRANSACTION_FEB_1 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(12, 2, 'WolfMedia', 'Elevate Records', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_2 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(13, 20, 'WolfMedia', 'Elevate Records', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_3 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(14, 200, 'WolfMedia', 'Melodic Avenue Music', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_4 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(15, 2000, 'WolfMedia', 'Melodic Avenue Music', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_5 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(16, 1.4, 'Elevate Records', 'Forest F', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_6 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(17, 7, 'Elevate Records', 'Forest F', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_7 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(18, 7, 'Elevate Records', 'Rain R', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_8 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(19, 140, 'Melodic Avenue Music', 'Rain R', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_9 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(20, 1400, 'Melodic Avenue Music', 'Rain R', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_10 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(21, 10, 'WolfMedia', 'Matthew Wilson', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_11 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(22, 10, 'WolfMedia', 'Matthew Wilson', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_12 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(23, 10, 'WolfMedia', 'Matthew Wilson', '2023-02-28');";
    public static final String INSERT_TRANSACTION_FEB_13 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(44, 10, 'Alex A', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_14 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(45, 10, 'John J', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_15 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(46, 1000, 'Test1 User1', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_16 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(47, 1000, 'Test2 User2', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_17 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(48, 100, 'Test3 User3', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_18 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(49, 60, 'Test4 User4', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_19 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(50, 22, 'Test5 User5', 'WolfMedia', '2023-02-01');";
    public static final String INSERT_TRANSACTION_FEB_20 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(51, 20, 'Test6 User6', 'WolfMedia', '2023-02-01');";

    // Transactions in March 2023
    public static final String INSERT_TRANSACTION_MAR_1 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(24, 3, 'WolfMedia', 'Elevate Records', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_2 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(25, 30, 'WolfMedia', 'Elevate Records', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_3 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(26, 300, 'WolfMedia', 'Melodic Avenue Music', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_4 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(27, 3000, 'WolfMedia', 'Melodic Avenue Music', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_5 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(28, 2.1, 'Elevate Records', 'Forest F', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_6 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(29, 10.5, 'Elevate Records', 'Forest F', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_7 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(30, 10.5, 'Elevate Records', 'Rain R', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_8 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(31, 210, 'Melodic Avenue Music', 'Rain R', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_9 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(32, 2100, 'Melodic Avenue Music', 'Rain R', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_10 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(33, 10, 'WolfMedia', 'Matthew Wilson', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_11 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(34, 10, 'WolfMedia', 'Matthew Wilson', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_12 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(35, 10, 'WolfMedia', 'Matthew Wilson', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_13 = "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) VALUES(36, 10, 'WolfMedia', 'Matthew Wilson', '2023-03-31');";
    public static final String INSERT_TRANSACTION_MAR_14 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(52, 10, 'Alex A', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_15 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(53, 10, 'John J', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_16 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(54, 1500, 'Test1 User1', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_17 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(55, 1500, 'Test2 User2', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_18 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(56, 150, 'Test3 User3', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_19 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(57, 90, 'Test4 User4', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_20 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(58, 33, 'Test5 User5', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_21 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(59, 20, 'Test6 User6', 'WolfMedia', '2023-03-01');";
    public static final String INSERT_TRANSACTION_MAR_22 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(60, 20, 'Test7 User7', 'WolfMedia', '2023-03-01');";

    // Transactions in April 2023
    public static final String INSERT_TRANSACTION_APR_1 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(61, 10, 'Alex A', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_2 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(62, 10, 'John J', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_3 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(63, 50000, 'Test1 User1', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_4 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(64, 50000, 'Test2 User2', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_5 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(65, 10000, 'Test3 User3', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_6 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(66, 10000, 'Test4 User4', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_7 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(67, 1000, 'Test5 User5', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_8 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(68, 1000, 'Test6 User6', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_9 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(69, 500, 'Test7 User7', 'WolfMedia', '2023-04-01');";
    public static final String INSERT_TRANSACTION_APR_10 =
            "INSERT INTO Accounts (transac_id, amount, payer, payee, payment_date) " +
                    "VALUES(70, 480, 'Test8 User8', 'WolfMedia', '2023-04-01');";
    // endregion
    // region Insert Relationship Data
    //TODO: setup listens_to
    //TODO: setup listens_to_p
//    public static final String INSERT_HOSTED_BY = "INSERT INTO hosted_by (podcast_id, user_id) VALUES(5001, 6001);";
    public static final String INSERT_RATED_1 = "INSERT INTO rates (user_id, podcast_id, rating) VALUES(8001, 5001, 5);";
    public static final String INSERT_RATED_2 = "INSERT INTO rates (user_id, podcast_id, rating) VALUES(8002, 5001, 4);";
//    public static final String INSERT_CONTRIBUTED_TO_1 = "INSERT INTO contributed_to (album_id, user_id) VALUES(4001, 2001);";
//    public static final String INSERT_CONTRIBUTED_TO_2 = "INSERT INTO contributed_to (album_id, user_id) VALUES(4002, 2002);";
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
    // no setup data required for sponsors, episode_has, subscribes_to

    // Payment relationship data
    // January
    public static final String INSERT_PAYS_RECORD_JAN_1 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3001, 1001, 4001, 1);";
    public static final String INSERT_PAYS_RECORD_JAN_2 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3001, 1002, 4001, 2);";
    public static final String INSERT_PAYS_RECORD_JAN_3 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3002, 1003, 4002, 3);";
    public static final String INSERT_PAYS_RECORD_JAN_4 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3002, 1004, 4002, 4);";
    public static final String INSERT_PAYS_ARTIST_JAN_1 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2001, 1001, 4001, 5);";
    public static final String INSERT_PAYS_ARTIST_JAN_2 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2001, 1002, 4001, 6);";
    public static final String INSERT_PAYS_ARTIST_JAN_3 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2002, 1002, 4001, 7);";
    public static final String INSERT_PAYS_ARTIST_JAN_4 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3002, 2002, 1003, 4002, 8);";
    public static final String INSERT_PAYS_ARTIST_JAN_5 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3002, 2002, 1004, 4002, 9);";
    public static final String INSERT_PAYS_PODCAST_HOST_JAN_1 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7001, 5001, 6001, 10);";
    public static final String INSERT_PAYS_PODCAST_HOST_JAN_2 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7002, 5001, 6001, 11);";
    public static final String INSERT_USER_PAYS_1 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8001, 37, 'Monthly', 10, 1, '2023-01-01');";
    public static final String INSERT_USER_PAYS_2 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8002, 38, 'Monthly', 10, 1, '2023-01-01');";
    public static final String INSERT_USER_PAYS_3 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8003, 39, 'Monthly', 500, 1, '2023-01-01');";
    public static final String INSERT_USER_PAYS_4 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8004, 40, 'Monthly', 500, 1, '2023-01-01');";
    public static final String INSERT_USER_PAYS_5 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8005, 41, 'Monthly', 50, 1, '2023-01-01');";
    public static final String INSERT_USER_PAYS_6 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8006, 42, 'Monthly', 30, 1, '2023-01-01');";
    public static final String INSERT_USER_PAYS_7 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8007, 43, 'Monthly', 11, 1, '2023-01-01');";

    // February
    public static final String INSERT_PAYS_RECORD_FEB_1 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3001, 1001, 4001, 12);";
    public static final String INSERT_PAYS_RECORD_FEB_2 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3001, 1002, 4001, 13);";
    public static final String INSERT_PAYS_RECORD_FEB_3 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3002, 1003, 4002, 14);";
    public static final String INSERT_PAYS_RECORD_FEB_4 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3002, 1004, 4002, 15);";
    public static final String INSERT_PAYS_ARTIST_FEB_1 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2001, 1001, 4001, 16);";
    public static final String INSERT_PAYS_ARTIST_FEB_2 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2001, 1002, 4001, 17);";
    public static final String INSERT_PAYS_ARTIST_FEB_3 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2002, 1002, 4001, 18);";
    public static final String INSERT_PAYS_ARTIST_FEB_4 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3002, 2002, 1003, 4002, 19);";
    public static final String INSERT_PAYS_ARTIST_FEB_5 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3002, 2002, 1004, 4002, 20);";
    public static final String INSERT_PAYS_PODCAST_HOST_FEB_1 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7001, 5001, 6001, 21);";
    public static final String INSERT_PAYS_PODCAST_HOST_FEB_2 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7002, 5001, 6001, 22);";
    public static final String INSERT_PAYS_PODCAST_HOST_FEB_3 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7003, 5001, 6001, 23);";
    public static final String INSERT_USER_PAYS_8 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8001, 44, 'Monthly', 10, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_9 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8002, 45, 'Monthly', 10, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_10 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8003, 46, 'Monthly', 1000, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_11 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8004, 47, 'Monthly', 1000, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_12 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8005, 48, 'Monthly', 100, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_13 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8006, 49, 'Monthly', 60, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_14 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8007, 50, 'Monthly', 22, 1, '2023-02-01');";
    public static final String INSERT_USER_PAYS_15 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8008, 51, 'Monthly', 20, 1, '2023-02-01');";

    // March
    public static final String INSERT_PAYS_RECORD_MAR_1 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3001, 1001, 4001, 24);";
    public static final String INSERT_PAYS_RECORD_MAR_2 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3001, 1002, 4001, 25);";
    public static final String INSERT_PAYS_RECORD_MAR_3 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3002, 1003, 4002, 26);";
    public static final String INSERT_PAYS_RECORD_MAR_4 = "INSERT INTO pays_record (rlabel_id, song_id, album_id, transac_id) VALUES(3002, 1004, 4002, 27);";
    public static final String INSERT_PAYS_ARTIST_MAR_1 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2001, 1001, 4001, 28);";
    public static final String INSERT_PAYS_ARTIST_MAR_2 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2001, 1002, 4001, 29);";
    public static final String INSERT_PAYS_ARTIST_MAR_3 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3001, 2002, 1002, 4001, 30);";
    public static final String INSERT_PAYS_ARTIST_MAR_4 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3002, 2002, 1003, 4002, 31);";
    public static final String INSERT_PAYS_ARTIST_MAR_5 = "INSERT INTO pays_artist (rlabel_id, user_id, song_id, album_id, transac_id) VALUES(3002, 2002, 1004, 4002, 32);";
    public static final String INSERT_PAYS_PODCAST_HOST_MAR_1 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7001, 5001, 6001, 33);";
    public static final String INSERT_PAYS_PODCAST_HOST_MAR_2 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7002, 5001, 6001, 34);";
    public static final String INSERT_PAYS_PODCAST_HOST_MAR_3 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7003, 5001, 6001, 35);";
    public static final String INSERT_PAYS_PODCAST_HOST_MAR_4 = "INSERT INTO pays_ph (pepi_id, podcast_id, user_id, transac_id) VALUES(7004, 5001, 6001, 36);";
    public static final String INSERT_USER_PAYS_16 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8001, 52, 'Monthly', 10, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_17 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8002, 53, 'Monthly', 10, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_18 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8003, 54, 'Monthly', 1500, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_19 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8004, 55, 'Monthly', 1500, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_20 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8005, 56, 'Monthly', 150, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_21 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8006, 57, 'Monthly', 90, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_22 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8007, 58, 'Monthly', 33, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_23 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8008, 59, 'Monthly', 20, 1, '2023-03-01');";
    public static final String INSERT_USER_PAYS_24 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8009, 60, 'Monthly', 20, 1, '2023-03-01');";

    // April
    public static final String INSERT_USER_PAYS_25 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8001, 61, 'Monthly', 10, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_26 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8002, 62, 'Monthly', 10, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_27 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8003, 63, 'Monthly', 50000, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_28 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8004, 64, 'Monthly', 50000, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_29 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8005, 65, 'Monthly', 10000, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_30 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8006, 66, 'Monthly', 10000, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_31 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8007, 67, 'Monthly', 1000, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_32 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8008, 68, 'Monthly', 1000, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_33 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8009, 69, 'Monthly', 500, 1, '2023-04-01');";
    public static final String INSERT_USER_PAYS_34 =
            "INSERT INTO user_pays (user_id, transac_id, plan, amount, num_months, start_date) " +
                    "VALUES(8010, 70, 'Monthly', 480, 1, '2023-04-01');";
    // endregion
    //Song historical data
    public static final String INSERT_HISTORICAL_SONG_COUNT_JAN_23_1 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1001, 4001, 10,'2023-01-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_FEB_23_1 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1001, 4001, 20,'2023-02-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_MAR_23_1 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1001, 4001, 30,'2023-03-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_JAN_23_2 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1002, 4001, 100,'2023-01-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_FEB_23_2 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1002, 4001, 200,'2023-02-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_MAR_23_2 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1002, 4001, 300,'2023-03-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_JAN_23_3 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1003, 4002, 1000,'2023-01-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_FEB_23_3 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1003, 4002, 2000,'2023-02-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_MAR_23_3 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1003, 4002, 3000,'2023-03-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_JAN_23_4 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1004, 4002, 10000,'2023-01-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_FEB_23_4 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1004, 4002, 20000,'2023-02-01');";
    public static final String INSERT_HISTORICAL_SONG_COUNT_MAR_23_4 = "INSERT INTO Historical_Song_Count(song_id, album_id, play_count, month) VALUES (1004, 4002, 30000,'2023-03-01');";



}
