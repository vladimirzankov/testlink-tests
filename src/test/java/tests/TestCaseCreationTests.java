package tests;

import model.TestCaseData;
import model.TestSuiteData;
import org.junit.Test;
import java.util.Arrays;
import static model.TestCaseData.*;

public class TestCaseCreationTests extends LoginFixture {

    TestSuiteData testSuite = new TestSuiteData("TestSuite1", "test");
    TestCaseData testCase1 = new TestCaseData.Builder()
            .setTestSuite(testSuite)
            .setTitle("Создание нового тест-сьюита")
            .setSummary(".")
            .setPreconditions("В тест-линке должен быть создан продукт, чтобы можно было создавать тесты.")
            .setStatus(TestCaseData.Status.READY_FOR_REVIEW)
            .setImportance(TestCaseData.Importance.HIGH)
            .setExecutionType(TestCaseData.ExecutionType.AUTOMATED)
            .setDuration(60)
            .setSteps(Arrays.asList(
                    new Step("Открыть окно редактирования тестов", "Окно открыто", ExecutionType.AUTOMATED),
                    new Step("Создать новый тест-сьюит, заполнив все поля", "Тест-сьюит создан", ExecutionType.MANUAL),
                    new Step("Создать новый тест-сьюит, заполнив все поля", "Тест-сьюит создан", ExecutionType.AUTOMATED)))
            .build();
    TestCaseData testCase2 = new TestCaseData.Builder()
            .setTestSuite(testSuite)
            .setTitle("Login Test")
            .setSummary("No summary")
            .setPreconditions("No preconditions")
            .setStatus(TestCaseData.Status.FINAL)
            .setImportance(TestCaseData.Importance.LOW)
            .setExecutionType(TestCaseData.ExecutionType.MANUAL)
            .setDuration(1)
            .setSteps(Arrays.asList(
                    new Step("Enter Email Address", "Done", ExecutionType.AUTOMATED),
                    new Step("Enter Password", "Done", ExecutionType.MANUAL),
                    new Step("Click Sign in", "Login successful", ExecutionType.AUTOMATED)))
            .build();

    @Test
    public void createTestSuiteAndTestCases() {
        homePage.open(initialProject);
        homePage.goToTestSpecificationPage();
        testSpecificationPage.initTestSuiteCreation();
        testSuiteCreation.create(testSuite);
        testSpecificationPage.initTestCaseCreation(testSuite);
        testCaseCreation.create(testCase1);
        testCaseEditing.initStepsCreation();
        stepsCreation.create(testCase1.getSteps());
        testSpecificationPage.initTestCaseCreation(testSuite);
        testCaseCreation.create(testCase2);
    }
}
