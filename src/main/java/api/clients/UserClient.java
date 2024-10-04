package api.clients;

import api.models.Credentials;
import api.models.User;
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
                .extract().response(); // Извлекаем и возвращаем ответ
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
                .extract().response(); // Извлекаем и возвращаем ответ
    }

    /**
     * Удаление пользователя.
     * Отправляет запрос на удаление пользователя, используя accessToken, извлеченный из ответа.
     *
     * @param response - ответ сервера, содержащий accessToken.
     */
    @Step("Удаление пользователя по ответу")
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
                .delete(DELETE) // Отправляем DELETE-запрос
                .then()
                .statusCode(202); // Проверяем, что удаление прошло успешно (статус код 202)
    }

    /**
     * Удаление пользователя.
     * Отправляет запрос на удаление пользователя, используя переданный accessToken.
     *
     * @param accessToken - токен доступа пользователя.
     */
    @Step("Удаление пользователя по токену")
    public void delete(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return; // Если accessToken отсутствует или пуст, операция отменяется
        }

        // Отправляем запрос на удаление с использованием токена авторизации
        given()
                .spec(getSpecSettings()) // Устанавливаем спецификацию запроса
                .header("authorization", accessToken) // Добавляем заголовок авторизации
                .when()
                .delete(DELETE) // Отправляем DELETE-запрос
                .then()
                .statusCode(202); // Проверяем, что удаление прошло успешно (статус код 202)
    }
}
