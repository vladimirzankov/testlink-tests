package pages;

import model.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class TestSpecificationPage extends Page{

    Frame mainframe;
    Frame treeframe;
    Frame workframe;

    @FindBy(css="img[title=Actions]")
    private WebElement actions;

    @FindBy(css="#create_tc")
    private WebElement createTestCaseButton;

    public TestSpecificationPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.treeframe = new Treeframe(driver);
        this.workframe = new Workframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void initTestSuiteCreation() {
        switchTo(workframe);
        driver.findElement(By.cssSelector("img[title=Actions]")).click();
        driver.findElement(By.cssSelector("#new_testsuite")).click();
    }

    public void initTestCaseCreation(TestSuiteData testSuite) {
        switchTo(treeframe);
        getTestSuiteElementByName(testSuite.getName()).click();
        switchTo(workframe);
        actions.click();
        createTestCaseButton.click();
    }

    public WebElement getTestSuiteElementByName(String name) {
        String locator = String.format("//span[contains(text(), '%s')]", name);
        return driver.findElement(By.xpath(locator));
    }
}
