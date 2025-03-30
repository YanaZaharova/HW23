package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.*;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getAuthCode() {
        var code = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection()) {
            return runner.query(conn, code, new BeanHandler<>(DataHelper.VerificationCode.class));
        }
    }

    @SneakyThrows
    public static void cleanAllData() {
        var auth = "DELETE FROM auth_codes;";
        var transactions = "DELETE FROM card_transactions;";
        var card = "DELETE FROM cards;";
        var user = "DELETE FROM users;";
        try (var conn = getConnection()) {
            runner.update(conn, auth);
            runner.update(conn, transactions);
            runner.update(conn, card);
            runner.update(conn, user);
        }
    }

    @SneakyThrows
    public static void cleanCodes() {
        try (var conn = getConnection()) {
            runner.execute(conn,"DELETE FROM auth_codes");
        }
    }
}

