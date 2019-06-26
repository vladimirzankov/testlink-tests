package model;

import java.util.List;

public class ExecutionData {
    TestCaseData testCase;
    List<Step> steps;
    Status status;

    public ExecutionData(TestCaseData testCase, List<Step> steps, Status status) {
        if(testCase.getSteps().size() != steps.size())
            throw new IllegalArgumentException("Incorrect number of steps");
        this.steps = steps;
        this.status = status;
    }
    public List<Step> getSteps() {
        return steps;
    }

    public Status getStatus() {
        return status;
    }

    public static class Step {
        String notes;
        Status status;

        public Step(String notes, Status status) {
            this.notes = notes;
            this.status = status;
        }

        public Status getStatus() {
            return status;
        }
    }
    public enum Status {
        PASSED,
        FAILED,
        BLOCKED;

        @Override
        public String toString() {
            return TestCaseData.capitalizeFirstLetter(this.name());
        }
    }
}
