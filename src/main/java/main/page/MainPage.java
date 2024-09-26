package main.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // Кнопка для входа в аккаунт
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']") //
    private SelenideElement enterAccountButton;

    // Кнопка оформления заказа
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Оформить заказ')]") //
    private SelenideElement createOrderButton;

    // Кнопка Личного Кабинета
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']") //
    private SelenideElement accountButton;

    // Заголовок "Соберите бургер"
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement constructorHeader;

    // Раздел "Булки"
    @FindBy(how = How.XPATH, using = "//div[span[text()='Булки']]")
    private SelenideElement bunsButton;

    // Раздел "Соусы"
    @FindBy(how = How.XPATH, using = "//div[span[text()='Соусы']]")
    private SelenideElement sauceButton;

    // Раздел "Начинки"
    @FindBy(how = How.XPATH, using = "//*[text()='Начинки']")
    private SelenideElement fillingsButton;

    // Заголовки разделов
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement bunsHeader;

    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement sauceHeader;

    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement fillingsHeader;

    // Выбранный раздел ингредиентов
    @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
    private SelenideElement sectionIngredients;

    // Контейнер конструктора
    @FindBy(how = How.XPATH, using = "//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']")
    private SelenideElement constructorContainer;

    // Сообщение об ошибке
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement errorMessage;

    @Step("Клик по кнопке Войти в аккаунт")
    public SignInPage clickEnterAccountButton() {
        enterAccountButton.click();
        return page(SignInPage.class);
    }

    @Step("Проверка наличия кнопки Оформить заказ")
    public boolean isCreateOrderButtonExist() {
        createOrderButton.shouldBe(Condition.visible);
        return createOrderButton.exists();
    }

    @Step("Клик по кнопке Личный кабинет до авторизации")
    public SignInPage clickAccountButtonBeforeAuthorization() {
        accountButton.click();
        return page(SignInPage.class);
    }

    @Step("Клик по кнопке Личный кабинет после авторизации")
    public UserProfilePage clickAccountButtonAfterAuthorization() {
        accountButton.click();
        return page(UserProfilePage.class);
    }

    @Step("Проверка наличия заголовка Конструктор")
    public boolean isConstructorHeaderExist() {
        return constructorHeader.exists();
    }

    @Step("Клик по разделу Булки")
    public MainPage clickBunsButton() {
        bunsButton.click();
        return this;
    }

    @Step("Клик по разделу Соусы")
    public MainPage clickSaucesButton() {
        sauceButton.click();
        return this;
    }

    @Step("Клик по разделу Начинки")
    public MainPage clickFillingsButton() {
        fillingsButton.click();
        return this;
    }

    @Step("Проверка отображения заголовка Булки")
    public boolean isBunsHeaderIsDisplayed() {
        return sectionIngredients.getText().contentEquals("Булки");
    }

    @Step("Проверка отображения заголовка Соусы")
    public boolean isSaucesHeaderIsDisplayed() {
        return sectionIngredients.getText().contentEquals("Соусы");
    }

    @Step("Проверка отображения заголовка Начинки")
    public boolean isFillingsHeaderIsDisplayed() {
        return sectionIngredients.getText().contentEquals("Начинки");
    }

    @Step("Прокрутка до заголовка Булки")
    public MainPage scrollToBunsHeader() {
        constructorContainer.click();
        bunsHeader.scrollIntoView(false);
        return this;
    }

    @Step("Прокрутка до заголовка Соусы")
    public MainPage scrollToSaucesHeader() {
        constructorContainer.click();
        sauceHeader.scrollIntoView(false);
        return this;
    }

    @Step("Прокрутка до заголовка Начинки")
    public MainPage scrollToFillingsHeader() {
        constructorContainer.click();
        fillingsHeader.scrollIntoView(false);
        return this;
    }

    @Step("Проверка наличия сообщения об ошибке")
    public boolean isErrorMessageExist() {
        return errorMessage.exists();
    }
}