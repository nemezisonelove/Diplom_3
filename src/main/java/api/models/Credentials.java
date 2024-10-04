package api.models;

/**
 * Класс модели учетных данных.
 * Содержит поля: email и пароль.
 */
public class Credentials {

    /**
     * Email пользователя.
     */
    private String email;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Конструктор для создания экземпляра с email и паролем.
     * @param email - Email пользователя
     * @param password - Пароль пользователя
     */
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Геттер для email.
     * @return Email пользователя.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Сеттер для email.
     * @param email Email пользователя.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Геттер для пароля.
     * @return Пароль пользователя.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер для пароля.
     * @param password Пароль пользователя.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Переопределение метода toString для вывода учетных данных.
     * @return строка с email и паролем.
     */
    @Override
    public String toString() {
        return "email: " + email + ", password: " + password;
    }
}
