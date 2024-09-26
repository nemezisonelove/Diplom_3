import io.qameta.allure.junit4.DisplayName;
import main.page.SignInPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Тест проверяет:
 * - Выход из личного кабинета через кнопку "Выйти".
 */
public class ExitTest extends BaseTest {

    /**
     * Тест на выход из личного кабинета.
     * Ожидается, что после нажатия кнопки "Выйти" пользователь будет разлогинен, и кнопка "Войти" снова станет доступной.
     */
    @Test
    @DisplayName("Выход из личного кабинета через кнопку Выйти")
    public void logoutUserProfileButtonTest() {
        open(SignInPage.URL_SIGN_IN_PAGE, SignInPage.class) // Открытие страницы авторизации
                .setEmail(user.getEmail()) // Ввод email пользователя
                .setPassword(user.getPassword()) // Ввод пароля пользователя
                .clickSignInButton() // Клик по кнопке "Войти"
                .clickAccountButtonAfterAuthorization() // Переход в личный кабинет
                .clickLogOutButton() // Клик по кнопке "Выйти"
                .isEnterButtonExist(); // Проверка наличия кнопки "Войти" после выхода
    }
}