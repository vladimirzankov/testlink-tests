package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.frames.*;

public abstract class Page {

    WebDriver driver;
    WebDriverWait wait;

    public void switchTo(Frame frame) {
        frame.switchToSelf();
    }
}
