package Firefox.test;

import Firefox.pageobject.AboutRentPage;
import Firefox.pageobject.ForWhomPage;
import Firefox.pageobject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static Chrome.URL.URL.APP_URL;

public class OrderTest {
    WebDriver webDriver;
    MainPage mainPage;
    ForWhomPage forWhomPage;
    AboutRentPage aboutRentPage;

    @Before
    public void init() {
        WebDriverManager.firefoxdriver().setup();;
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkOrderOfScooter1() {
        mainPage = new MainPage(webDriver);

        webDriver.get(APP_URL);
        mainPage.hideCookieWindow();
        mainPage.clickOnOrderButton();

        forWhomPage = new ForWhomPage(webDriver);
        forWhomPage.fillFirstOrderPage("Максим","Константинов","Ротмистрова 27", "Спортивная", "+79201869974");

        aboutRentPage = new AboutRentPage(webDriver);
        aboutRentPage.fillSecondOrderPage("08.12.2024", "сутки", "серая безысходность", "Жду самокат");

        aboutRentPage.clickOnYesButton();
        aboutRentPage.checkOrderIsConfirm();
    }

    @Test
    public void checkOrderOfScooter() {
        mainPage = new MainPage(webDriver);

        webDriver.get(APP_URL);
        mainPage.hideCookieWindow();
        mainPage.clickOnOrderButton();

        forWhomPage = new ForWhomPage(webDriver);
        forWhomPage.fillFirstOrderPage("Ольга","Хрусталева","Калинина 94", "Черкизовская", "+79105368481");

        aboutRentPage = new AboutRentPage(webDriver);
        aboutRentPage.fillSecondOrderPage("15.12.2024", "двое суток", "чёрный жемчуг", "Пишите когда будете подьезжать");

        aboutRentPage.clickOnYesButton();
        aboutRentPage.checkOrderIsConfirm();
    }

    @After
    public void closeBrowser() {
        webDriver.quit();
    }
}


