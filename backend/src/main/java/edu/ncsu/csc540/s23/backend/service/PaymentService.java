package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.dto.ArtistPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.PodcastHostPaymentDTO;
import edu.ncsu.csc540.s23.backend.model.dto.RecordLabelPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
