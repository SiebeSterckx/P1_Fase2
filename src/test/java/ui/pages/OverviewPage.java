package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class OverviewPage extends Page {

    //materials
    @FindBy(id = "materials")
    private WebElement materials;

    public OverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"Controller?command=MaterialsOverview");
    }

    public boolean containsMaterialWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(name)) {
                found=true;
            }
        }
        return found;
    }

    public int getNumberOfMaterials() {
        ArrayList<WebElement> listItems = (ArrayList<WebElement>) this.driver.findElements(By.cssSelector("table tr"));
        return listItems.size()-1;
    }
}
