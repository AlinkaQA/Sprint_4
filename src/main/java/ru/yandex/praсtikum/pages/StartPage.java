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
    private final By homeHeader = By.className("Home_Header__iJKdX");
    private final By upOrderButton = By.className("Button_Button__ra12g");
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By questionsHeader = By.className("Home_FourPart__1uthg");
    private final By orderState = By.xpath(".//button[text()='Статус заказа']");
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By buttonGo = By.xpath(".//button[text()='Go!']");
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");
    private final By cookiesButton = By.className("App_CookieButton__3cvqF");

    // Локаторы для вопросов
    private final By costQuestion = By.id("accordion__heading-0");
    private final By multipleScootersQuestion = By.id("accordion__heading-1");
    private final By rentalTimeQuestion = By.id("accordion__heading-2");
    private final By sameDayDeliveryQuestion = By.id("accordion__heading-3");
    private final By orderChangeQuestion = By.id("accordion__heading-4");
    private final By batteryLifeQuestion = By.id("accordion__heading-5");
    private final By cancellationQuestion = By.id("accordion__heading-6");
    private final By deliveryAreaQuestion = By.id("accordion__heading-7");

    // Локаторы для ответов (панелей)
    private final By costAnswer = By.id("accordion__panel-0");
    private final By multipleScootersAnswer = By.id("accordion__panel-1");
    private final By rentalTimeAnswer = By.id("accordion__panel-2");
    private final By sameDayDeliveryAnswer = By.id("accordion__panel-3");
    private final By orderChangeAnswer = By.id("accordion__panel-4");
    private final By batteryLifeAnswer = By.id("accordion__panel-5");
    private final By cancellationAnswer = By.id("accordion__panel-6");
    private final By deliveryAreaAnswer = By.id("accordion__panel-7");

    // Локаторы для элементов ответов через XPath
    private final By costAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-0']");
    private final By multipleScootersAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-1']");
    private final By rentalTimeAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-2']");
    private final By sameDayDeliveryAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-3']");
    private final By orderChangeAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-4']");
    private final By batteryLifeAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-5']");
    private final By cancellationAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-6']");
    private final By deliveryAreaAnswerItem = By.xpath(".//*[@data-accordion-component='AccordionItemPanel' and @aria-labelledby='accordion__heading-7']");

    // Тексты ответов
    private final String costAnswerText = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private final String multipleScootersAnswerText = "Пока что у нас так: один заказ — один самокат. " +
            "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private final String rentalTimeAnswerText = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
            "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
            "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private final String sameDayDeliveryAnswerText = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private final String orderChangeAnswerText = "Пока что нет! Но если что-то срочное — " +
            "всегда можно позвонить в поддержку по красивому номеру 1010.";
    private final String batteryLifeAnswerText = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
            "даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private final String cancellationAnswerText = "Да, пока самокат не привезли. Штрафа не будет, " +
            "объяснительной записки тоже не попросим. Все же свои.";
    private final String deliveryAreaAnswerText = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод ожидания загрузки главной страницы
    public StartPage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    // Метод прокрутки к блоку "Вопросы о важном"
    public StartPage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    // Метод клика на верхнюю кнопку "Заказать"
    public StartPage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    // Метод клика на нижнюю кнопку "Заказать"
    public StartPage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
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
        driver.findElement(cookiesButton).click();
        return this;
    }

    // Геттеры для локаторов и текстов
    public By getCostQuestion() { return costQuestion; }
    public By getMultipleScootersQuestion() { return multipleScootersQuestion; }
    public By getRentalTimeQuestion() { return rentalTimeQuestion; }
    public By getSameDayDeliveryQuestion() { return sameDayDeliveryQuestion; }
    public By getOrderChangeQuestion() { return orderChangeQuestion; }
    public By getBatteryLifeQuestion() { return batteryLifeQuestion; }
    public By getCancellationQuestion() { return cancellationQuestion; }
    public By getDeliveryAreaQuestion() { return deliveryAreaQuestion; }

    public By getCostAnswer() { return costAnswer; }
    public By getMultipleScootersAnswer() { return multipleScootersAnswer; }
    public By getRentalTimeAnswer() { return rentalTimeAnswer; }
    public By getSameDayDeliveryAnswer() { return sameDayDeliveryAnswer; }
    public By getOrderChangeAnswer() { return orderChangeAnswer; }
    public By getBatteryLifeAnswer() { return batteryLifeAnswer; }
    public By getCancellationAnswer() { return cancellationAnswer; }
    public By getDeliveryAreaAnswer() { return deliveryAreaAnswer; }

    public By getCostAnswerItem() { return costAnswerItem; }
    public By getMultipleScootersAnswerItem() { return multipleScootersAnswerItem; }
    public By getRentalTimeAnswerItem() { return rentalTimeAnswerItem; }
    public By getSameDayDeliveryAnswerItem() { return sameDayDeliveryAnswerItem; }
    public By getOrderChangeAnswerItem() { return orderChangeAnswerItem; }
    public By getBatteryLifeAnswerItem() { return batteryLifeAnswerItem; }
    public By getCancellationAnswerItem() { return cancellationAnswerItem; }
    public By getDeliveryAreaAnswerItem() { return deliveryAreaAnswerItem; }

    public String getCostAnswerText() { return costAnswerText; }
    public String getMultipleScootersAnswerText() { return multipleScootersAnswerText; }
    public String getRentalTimeAnswerText() { return rentalTimeAnswerText; }
    public String getSameDayDeliveryAnswerText() { return sameDayDeliveryAnswerText; }
    public String getOrderChangeAnswerText() { return orderChangeAnswerText; }
    public String getBatteryLifeAnswerText() { return batteryLifeAnswerText; }
    public String getCancellationAnswerText() { return cancellationAnswerText; }
    public String getDeliveryAreaAnswerText() { return deliveryAreaAnswerText; }
}