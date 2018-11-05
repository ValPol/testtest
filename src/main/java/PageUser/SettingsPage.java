package PageUser;

import main.java.CommonPage;

import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Selenide.*;

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
         * Способ установки значения дропдауна через js
         js.executeScript("var select = document.querySelector('select[name=\"country\"]');" +"select.value = \"RU\";select.dispatchEvent(new Event('input', { bubbles: true }));");
         */
        while ($(birthdayField).getValue().compareToIgnoreCase("10-10-2010") != 0) {
            delayAndSendKeysForCssElement(birthdayField, "10-10-2010");
            waitSomeSeconds(1);
        }
        delayAndClickForCssElement(maleGendeRBtn);
        delayAndSetValueForCSSElement(phoneField, "1234456688");
        delayAndSendKeysForCssElement(fileInput, System.getProperty("user.dir") + "\\src\\test\\resources\\test.jpg");
        existForXpathElement("//div[text()=\"test.jpg\"]");
        delayAndClickWithoutScrollForCssElement(agreeChB);
        delayAndClickWithoutScrollForCssElement(submitBtn);
    }

    public void kycFormPosted() {
        disappearForXpathElement("//div[text()=\"test.jpg\"]");
    }

}
