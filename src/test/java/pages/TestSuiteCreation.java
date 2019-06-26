package pages;

import model.TestSuiteData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class TestSuiteCreation extends Page {

    Frame mainframe;
    Frame workframe;

    @FindBy(css="#name")
    private WebElement name;

    @FindBy(css="iframe[title='Rich Text Editor, details']")
    private WebElement details;

    @FindBy(css="input[value=Save]")
    private WebElement saveButton;

    public TestSuiteCreation(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.workframe = new Workframe(driver);
        PageFactory.initElements(driver, this);
    }
    public void create(TestSuiteData testSuite) {
        switchTo(workframe);
        name.sendKeys(testSuite.getName());
        if(testSuite.getDetails() != null) {
            driver.switchTo().frame(this.details);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"p\")[0].innerHTML=\""+ testSuite.getDetails() + "\";");
            driver.switchTo().parentFrame();
        }
        saveButton.click();
    }
}