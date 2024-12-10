package Chrome.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class AboutRentPage {
    WebDriver webDriver;

    public AboutRentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.xpath(".//span[@class='Dropdown-arrow']");
    private By commentInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private By OrderСonfirmWindow = By.xpath(".//*[text()='Заказ оформлен']");

    public void writeDateOfDelivery(String date) {
        webDriver.findElement(dateInput).sendKeys(date);
    }

    public void chooseRentalPeriod(String time) {
        webDriver.findElement(rentalPeriodDropdown).click();
        webDriver.findElement(By.xpath(String.format("//*[text()='%s']", time))).click();
    }

    public void chooseColor(String color) {
        webDriver.findElement(By.xpath(String.format(".//*[text()='%s']", color))).click();
    }

    public void leaveComment(String comment) {
        webDriver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOnOrderButton() {
        webDriver.findElement(orderButton).click();
    }

    public void clickOnYesButton() {
        webDriver.findElement(yesButton).click();
    }

    public void checkOrderIsConfirm() {
        var isDisplayed = webDriver.findElement(OrderСonfirmWindow).isDisplayed();
        assertTrue("Заказ не зарегистрирован", isDisplayed);
    }

    public void fillSecondOrderPage(String date, String time, String color, String comment) {
        writeDateOfDelivery(date);
        chooseRentalPeriod(time);
        chooseColor(color);
        leaveComment(comment);
        clickOnOrderButton();

    }
}
