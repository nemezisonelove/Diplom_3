package api.generator;

import api.models.User;
import manager.TestValue;

/**
 * Генератор пользователей для тестирования.
 * Создает объекты пользователя с тестовыми данными.
 */
public class UserGenerator {

    /**
     * Создает и возвращает объект пользователя с тестовыми данными.
     * Имя, email и пароль берутся из класса TestValue.
     * @return новый экземпляр класса User с тестовыми данными
     */
    public static User getUser() {
        String name = TestValue.TEST_NAME_ONE; // Тестовое имя пользователя
        String email = TestValue.TEST_EMAIL_ONE; // Тестовый email пользователя
        String password = TestValue.TEST_PASSWORD_ONE; // Тестовый пароль пользователя

        return new User(email, password, name);
    }
}