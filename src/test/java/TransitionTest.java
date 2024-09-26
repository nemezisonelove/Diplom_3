import io.qameta.allure.junit4.DisplayName;
import main.page.SignInPage;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;

/**
 * Тесты для проверки переходов между страницами:
 * - Переход из личного кабинета на главную страницу через кнопку "Конструктор".
 * - Переход из личного кабинета на главную страницу через кнопку "Лого".
 * - Проверка перехода по клику на "Личный кабинет".
 */
public class TransitionTest extends BaseTest {

    /**
     * Тест на переход из личного кабинета на главную страницу через кнопку "Конструктор".
     * Ожидается, что после нажатия кнопки "Конструктор" откроется главная страница с разделом конструктора.
     */
    @Test
    @DisplayName("Переход из Личного кабинета на главную страницу через кнопку Конструктор")
    public void openConstructorButtonTest() {
        open(SignInPage.URL_SIGN_IN_PAGE, SignInPage.class) // Открываем страницу авторизации
                .signInUser(user) // Логинимся пользователем
                .clickAccountButtonAfterAuthorization() // Переходим в личный кабинет
                .clickConstructorButton() // Кликаем на кнопку "Конструктор"
                .isConstructorHeaderExist(); // Проверяем наличие заголовка конструктора
    }

    /**
     * Тест на переход из личного кабинета на главную страницу через кнопку "Лого".
     * Ожидается, что после нажатия на логотип откроется главная страница с разделом конструктора.
     */
    @Test
    @DisplayName("Переход из Личного кабинета на главную страницу через кнопку Лого")
    public void openLogoPictureTest() {
        open(SignInPage.URL_SIGN_IN_PAGE, SignInPage.class) // Открываем страницу авторизации
                .signInUser(user) // Логинимся пользователем
                .clickAccountButtonAfterAuthorization() // Переходим в личный кабинет
                .clickLogoPicture() // Кликаем на логотип
                .isConstructorHeaderExist(); // Проверяем наличие заголовка конструктора
    }

    /**
     * Тест на проверку перехода в личный кабинет по клику на "Личный кабинет".
     * Ожидается, что после нажатия кнопки "Личный кабинет" откроется профиль пользователя.
     */
    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void clickUserProfileButtonTest() {
        open(SignInPage.URL_SIGN_IN_PAGE, SignInPage.class) // Открываем страницу авторизации
                .signInUser(user) // Логинимся пользователем
                .clickAccountButtonAfterAuthorization() // Кликаем на кнопку "Личный кабинет"
                .isUserProfileHeaderExist(); // Проверяем наличие заголовка "Профиль"
    }
}
