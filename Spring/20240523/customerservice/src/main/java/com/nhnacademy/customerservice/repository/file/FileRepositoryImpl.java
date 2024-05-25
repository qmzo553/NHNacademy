package com.nhnacademy.customerservice.repository.file;

import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {

    @Override
    public List<File> getFilesByInquiryId(long inquiryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from cs_files where file_inquiryId = ?";
        List<File> files = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, inquiryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                files.add(new File(
                   rs.getString("file_name"),
                   rs.getLong("file_inquiryId")
                ));
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return files;
    }

    @Override
    public int save(File file) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into cs_files (file_name, file_inquiryId) values (?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, file.getFileName());
            preparedStatement.setLong(2, file.getInquiryId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
