package pages;

import model.TestProjectData;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.frames.*;

public class TestProjectManagementPage extends Page {

    @FindBy(css="#create")
    private WebElement createButton;

    @FindBy(xpath="//button[text() = 'Yes']")
    private WebElement popupYesButton;

    Frame mainframe;

    public TestProjectManagementPage(WebDriver driver) {
        this.driver = driver;
        this.mainframe = new Mainframe(driver);
        PageFactory.initElements(driver, this);
    }

    public void initProjectCreation() {
        switchTo(mainframe);
        createButton.click();
    }

    public void deleteProject(String projectName) {
        switchTo(mainframe);
        driver.findElement(By.xpath("//tr[td[a[contains(text(), '" + projectName + "')]]]/td[last()]/img")).click();
        popupYesButton.click();
    }

    public WebElement findProjectRow(String projectName) {
        switchTo(mainframe);
        return driver.findElement(By.xpath("//tr[td[a[normalize-space(text())='" + projectName + "']]]"));
    }

    public void checkDisplayedProjectSettings(TestProjectData project) {
        WebElement row = findProjectRow(project.getName());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(row.findElement(By.xpath("td[2]")).getText()).isEqualTo(project.getDescription());
        softly.assertThat(row.findElement(By.xpath("td[3]")).getText()).isEqualTo(project.getPrefix());
        softly.assertThat(row.findElement(By.xpath("td[6]/input")).getAttribute("title").contains("Enabled")).isEqualTo(project.isEnableRequirements());
        softly.assertThat(row.findElement(By.xpath("td[7]/input")).getAttribute("title").contains("Active")).isEqualTo(project.isActive());
        softly.assertThat(row.findElement(By.xpath("td[8]/img")).getAttribute("title").equals("Public")).isEqualTo(project.isPublic());
        softly.assertAll();
    }

}
