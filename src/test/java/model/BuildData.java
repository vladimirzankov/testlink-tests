package model;

import java.time.LocalDate;

public class BuildData {
    private TestPlanData testPlan;
    private String title;
    private String description;
    private boolean isActive;
    private boolean isOpen;
    private LocalDate releaseDate;

    public BuildData(TestPlanData testPlan, String title) {
        this.testPlan = testPlan;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
