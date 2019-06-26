package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.Frame;
import pages.frames.Mainframe;

public class TestPlanManagementPage extends Page {

    Frame mainframe;

    @FindBy(css="[name=create_testplan]")
    private WebElement createButton;

    public TestPlanManagementPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void initTestPlanCreation() {
        switchTo(mainframe);
        createButton.click();
    }
}
