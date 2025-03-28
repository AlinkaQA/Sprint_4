package ru.yandex.praсtikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praсtikum.pages.ScooterInfoPage;
import ru.yandex.praсtikum.pages.StartPage;
import ru.yandex.praсtikum.pages.StartPoint;
import ru.yandex.praсtikum.pages.UserInfo;
import ru.yandex.praсtikum.pages.constants.OrderButton;
import ru.yandex.praсtikum.pages.constants.ScooterColor;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praсtikum.pages.constants.OrderButton.DOWN_BUTTON;
import static ru.yandex.praсtikum.pages.constants.OrderButton.UP_BUTTON;
import static ru.yandex.praсtikum.pages.constants.RentTime.*;

@RunWith(Parameterized.class)
public class OrderCheckTest {

    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final String stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final ScooterColor colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final OrderButton button;

    public OrderCheckTest(OrderButton button,
                          String name,
                          String surname,
                          String address,
                          String stateMetroNumber,
                          String telephoneNumber,
                          String date,
                          String duration,
                          ScooterColor colour,
                          String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "Иван", "Иванов", "ул. Тверская, д. 15", "7", "79292058292", "14.07.2023", FIVE_DAYS, ScooterColor.GREY, "comments three"},
                {UP_BUTTON, "Пётр", "Петров", "ул. Новый Арбат, д. 22", "10", "79291137811", "22.02.2022", SIX_DAYS , ScooterColor.BLACK, "comments one"},
                {UP_BUTTON, "Алексей", "Сидоров", "ул. Большая Никитская, д. 10", "123", "79298311711", "28.05.2023", ONE_DAY, ScooterColor.BLACK, "comments one"},
                {DOWN_BUTTON, "Дмитрий", "Козлов", "ул. Садовая-Кудринская, д. 5", "10", "79293345733", "17.05.2018", FIVE_DAYS, ScooterColor.GREY, "comments two"},
                {DOWN_BUTTON, "Сергей", "Морозов", "ул. Пресненский Вал, д. 8", "1", "79992345252", "12.09.2023", ONE_DAY, ScooterColor.BLACK, "comments one"},
                {DOWN_BUTTON, "Михаил", "Васильев", "ул. Краснопролетарская, д. 12", "123", "79293936303", "21.01.2021", SIX_DAYS, ScooterColor.BLACK, "comments one"} ,
        };
    }

    @Before
    public void startUp() {
        String browser = System.getProperty("browser", "firefox");

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get(site);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateOrderWithUpButton() {
        new StartPage(driver)
                .waitForLoadHomePage()
                .clickCookiesButton()
                .clickCreateOrderButton(button);

        new UserInfo(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();


        new ScooterInfoPage(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        StartPoint startPoint = new StartPoint(driver);
        startPoint.clickButtonYes();

        assertTrue(startPoint.getHeaderAfterCreateOrder().contains(expectedHeader));
    }
}
