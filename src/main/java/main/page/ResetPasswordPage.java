package main.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class ResetPasswordPage {

    // URL страницы восстановления пароля
    public final static String RESET_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Ссылка "Войти" на странице восстановления пароля
    @FindBy(xpath = "//a[text()='Войти']")
    private SelenideElement linkResetPasswordToLoginForm;

    @Step("Кликнуть на кнопку 'Войти' на странице восстановления пароля")
    public SignInPage clickResetPasswordButton() {
        linkResetPasswordToLoginForm.click();
        return page(SignInPage.class);
    }
}