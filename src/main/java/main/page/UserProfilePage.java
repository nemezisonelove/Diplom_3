package main.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class UserProfilePage {

    // Кнопка "Профиль"
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement userProfileButton;

    // Кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    // Логотип
    @FindBy(how = How.XPATH, using = "//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoPicture;

    // Кнопка "Выход"
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @Step("Кликнуть на кнопку 'Выход'")
    public SignInPage clickLogOutButton() {
        logoutButton.click();
        return page(SignInPage.class);
    }

    @Step("Проверить, что заголовок 'Профиль' существует")
    public boolean isUserProfileHeaderExist() {
        userProfileButton.shouldBe(Condition.visible);
        return userProfileButton.exists();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickConstructorButton() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("Клик по Логотипу")
    public MainPage clickLogoPicture() {
        logoPicture.click();
        return page(MainPage.class);
    }
}