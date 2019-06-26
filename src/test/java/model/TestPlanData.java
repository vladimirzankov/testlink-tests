package model;

public class TestPlanData {
    String name;
    String description;
    Boolean isActive;
    Boolean isPublic;

    public TestPlanData(String name, String description, boolean isActive, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getDescription() {
        return description;
    }
}
