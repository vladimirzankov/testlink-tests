package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class AddTestCaseToTestPlans extends Page {

    Frame mainframe;
    Frame workframe;

    @FindBy(css="#add2testplan")
    private WebElement addButton;

    @FindBy(css="input[name=cancel]")
    private WebElement cancelButton;

    public AddTestCaseToTestPlans(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.workframe = new Workframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void addToTestPlan(String testPlanName) {
        switchTo(workframe);
        driver.findElement(By.xpath("//tr[td[text()='" + testPlanName + "']]/td[1]/input")).click();
        addButton.click();
    }
}
