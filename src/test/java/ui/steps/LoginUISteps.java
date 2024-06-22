package ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import ui.pages.DriverHelper;
import ui.pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginUISteps {

    private LoginPage loginPage = PageFactory.initElements(DriverHelper.getDriver(), LoginPage.class);

    @Given("Lars has filled in his email in the email field")
    public void lars_has_filled_in_his_email_in_the_email_field() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.setEmail("aanbieder@mbl.be");
        loginPage.setPassword("t");
    }
    @When("Lars chooses to login")
    public void lars_chooses_to_login() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.submit();
    }
    @Then("Lars should be able to login")
    public void lars_should_be_able_to_login() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(loginPage.checkWelcomeMsg("Welcome aanbieder@mbl.be"));
        loginPage.logout();
    }

    @Given("Lars has not filled in his email in the email field")
    public void lars_has_not_filled_in_his_email_in_the_email_field() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("Lars should be given an error message that his email must be filled in")
    public void lars_should_be_given_an_error_message_that_his_email_must_be_filled_in() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.submit();
        assertTrue(loginPage.getError("User not found/Credentials are incorrect"));
    }

    @Given("Lars has provided the his password")
    public void lars_has_provided_the_his_password() {
        // Write code here that turns the phrase above into concrete actions

        loginPage.setEmail("aanbieder@mbl.be");
        loginPage.setPassword("t");
    }

    @Given("Lars has provided the no password")
    public void lars_has_provided_the_no_password() {
        // Write code here that turns the phrase above into concrete actions

        loginPage.setPassword("");
    }
    @Then("Lars should be given an error message that a password must be provided")
    public void lars_should_be_given_an_error_message_that_a_password_must_be_provided() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.submit();
        assertTrue(loginPage.getError("User not found/Credentials are incorrect"));
    }

    @Given("Lars has provided the right password")
    public void lars_has_provided_the_right_password() {

        loginPage.setEmail("aanbieder@mbl.be");
        loginPage.setPassword("t");

    }

    @Given("Lars has provided the wrong password")
    public void lars_has_provided_the_wrong_password() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.setEmail("aanbieder@mbl.be");
        loginPage.setPassword("tt");
    }
    @Then("Lars should be given an error message that his credentials are wrong")
    public void lars_should_be_given_an_error_message_that_his_credentials_are_wrong() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(loginPage.getError("Incorrect password"));
    }

    @Given("Lars is registered")
    public void lars_is_registered() {
        loginPage.setEmail("aanbieder@mbl.be");
        loginPage.setPassword("t");
    }

    @Given("Lars is not registered")
    public void lars_is_not_registered() {
        loginPage.setEmail("aanbiedhyr@mbl.be");
        loginPage.setPassword("t");
    }
    @Then("Lars should be given an error message that he isn’t registered")
    public void lars_should_be_given_an_error_message_that_he_isn_t_registered() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(loginPage.getError("User not found/Credentials are incorrect"));
    }
}
