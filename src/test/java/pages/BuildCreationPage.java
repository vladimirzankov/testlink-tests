package pages;

import model.BuildData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.Frame;
import pages.frames.Mainframe;

public class BuildCreationPage extends Page {

    Frame mainframe;

    @FindBy(css="#build_name")
    private WebElement title;

    @FindBy(css="[name=do_create]")
    private WebElement createButton;

    public BuildCreationPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void create(BuildData build) {
        switchTo(mainframe);
        title.sendKeys(build.getTitle());
        createButton.click();
    }
}
