package pages;

import controls.Checkbox;
import model.TestProjectData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.Frame;
import pages.frames.Mainframe;

public class TestProjectCreationPage extends Page {

    @FindBy(css="select[name=copy_from_tproject_id]")
    private WebElement selectExistingProject;

    @FindBy(css="input[name=tprojectName]")
    private WebElement name;

    @FindBy(css="input[name=tcasePrefix]")
    private WebElement prefix;

    @FindBy(css="input[name=optReq]")
    private WebElement enableRequirements;

    @FindBy(css="input[name=optPriority]")
    private WebElement enablePriority;

    @FindBy(css="input[name=optAutomation]")
    private WebElement enableAutomation;

    @FindBy(css="input[name=optInventory]")
    private WebElement enableInventory;

    @FindBy(css="input[name=active]")
    private WebElement isActive;

    @FindBy(css="input[name=is_public]")
    private WebElement isPublic;

    @FindBy(css="input[name=doActionButton]")
    private WebElement createButton;

    @FindBy(css="input[name=go_back]")
    private WebElement cancelButton;

    Frame mainframe;

    public TestProjectCreationPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void create(TestProjectData project) {
        name.sendKeys(project.getName());
        prefix.sendKeys(project.getPrefix());
        new Checkbox(enableRequirements).interact(project.isEnableRequirements());
        new Checkbox(enablePriority).interact(project.isEnableTestPriority());
        new Checkbox(enableAutomation).interact(project.isEnableAutomation());
        new Checkbox(enableInventory).interact(project.isEnableInventory());
        new Checkbox(isActive).interact(project.isActive());
        new Checkbox(isPublic).interact(project.isPublic());
        createButton.click();
    }
}
