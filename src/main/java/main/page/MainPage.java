package main.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // Кнопка для входа в аккаунт
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement enterAccountButton;

    // Кнопка оформления заказа
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Оформить заказ')]")
    private SelenideElement createOrderButton;

    // Кнопка Личного Кабинета
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
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
        enterAccountButton.shouldBe(Condition.visible).click(); // Убедиться, что кнопка видима
        return page(SignInPage.class);
    }

    @Step("Проверка наличия кнопки Оформить заказ")
    public boolean isCreateOrderButtonExist() {
        createOrderButton.shouldBe(Condition.visible);
        return createOrderButton.exists();
    }

    @Step("Клик по кнопке Личный кабинет до авторизации")
    public SignInPage clickAccountButtonBeforeAuthorization() {
        accountButton.shouldBe(Condition.visible).click();
        return page(SignInPage.class);
    }

    @Step("Клик по кнопке Личный кабинет после авторизации")
    public UserProfilePage clickAccountButtonAfterAuthorization() {
        accountButton.shouldBe(Condition.visible).click();
        return page(UserProfilePage.class);
    }

    @Step("Проверка наличия заголовка Конструктор")
    public boolean isConstructorHeaderExist() {
        constructorHeader.shouldBe(Condition.visible);
        return constructorHeader.exists();
    }

    @Step("Клик по разделу Булки")
    public MainPage clickBunsButton() {
        bunsButton.shouldBe(Condition.visible).click(); // Убедиться, что кнопка видима и доступна для клика
        return this;
    }

    @Step("Клик по разделу Соусы")
    public MainPage clickSaucesButton() {
        sauceButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Клик по разделу Начинки")
    public MainPage clickFillingsButton() {
        fillingsButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Проверка отображения заголовка Булки")
    public boolean isBunsHeaderDisplayed() {
        return sectionIngredients.shouldBe(Condition.visible).getText().contentEquals("Булки");
    }

    @Step("Проверка отображения заголовка Соусы")
    public boolean isSaucesHeaderDisplayed() {
        return sectionIngredients.shouldBe(Condition.visible).getText().contentEquals("Соусы");
    }

    @Step("Проверка отображения заголовка Начинки")
    public boolean isFillingsHeaderDisplayed() {
        return sectionIngredients.shouldBe(Condition.visible).getText().contentEquals("Начинки");
    }

    @Step("Прокрутка до заголовка Булки")
    public MainPage scrollToBunsHeader() {
        bunsHeader.scrollIntoView(true);  // Прокрутка к разделу Булки
        return this;
    }

    @Step("Прокрутка до заголовка Соусы")
    public MainPage scrollToSaucesHeader() {
        sauceHeader.scrollIntoView(true);  // Прокрутка к разделу Соусы
        return this;
    }

    @Step("Прокрутка до заголовка Начинки")
    public MainPage scrollToFillingsHeader() {
        fillingsHeader.scrollIntoView(true);  // Прокрутка к разделу Начинки
        return this;
    }

    @Step("Проверка наличия сообщения об ошибке")
    public boolean isErrorMessageExist() {
        return errorMessage.shouldBe(Condition.visible).exists();
    }

    // Методы для использования JavaScript для кликов, если обычный клик не срабатывает
    @Step("Клик по разделу Булки с помощью JavaScript")
    public MainPage clickBunsButtonWithJS() {
        executeJavaScript("arguments[0].click();", bunsButton);
        return this;
    }

    @Step("Клик по разделу Соусы с помощью JavaScript")
    public MainPage clickSaucesButtonWithJS() {
        executeJavaScript("arguments[0].click();", sauceButton);
        return this;
    }

    @Step("Клик по разделу Начинки с помощью JavaScript")
    public MainPage clickFillingsButtonWithJS() {
        executeJavaScript("arguments[0].click();", fillingsButton);
        return this;
    }
}