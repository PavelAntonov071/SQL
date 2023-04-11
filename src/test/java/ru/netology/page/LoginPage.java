package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement loginInput = $x("//span[@data-test-id='login']//input");
    private final SelenideElement loginInputEmptyNotification = $x(
            "//span[@data-test-id='login']//span[@class='input__sub']");
    private final SelenideElement passwordInput = $x("//span[@data-test-id='password']//input");
    private final SelenideElement passwordInputEmptyNotification = $x(
            "//span[@data-test-id='password']//span[@class='input__sub']");
    private final SelenideElement loginButton = $x("//button[@data-test-id='action-login']");
    private final SelenideElement errorNotification = $x("//div[@data-test-id='error-notification']");
    private final SelenideElement errorButton = $x("//div[@data-test-id='error-notification']/button");

    public LoginPage() {
        loginInput.should(visible);
        passwordInput.should(visible);
        loginButton.should(visible);
        errorNotification.should(hidden);
        errorButton.should(hidden);
    }

    public void insert(String login, String password) {
        loginInput.val(login);
        passwordInput.val(password);
        loginButton.click();
    }

    public VerifyPage success() {
        errorNotification.should(hidden);
        errorButton.should(hidden);
        return new VerifyPage();
    }

    public void failed() {
        errorNotification.should(visible);
        errorNotification.$x(".//div[@class='notification__content']").
                should(text("Ошибка! " + "Неверно указан логин или пароль"));
        errorButton.click();
        errorNotification.should(hidden);
    }

    public void blocked() {
        errorNotification.should(visible);
        errorNotification.$x(".//div[@class='notification__content']").
                should(text("Ошибка! " + "Пользователь заблокирован"));
        errorButton.click();
        errorNotification.should(hidden);
    }

    public void emptyLogin() {
        loginInputEmptyNotification.should(text("Поле обязательно для заполнения"));
    }

    public void emptyPassword() {
        passwordInputEmptyNotification.should(text("Поле обязательно для заполнения"));
    }
}
