package manager;

import org.apache.commons.lang3.RandomStringUtils;

public class TestValue extends RandomStringUtils {
    /**
     * Содержит тестовые данные для создания пользователя, такие как: логин, пароль, имя
     */
    public static final String
            // Генерация тестового email
            TEST_EMAIL_ONE = randomAlphabetic(10).toLowerCase() + "@yandex.ru",

    // Тестовый пароль
    TEST_PASSWORD_ONE = "52spb",

    // Неправильный пароль для теста ошибок
    PASSWORD_ERROR = "52",

    // Тестовое имя пользователя
    TEST_NAME_ONE = randomAlphabetic(10);
}