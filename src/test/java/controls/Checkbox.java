package controls;

import org.openqa.selenium.WebElement;

public class Checkbox {

    private WebElement checkbox;

    public Checkbox(WebElement element) {
        this.checkbox = element;
    }

    public void interact(boolean needToBeSelected) {
        boolean isSelected = checkbox.isSelected();
        if(isSelected ^ needToBeSelected) checkbox.click();
    }
}
