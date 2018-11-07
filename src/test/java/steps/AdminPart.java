package test.java.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.CommonPage;
import main.java.PageUser.LogInPage;
import static com.codeborne.selenide.Selenide.open;

public class AdminPart extends CommonPage {

    LogInPage page;

    public AdminPart(){
        super();
    }

    @Given("^I have opened application admin login page$")
    public void iHaveOpenedApplicationAdminLoginPage() throws Throwable {
        page = open(config.getConfigParameter("AdminUri"), LogInPage.class);
    }

    @When("^I have opened KYC section$")
    public void iHaveOpenedKYCSection() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I have opened KYC request$")
    public void iHaveOpenedKYCRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I have approved KYC request$")
    public void iHaveApprovedKYCRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I have decline KYC request$")
    public void iHaveDeclineKYCRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();

    }

}
