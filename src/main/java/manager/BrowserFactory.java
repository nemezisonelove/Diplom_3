package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Фабрика браузеров
 * Реализует выбор браузера: Yandex или Chrome
 */
public class BrowserFactory {

    /**
     * Возвращает экземпляр WebDriver в зависимости от указанного браузера.
     * @param browserName - Название браузера (chrome или yandex)
     * @return WebDriver - экземпляр браузера
     */
    public static WebDriver getDriver(String browserName) {
        ChromeOptions options;
        switch (browserName) {
            case "chrome":
                // Настройка WebDriver для браузера Chrome
                WebDriverManager.chromedriver().setup();
                options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*", "start-maximized");
                return new ChromeDriver(options);
            case "yandex":
                // Настройка WebDriver для браузера Yandex (используется ChromeDriver)
                options = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");
                options.addArguments("--remote-allow-origins=*", "start-maximized");
                return new ChromeDriver(options);
            default:
                // Ошибка, если введено некорректное название браузера
                throw new RuntimeException("Некорректное название браузера");
        }
    }
}