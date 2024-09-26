package api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс модели пользователя.
 * Содержит поля: email, имя и пароль.
 */
@Setter
@Getter
@AllArgsConstructor
public class User {

    /**
     * Email пользователя.
     */
    private String email;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Переопределение метода toString для вывода данных пользователя.
     * @return строка с email, паролем и именем.
     */
    @Override
    public String toString() {
        return "email: " + email + ", password: " + password + ", name: " + name;
    }
}
