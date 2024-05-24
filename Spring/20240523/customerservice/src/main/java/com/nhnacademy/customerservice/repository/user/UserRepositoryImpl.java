package com.nhnacademy.customerservice.repository.user;

import com.nhnacademy.customerservice.domain.user.User;
import com.nhnacademy.customerservice.exception.UserNotFoundException;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> getUserByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM cs_users WHERE user_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getString("user_id"),
                        rs.getString("user_password"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        User.Role.valueOf(rs.getString("user_role")),
                        rs.getString("user_phone"),
                        rs.getString("user_email")
                ));
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUserIdAndPassword(String userId, String password) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM cs_users WHERE user_id = ? AND user_password = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getString("user_id"),
                        rs.getString("user_password"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        User.Role.valueOf(rs.getString("user_role")),
                        rs.getString("user_phone"),
                        rs.getString("user_email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int saveUser(User user) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO cs_users (user_id, user_password, user_name, user_age, user_role, user_phone, user_email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getEmail());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM cs_users WHERE user_id = ?";
        int count = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }


}
