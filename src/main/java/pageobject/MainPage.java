package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver webDriver;

    private By sectionOfQuestions = By.xpath(".//div[@class='accordion']");
    private By cookieButtonLocator = By.xpath(".//div/button[@id='rcc-confirm-button']");
    private By orderTopButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    private By orderBottomButton = By.xpath("//div[5]/button");
    private String questionLocatorTemplate = ".//div[@class='accordion__button' and @id='accordion__heading-%d']";
    private String answerLocatorTemplate = ".//div[@id='accordion__panel-%d']";

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void hideCookieWindow() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement cookieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieButtonLocator));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", cookieButton);
        cookieButton.click();
    }

    public void scrollToSectionOfQuestions() {
        WebElement sectionElement = webDriver.findElement(sectionOfQuestions);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", sectionElement);
    }

    public void clickOnQuestion(int questionIndex) {
        By questionLocator = By.xpath(String.format(questionLocatorTemplate, questionIndex));
        webDriver.findElement(questionLocator).click();
    }

    public String getTextOfAnswer(int answerIndex) {
        By answerLocator = By.xpath(String.format(answerLocatorTemplate, answerIndex));
        return webDriver.findElement(answerLocator).getText();
    }

    public void clickOnOrderTopButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(orderTopButton));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", orderButton);
        orderButton.click();
    }

    public void clickOnOrderBottomButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(orderBottomButton));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", orderButton);
        orderButton.click();
    }
}