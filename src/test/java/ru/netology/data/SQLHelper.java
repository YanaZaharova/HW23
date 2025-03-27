package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static void cleanAllData() {
        val auth = "DELETE FROM auth_codes;";
        val transactions = "DELETE FROM card_transactions;";
        val card = "DELETE FROM cards;";
        val user = "DELETE FROM users;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        ) {
            runner.update(conn, auth);
            runner.update(conn, transactions);
            runner.update(conn, card);
            runner.update(conn, user);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static String getAuthCode() {
        try (val conn = getConnection()) {
            try (Statement statement = conn.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT code FROM auth_codes;")) {
                    while (resultSet.next()) return resultSet.getString("code");
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
