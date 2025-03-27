package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AppDeadlineTest {

    @BeforeEach
    public void openAndLogin() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanDatabase() {
        SQLHelper.cleanAllData();
    }

    @Test
    void shouldGetCodeAndLogIn() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(SQLHelper.getAuthCode());
    }

    @Test
    void shouldBlockIfPasswordIsInvalid() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPassword();
        loginPage.invalidLogin(authInfo);
        loginPage.getInvalidDataMessage();
        loginPage.cleanInputFields();
        loginPage.invalidLogin(authInfo);
        loginPage.getInvalidDataMessage();
        loginPage.cleanInputFields();
        loginPage.invalidLogin(authInfo);
        loginPage.getBlockedMessage();
    }
}
