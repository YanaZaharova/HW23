package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanCodes;

public class AppDeadlineTest {

    @BeforeEach
    public void openAndLogin() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanDatabase() {
        SQLHelper.cleanAllData();
    }

    @AfterEach
    void cleanAuthCodes() {
        cleanCodes();
    }

    @Test
    void shouldGetCodeAndLogIn() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(String.valueOf(SQLHelper.getAuthCode()));
    }

    @Test
    void shouldBlockIfPasswordIsInvalid() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidPassword();
        loginPage.logIn(authInfo);
        loginPage.getErrorOrBlockedMessage(DataHelper.getErrorMessage());
        loginPage.cleanInputFields();
        loginPage.logIn(authInfo);
        loginPage.getErrorOrBlockedMessage(DataHelper.getErrorMessage());
        loginPage.cleanInputFields();
        loginPage.logIn(authInfo);
        loginPage.getErrorOrBlockedMessage(DataHelper.getBlockedMessage());
    }
}
