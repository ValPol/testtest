package PageUser;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SettingsPage extends CommonPage {

    private final String settingsPage = "//a[@href=\"/settings\"]/.";

    private final String verificationTab = "//input[@value=\"kyc\"]//..";

    private final String firstNameField = "[name='firstName']";

    private final String lastNameField = "[name='lastName']";

    private final String countrySelect = "[name='country']";

    private final String maleGendeRBtn = "//input[@value='male']//..";


    public void getKYCForm() {
        //delayAndClickForXpathElement(settingsPage);
      ///  delayAndClickForXpathElement(verificationTab);
        open ("http://tokensale.dev.avalab.io/settings/kyc");
    }

    public void fillKYCForm(){
        delayAndSetValueForCSSElement(firstNameField, "John");
        delayAndSetValueForCSSElement(lastNameField, "John");



    }

}
