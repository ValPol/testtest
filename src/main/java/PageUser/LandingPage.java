package main.java.PageUser;

import main.java.CommonPage;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$$;

public class LandingPage extends CommonPage {

    String howToPexNowBtn = "//a[text()=\"How to Pex Now\"]";

    String aboutNowBtn = "//a[text()=\"About Us\"]";

    String pexLifeBtn = "//a[text()=\"Pex Life\"]";

    String pexForBusinessBtn = "//a[text()=\"Pex for Business\"]";

    String myAccountBtn = "//span[text()=\"My Account\"]";

    /**
     * Pex life page
     */

    String instagramImg = "//img[contains(@src, \"instagram\")]";

    String pinterestImg = "//span[contains(@data-pin-src, \"pin\")]";

    String instagramPgBtn="[aria-label=\"Instagram\"]";

    String pinterestPgBtn = "//div[text()=\"Pinterest\"]";


    public void openPexLife(){
        clickElement("xpath", pexLifeBtn);
        waitApperanceElement("xpath", instagramImg);
    }

    public void checkInstagram(){
        $$(By.xpath(instagramImg)).shouldHaveSize(9);
        clickElement("xpath", instagramImg );
        waitApperanceElement("css", instagramPgBtn);
    }

    public void checkPinterest(){
        clickElement("xpath", pinterestImg );
        waitApperanceElement("css", pinterestPgBtn);
    }

    public void enterUsersCabinet(){
        clickElement("xpath", myAccountBtn);
        waitApperanceElement("xpath", "//h2[text()=\"Login to your account.\"]");
    }
}