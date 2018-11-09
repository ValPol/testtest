package main.java.PageUser;

import main.java.CommonPage;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

import java.io.File;

import main.IAction;

public class SettingsPage extends CommonPage {

    private final String settingsPage = "#main-navigation a[href=\"/settings\"]";

    private final String verificationTab = "[role=\"tab\"][aria-controls=\"settings-kyc\"]";

    private final String firstNameField = "[name='firstName']";

    private final String lastNameField = "[name='lastName']";

    private final String countrySelect = "select[name='country']";

    private final String maleGendeRBtn = ".Switcher_switcher_XqmzG label";

    private final String birthdayField = "input[name='birthDate']";

    private final String phoneField = "input[name='phone']";

    private final String fileInput = "input[type='file']";

    private final String agreeChB = "input[type='checkbox']";

    private final String submitBtn = "button[type='submit']";

    public void getKYCForm() {
        $("#main-navigation");
        delayAndClickForCssElement(settingsPage);

        delayAndClickWithoutScrollForCssElement(verificationTab);
    }

    public void fillKYCForm() throws InterruptedException {
        delayAndSetValueForCSSElement(firstNameField, "John");
        delayAndSetValueForCSSElement(lastNameField, "John");
        $(countrySelect).sendKeys(Keys.ARROW_DOWN);
        /**
         * Способ установки значения дропдауна через js js.executeScript("var
         * select = document.querySelector('select[name=\"country\"]');"
         * +"select.value = \"RU\";select.dispatchEvent(new Event('input', {
         * bubbles: true }));");
         */
        doWhile(() -> {
                    delayAndSetValueForCssElement(birthdayField, "");
                    waitSomeSeconds(2);
                    delayAndSendKeysForCssElement(birthdayField, "2010-10-10");
                    waitSomeSeconds(1);
                    return $(birthdayField).getValue().compareToIgnoreCase("2010-10-10") == 0;
                }
        );

        delayAndClickForCssElement(maleGendeRBtn);
        delayAndSetValueForCSSElement(phoneField, "1234456688");
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/test.jpg");
        delayAndSendKeysForCssElement(fileInput, file.getAbsolutePath());
        existForXpathElement("//div[text()=\"test.jpg\"]");
        delayAndClickWithoutScrollForCssElement(agreeChB);
        delayAndClickWithoutScrollForCssElement(submitBtn);
    }

    public void kycFormPosted() {
        disappearForXpathElement("//div[text()=\"test.jpg\"]");
    }

}
