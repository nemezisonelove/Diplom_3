package api.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Класс содержит ссылку на тестируемый сайт и настройки RestAssured.
 */
public class Client {

    // Базовый URL тестируемого сайта
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    /**
     * Метод для получения настроек спецификации запроса (RequestSpecification).
     * Устанавливает базовый URI и тип контента (JSON).
     *
     * @return RequestSpecification - спецификация запроса для использования в тестах.
     */
    public static RequestSpecification getSpecSettings() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON) // Установка типа контента JSON
                .setBaseUri(BASE_URL + "api/") // Установка базового URI для API
                .build(); // Построение спецификации
    }
}