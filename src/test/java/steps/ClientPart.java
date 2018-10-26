package steps;

import PageUser.CommonPage;
import PageUser.LogInPage;
import PageUser.SettingsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.ConfigContainer;

import static com.codeborne.selenide.Selenide.open;

public class ClientPart extends CommonPage {

    LogInPage page;
    ConfigContainer config = new ConfigContainer();
    SettingsPage settingsPage = new SettingsPage();


    @Given("^I have opened application log in page$")
    public void iHaveOpenedApplicationLogInPage() throws Throwable {
        page = open("http://tokensale.dev.avalab.io", LogInPage.class);
    }

    @When("^I enter \"([^\"]*)\"$")
    public void iEnter(int credentials) throws Throwable {
        page.setLoginCredentials(credentials);
    }

    @When("^I have select signature \"([^\"]*)\"$")
    public void iHaveSelectSignature(int credentials) throws Throwable {
        page.setLoginCredentials(credentials);
    }

    @When("^I have opened KYC-form$")
    public void iHaveOpenedKYCForm() throws Throwable {
        settingsPage.getKYCForm();
    }

    @When("^I have filled KYC-form$")
    public void iHaveFilledKYCForm() throws Throwable {
        settingsPage.fillKYCForm();
    }

    @Then("^I have posted KYC-request$")
    public void iHavePostedKYCRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
