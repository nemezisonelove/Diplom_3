import io.qameta.allure.junit4.DisplayName;
import main.page.MainPage;
import main.page.ResetPasswordPage;
import main.page.SignUpPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Тесты для проверки входа на сайт через различные формы:
 * - Вход по кнопке «Войти в аккаунт» на главной странице.
 * - Вход через кнопку «Личный кабинет».
 * - Вход через кнопку в форме регистрации.
 * - Вход через кнопку в форме восстановления пароля.
 */
public class LoginWebTest extends BaseTest {

    /**
     * Тест на вход по кнопке «Войти в аккаунт» на главной странице.
     * Ожидается, что после входа будет доступна кнопка «Оформить заказ».
     */
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void signInButtonMainPageTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class) // Открываем главную страницу
                .clickEnterAccountButton() // Кликаем на кнопку «Войти в аккаунт»
                .setEmail(user.getEmail()) // Вводим email
                .setPassword(user.getPassword()) // Вводим пароль
                .clickSignInButton() // Кликаем на кнопку «Войти»
                .isCreateOrderButtonExist(); // Проверяем наличие кнопки «Оформить заказ»
    }

    /**
     * Тест на вход через кнопку «Личный кабинет».
     * Ожидается, что после входа будет доступна кнопка «Оформить заказ».
     */
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void signInButtonUserProfileTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class) // Открываем главную страницу
                .clickAccountButtonBeforeAuthorization() // Кликаем на кнопку «Личный кабинет»
                .setEmail(user.getEmail()) // Вводим email
                .setPassword(user.getPassword()) // Вводим пароль
                .clickSignInButton() // Кликаем на кнопку «Войти»
                .isCreateOrderButtonExist(); // Проверяем наличие кнопки «Оформить заказ»
    }

    /**
     * Тест на вход через кнопку в форме регистрации.
     * Ожидается, что после входа будет доступна кнопка «Оформить заказ».
     */
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void signInButtonSignUpTest() {
        open(SignUpPage.SIGN_UP_PAGE_URL, SignUpPage.class) // Открываем страницу регистрации
                .clickSignUpPageEnterButton() // Кликаем на кнопку «Войти» в форме регистрации
                .setEmail(user.getEmail()) // Вводим email
                .setPassword(user.getPassword()) // Вводим пароль
                .clickSignInButton() // Кликаем на кнопку «Войти»
                .isCreateOrderButtonExist(); // Проверяем наличие кнопки «Оформить заказ»
    }

    /**
     * Тест на вход через кнопку в форме восстановления пароля.
     * Ожидается, что после входа будет доступна кнопка «Оформить заказ».
     */
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void signInButtonResetPasswordTest() {
        open(ResetPasswordPage.RESET_PASSWORD_PAGE_URL, ResetPasswordPage.class) // Открываем страницу восстановления пароля
                .clickResetPasswordButton() // Кликаем на кнопку «Войти» на странице восстановления пароля
                .setEmail(user.getEmail()) // Вводим email
                .setPassword(user.getPassword()) // Вводим пароль
                .clickSignInButton() // Кликаем на кнопку «Войти»
                .isCreateOrderButtonExist(); // Проверяем наличие кнопки «Оформить заказ»
    }
}
