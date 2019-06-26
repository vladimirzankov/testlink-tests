package pages.frames;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Mainframe extends Frame {
    WebDriver driver;
    DefaultContent parent;

    @FindBy(css="frame[name=treeframe]")
    private WebElement treeframe;

    @FindBy(css="frame[name=workframe]")
    private WebElement workframe;

    public Mainframe(WebDriver driver) {
        this.driver = driver;
        parent = new DefaultContent(driver);
        PageFactory.initElements(driver, this);
    }

    public void switchToSelf() {
        parent.switchToSelf();
        parent.switchToMainframe();
    }

    public void switchToTreeframe() {
        driver.switchTo().frame(treeframe);
    }

    public void switchToWorkframe() {
        driver.switchTo().frame(workframe);
    }
}
