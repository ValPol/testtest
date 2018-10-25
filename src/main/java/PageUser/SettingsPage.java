package PageUser;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SettingsPage extends CommonPage {

    private final String settingsPage = "#main-navigation a[href=\"/settings\"]";

    private final String verificationTab = "[role=\"tab\"][aria-controls=\"settings-kyc\"]";

    private final String firstNameField = "[name='firstName']";

    private final String lastNameField = "[name='lastName']";

    private final String countrySelect = "[name='country']";

    private final String maleGendeRBtn = "//input[@value='male']//..";


    public void getKYCForm() {
        $("#main-navigation");
        delayAndClickForCssElement(settingsPage);
        delayAndClickForCssElement(verificationTab);
    }

    public void fillKYCForm(){
        delayAndSetValueForCSSElement(firstNameField, "John");
        delayAndSetValueForCSSElement(lastNameField, "John");



    }

}
