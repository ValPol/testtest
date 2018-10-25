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

public class LogIn extends CommonPage {

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

    @Then("^I successfully log in$")
    public void iSuccessfullyLogIn() throws Throwable {
    }


    @When("^I have opened KYC-form$")
    public void iHaveOpenedKYCForm() throws Throwable {
        settingsPage.getKYCForm();
    }

    @When("^I fill KYC-form$")
    public void iFillKYCForm() throws Throwable {
        settingsPage.fillKYCForm();
    }
}
