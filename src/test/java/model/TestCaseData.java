package model;

import java.util.ArrayList;
import java.util.List;

public class TestCaseData {
    private TestSuiteData testSuite;
    private String title;
    private String summary;
    private String preconditions;
    private Status status;
    private Importance importance;
    private ExecutionType executionType;
    private int duration;
    private List<Step> steps = new ArrayList<>();
    
    private TestCaseData() {};

    public void setTestSuite(TestSuiteData testSuite) {
        this.testSuite = testSuite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public void setExecutionType(ExecutionType executionType) {
        this.executionType = executionType;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public TestSuiteData getTestSuite() {
        return testSuite;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public Status getStatus() {
        return status;
    }

    public Importance getImportance() {
        return importance;
    }

    public ExecutionType getExecutionType() {
        return executionType;
    }

    public int getDuration() {
        return duration;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public static class Builder {
        TestCaseData testCase = new TestCaseData();
        
        public Builder setTestSuite(TestSuiteData testSuite) {
            testCase.setTestSuite(testSuite); return this;
        }
        public Builder setTitle(String title) {
            testCase.setTitle(title); return this;
        }
        public Builder setSummary(String summary) {
            testCase.setSummary(summary); return this;
        }
        public Builder setPreconditions(String preconditions) {
            testCase.setPreconditions(preconditions); return this;
        }
        public Builder setStatus(Status status) {
            testCase.setStatus(status); return this;
        }
        public Builder setImportance(Importance importance) {
            testCase.setImportance(importance); return this;
        }
        public Builder setExecutionType(ExecutionType executionType) {
            testCase.setExecutionType(executionType); return this;
        }
        public Builder setDuration(int duration) {
            testCase.setDuration(duration); return this;
        }
        public Builder setSteps(List<Step> steps) {
            testCase.setSteps(steps); return this;
        }

        public TestCaseData build() {
            return testCase;
        }
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0)
            return original;
        return (original.substring(0, 1) + original.substring(1).toLowerCase()).replace('_', ' ');
    }

    public static class Step {
        private String actions;
        private String results;
        private ExecutionType executionType;

        public Step(String actions, String results, ExecutionType executionType) {
            this.setActions(actions);
            this.setResults(results);
            this.setExecutionType(executionType);
        }

        public String getActions() {
            return actions;
        }

        public void setActions(String actions) {
            this.actions = actions;
        }

        public String getResults() {
            return results;
        }

        public void setResults(String results) {
            this.results = results;
        }

        public ExecutionType getExecutionType() {
            return executionType;
        }

        public void setExecutionType(ExecutionType executionType) {
            this.executionType = executionType;
        }
    }

    public enum Status {
        DRAFT,
        READY_FOR_REVIEW,
        REVIEW_IN_PROGRESS,
        REWORK,
        OBSOLETE,
        FUTURE,
        FINAL;

        @Override
        public String toString() {
            return capitalizeFirstLetter(this.name());
        }
    }

    public enum Importance {
        HIGH,
        MEDIUM,
        LOW;

        @Override
        public String toString() {
            return capitalizeFirstLetter(this.name());
        }
    }

    public enum ExecutionType {
        MANUAL,
        AUTOMATED;

        @Override
        public String toString() {
            return capitalizeFirstLetter(this.name());
        }
    }
}
