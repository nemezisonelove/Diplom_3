import api.clients.UserClient;
import api.generator.UserGenerator;
import api.models.Credentials;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
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
    private String name;
    private String email;
    private String password;
    private Credentials validCredentials;
    private Credentials invalidCredentials;

    @Before
    public void setUp() {
        // Генерация данных пользователя один раз и инициализация клиента
        userClient = new UserClient();
        name = UserGenerator.getUser().getName();
        email = UserGenerator.getUser().getEmail();
        password = UserGenerator.getUser().getPassword();

        // Создаем объекты Credentials для валидного и невалидного пользователя
        validCredentials = new Credentials(email, password);
        invalidCredentials = new Credentials(email, TestValue.PASSWORD_ERROR);
    }

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
        assertTrue(currentUrl.contains("/login"));
    }

    /**
     * Тест на некорректную регистрацию (ошибка при коротком пароле).
     * Ожидается, что будет показано сообщение об ошибке, так как пароль слишком короткий.
     */
    @Test
    @DisplayName("Регистрация пользователя - некорректная")
    @Description("Ошибка при некорректном пароле. Минимальная длина пароля — шесть символов.")
    public void checkErrorRegistrationStellar() {
        boolean invalidDataRegister = open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .clickEnterAccountButton() // Переход на страницу входа
                .clickSignUpButton() // Переход на страницу регистрации
                .setName(name) // Ввод имени
                .setEmail(email) // Ввод email
                .setPassword(TestValue.PASSWORD_ERROR) // Ввод некорректного пароля
                .clickConfirmSignUpButton() // Подтверждение регистрации
                .isErrorMessageExist(); // Проверка наличия сообщения об ошибке

        // Утверждение, что сообщение об ошибке появилось
        assertTrue(invalidDataRegister);
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Удаляет пользователей с валидными и невалидными учетными данными.
     */
    @After
    public void tearDown() {
        // Удаление пользователя с валидными учетными данными
        Response response = userClient.login(validCredentials);
        String accessToken = response.body().jsonPath().getString("accessToken");
        if (accessToken != null) {
            userClient.delete(accessToken);  // Передаем токен доступа
        }

        // Удаление пользователя с невалидными учетными данными
        Response invalidResponse = userClient.login(invalidCredentials);
        String invalidAccessToken = invalidResponse.body().jsonPath().getString("accessToken");
        if (invalidAccessToken != null) {
            userClient.delete(invalidAccessToken);  // Передаем токен доступа
        }
    }
}
