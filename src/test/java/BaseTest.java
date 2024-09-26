import api.clients.Client;
import api.clients.UserClient;
import api.generator.UserGenerator;
import api.models.Credentials;
import api.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import manager.BrowserFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * Родительский класс для тестов.
 * Содержит:
 * - создание фейкового пользователя,
 * - метод для выбора браузера (Yandex/Chrome),
 * - метод для удаления пользователя и закрытия браузера.
 */
public class BaseTest {

    // Фейковый пользователь, генерируемый для тестов
    public static final User user = UserGenerator.getUser();

    // Экземпляр WebDriver для управления браузером
    public WebDriver driver;

    // Клиент для работы с пользователем (создание, удаление, логин)
    public UserClient userClient;

    /**
     * Метод, выполняющийся перед каждым тестом.
     * Открывает браузер Yandex, переходит на базовый URL и создает пользователя.
     */
    @Before
    @Step("Открыть страницу")
    public void setUp() {
        // Инициализация браузера Yandex через фабрику
        driver = BrowserFactory.getDriver("yandex");
        driver.get(Client.BASE_URL); // Переход на базовую страницу

        // Инициализация клиента пользователя и создание пользователя
        userClient = new UserClient();
        userClient.createUser(user);
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Логинит пользователя, удаляет его, а затем закрывает браузер.
     */
    @After
    @Step("Закрытие браузера")
    public void cleanUp() {
        // Создание учетных данных для логина
        Credentials credentials = new Credentials(user.getEmail(), user.getPassword());

        // Логин пользователя
        Response response = userClient.login(credentials);

        // Если есть токен доступа, удаляем пользователя
        if (response.body().jsonPath().getString("accessToken") != null) {
            userClient.delete(response);
        }

        // Закрытие браузера
        driver.quit();
    }
}