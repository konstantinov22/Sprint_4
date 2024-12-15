package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.AboutRentPage;
import pageobject.ForWhomPage;
import pageobject.MainPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static base.url.URL.APP_URL;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver webDriver;
    private String browser;
    MainPage mainPage;
    ForWhomPage forWhomPage;
    AboutRentPage aboutRentPage;

    @Parameterized.Parameters(name = "{index}: Browser = {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"firefox"}
        });
    }

    public OrderTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void init() {
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkOrderOfScooterMaximTest() {
        executeOrderTest("Максим", "Константинов", "Ротмистрова 27", "Спортивная", "+79209999999",
                "08.12.2024", "сутки", "серая безысходность", "Жду самокат", true);
    }

    @Test
    public void checkOrderOfScooterOlgaTest() {
        executeOrderTest("Ольга", "Хрусталева", "Калинина 94", "Черкизовская", "+79177777777",
                "15.12.2024", "двое суток", "чёрный жемчуг", "Пишите когда будете подьезжать", false);
    }

    private void executeOrderTest(String firstName, String lastName, String address, String metroStation,
                                  String phoneNumber, String date, String rentalPeriod, String scooterColor,
                                  String comment, boolean useTopButton) {
        mainPage = new MainPage(webDriver);

        webDriver.get(APP_URL);
        mainPage.hideCookieWindow();
        if (useTopButton) {
            mainPage.clickOnOrderTopButton();
        } else {
            mainPage.clickOnOrderBottomButton();
        }

        forWhomPage = new ForWhomPage(webDriver);
        forWhomPage.fillFirstOrderPage(firstName, lastName, address, metroStation, phoneNumber);

        aboutRentPage = new AboutRentPage(webDriver);
        aboutRentPage.fillSecondOrderPage(date, rentalPeriod, scooterColor, comment);

        aboutRentPage.clickOnYesButton();
        aboutRentPage.checkOrderIsConfirm();
    }

    @After
    public void closeBrowser() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}