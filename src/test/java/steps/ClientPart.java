package test.steps;

import main.java.PageUser.LogInPage;
import main.java.PageUser.SettingsPage;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Selenide.open;
import main.java.CommonPage;

public class ClientPart extends CommonPage {

    LogInPage page;
    SettingsPage settingsPage = new SettingsPage();

    @Given("^I have opened application log in page$")
    public void iHaveOpenedApplicationLogInPage() throws Throwable {
        page = open(config.getConfigParameter("UserUri"), LogInPage.class);
    }

    @When("^I enter \"([^\"]*)\"$")
    public void iEnter(String credentials) throws Throwable {
        page.setLoginCredentials(credentials);
    }

    @When("^I have select signature \"([^\"]*)\"$")
    public void iHaveSelectSignature(String credentials) throws Throwable {
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
        settingsPage.kycFormPosted();
    }
}
