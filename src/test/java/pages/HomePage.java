package pages;

import model.TestProjectData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class HomePage extends Page {

    Mainframe mainframe;

    @FindBy(css="[name=titlebar]")
    private WebElement titlebar;

    @FindBy(css="img[title=Logout]")
    private WebElement logoutButton;

    @FindBy(css="select[name=testproject]")
    private WebElement selectTestProject;

    @FindBy(xpath=("//a[contains(text(), 'Test Project Management')]"))
    private WebElement testProjectManagement;

    @FindBy(xpath="//a[contains(text(), 'Test Specification')]")
    private WebElement testSpecification;

    @FindBy(xpath=("//a[text()='Test Plan Management']"))
    private WebElement testPlanManagement;

    @FindBy(xpath=("//a[text()='Builds / Releases']"))
    private WebElement buildManagement;

    @FindBy(xpath=("//a[text()='Execute Tests']"))
    private WebElement testExecution;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(System.getProperty("baseUrl") +"/index.php");
    }

    public void open(TestProjectData project) {

        open();
        driver.switchTo().frame(titlebar);
        selectTestProject.click();
        selectTestProject.findElement(By.cssSelector("option[title = '" + project.getPrefix() + ":" + project.getName() + "']")).click();
    }

    public void logOut() {
        driver.switchTo().frame(titlebar);
        logoutButton.click();
    }

    public void goToTestProjectManagement() {
        switchTo(mainframe);
        testProjectManagement.click();
    }

    public void goToTestSpecificationPage() {
        switchTo(mainframe);
        testSpecification.click();
    }

    public void goToTestPlanManagementPage() {
        switchTo(mainframe);
        testPlanManagement.click();
    }

    public void goToBuildManagementPage() {
        switchTo(mainframe);
        buildManagement.click();
    }

    public void goToTestExecutionPage() {
        switchTo(mainframe);
        testExecution.click();
    }
}
