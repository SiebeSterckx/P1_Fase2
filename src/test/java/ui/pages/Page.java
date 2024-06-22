package ui.pages;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public abstract class Page {
    WebDriver driver;
    String path = Config.BASE_URL;

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }
}
