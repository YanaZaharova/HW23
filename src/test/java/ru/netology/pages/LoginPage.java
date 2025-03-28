package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement invalidDataMessage = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo loginInfo) {
        loginField.setValue(loginInfo.getLogin());
        passwordField.setValue(loginInfo.getPassword());
        loginButton.click();
    }

    public void getInvalidDataMessage() {
        invalidDataMessage.shouldBe(visible).shouldHave(text(" Ошибка Ошибка! Неверно указан логин или пароль"));

    }

    public void getBlockedMessage() {
        invalidDataMessage.shouldBe(visible).shouldHave(text("Личный кабинет заблокирован"));
    }

    public void cleanInputFields() {
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }
}
