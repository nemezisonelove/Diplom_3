import api.clients.UserClient;
import api.generator.UserGenerator;
import api.models.Credentials;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.After;
import org.junit.Test;
import manager.TestValue;
import main.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тесты для проверки:
 * - Регистрация пользователя - успешная
 * - Регистрация пользователя - некорректная
 * - Удаление пользователей после создания.
 */
public class RegistrationWebTest {

    private UserClient userClient;

    // Генерация тестовых данных
    protected String name = UserGenerator.getUser().getName();
    protected String email = UserGenerator.getUser().getEmail();
    protected String password = UserGenerator.getUser().getPassword();

    /**
     * Тест на успешную регистрацию пользователя.
     * Ожидается, что после успешной регистрации пользователя будет осуществлен переход на страницу логина.
     */
    @Test
    @DisplayName("Регистрация пользователя - успешная")
    @Description("Успешная регистрация пользователя")
    public void checkSuccessRegistrationPersonalAccountButton() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .clickEnterAccountButton() // Переход на страницу входа
                .clickSignUpButton() // Переход на страницу регистрации
                .setName(name) // Ввод имени
                .setEmail(email) // Ввод email
                .setPassword(password) // Ввод пароля
                .clickConfirmSignUpButton() // Подтверждение регистрации
                .signUpUser(email, password); // Логин после регистрации

        // Проверка текущего URL после регистрации
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("https://stellarburgers.nomoreparties.site/login", currentUrl);
    }

    /**
     * Тест на некорректную регистрацию (ошибка при коротком пароле).
     * Ожидается, что будет показано сообщение об ошибке, так как пароль слишком короткий.
     */
    @Test
    @DisplayName("Регистрация пользователя - некорректная")
    @Description("Ошибка при некорректном пароле. Минимальная длина пароля — шесть символов.")
    public void checkErrorRegistrationStellar() {
        boolean inValidDataRegister = open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .clickEnterAccountButton() // Переход на страницу входа
                .clickSignUpButton() // Переход на страницу регистрации
                .setName(name) // Ввод имени
                .setEmail(email) // Ввод email
                .setPassword(TestValue.PASSWORD_ERROR) // Ввод некорректного пароля
                .clickConfirmSignUpButton() // Подтверждение регистрации
                .errorMessageGetText() // Проверка сообщения об ошибке
                .clickEnterLinkButton() // Переход на страницу входа
                .signUpUser(email, TestValue.PASSWORD_ERROR) // Попытка логина с некорректными данными
                .isErrorMessageExist(); // Проверка наличия сообщения об ошибке

        // Утверждение, что сообщение об ошибке появилось
        assertTrue(inValidDataRegister);
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Удаляет пользователей с валидными и невалидными учетными данными.
     */
    @After
    public void tearDown() {
        userClient = new UserClient();

        // Удаление пользователя с валидными учетными данными
        Credentials credentials = new Credentials(email, password);
        Response response = userClient.login(credentials);
        if (response.body().jsonPath().getString("accessToken") != null) {
            userClient.delete(response);
        }

        // Удаление пользователя с невалидными учетными данными
        Credentials userInValidCredentials = new Credentials(email, TestValue.PASSWORD_ERROR);
        Response inValidResponse = userClient.login(userInValidCredentials);
        if (inValidResponse.body().jsonPath().getString("accessToken") != null) {
            userClient.delete(inValidResponse);
        }
    }
}
