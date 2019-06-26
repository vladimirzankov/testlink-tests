package tests;

import model.TestProjectData;
import org.junit.After;
import org.junit.Test;

public class TestProjectCreationTests extends LoginFixture {

    TestProjectData project = new TestProjectData(null, "Project 1", "PROJ", null, true, false, true, false, true, true);

    @Test
    public void createTestProject() {
        homePage.open(initialProject);
        homePage.goToTestProjectManagement();
        testProjectManagementPage.initProjectCreation();
        testProjectCreationPage.create(project);
        testProjectManagementPage.checkDisplayedProjectSettings(project);
    }

    @After
    public void deleteProject() {
        homePage.open();
        homePage.goToTestProjectManagement();
        testProjectManagementPage.deleteProject(project.getName());
    }
}
