package main.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ResetPasswordPage {

    // URL страницы восстановления пароля
    public final static String RESET_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Ссылка "Войти" на странице восстановления пароля
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement resetPasswordToLoginLink;

    @Step("Кликнуть на кнопку 'Войти' на странице восстановления пароля")
    public SignInPage clickResetPasswordButton() {
        resetPasswordToLoginLink.shouldBe(Condition.visible).click();  // Проверить, что ссылка видима и доступна
        return page(SignInPage.class);
    }
}