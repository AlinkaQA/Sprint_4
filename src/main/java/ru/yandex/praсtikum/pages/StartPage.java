package ru.yandex.praсtikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static ru.yandex.praсtikum.pages.constants.OrderButton.DOWN_BUTTON;
import static ru.yandex.praсtikum.pages.constants.OrderButton.UP_BUTTON;

public class StartPage {
    private final WebDriver driver;

    // Локаторы для главной страницы
    public static final By HOME_HEADER = By.className("Home_Header__iJKdX");
    public static final By UP_ORDER_BUTTON = By.className("Button_Button__ra12g");
    public static final By DOWN_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    public static final By QUESTIONS_HEADER = By.className("Home_FourPart__1uthg");
    public static final By ORDER_STATE = By.xpath(".//button[text()='Статус заказа']");
    public static final By NUMBER_ORDER = By.xpath(".//input[@placeholder='Введите номер заказа']");
    public static final By BUTTON_GO = By.xpath(".//button[text()='Go!']");
    public static final By YANDEX_BUTTON = By.xpath(".//*[@alt='Yandex']");
    public static final By COOKIES_BUTTON = By.className("App_CookieButton__3cvqF");

    // Локаторы для вопросов
    public static final By COST_QUESTION = By.id("accordion__heading-0");
    public static final By MULTIPLE_SCOOTERS_QUESTION = By.id("accordion__heading-1");
    public static final By RENTAL_TIME_QUESTION = By.id("accordion__heading-2");
    public static final By SAME_DAY_DELIVERY_QUESTION = By.id("accordion__heading-3");
    public static final By ORDER_CHANGE_QUESTION = By.id("accordion__heading-4");
    public static final By BATTERY_LIFE_QUESTION = By.id("accordion__heading-5");
    public static final By CANCELLATION_QUESTION = By.id("accordion__heading-6");
    public static final By DELIVERY_AREA_QUESTION = By.id("accordion__heading-7");

    // Локаторы для ответов (панелей)
    public static final By COST_ANSWER = By.id("accordion__panel-0");
    public static final By MULTIPLE_SCOOTERS_ANSWER = By.id("accordion__panel-1");
    public static final By RENTAL_TIME_ANSWER = By.id("accordion__panel-2");
    public static final By SAME_DAY_DELIVERY_ANSWER = By.id("accordion__panel-3");
    public static final By ORDER_CHANGE_ANSWER = By.id("accordion__panel-4");
    public static final By BATTERY_LIFE_ANSWER = By.id("accordion__panel-5");
    public static final By CANCELLATION_ANSWER = By.id("accordion__panel-6");
    public static final By DELIVERY_AREA_ANSWER = By.id("accordion__panel-7");

    // Тексты ответов
    public static final String COST_ANSWER_TEXT = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String MULTIPLE_SCOOTERS_ANSWER_TEXT = "Пока что у нас так: один заказ — один самокат. " +
            "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String RENTAL_TIME_ANSWER_TEXT = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
            "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
            "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String SAME_DAY_DELIVERY_ANSWER_TEXT = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String ORDER_CHANGE_ANSWER_TEXT = "Пока что нет! Но если что-то срочное — " +
            "всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String BATTERY_LIFE_ANSWER_TEXT = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
            "даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String CANCELLATION_ANSWER_TEXT = "Да, пока самокат не привезли. Штрафа не будет, " +
            "объяснительной записки тоже не попросим. Все же свои.";
    public static final String DELIVERY_AREA_ANSWER_TEXT = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки главной страницы
    public StartPage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(HOME_HEADER).getText() != null
                && !driver.findElement(HOME_HEADER).getText().isEmpty()
        ));
        return this;
    }

    // Метод ожидания загрузки ответа на вопрос
    public StartPage waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
        return this;
    }

    // Метод прокрутки ко второй кнопке "Заказать"
    public StartPage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(DOWN_ORDER_BUTTON));
        return this;
    }

    // Метод прокрутки к блоку "Вопросы о важном"
    public StartPage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(QUESTIONS_HEADER));
        return this;
    }

    // Метод клика на верхнюю кнопку "Заказать"
    public StartPage clickUpOrderButton() {
        driver.findElement(UP_ORDER_BUTTON).click();
        return this;
    }

    // Метод клика на нижнюю кнопку "Заказать"
    public StartPage clickDownOrderButton() {
        driver.findElement(DOWN_ORDER_BUTTON).click();
        return this;
    }

    // Метод выбора кнопки "Заказать" (верхняя или нижняя)
    public void clickCreateOrderButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    // Метод клика на вопрос
    public StartPage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

    // Метод клика на кнопку принятия куки
    public StartPage clickCookiesButton() {
        driver.findElement(COOKIES_BUTTON).click();
        return this;
    }

    // Метод получения текста ответа
    public String getAnswerText(By questionLocator) {
        By answerLocator = getAnswerLocator(questionLocator);
        return driver.findElement(answerLocator).getText();
    }

    // Метод для получения локатора ответа в зависимости от вопроса
    private By getAnswerLocator(By questionLocator) {
        if (questionLocator.equals(COST_QUESTION)) {
            return COST_ANSWER;
        } else if (questionLocator.equals(MULTIPLE_SCOOTERS_QUESTION)) {
            return MULTIPLE_SCOOTERS_ANSWER;
        } else if (questionLocator.equals(RENTAL_TIME_QUESTION)) {
            return RENTAL_TIME_ANSWER;
        } else if (questionLocator.equals(SAME_DAY_DELIVERY_QUESTION)) {
            return SAME_DAY_DELIVERY_ANSWER;
        } else if (questionLocator.equals(ORDER_CHANGE_QUESTION)) {
            return ORDER_CHANGE_ANSWER;
        } else if (questionLocator.equals(BATTERY_LIFE_QUESTION)) {
            return BATTERY_LIFE_ANSWER;
        } else if (questionLocator.equals(CANCELLATION_QUESTION)) {
            return CANCELLATION_ANSWER;
        } else if (questionLocator.equals(DELIVERY_AREA_QUESTION)) {
            return DELIVERY_AREA_ANSWER;
        } else {
            throw new IllegalArgumentException("Unknown question locator: " + questionLocator);
        }
    }
}
