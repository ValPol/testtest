package PageUser;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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
        //open("http://tokensale.dev.avalab.io/settings/kyc");
        //Select settingsDrD = new Select(driver.findElement(By.xpath("//select[@name=\"settings\"]")));
       // settingsDrD.selectByValue("kyc");
       delayAndClickWithoutScrollForCssElement(verificationTab);
    }

    public void fillKYCForm(){
        delayAndSetValueForCSSElement(firstNameField, "John");
        delayAndSetValueForCSSElement(lastNameField, "John");
        Select countryDrD = new Select(driver.findElement(By.cssSelector(countrySelect)));

        countryDrD.selectByValue("DZ");
      //  delayAndClickWithoutScrollForXpathElement("{//input[@value=\"male\"]//..");
        $(".Switcher_switcher_XqmzG label", 1).click();
    }

}
