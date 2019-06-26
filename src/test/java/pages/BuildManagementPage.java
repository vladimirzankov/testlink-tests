package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class BuildManagementPage extends Page {

    Frame mainframe;

    @FindBy(css="input[name=create_build_bottom]")
    private WebElement createButton;

    public BuildManagementPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void initBuildCreation() {
        switchTo(mainframe);
        createButton.click();
    }
}
