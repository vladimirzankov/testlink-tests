package pages;

import model.TestCaseData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.frames.*;

import java.util.List;

public class StepsCreation extends Page {

    Frame mainframe;
    Frame workframe;

    private final String stepsLocator = "iframe[title='Rich Text Editor, steps']";
    @FindBy(css=stepsLocator)
    private WebElement steps;

    @FindBy(css="iframe[title='Rich Text Editor, expected_results']")
    private WebElement expectedResults;

    @FindBy(css="select[name='exec_type']")
    private WebElement executionType;

    @FindBy(css="#do_update_step")
    private WebElement saveButton;

    @FindBy(css="#do_update_step_and_exit")
    private WebElement saveAndExitButton;

    @FindBy(css="input[name=cancel]")
    private WebElement cancelButton;

    public StepsCreation(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.workframe = new Workframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void create(List<TestCaseData.Step> steps) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        int count = getRowsCount();
        switchTo(workframe);
        for(TestCaseData.Step step : steps) {
            driver.switchTo().frame(this.steps);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"p\")[0].innerHTML=\"" + step.getActions() + "\"");
            driver.switchTo().parentFrame();
            driver.switchTo().frame(this.expectedResults);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"p\")[0].innerHTML=\"" + step.getResults() + "\"");
            driver.switchTo().parentFrame();
            Select executionType = new Select(this.executionType);
            executionType.selectByVisibleText(step.getExecutionType().toString());
            WebElement staleStepsElement = driver.findElement(By.cssSelector(stepsLocator));
            saveButton.click();
            wait.until(ExpectedConditions.stalenessOf(staleStepsElement));
        }
        cancelButton.click();
    }

    public int getRowsCount() {
        return driver.findElements(By.cssSelector("[id *= 'step_row']")).size();
    }
}
