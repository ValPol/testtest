package test.java.steps;


import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.CommonPage;
import main.java.PageAdmin.LogInAdminPage;
import main.java.PageAdmin.kycRequestPage;

import static com.codeborne.selenide.Selenide.open;

public class AdminPart extends CommonPage {
    LogInAdminPage page;
    kycRequestPage kycRequestPage = new kycRequestPage();

    @Given("^I have opened application admin login page$")
    public void iHaveOpenedApplicationAdminLoginPage() throws Throwable {
        page = open("http://tokensale.dev.avalab.io/admin/signin?redirect=%2F", LogInAdminPage.class);
    }


    @When("^I have select admin signature \"([^\"]*)\"$")
    public void iHaveSelectAdminSignature(String credentials) throws Throwable {
        page.setLoginAdminCredentials(credentials);
    }

    @When("^I have opened KYC section$")
    public void iHaveOpenedKYCSection() throws Throwable {
        kycRequestPage.openKYCPage();
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
