package com.nhnacademy.customerservice.repository.answer;

import com.nhnacademy.customerservice.domain.answer.Answer;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {

    private final DbConnectionThreadLocal dbConnectionThreadLocal;

    @Override
    public Optional<Answer> getAnswerByInquiryId(long inquiryId) {
        Connection connection = dbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM cs_answers WHERE inquiry_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, inquiryId);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(new Answer(
                        rs.getLong("inquiry_id"),
                        rs.getString("writer_id"),
                        rs.getString("answer_content"),
                        rs.getTimestamp("answer_createdAt").toLocalDateTime()
                ));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int saveAnswer(Answer answer) {
        Connection connection = dbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO cs_answers (inquiry_id, writer_id, answer_content, answer_createdAt) VALUES (?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, answer.getInquiryId());
            preparedStatement.setString(2, answer.getManagerId());
            preparedStatement.setString(3, answer.getContent());
            preparedStatement.setString(4, answer.getCreateAt().toString());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByInquiryId(long inquiryId) {
        Connection connection = dbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM cs_answers WHERE inquiry_id = ?";
        int count = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, inquiryId);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
