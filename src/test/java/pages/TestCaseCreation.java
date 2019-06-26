package pages;

import model.TestCaseData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.frames.*;

public class TestCaseCreation extends Page {

    Frame mainframe;
    Frame workframe;

    @FindBy(css="#testcase_name")
    private WebElement title;

    @FindBy(css="iframe[title='Rich Text Editor, summary']")
    private WebElement summary;

    @FindBy(css="iframe[title='Rich Text Editor, preconditions']")
    private WebElement preconditions;

    @FindBy(css="p")
    private WebElement pElement;

    @FindBy(css="select#tc_status")
    private WebElement status;

    @FindBy(css="select[name=importance]")
    private WebElement importance;

    @FindBy(css="select[name=exec_type]")
    private WebElement exceptionType;

    @FindBy(css="#estimated_execution_duration")
    private WebElement duration;

    @FindBy(css="#do_create_button_2")
    private WebElement createButton;

    public TestCaseCreation (WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.workframe = new Workframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void create(TestCaseData testCase) {
        switchTo(workframe);
        title.sendKeys(testCase.getTitle());
        if(testCase.getSummary() != null) {
            driver.switchTo().frame(summary);
            /*pElement.click();
            String summary = testCase.getSummary().replaceAll("\n", "" + Keys.ENTER);
            driver.switchTo().activeElement().sendKeys(summary);*/
            ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"p\")[0].innerHTML=\"" + testCase.getSummary() + "\";");
            driver.switchTo().parentFrame();
        }
        if(testCase.getPreconditions() != null) {
            driver.switchTo().frame(preconditions);
            ((JavascriptExecutor)driver).executeScript("document.getElementsByTagName(\"p\")[0].innerHTML=\"" + testCase.getPreconditions() + "\";");
            driver.switchTo().parentFrame();
        }
        if(testCase.getStatus() != null) {
            Select status = new Select(this.status);
            status.selectByVisibleText(testCase.getStatus().toString());
        }
        if(testCase.getImportance() != null) {
            Select importance = new Select(this.importance);
            importance.selectByVisibleText(testCase.getImportance().toString());
        }
        if(testCase.getExecutionType() != null) {
            Select exceptionType = new Select(this.exceptionType);
            exceptionType.selectByVisibleText(testCase.getExecutionType().toString());
        }
        duration.sendKeys(String.valueOf(testCase.getDuration()));
        createButton.click();
    }
}
