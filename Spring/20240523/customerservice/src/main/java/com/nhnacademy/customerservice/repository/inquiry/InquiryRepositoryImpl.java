package com.nhnacademy.customerservice.repository.inquiry;

import com.nhnacademy.customerservice.domain.inquiry.Inquiry;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class InquiryRepositoryImpl implements InquiryRepository {

    @Override
    public List<Inquiry> getInquiriesByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM cs_inquiries WHERE inquiry_writerId = ?";
        List<Inquiry> inquiries = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                inquiries.add(new Inquiry(
                        rs.getLong("inquiry_id"),
                        rs.getString("inquiry_title"),
                        rs.getString("inquiry_content"),
                        rs.getTimestamp("inquiry_createdAt").toLocalDateTime(),
                        rs.getString("inquiry_writerId"),
                        Inquiry.Category.valueOf(rs.getString("inquiry_category"))
                ));
            }

            rs.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        return inquiries;
    }

    @Override
    public Optional<Inquiry> getInquiryByInquiryId(Long inquiryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM cs_inquiries WHERE inquiry_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, inquiryId);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(new Inquiry(
                        rs.getLong("inquiry_id"),
                        rs.getString("inquiry_title"),
                        rs.getString("inquiry_content"),
                        rs.getTimestamp("inquiry_createdAt").toLocalDateTime(),
                        rs.getString("inquiry_writerId"),
                        Inquiry.Category.valueOf(rs.getString("inquiry_category"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Inquiry> getInquiriesNoAnswerYet() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * " +
                "FROM cs_inquiries i " +
                "LEFT JOIN cs_answers a ON i.inquiry_id = a.inquiry_id " +
                "WHERE a.inquiry_id IS NULL;";

        List<Inquiry> inquiries = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                inquiries.add(new Inquiry(
                        rs.getLong("inquiry_id"),
                        rs.getString("inquiry_title"),
                        rs.getString("inquiry_content"),
                        rs.getTimestamp("inquiry_createdAt").toLocalDateTime(),
                        rs.getString("inquiry_writerId"),
                        Inquiry.Category.valueOf(rs.getString("inquiry_category"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return inquiries;
    }

    @Override
    public int saveInquiry(Inquiry inquiry) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO cs_inquiries (inquiry_title, inquiry_content, inquiry_createdAt, inquiry_writerId, inquiry_category) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, inquiry.getTitle());
            preparedStatement.setString(2, inquiry.getContent());
            preparedStatement.setString(3, inquiry.getCreateAt().toString());
            preparedStatement.setString(4, inquiry.getWriterId());
            preparedStatement.setString(5, inquiry.getCategory().name());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getLastInquiryId() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT max(inquiry_id) FROM cs_inquiries";
        long inquiryId = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                inquiryId = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inquiryId;
    }
}
