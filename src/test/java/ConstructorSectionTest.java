import io.qameta.allure.junit4.DisplayName;
import main.page.MainPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class ConstructorSectionTest extends BaseTest {

    /**
     * Тест на проверку перехода к разделу "Булки".
     * Ожидается, что после клика на кнопку "Булки", заголовок раздела будет отображен.
     */
    @Test
    @DisplayName("Переход к разделу Булки")
    public void clickBunsSectionButtonTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .scrollToBunsHeader() // Прокрутка к разделу "Булки", чтобы гарантировать его видимость
                .clickBunsButtonWithJS() // Используем JavaScript для клика по разделу "Булки"
                .isBunsHeaderDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку перехода к разделу "Соусы".
     * Ожидается, что после клика на кнопку "Соусы", заголовок раздела будет отображен.
     */
    @Test
    @DisplayName("Переход к разделу Соусы")
    public void clickSaucesSectionButtonTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .scrollToSaucesHeader() // Прокрутка к разделу "Соусы"
                .clickSaucesButtonWithJS() // Используем JavaScript для клика по разделу "Соусы"
                .isSaucesHeaderDisplayed(); // Проверка отображения заголовка
    }

    /**
     * Тест на проверку перехода к разделу "Начинки".
     * Ожидается, что после клика на кнопку "Начинки", заголовок раздела будет отображен.
     */
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void clickFillingsSectionButtonTest() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class)
                .scrollToFillingsHeader() // Прокрутка к разделу "Начинки"
                .clickFillingsButtonWithJS() // Используем JavaScript для клика по разделу "Начинки"
                .isFillingsHeaderDisplayed(); // Проверка отображения заголовка
    }
}
