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
        clickElement("css", settingsPage);

        clickElement("css", verificationTab);
    }

    public void fillKYCForm() throws InterruptedException {
        sendKeysForField("css", firstNameField, "John");
        sendKeysForField("css", lastNameField, "John");
        $(countrySelect).sendKeys(Keys.ARROW_DOWN);
        /**
         * Способ установки значения дропдауна через js js.executeScript("var
         * select = document.querySelector('select[name=\"country\"]');"
         * +"select.value = \"RU\";select.dispatchEvent(new Event('input', {
         * bubbles: true }));");
         */
        doWhile(() -> {
                    sendKeysForField("css", birthdayField, "");
                    waitSomeSeconds(2);
                    sendKeysForField("css", birthdayField, "2010-10-10");
                    waitSomeSeconds(2);
                    return $(birthdayField).getValue().compareToIgnoreCase("2010-10-10") == 0;
                }
        );

        clickElement("css", maleGendeRBtn);
        sendKeysForField("css", phoneField, "1234456688");
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/test.jpg");
        sendKeysForField("css", fileInput, file.getAbsolutePath());
        existForXpathElement("//div[text()=\"test.jpg\"]");
        clickElement("css", agreeChB);
        clickElement("css", submitBtn);
    }

    public void kycFormPosted() {
        disappearForXpathElement("//div[text()=\"test.jpg\"]");
    }

}
