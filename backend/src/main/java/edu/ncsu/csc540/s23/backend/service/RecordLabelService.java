package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.constants.OperationQuery;
import edu.ncsu.csc540.s23.backend.model.RecordLabel;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RecordLabelService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        try {
            return jdbcTemplate.getDataSource().getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public List<RecordLabel> getAllRecordLabels() {
        return jdbcTemplate.query(OperationQuery.GET_ALL_RECORD_LABELS, BeanPropertyRowMapper.newInstance(RecordLabel.class));
    }

    public Long createNewRecordLabel(RecordLabel recordLabel) {
        try {
            Connection connection = getConnection();
            String[] generatedColumns = { "rlabel_id" };
            PreparedStatement statement = connection.prepareStatement(OperationQuery.INSERT_RECORD_LABEL, generatedColumns);
            statement.setString(1,recordLabel.getRecordLabelName());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected <= 0) throw new SQLException("Record Label creation failed");

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()) {
                return result.getLong(1);
            }

            return -1L;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public RecordLabel getRecordLabel(Long id) {
        return jdbcTemplate.queryForObject(OperationQuery.GET_RECORD_LABEL_BY_ID, (rs, rowNum) -> {
            RecordLabel recordLabel = new RecordLabel();
            recordLabel.setRecordLabelId(rs.getLong(1));
            recordLabel.setRecordLabelName(rs.getString(2));
            return recordLabel;
        }, id);
    }

    //update record label
    public boolean updateRecordLabel(RecordLabel recordLabel) {
        return jdbcTemplate.update(OperationQuery.UPDATE_RECORD_LABEL, recordLabel.getRecordLabelName(), recordLabel.getRecordLabelId()) >0;
    }
}
