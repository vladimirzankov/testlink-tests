package model;

public class TestSuiteData {
    private String name;
    private String details;

    public TestSuiteData(String name, String details) {
        this.setName(name);
        this.setDetails(details);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
