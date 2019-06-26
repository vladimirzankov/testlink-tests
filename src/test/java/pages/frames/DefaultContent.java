package pages.frames;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DefaultContent extends Frame {

    public DefaultContent(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(css="frame[name=mainframe]")
    private WebElement mainframe;

    public void switchToSelf() {
        driver.switchTo().defaultContent();
        PageFactory.initElements(driver, this);
    }

    public void switchToMainframe() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(mainframe);
    }

}
