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
    private final By homeHeader = By.className("Home_Header__iJKdX");
    private final By upOrderButton = By.className("Button_Button__ra12g");
    private final By downOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By questionsHeader = By.className("Home_FourPart__1uthg");
    private final By orderState = By.xpath(".//button[text()='Статус заказа']");
    private final By numberOrder = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By buttonGo = By.xpath(".//button[text()='Go!']");
    private final By yandexButton = By.xpath(".//*[@alt='Yandex']");
    private final By cookiesButton = By.className("App_CookieButton__3cvqF");

    WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    // - Метод ожидания загрузки главной страницы
    public StartPage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }


    // - Метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    // - Метод прокрутки ко второй кнопке "Заказать"
    public StartPage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }

    // - Метод прокрутки к блоку "Вопросы о важном"
    public StartPage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }


    public StartPage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    public StartPage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }

    public void clickCreateOrderButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    public StartPage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }


    public StartPage clickCookiesButton() {
        driver.findElement(cookiesButton).click();
        return this;
    }
}