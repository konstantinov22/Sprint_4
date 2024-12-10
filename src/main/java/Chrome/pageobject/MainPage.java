package Chrome.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By sectionOfQuestions = By.xpath(".//div[@class='accordion']");

    public void hideCookieWindow() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        By cookieButtonLocator = By.xpath(".//div/button[@id='rcc-confirm-button']");
        WebElement cookieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieButtonLocator));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", cookieButton);
        cookieButton.click();
    }

    public void scrollToSectionOfQuestions() {
        WebElement sectionElement = webDriver.findElement(sectionOfQuestions);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", sectionElement);
    }

    public void clickOnQuestion(int questionIndex) {
        By questionLocator = By.xpath(".//div[@class='accordion__button' and @id='accordion__heading-" + questionIndex + "']");
        webDriver.findElement(questionLocator).click();
    }

    public String getTextOfAnswer(int answerIndex) {
        By answerLocator = By.xpath(".//div[@id='accordion__panel-" + answerIndex + "']");
        return webDriver.findElement(answerLocator).getText();
    }

    public void clickOnOrderButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        By orderButtonLocator = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButtonLocator));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", orderButton);
        orderButton.click();
    }
}
