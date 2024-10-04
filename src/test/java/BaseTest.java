import api.clients.Client;
import api.clients.UserClient;
import api.generator.UserGenerator;
import api.models.Credentials;
import api.models.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
     * Открывает браузер (по умолчанию Yandex), переходит на базовый URL и создает пользователя.
     */
    @Before
    @Step("Открыть страницу и создать пользователя")
    public void setUp() {
        if (driver == null) {
            // Получаем значение браузера из системного свойства или по умолчанию Yandex
            String browser = System.getProperty("browser", "yandex");

            // Инициализация браузера на основе выбранного браузера
            switch (browser.toLowerCase()) {
                case "chrome":
                    // Используем WebDriverManager для автоматического управления ChromeDriver
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "yandex":
                    // Указываем путь к YandexDriver вручную
                    System.setProperty("webdriver.chrome.driver", "C:/Program Files/drivers/yandexdriver.exe"); // Путь к YandexDriver
                    ChromeOptions options = new ChromeOptions();
                    options.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe"); // Укажите путь к Yandex Browser
                    driver = new ChromeDriver(options);
                    break;

                default:
                    throw new RuntimeException("Неизвестный браузер: " + browser);
            }
        }

        // Проверка, если браузер уже на главной странице
        if (!driver.getCurrentUrl().equals(Client.BASE_URL)) {
            driver.get(Client.BASE_URL); // Переход на базовую страницу
        }

        // Инициализация клиента пользователя и создание пользователя
        userClient = new UserClient();
        Response response = userClient.createUser(user);  // Создание пользователя через API
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Не удалось создать пользователя: " + response.asString());
        }
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Логинит пользователя, удаляет его, а затем закрывает браузер.
     */
    @After
    @Step("Удаление пользователя и закрытие браузера")
    public void cleanUp() {
        if (userClient != null) {
            // Создание учетных данных для логина
            Credentials credentials = new Credentials(user.getEmail(), user.getPassword());

            // Логин пользователя
            Response response = userClient.login(credentials);

            // Если есть токен доступа, удаляем пользователя
            String accessToken = response.body().jsonPath().getString("accessToken");
            if (accessToken != null) {
                userClient.delete(response);
            }
        }

        // Закрытие браузера, если он был инициализирован
        if (driver != null) {
            driver.quit();
            driver = null; // Сброс драйвера для последующего использования
        }
    }
}
