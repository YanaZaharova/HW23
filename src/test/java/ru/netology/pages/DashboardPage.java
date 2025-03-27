package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private final SelenideElement heading = $(".heading_size_xl");

    public DashboardPage() {
        heading.shouldBe(visible);
    }
}
