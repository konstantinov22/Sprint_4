package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForWhomPage {
    WebDriver webDriver;
    public ForWhomPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    private By nameInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@placeholder='* Имя']");
    private By surnameInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@placeholder='* Фамилия']");
    private By adressInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[contains(@placeholder, 'Адрес')]");
    private By metroInput = By.xpath(".//div[@class='select-search__value']/input[@placeholder='* Станция метро']");
    private By telephoneInput = By.xpath(".//div[@class='Input_InputContainer__3NykH']/input[@placeholder='* Телефон: на него позвонит курьер']");
    private By continueButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public void writeName(String name) {
        webDriver.findElement(nameInput).sendKeys(name);
    }

    public void writeSurname(String surname) {
        webDriver.findElement(surnameInput).sendKeys(surname);
    }

    public void writeAdress(String adress) {
        webDriver.findElement(adressInput).sendKeys(adress);
    }

    public void clickOnMetroStation(String metro) {
        webDriver.findElement(metroInput).click();
        webDriver.findElement(By.xpath(String.format("//*[text()='%s']",metro))).click();
    }

    public void writeTelephone(String telephone) {
        webDriver.findElement(telephoneInput).sendKeys(telephone);
    }
    public void clickOnContinueButton() {
        webDriver.findElement(continueButton).click();
    }
    public void fillFirstOrderPage(String name, String surname, String adress, String metro, String telephone) {
        writeName(name);
        writeSurname(surname);
        writeAdress(adress);
        clickOnMetroStation(metro);
        writeTelephone(telephone);
        clickOnContinueButton();
    }
}
