package model;

public class TestProjectData {

    private String fromProject;
    private String name;
    private String prefix;
    private String description;
    private Boolean enableRequirements;
    private Boolean enableTestPriority;
    private Boolean enableAutomation;
    private Boolean enableInventory;
    private Boolean isActive;
    public TestProjectData(String fromProject, String name, String prefix, String description, Boolean enableRequirements, Boolean enableTestPriority, Boolean enableAutomation, Boolean enableInventory, Boolean isActive, Boolean isPublic) {
        this.enableRequirements = Boolean.FALSE;
        this.enableTestPriority = Boolean.FALSE;
        this.enableAutomation = Boolean.FALSE;
        this.enableInventory = Boolean.FALSE;
        this.isActive = Boolean.TRUE;
        this.isPublic = Boolean.TRUE;
        this.fromProject = fromProject;
        this.name = name;
        this.prefix = prefix;
        this.description = description;
        this.enableRequirements = enableRequirements;
        this.enableTestPriority = enableTestPriority;
        this.enableAutomation = enableAutomation;
        this.enableInventory = enableInventory;
        this.isActive = isActive;
        this.isPublic = isPublic;
    }

    private Boolean isPublic;

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDescription() {
        if(description != null)
            return description;
        else return "";
    }

    public Boolean isEnableRequirements() {
        return enableRequirements;
    }

    public Boolean isEnableTestPriority() {
        return enableTestPriority;
    }

    public Boolean isEnableAutomation() {
        return enableAutomation;
    }

    public Boolean isEnableInventory() {
        return enableInventory;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Boolean isPublic() {
        return isPublic;
    }
}
