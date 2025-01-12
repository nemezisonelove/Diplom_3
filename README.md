# Diplom_3

## Описание

Это проект для тестирования функционала веб-приложения **Stellar Burgers**, которое помогает пользователям заказывать бургеры. В проекте реализованы тесты с использованием паттерна **Page Object**. Тесты проверяют такие сценарии, как регистрация, вход в систему, переходы между страницами, а также работу раздела конструктора бургеров. Тесты выполняются в браузерах **Google Chrome** и **Яндекс Браузер**.

### Тестовые сценарии:

1. **Регистрация: `src/test/RegistrationWebTest.java`**
    - Успешная регистрация нового пользователя.
    - Ошибка при регистрации из-за короткого пароля (меньше 6 символов).

2. **Вход в систему: `src/test/LoginWebTest.java`**
    - Вход через кнопку «Зарегистрироваться» на главной странице.
    - Вход через кнопку «Личный кабинет».
    - Вход через кнопку на странице регистрации.
    - Вход через кнопку на странице восстановления пароля.

3. **Переход в личный кабинет: `src/test/TransitionTest.java`**
    - Проверка перехода в личный кабинет через кнопку «Личный кабинет».

4. **Переход из личного кабинета в конструктор: `src/test/TransitionTest.java`**
    - Переход на главную страницу через кнопку «Конструктор».
    - Переход на главную страницу через клик по логотипу **Stellar Burgers**.

5. **Выход из системы: `src/test/ExitTest.java`**
    - Проверка корректного выхода из личного кабинета с помощью кнопки «Выход».

6. **Раздел "Конструктор": `src/test/ConstructorSectionTest.java`**
    - Проверка переходов между разделами конструктора:
        - «Булки»
        - «Соусы»
        - «Начинки»

---

## Зависимости

Проект использует следующие зависимости:

- **JUnit** — версия `4.13.2`
- **AspectJ** — версия `1.9.7`
- **Allure** — версия `2.22.0`
- **Selenide** — версия `5.23.2`
- **RestAssured** — версия `4.4.0`
- **Gson** — версия `2.8.9`
- **JUnit Data Provider** — версия `1.13.1`
- **slf4j API** — версия `2.0.7`
- **Lombok** — версия `1.18.28`
