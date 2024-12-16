package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static base.url.URL.APP_URL;

@RunWith(Parameterized.class)
public class QuestionsTest {
    private WebDriver webDriver;
    private String browser;
    private int questionIndex;
    private String expectedAnswer;
    private MainPage mainPage;

    private static final Object[][] TEST_DATA = new Object[][] {
            {"chrome", 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"chrome", 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"chrome", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"chrome", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"chrome", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"chrome", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"chrome", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"chrome", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
            {"firefox", 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"firefox", 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"firefox", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"firefox", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"firefox", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"firefox", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"firefox", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"firefox", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
    };

    @Parameterized.Parameters(name = "{index}: Browser = {0}, Question = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(TEST_DATA);
    }

    public QuestionsTest(String browser, int questionIndex, String expectedAnswer) {
        this.browser = browser;
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
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
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        mainPage = new MainPage(webDriver);
        webDriver.get(APP_URL);
        mainPage.hideCookieWindow();
        mainPage.scrollToSectionOfQuestions();
    }

    @Test
    public void checkAnswer() {
        mainPage.clickOnQuestion(questionIndex);
        String actualAnswerText = mainPage.getTextOfAnswer(questionIndex);
        Assert.assertEquals(expectedAnswer, actualAnswerText);
    }

    @After
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
