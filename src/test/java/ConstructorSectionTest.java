import io.qameta.allure.junit4.DisplayName;
import main.page.MainPage;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;

/**
 * Тесты для проверки:
 * - Переход к разделам: Булки, Соусы, Начинки.
 * - Скролл к разделам: Булки, Соусы, Начинки.
 */
public class ConstructorSectionTest extends BaseTest {

    /**
     * Тест на проверку перехода к разделу "Булки".
     * Ожидается, что после клика на кнопку "Булки", заголовок раздела будет отображен.
     */
    @Test
    @DisplayName("Переход к разделу Булки")
    public void clickBunsSectionButtonTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .clickSaucesButton() // Клик по разделу "Соусы" (если нужно)
                .clickBunsButton() // Клик по разделу "Булки"
                .isBunsHeaderIsDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку перехода к разделу "Соусы".
     * Ожидается, что после клика на кнопку "Соусы", заголовок раздела будет отображен.
     */
    @Test
    @DisplayName("Переход к разделу Соусы")
    public void clickSaucesSectionButtonTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .clickSaucesButton() // Клик по разделу "Соусы"
                .isSaucesHeaderIsDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку перехода к разделу "Начинки".
     * Ожидается, что после клика на кнопку "Начинки", заголовок раздела будет отображен.
     */
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void clickFillingsSectionButtonTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .clickFillingsButton() // Клик по разделу "Начинки"
                .isFillingsHeaderIsDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку скролла к разделу "Булки".
     * Ожидается, что после скролла к разделу "Булки", заголовок будет отображен.
     */
    @Test
    @DisplayName("Скролл к разделу Булки")
    public void scrollBunsSectionTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .scrollToBunsHeader() // Скролл к разделу "Булки"
                .isBunsHeaderIsDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку скролла к разделу "Соусы".
     * Ожидается, что после скролла к разделу "Соусы", заголовок будет отображен.
     */
    @Test
    @DisplayName("Скролл к разделу Соусы")
    public void scrollSaucesSectionTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .scrollToSaucesHeader() // Скролл к разделу "Соусы"
                .isSaucesHeaderIsDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку скролла к разделу "Начинки".
     * Ожидается, что после скролла к разделу "Начинки", заголовок будет отображен.
     */
    @Test
    @DisplayName("Скролл к разделу Начинки")
    public void scrollFillingsSectionTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .scrollToFillingsHeader() // Скролл к разделу "Начинки"
                .isFillingsHeaderIsDisplayed(); // Проверка отображения заголовка
    }
}
