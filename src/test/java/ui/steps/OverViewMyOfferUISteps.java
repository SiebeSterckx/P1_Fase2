package ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import ui.pages.DriverHelper;
import ui.pages.LoginPage;
import ui.pages.OverviewPage;

import static org.junit.Assert.assertTrue;

public class OverViewMyOfferUISteps {

    private LoginPage loginPage;
    private OverviewPage overviewPage;

    @Given("I am logged in as an Aanbieder")
    public void i_am_logged_in_as_an_aanbieder() {
        // Write code here that turns the phrase above into concrete actions
        loginPage = PageFactory.initElements(DriverHelper.getDriver(), LoginPage.class);
        loginPage.setEmail("aanbieder@mbl.be");
        loginPage.setPassword("t");
        loginPage.submit();
    }
    @Given("there are offers")
    public void there_are_offers() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I go to the overview of my offers")
    public void i_go_to_the_overview_of_my_offers() {
        // Write code here that turns the phrase above into concrete actions
        overviewPage = PageFactory.initElements(DriverHelper.getDriver(), OverviewPage.class);
    }
    @Then("I see all my offers")
    public void i_see_all_my_offers() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(overviewPage.getNumberOfMaterials() > 0);
        loginPage = PageFactory.initElements(DriverHelper.getDriver(), LoginPage.class);
        loginPage.logout();
    }
}
