package tests;

import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import model.*;
import model.TestSuiteData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;

import static model.ExecutionData.*;

public class TestCaseExecutionTests extends LoginFixture {

    ExecutionData execution1 = new ExecutionData(initialTestCase, Arrays.asList(
            new Step(null, Status.PASSED),
            new Step(null, Status.PASSED),
            new Step(null, Status.PASSED)),
            Status.PASSED);
    ExecutionData execution2 = new ExecutionData(initialTestCase, Arrays.asList(new Step(null, Status.FAILED), null, null), Status.FAILED);

    @Test
    public void checkExecutionColor() {
        homePage.open(initialProject);
        homePage.goToTestExecutionPage();
        testExecutionPage.initTestCaseExecution(initialTestCase);
        Assert.assertEquals("rgba(0, 0, 0, 1)", testExecutionPage.getLastExecutionColor().asRgba());
        testExecutionPage.executeTestCase(execution1);
        Assert.assertEquals("rgba(0, 100, 0, 1)", testExecutionPage.getLastExecutionColor().asRgba());
        Assert.assertEquals("rgba(213, 238, 213, 1)", testExecutionPage.getColorInTree(initialTestCase).asRgba());
        testExecutionPage.executeTestCase(execution2);
        Assert.assertEquals("rgba(178, 34, 34, 1)", testExecutionPage.getLastExecutionColor().asRgba());
        Assert.assertEquals("rgba(238, 213, 213, 1)", testExecutionPage.getColorInTree(initialTestCase).asRgba());
    }
}
