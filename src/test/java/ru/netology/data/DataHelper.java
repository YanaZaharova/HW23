package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidPassword() {
        Faker faker = new Faker();
        var invPass = faker.internet().password();
        return new AuthInfo("vasya", invPass);
    }

    public static String getErrorMessage() {
        return " Ошибка Ошибка! Неверно указан логин или пароль";
    }

    public static String getBlockedMessage() {
        return "Личный кабинет заблокирован";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }
}