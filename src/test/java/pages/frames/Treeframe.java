package pages.frames;

import org.openqa.selenium.WebDriver;

public class Treeframe extends Frame {
    WebDriver driver;
    Mainframe parent;

    public Treeframe(WebDriver driver) {
        this.driver = driver;
        parent = new Mainframe(driver);
    }

    public void switchToSelf() {
        parent.switchToSelf();
        parent.switchToTreeframe();
    }

}
