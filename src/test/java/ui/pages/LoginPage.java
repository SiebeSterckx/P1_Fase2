package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "Login")
    private WebElement login;

    @FindBy(id = "welcome_msg")
    private WebElement welcomeMsg;

    @FindBy(className = "text-danger")
    private WebElement error;

    @FindBy(id = "Logout")
    private WebElement logout;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller?command=Home");
    }

    public void setEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public void setPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void submit() {
        login.click();
    }

    public boolean checkWelcomeMsg(String tekst) {
        return welcomeMsg.getText().contains(tekst);
    }

    public boolean getError(String error) {
        return this.error.getText().equals(error);
    }

    public void logout() {
        logout.click();
    }

    public boolean checkIfLogin() {
        try {
            return logout.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
