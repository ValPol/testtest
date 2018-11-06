package main.java.PageUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SettingsPage extends CommonPage {

    private final String settingsPage = "#main-navigation a[href=\"/settings\"]";

    private final String verificationTab = "[role=\"tab\"][aria-controls=\"settings-kyc\"]";

    private final String firstNameField = "[name='firstName']";

    private final String lastNameField = "[name='lastName']";

    private final String countrySelect = "select[name='country']";

    private final String optionValue = "option[value='DZ']";

    private final String maleGendeRBtn = "//input[@value='male']//..";

    private final String birthdayField = "input[name='birthDate']";

    private final String phoneField = "input[name='phone']";

    private final String fileInput = "input[type='file']";

    private final String agreeChB = "input[type='checkbox']";

    private final String submitBtn = "button[type='submit']";

    public void getKYCForm() {
        $("#main-navigation");
        delayAndClickForCssElement(settingsPage);
        //open("http://tokensale.dev.avalab.io/settings/kyc");
        //Select settingsDrD = new Select(driver.findElement(By.xpath("//select[@name=\"settings\"]")));
        // settingsDrD.selectByValue("kyc");
        delayAndClickWithoutScrollForCssElement(verificationTab);
    }

    public void fillKYCForm() {
        delayAndSetValueForCSSElement(firstNameField, "John");
        delayAndSetValueForCSSElement(lastNameField, "John");

        //  delayAndClickWithoutScrollForXpathElement("{//input[@value=\"male\"]//..");
        delayAndSetValueForCSSElement(birthdayField, "01-01-2000");
        $(".Switcher_switcher_XqmzG label", 1).click();
        delayAndSetValueForCSSElement(phoneField, "1234456688");
        delayAndSendKeysForCssElement(fileInput, System.getProperty("user.dir") + "/src/test/resources/test.jpg");
        delayAndClickWithoutScrollForCssElement(agreeChB);

        Select dropDown = new Select(driver.findElement(By.cssSelector(countrySelect)));
        List<WebElement> options = dropDown.getOptions();
        for (WebElement option : options) {
            if (option.getAttribute("value").equals("AL")) {
                option.click();
                break;
            }
        }

//        delayAndClickWithoutScrollForCssElement(countrySelect);
        // $(countrySelect).click();
        // $(optionValue).click();
        ///JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.querySelector(\"select[name='country']\").setAttribute('class', 'vf-dirty vf-valid vf-touched')");
        //$(countrySelect).sendKeys(Keys.ENTER);
        //  $(countrySelect).sendKeys(Keys.ENTER);
        // $(optionValue).sendKeys(Keys.ENTER);
        // $(optionValue).click();
        //     delayAndClickWithoutScrollForCssElement(countrySelect);
        delayAndClickWithoutScrollForCssElement(submitBtn);

    }

}
