package test.steps;

import cucumber.api.PendingException;
import main.java.PageUser.LogInPage;
import main.java.PageUser.SettingsPage;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Selenide.open;

import main.java.CommonPage;

public class ClientPart extends CommonPage {

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

    @When("^I have opened sign in page$")
    public void iHaveOpenedSignInPage() throws Throwable {
        page.enterSignInTab();
    }

    @When("^I have confirm new account$")
    public void iHaveConfirmNewAccount() throws Throwable {
        page.confirmNewAccount();
    }

    @When("^I have set credentials$")
    public void iHaveSetCredentials() throws Throwable {
        page.setCredentials();
    }

    @Then("^I successfully log in$")
    public void iSuccessfullyLogIn() throws Throwable {
        page.openUserSpace();
    }

}
