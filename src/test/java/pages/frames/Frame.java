package pages.frames;

import org.openqa.selenium.WebDriver;

public abstract class Frame {
    WebDriver driver;
    public abstract void switchToSelf();
}
