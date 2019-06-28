package tests;

import app.APIHelper;
import app.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.*;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static final Logger logger = LogManager.getLogger(TestCaseCreationTests.class);
    PropertiesConfiguration properties;
    ChromeOptions options;
    protected WebDriver driver;
    protected WebDriverWait wait;
    public LoginPage loginPage;
    public HomePage homePage;
    public TestProjectManagementPage testProjectManagementPage;
    public TestProjectCreationPage testProjectCreationPage;
    public TestSpecificationPage testSpecificationPage;
    public TestSuiteCreation testSuiteCreation;
    public TestCaseCreation testCaseCreation;
    public TestCaseEditing testCaseEditing;
    public StepsCreation stepsCreation;
    public AddTestCaseToTestPlans addTestCaseToTestPlans;
    public TestPlanManagementPage testPlanManagementPage;
    public TestPlanCreationPage testPlanCreationPage;
    public BuildManagementPage buildManagementPage;
    public BuildCreationPage buildCreationPage;
    public TestExecutionPage testExecutionPage;
    public APIHelper apiHelper;

    //...... Initial test data ......//

    TestProjectData initialProject = new TestProjectData(null, "Initial project", "Initial", null, false, true, true, false, true, true);
    TestPlanData initialTestPlan = new TestPlanData("Initial test plan", null,true, true);
    TestSuiteData initialTestSuite = new TestSuiteData("Initial test suite", null);
    TestCaseData initialTestCase = new TestCaseData.Builder()
            .setTestSuite(initialTestSuite)
            .setTitle("Initial test case")
            .setSteps(Arrays.asList(
                    new TestCaseData.Step("Action 1", "Result 1", TestCaseData.ExecutionType.AUTOMATED),
                    new TestCaseData.Step("Action 2", "Result 2", TestCaseData.ExecutionType.AUTOMATED),
                    new TestCaseData.Step("Action 3", "Result 3", TestCaseData.ExecutionType.AUTOMATED)))
            .build();
    BuildData initialBuild = new BuildData(initialTestPlan, "Initial build");

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        properties = new PropertiesConfiguration();
        properties.read(new FileReader(new File(String.format("src/test/resources/config.properties"))));
        SystemConfiguration.setSystemProperties(properties);
        apiHelper = new APIHelper();
        apiHelper.create(initialProject, initialTestPlan, initialBuild, initialTestSuite, initialTestCase);
        driver = new WebDriverFactory().createNew(System.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 4);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        testProjectManagementPage = new TestProjectManagementPage(driver);
        testProjectCreationPage = new TestProjectCreationPage(driver);
        testSpecificationPage = new TestSpecificationPage(driver);
        testSuiteCreation = new TestSuiteCreation(driver);
        testCaseCreation = new TestCaseCreation(driver);
        testCaseEditing = new TestCaseEditing(driver);
        stepsCreation = new StepsCreation(driver);
        addTestCaseToTestPlans = new AddTestCaseToTestPlans(driver);
        testPlanManagementPage = new TestPlanManagementPage(driver);
        testPlanCreationPage = new TestPlanCreationPage(driver);
        buildManagementPage = new BuildManagementPage(driver);
        buildCreationPage = new BuildCreationPage(driver);
        testExecutionPage = new TestExecutionPage(driver);
    }

    @After
    public void teardown() {
        homePage.open();
        homePage.goToTestProjectManagement();
        testProjectManagementPage.deleteProject(initialProject.getName());
        if (driver != null)
            driver.quit();
    }
}
