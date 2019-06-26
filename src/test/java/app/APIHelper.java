package app;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.*;
import model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APIHelper {

    TestLinkAPI api;

    public APIHelper() throws Exception {
        api = new TestLinkAPI(new URL(System.getProperty("baseUrl") + "/lib/api/xmlrpc/v1/xmlrpc.php"), System.getProperty("adminDevKey"));
    }

    public TestProject create(TestProjectData project) {
        return api.createTestProject(project.getName(), project.getPrefix(), project.getDescription(), project.isEnableRequirements(), project.isEnableTestPriority(), project.isEnableAutomation(), project.isEnableInventory(), project.isActive(), project.isPublic());
    }

    public void create(TestProjectData project, TestPlanData testPlan, BuildData build, TestSuiteData testSuite, TestCaseData testCase) {
        TestProject createdProject = create(project);
        TestPlan createdTestPlan = api.createTestPlan(testPlan.getName(), createdProject.getName(), testPlan.getDescription(), testPlan.isActive(), testPlan.isPublic());
        Build createdBuild = api.createBuild(createdTestPlan.getId(), build.getTitle(), build.getDescription());
        TestSuite createdTestSuite = api.createTestSuite(createdProject.getId(), testSuite.getName(), testSuite.getDetails(), null, 0, true, ActionOnDuplicate.BLOCK);
        List<TestCaseStep> stepsForCreate = new ArrayList<>();
        TestCaseData.Step currentStep;
        for(int i = 0; i < testCase.getSteps().size(); i++) {
            currentStep = testCase.getSteps().get(i);
            stepsForCreate.add(new TestCaseStep(i+1, 1, i+1, currentStep.getActions(), currentStep.getResults(), true, ExecutionType.valueOf(currentStep.getExecutionType().name())));
        }
        String summary = testCase.getSummary() != null && !testCase.getSummary().equals("") ? testCase.getSummary() : "_";
        TestCaseStatus status = testCase.getStatus() != null ? TestCaseStatus.valueOf(testCase.getStatus().name()) : null;
        TestImportance importance = testCase.getImportance() != null ? TestImportance.valueOf(testCase.getImportance().name()) : null;
        ExecutionType executionType = testCase.getExecutionType() != null ? ExecutionType.valueOf(testCase.getExecutionType().name()) : null;
        TestCase createdTestCase = api.createTestCase(testCase.getTitle(), createdTestSuite.getId(), createdProject.getId(), System.getProperty("adminLogin"), summary, stepsForCreate, testCase.getPreconditions(), status, importance, executionType, 0, 0, true, ActionOnDuplicate.BLOCK);
        api.addTestCaseToTestPlan(createdProject.getId(), createdTestPlan.getId(), createdTestCase.getId(), createdTestCase.getVersion(), 0, 0, 0);
    }
}
