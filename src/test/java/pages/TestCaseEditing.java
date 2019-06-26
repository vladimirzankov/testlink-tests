package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class TestCaseEditing extends Page {

    Frame mainframe;
    Frame workframe;

    @FindBy(css="img[title=Actions]")
    private WebElement actions;

    @FindBy(css="[name='create_step']")
    private WebElement createStepButton;

    @FindBy(css="[id ^= addTc2Tplan]")
    private WebElement addToTestPlanButton;

    public TestCaseEditing(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.workframe = new Workframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void initStepsCreation() {

        createStepButton.click();
    }

    public void initAddingToTestPlan() {
        switchTo(workframe);
        actions.click();
        addToTestPlanButton.click();
    }

}
