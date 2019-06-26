package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

    @FindBy(css="#tl_login")
    public WebElement loginField;

    @FindBy(css="#tl_password")
    public WebElement passwordField;

    @FindBy(css="[type=submit]")
    public WebElement submitButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void logIn(String login, String password) {
        driver.get(System.getProperty("baseUrl") + "/login.php");
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        submitButton.click();
    }
}
