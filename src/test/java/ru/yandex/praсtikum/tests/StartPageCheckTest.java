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
    private final By answerLocator;
    private final By answerItemLocator;
    private final String expectedAnswerText;
    private StartPage startPage;

    public StartPageCheckTest(By questionLocator, By answerLocator, By answerItemLocator, String expectedAnswerText) {
        this.questionLocator = questionLocator;
        this.answerLocator = answerLocator;
        this.answerItemLocator = answerItemLocator;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        StartPage page = new StartPage(null); // null, так как WebDriver еще не инициализирован
        return new Object[][]{
                {page.getCostQuestion(), page.getCostAnswer(), page.getCostAnswerItem(), page.getCostAnswerText()},
                {page.getMultipleScootersQuestion(), page.getMultipleScootersAnswer(), page.getMultipleScootersAnswerItem(), page.getMultipleScootersAnswerText()},
                {page.getRentalTimeQuestion(), page.getRentalTimeAnswer(), page.getRentalTimeAnswerItem(), page.getRentalTimeAnswerText()},
                {page.getSameDayDeliveryQuestion(), page.getSameDayDeliveryAnswer(), page.getSameDayDeliveryAnswerItem(), page.getSameDayDeliveryAnswerText()},
                {page.getOrderChangeQuestion(), page.getOrderChangeAnswer(), page.getOrderChangeAnswerItem(), page.getOrderChangeAnswerText()},
                {page.getBatteryLifeQuestion(), page.getBatteryLifeAnswer(), page.getBatteryLifeAnswerItem(), page.getBatteryLifeAnswerText()},
                {page.getCancellationQuestion(), page.getCancellationAnswer(), page.getCancellationAnswerItem(), page.getCancellationAnswerText()},
                {page.getDeliveryAreaQuestion(), page.getDeliveryAreaAnswer(), page.getDeliveryAreaAnswerItem(), page.getDeliveryAreaAnswerText()}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Улучшение: браузер запускается в полноэкранном режиме
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
                .waitLoadAfterClickQuestion(answerItemLocator)
                .getAnswerText(answerLocator);

        assertEquals(expectedAnswerText, actualAnswerText);
    }
}