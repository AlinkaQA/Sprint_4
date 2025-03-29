package ru.yandex.praсtikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praсtikum.pages.StartPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StartPageCheckTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final By questionLocator;
    private final String expectedAnswerText;
    private StartPage startPage;

    public StartPageCheckTest(By questionLocator, String expectedAnswerText) {
        this.questionLocator = questionLocator;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {StartPage.COST_QUESTION, StartPage.COST_ANSWER_TEXT},
                {StartPage.MULTIPLE_SCOOTERS_QUESTION, StartPage.MULTIPLE_SCOOTERS_ANSWER_TEXT},
                {StartPage.RENTAL_TIME_QUESTION, StartPage.RENTAL_TIME_ANSWER_TEXT},
                {StartPage.SAME_DAY_DELIVERY_QUESTION, StartPage.SAME_DAY_DELIVERY_ANSWER_TEXT},
                {StartPage.ORDER_CHANGE_QUESTION, StartPage.ORDER_CHANGE_ANSWER_TEXT},
                {StartPage.BATTERY_LIFE_QUESTION, StartPage.BATTERY_LIFE_ANSWER_TEXT},
                {StartPage.CANCELLATION_QUESTION, StartPage.CANCELLATION_ANSWER_TEXT},
                {StartPage.DELIVERY_AREA_QUESTION, StartPage.DELIVERY_AREA_ANSWER_TEXT}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(site);
        startPage = new StartPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkQuestions() {
        String actualAnswerText = startPage
                .waitForLoadHomePage()
                .clickCookiesButton()
                .scrollToQuestions()
                .clickQuestion(questionLocator)
                .getAnswerText(questionLocator);  // Получаем текст ответа, передавая локатор вопроса

        assertEquals(expectedAnswerText, actualAnswerText);
    }
}

