package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.Song;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PayRecordDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PodcastHostPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.RecordLabelPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Double getPaymentToRecordLabel(Long recordLabelId, int month, int year) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PAYMENT_TO_RL_BY_ID, Double.class, recordLabelId, month, year);
    }

    public Double getPaymentToArtist(Long artistId, int month, int year) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PAYMENT_TO_ARTIST_BY_ID, Double.class, artistId, month, year);
    }

    public Double getPaymentToPodcastHost(Long podcastHostId, int month, int year) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_PAYMENT_TO_PH_BY_ID, Double.class, podcastHostId, month, year);
    }

    public List<RecordLabelPaymentDTO> getPaymentToRecordLabels() {
        return jdbcTemplate.query(OperationQuery.GET_PAYMENTS_TO_RECORD_LABELS, BeanPropertyRowMapper.newInstance(RecordLabelPaymentDTO.class));
    }

    public List<ArtistPaymentDTO> getPaymentToArtists() {
        return jdbcTemplate.query(OperationQuery.GET_PAYMENTS_TO_ARTISTS, BeanPropertyRowMapper.newInstance(ArtistPaymentDTO.class));
    }

    public List<PodcastHostPaymentDTO> getPaymentToPodcastHosts() {
        return jdbcTemplate.query(OperationQuery.GET_PAYMENTS_TO_PODCAST_HOSTS, BeanPropertyRowMapper.newInstance(PodcastHostPaymentDTO.class));
    }

    public String payRecordLabel(PayRecordDTO payment, Optional<Integer> month, Optional<Integer> year) {
        int paymentMonth, paymentYear;
        if (month.isEmpty() || year.isEmpty()) {
            // month and year is empty, make payment for current month and year
            paymentMonth = LocalDate.now().getMonth().getValue();
            paymentYear = LocalDate.now().getYear();
        } else {
            paymentMonth = month.get();
            paymentYear = year.get();
        }
        // first check if there exists a payment for the given song, month and year
        boolean paymentExists = checkIfRecordPaymentExists(
                payment.getRecordLabelId(),
                payment.getSongId(),
                payment.getAlbumId(),
                paymentMonth,
                paymentYear);

        if (paymentExists) return "Payment already done for the month & year : " + Month.of(paymentMonth).name() + " " + paymentYear;

        // payment does not exist, calculate payment for the Song
        return "Payment successful";
    }

    private boolean checkIfRecordPaymentExists(Long recordLabelId, Long songId, Long albumId, int month, int year) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(OperationQuery.CHECK_IF_RECORD_PAYMENT_EXISTS, Boolean.class, recordLabelId, songId, albumId, month, year));
    }
}
