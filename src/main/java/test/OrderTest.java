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
    private final String browser;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;
    private final boolean useTopButton;

    MainPage mainPage;
    ForWhomPage forWhomPage;
    AboutRentPage aboutRentPage;

    @Parameterized.Parameters(name = "{index}: Browser={0}, FirstName={1}, LastName={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome", "Максим", "Константинов", "Ротмистрова 27", "Спортивная", "+79209999999",
                        "08.12.2024", "сутки", "серая безысходность", "Жду самокат", true},
                {"chrome", "Ольга", "Хрусталева", "Калинина 94", "Черкизовская", "+79177777777",
                        "15.12.2024", "двое суток", "чёрный жемчуг", "Пишите когда будете подьезжать", true},
                {"firefox", "Максим", "Константинов", "Ротмистрова 27", "Спортивная", "+79209999999",
                        "08.12.2024", "сутки", "серая безысходность", "Жду самокат", false},
                {"firefox", "Ольга", "Хрусталева", "Калинина 94", "Черкизовская", "+79177777777",
                        "15.12.2024", "двое суток", "чёрный жемчуг", "Пишите когда будете подьезжать", false}
        });
    }

    public OrderTest(String browser, String firstName, String lastName, String address, String metroStation,
                     String phoneNumber, String date, String rentalPeriod, String scooterColor,
                     String comment, boolean useTopButton) {
        this.browser = browser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
        this.useTopButton = useTopButton;
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
    public void checkOrderTest() {
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
