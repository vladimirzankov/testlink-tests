package pages;

import model.TestPlanData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.Frame;
import pages.frames.Mainframe;

public class TestPlanCreationPage extends Page {

    Frame mainframe;

    @FindBy(css="[name=testplan_name]")
    private WebElement name;

    @FindBy(xpath="//tr[contains(.,'Active')]//input[@type='checkbox']")
    private WebElement isActive;

    @FindBy(xpath="//tr[contains(.,'Public')]//input[@type='checkbox']")
    private WebElement isPublic;

    @FindBy(css="[name=do_create]")
    private WebElement createButton;

    @FindBy(css="[name=go_back]")
    private WebElement cancelButton;

    public TestPlanCreationPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void create(TestPlanData testPlan) {
        switchTo(mainframe);
        name.sendKeys(testPlan.getName());
        if(testPlan.isActive()) isActive.click();
        if(testPlan.isPublic()) isPublic.click();
        createButton.click();
    }
}
