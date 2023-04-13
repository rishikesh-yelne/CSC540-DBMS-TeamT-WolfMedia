package edu.ncsu.csc540.s23.backend.constants;

public class OperationQuery {
    public static final String GET_ALL_USERS = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User";
    public static final String GET_ALL_ARTISTS = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, rlabel_id as recordLabelId, primary_genre_id, status, type, artist_country FROM User u JOIN Artist a ON u.user_id=a.user_id;";
    public static final String GET_ALL_PODCAST_HOSTS = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, city FROM User u JOIN Podcast_Host p ON u.user_id=p.user_id;";
    public static final String GET_ALL_GENRES = "SELECT genre_id, name, gtype as genreType FROM Genre";
    public static final String GET_ALL_GUESTSPEAKERS = "SELECT gspeaker_id as guestSpeakerId, gfirst_name as guestSpeakerFirstName, glast_name as guestSpeakerLastName, gphone_num as guestSpeakerPhoneNum, gemail as guestSpeakerEmail, gcity as guestSpeakerCity FROM Guest_Speaker ";
    public static final String GET_ALL_SPONSORS = "SELECT sponsor_id as sponsorId, sfirst_name as sponsorFirstName, slast_name as sponsorLastName, organization, semail as sponsorEmail, scity as sponsorCity FROM Sponsor";
    public static final String GET_ALL_SONGS = "SELECT song_id as songId, album_id as albumId, title, duration, track_no as trackNo, release_date as releaseDate, release_country as releaseCountry, language, royalty_rate as royalty_rate FROM Song";
    public static final String GET_SONGS_BY_ARTIST_ID =
            "SELECT song.song_id as songId, song.album_id as albumId, title, duration, track_no as trackNo, release_date as releaseDate, release_country as releaseCountry, language, royalty_rate as royalty_rate, pb.is_main as isMainArtist " +
                    "FROM Song song JOIN performed_by pb ON song.song_id = pb.song_id AND song.album_id = pb.album_id WHERE pb.user_id = ?";
    public static final String GET_SONGS_BY_ALBUM_ID =
            "SELECT song_id as songId, album_id as albumId, title, duration, track_no as trackNo, release_date as releaseDate, release_country as releaseCountry, language, royalty_rate as royalty_rate " +
                    "FROM Song WHERE album_id = ?";
    public static final String GET_ALL_ALBUMS = "SELECT album_id as albumId, album_name as albumName, release_year as releaseYear, edition FROM Album";
    public static final String GET_ALL_RECORD_LABELS = "SELECT rlabel_id as recordLabelId, rlabel_name as recordLabelName FROM Record_Label";
    public static final String GET_ALL_PODCASTS = "SELECT podcast_id, user_id as podcastHostId, pname as podcastName, planguage as podcastLanguage, country, flat_fee FROM Podcast";
    public static final String GET_PODCAST_BY_PODCAST_HOST_ID = "SELECT podcast_id, pname as podcastName, planguage as podcastLanguage, country, user_id as podcastHostId, flat_fee FROM Podcast WHERE user_id=?;";
    public static final String GET_ALL_PODCAST_EPISODES = "SELECT pepi_id as podcastEpisodeId, podcast_id, epi_title as episodeTitle, prelease_date as podcastReleaseDate, pduration as podcastDuration, adv_count as advertisementCount, episode_no, bonus_rate FROM Podcast_Episode";
    public static final String GET_PODCAST_EPISODES_BY_PODCAST = "SELECT pepi_id as podcastEpisodeId, podcast_id, epi_title as episodeTitle, prelease_date as podcastReleaseDate, pduration as podcastDuration, adv_count as advertisementCount, episode_no, bonus_rate FROM Podcast_Episode WHERE podcast_id=?";
    public static final String GET_PODCAST_EPISODES_BY_PODCAST_TILL_MONTH_YEAR =
            "SELECT pepi_id as podcastEpisodeId, podcast_id, epi_title as episodeTitle, prelease_date as podcastReleaseDate, pduration as podcastDuration, adv_count as advertisementCount, episode_no, bonus_rate " +
                    "FROM Podcast_Episode WHERE podcast_id=? AND month(prelease_date)<= ? AND year(prelease_date)<= ?";
    public static final String INSERT_USER = "INSERT INTO User (first_name, last_name, email_id, phone_num, reg_date) VALUES(?, ?, ?, ?, ?)";
    public static final String INSERT_ARTIST = "INSERT INTO Artist (user_id, rlabel_id, primary_genre_id, status, type, artist_country) VALUES(?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PODCAST_HOST = "INSERT INTO Podcast_Host (user_id, city) VALUES(?, ?)";
    public static final String GET_USER_BY_ID = "SELECT user_id, first_name, last_name, email_id, phone_num, reg_date FROM User WHERE user_id=?;";
    public static final String GET_GENRE_BY_ID = "SELECT genre_id, name, gtype as genreType FROM Genre WHERE genre_id=?;";
    public static final String GET_GUEST_SPEAKER_BY_ID = "SELECT gspeaker_id, gfirst_name, glast_name, gphone_num, gemail, gcity FROM Guest_Speaker WHERE gspeaker_id=?;";
    public static final String GET_SPONSOR_BY_ID = "SELECT sponsor_id, sfirst_name, slast_name, organization, semail, scity FROM Sponsor WHERE sponsor_id=?;";
    public static final String GET_SONG_BY_ID = "SELECT song_id, album_id, title, duration, track_no, release_date, release_country, language, royalty_rate FROM Song WHERE song_id=? AND album_id=?;";
    public static final String GET_ALBUM_BY_ID = "SELECT album_id, album_name, release_year, edition FROM Album WHERE album_id=?;";
    public static final String GET_ARTIST_BY_ID = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, rlabel_id as recordLabelId, primary_genre_id, status, type, artist_country FROM User u JOIN Artist a ON u.user_id=a.user_id WHERE u.user_id=?;";
    public static final String GET_PODCAST_HOST_BY_ID = "SELECT u.user_id as user_id, first_name, last_name, email_id, phone_num, reg_date, city FROM User u JOIN Podcast_Host p ON u.user_id=p.user_id WHERE u.user_id=?;";
    public static final String GET_PODCAST_BY_ID = "SELECT podcast_id, user_id as podcastHostId, pname as podcastName, planguage as podcastLanguage, country, flat_fee FROM Podcast WHERE podcast_id=?;";
    public static final String GET_PODCAST_EPISODE_BY_ID = "SELECT pepi_id, podcast_id, epi_title as episodeTitle, prelease_date as podcastReleaseDate, pduration as podcastDuration, adv_count as advertisementCount, episode_no, bonus_rate FROM Podcast_Episode WHERE pepi_id=? AND podcast_id=?;";
    public static final String GET_RECORD_LABEL_BY_ID = "SELECT rlabel_id as recordLabelId, rlabel_name as recordLabelName FROM Record_Label WHERE rlabel_id=?;";
    public static final String INSERT_GENRE = "INSERT INTO Genre (name, gtype) VALUES (?, ?)";
    public static final String INSERT_GUESTSPEAKER = "INSERT INTO Guest_Speaker (gfirst_name, glast_name, gphone_num, gemail, gcity) VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_SPONSOR = "INSERT INTO Sponsor (sfirst_name, slast_name, organization, semail, scity) VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_SONG = "INSERT INTO Song (album_id, title, duration, track_no, release_date, release_country, language, royalty_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_ALBUM = "INSERT INTO Album (album_name, release_year, edition) VALUES (?, ?, ?)";
    public static final String INSERT_RECORD_LABEL = "INSERT INTO Record_Label (rlabel_name) VALUES (?)";
    public static final String INSERT_PODCAST = "INSERT INTO Podcast (user_id, pname, planguage, country) VALUES (?, ?, ?, ?)";
    public static final String INSERT_PODCAST_EPISODE = "INSERT INTO Podcast_Episode (podcast_id, epi_title, prelease_date, pduration, adv_count, episode_no) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_SONG = "UPDATE Song SET title=?, duration=?, track_no=?, release_date=?, release_country=?, language=?, royalty_rate=? WHERE song_id=? AND album_id = ?";
    public static final String UPDATE_ARTIST = "UPDATE User u JOIN Artist a ON u.user_id=a.user_id SET first_name=?, last_name=?, email_id=?, phone_num=?, reg_date=?, rlabel_id=?, primary_genre_id=?, status=?, type=?, artist_country=? WHERE a.user_id=?";
    public static final String UPDATE_PODCAST_HOST = "UPDATE User u JOIN Podcast_Host ph ON u.user_id=ph.user_id SET first_name=?, last_name=?, email_id=?, phone_num=?, reg_date=?, city=? WHERE ph.user_id=?";
    public static final String DELETE_SONG = "DELETE FROM Song WHERE song_id=?;";
    public static final String ASSIGN_PODCAST_HOST = "UPDATE Podcast SET user_id=? WHERE podcast_id=?;";
    public static final String ASSIGN_ARTIST_TO_SONG = "INSERT INTO performed_by (song_id, album_id, user_id, is_main) VALUES (?, ?, ?, ?)";
    public static final String ASSIGN_PRIMARY_GENRE_TO_ARTIST="UPDATE User u JOIN Artist a ON u.user_id=a.user_id SET primary_genre_id=? WHERE a.user_id=?;";
    public static final String ASSIGN_GUEST_SPEAKER_TO_PODCAST_EPISODE = "INSERT INTO episode_has (gspeaker_id ,pepi_id, podcast_id) VALUES (?, ?, ?)";


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
    public static final String UPDATE_GENRE =
            "UPDATE Genre " +
                    "SET name = ?, gtype = ? " +
                    "WHERE genre_id = ?;";
    public static final String UPDATE_GENRE_NAME =
            "UPDATE Genre " +
                    "SET name = ? " +
                    "WHERE genre_id = ?";
    public static final String UPDATE_PODCAST =
            "UPDATE Podcast " +
                    "SET user_id =?, pname = ?, planguage = ?, country = ? " +
                    "WHERE podcast_id = ?;";

    public static final String UPDATE_RECORD_LABEL =
            "UPDATE Record_Label " +
            "SET rlabel_name = ? " +
            "WHERE rlabel_id = ?;";
    public static final String UPDATE_PODCAST_EPISODE =
            "UPDATE Podcast_Episode " +
            "SET podcast_id = ?, epi_title = ?, prelease_date = ?, " +
            "pduration = ?, adv_count = ?, episode_no = ? " +
            "WHERE pepi_id = ?;";
    public static final String UPDATE_PODCAST_EPISODE_PODCAST_ID =
            "UPDATE Podcast_Episode " +
            "SET podcast_id = ? " +
            "WHERE pepi_id = ?";
    public static final String CHECK_IF_RECORD_PAYMENT_EXISTS =
            "SELECT count(pr.transac_id) > 0 FROM pays_record pr JOIN Accounts acc ON pr.transac_id=acc.transac_id " +
                    "WHERE pr.rlabel_id=? AND pr.song_id=? AND pr.album_id=? AND month(acc.payment_date)=? AND year(acc.payment_date)=?;";
    public static final String CHECK_IF_ARTIST_PAYMENT_EXISTS =
            "SELECT count(pa.transac_id) > 0 FROM pays_artist pa JOIN Accounts acc ON pa.transac_id=acc.transac_id " +
                    "WHERE pa.user_id=? AND pa.song_id=? AND pa.album_id=? AND month(acc.payment_date)=? AND year(acc.payment_date)=?;";
    public static final String CHECK_IF_PODCAST_HOST_PAYMENT_EXISTS =
            "SELECT count(pp.transac_id) > 0 FROM pays_ph pp JOIN Accounts acc ON pp.transac_id=acc.transac_id " +
                    "WHERE pp.user_id=? AND pp.podcast_id=? AND pp.pepi_id=? AND month(acc.payment_date)=? AND year(acc.payment_date)=?;";
    public static final String INSERT_ACCOUNTS =
            "INSERT INTO Accounts (amount, payer, payee, payment_date) VALUES (?, ?, ?, ?);";
    public static final String PAY_RECORD_LABEL =
            "INSERT INTO pays_record (transac_id, rlabel_id, song_id, album_id) VALUES (?, ?, ?, ?);";
    public static final String PAY_ARTIST =
            "INSERT INTO pays_artist (transac_id, rlabel_id, user_id, song_id, album_id) VALUES (?, ?, ?, ?, ?);";
    public static final String PAY_PODCAST_HOST =
            "INSERT INTO pays_ph (transac_id, podcast_id, pepi_id, user_id) VALUES(?, ?, ?, ?);";
    public static final String GET_SONG_PLAY_COUNT_FOR_MONTH =
            "SELECT COUNT(*) FROM listens_to " +
                    "WHERE song_id=? AND album_id=? AND month(timestamp)=? AND year(timestamp)=?;";
    public static final String GET_SONG_ARTIST_COUNT =
            "SELECT COUNT(*) FROM performed_by " +
                    "WHERE song_id=? AND album_id=?;";
    public static final String GET_SONGS_BY_RECORD_LABEL_ID =
            "SELECT s.song_id as songId, s.album_id as albumId, s.title, s.duration, s.track_no as trackNo, s.release_date as releaseDate, s.release_country as releaseCountry, s.language, s.royalty_rate as royalty_rate " +
                    "FROM Song s " +
                    "JOIN performed_by pb ON s.song_id = pb.song_id AND s.album_id = pb.album_id " +
                    "JOIN Artist a ON pb.user_id = a.user_id " +
                    "WHERE a.rlabel_id=?;";
    public static final String DELETE_PODCAST_EPISODE = "DELETE FROM Podcast_Episode WHERE pepi_id = ?;";
    public static final String DELETE_ARTIST = "DELETE FROM Artist WHERE user_id = ?;";
    public static final String DELETE_PODCAST_HOST = "DELETE FROM Podcast_Host WHERE user_id = ?;";
    public static final String ADD_SPONSOR_TO_PODCAST = "INSERT INTO sponsors (podcast_id, sponsor_id) VALUES(?, ?)";
    public static final String GET_SPONSORS_OF_PODCAST =
            "SELECT s.sponsor_id as sponsorId, sfirst_name as sponsorFirstName, slast_name as sponsorLastName, organization, semail as sponsorEmail, scity as sponsorCity " +
                    "FROM Sponsor s JOIN sponsors ss ON s.sponsor_id = ss.sponsor_id WHERE ss.podcast_id = ?";
    public static final String RATE_PODCAST =
            "INSERT INTO rates (podcast_id, user_id, rating) VALUES(?, ?, ?);";
    public static final String GET_PODCAST_RATING_FOR_PODCAST =
            "SELECT avg(rating) FROM rates WHERE podcast_id=?;";
    public static final String GET_PODCAST_RATINGS_FOR_PODCAST =
            "SELECT user_id, podcast_id, rating FROM rates WHERE podcast_id=?;";
    public static final String GET_PODCAST_RATINGS =
            "SELECT avg(rating) as rating, podcast_id FROM rates GROUP BY podcast_id;";
}
