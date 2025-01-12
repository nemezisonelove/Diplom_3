package api.clients;

import api.models.models.Credentials;
import api.models.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Класс для конфигурации запросов: регистрация, удаление и логин пользователя.
 */
public class UserClient extends Client {

    // Константы для конечных точек API
    private static final String REGISTER = "auth/register/";
    private static final String LOGIN = "auth/login/";
    private static final String DELETE = "auth/user/";

    /**
     * Регистрация пользователя.
     * Отправляет запрос на регистрацию нового пользователя.
     *
     * @param user - объект пользователя с данными для регистрации.
     * @return Response - ответ сервера.
     */
    @Step("Регистрация пользователя")
    public Response createUser(User user) {
        return (Response) given()
                .spec(getSpecSettings()) // Устанавливаем спецификацию запроса
                .body(user) // Передаем тело запроса
                .when()
                .post(REGISTER) // Отправляем POST-запрос на регистрацию
                .then()
                .extract(); // Извлекаем ответ
    }

    /**
     * Логин пользователя.
     * Отправляет запрос на вход пользователя с его учетными данными.
     *
     * @param credentials - учетные данные (email и пароль).
     * @return Response - ответ сервера.
     */
    @Step("Логин пользователя")
    public static Response login(Credentials credentials) {
        return (Response) given()
                .spec(getSpecSettings()) // Устанавливаем спецификацию запроса
                .body(credentials) // Передаем тело запроса
                .when()
                .post(LOGIN) // Отправляем POST-запрос на логин
                .then()
                .extract(); // Извлекаем ответ
    }

    /**
     * Удаление пользователя.
     * Отправляет запрос на удаление пользователя, используя accessToken.
     *
     * @param response - ответ сервера, содержащий accessToken.
     */
    @Step("Удаление пользователя")
    public void delete(Response response) {
        // Извлекаем accessToken из ответа
        String accessToken = response.body().jsonPath().getString("accessToken");
        if (accessToken == null) {
            return; // Если accessToken отсутствует, операция отменяется
        }

        // Отправляем запрос на удаление с использованием токена авторизации
        given()
                .spec(getSpecSettings()) // Устанавливаем спецификацию запроса
                .header("authorization", accessToken) // Добавляем заголовок авторизации
                .when()
                .delete(DELETE); // Отправляем DELETE-запрос
    }
}