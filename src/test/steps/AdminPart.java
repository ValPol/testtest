package test.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.CommonPage;
import main.java.PageAdmin.KycRequestPage;
import main.java.PageAdmin.LogInAdminPage;

import static com.codeborne.selenide.Selenide.open;

public class AdminPart extends CommonPage {

    LogInAdminPage page;
    KycRequestPage kycRequestPage = new KycRequestPage();

    public AdminPart() {
        super();
    }

    @Given("^I have opened application admin login page$")
    public void iHaveOpenedApplicationAdminLoginPage() throws Throwable {
        page = open(config.getConfigParameter("AdminUri"), LogInAdminPage.class);
    }


    @When("^I have select admin signature \"([^\"]*)\"$")
    public void iHaveSelectAdminSignature(String typeOfUser) throws Throwable {
        page.setLoginAdminCredentials(typeOfUser);
    }

    @When("^I have opened KYC section$")
    public void iHaveOpenedKYCSection() throws Throwable {
        kycRequestPage.openKYCPage();
    }


    @Then("^I have set \"([^\"]*)\" KYC request$")
    public void iHaveSetKYCRequest(String status) throws Throwable {
        kycRequestPage.setStatusKYCRequest(status);
    }


    @Then("^I have decline KYC request$")
    public void iHaveDeclineKYCRequest() throws Throwable {
        kycRequestPage.declineKYCRequest();
    }

    @When("^I have opened KYC \"([^\"]*)\" request$")
    public void iHaveOpenedKYCRequest(String status) throws Throwable {
        kycRequestPage.openKYCrequest(status);
    }
}
