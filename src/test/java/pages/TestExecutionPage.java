package pages;

import model.TestCaseData;
import model.ExecutionData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.frames.*;

import java.util.List;

public class TestExecutionPage extends Page {

    Frame mainframe;
    Frame workframe;
    Frame treeframe;

    @FindBy(css="select.step_status")
    private List<WebElement> executionStatuses;

    @FindBy(css="[id ^= fastExecp]")
    private WebElement passButton;

    @FindBy(css="[id ^= fastExecf]")
    private WebElement failButton;

    @FindBy(css="[id ^= fastExecb]")
    private WebElement blockButton;

    @FindBy(css="[title='Delete execution']")
    private WebElement deleteExecutionButton;

    @FindBy(css="#toggle_history_on_off[name=btn_history_off]")
    private WebElement historyOffButton;

    @FindBy(xpath="//div[@class='exec_history_title']/following-sibling::div")
    private WebElement lastExecution;

    public WebElement selectTestSuiteInTree(String testSuiteName) {
        return driver.findElement(By.xpath("//div[a[span[contains(text(), '" + testSuiteName + " (')]]]"));
    }

    public TestExecutionPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        this.workframe = new Workframe(driver);
        this.treeframe = new Treeframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void initTestCaseExecution(TestCaseData testCase) {
        switchTo(treeframe);
        WebElement testSuiteNode = selectTestSuiteInTree(testCase.getTestSuite().getName());
        testSuiteNode.findElement(By.xpath("img[1]")).click();
        testSuiteNode.findElement(By.xpath("following-sibling::ul//a[contains(., '" + testCase.getTitle() + "')]")).click();
    }

    public void executeTestCase(ExecutionData testCasePassing) {
        switchTo(workframe);
        for(int i = 0; i < executionStatuses.size(); i++) {
            if(testCasePassing.getSteps().get(i) != null)
                new Select(executionStatuses.get(i)).selectByVisibleText(testCasePassing.getSteps().get(i).getStatus().toString());
        }
        switch(testCasePassing.getStatus()) {
            case PASSED:
                passButton.click(); break;
            case FAILED:
                failButton.click(); break;
            case BLOCKED:
                blockButton.click(); break;
        }
    }

    public Color getLastExecutionColor() {
        switchTo(workframe);
        try {
            return Color.fromString(lastExecution.getCssValue("background-color"));
        }
        catch(org.openqa.selenium.NoSuchElementException ex) {
            historyOffButton.click();
            return Color.fromString(lastExecution.getCssValue("background-color"));
        }
    }

    public Color getColorInTree(TestCaseData testCase) {
        switchTo(treeframe);
        WebElement testSuiteNode = selectTestSuiteInTree(testCase.getTestSuite().getName());
        return Color.fromString(testSuiteNode.findElement(By.xpath("following-sibling::ul//a[contains(., '" + testCase.getTitle() + "')]/span/span")).getCssValue("background-color"));
    }

}
