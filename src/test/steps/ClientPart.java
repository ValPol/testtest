package test.steps;

import main.java.PageUser.LandingPage;
import main.java.PageUser.LogInPage;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.codeborne.selenide.Selenide.open;

import main.java.CommonPage;

public class ClientPart extends CommonPage {
    LandingPage landingPage=new LandingPage();
    LogInPage logInPage=new LogInPage();



    @Given("^I have opened application log in page$")
    public void iHaveOpenedApplicationLogInPage()  {
        page = open(config.getConfigParameter("UserUri"), LogInPage.class);
    }

    @When("^I enter \"([^\"]*)\"$")
    public void iEnter(String credentials)   {
        page.setLoginCredentials(credentials);
    }


    @When("^I choose pexlife page$")
    public void iChoosePexlifePage()  {
        landingPage.openPexLife();
    }

    @When("^I check instagram$")
    public void icheckInstagram()  {
        landingPage.checkInstagram();
    }

    @Then("^I check pinterest$")
    public void iCheckPinterest()  {
        landingPage.checkPinterest();
    }

    @Then("^I enter users cabinet$")
    public void iEnterUsersCabinet()  {
        landingPage.enterUsersCabinet();
    }

    @Then("^I enter credentials$")
    public void iEnterCredentials()  {
        logInPage.setLoginCredentials("user");
    }
}
