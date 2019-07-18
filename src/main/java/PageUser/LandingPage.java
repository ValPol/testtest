package main.java.PageUser;

import main.java.CommonPage;

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


    public void openPexLife(){
        clickElement("xpath", pexLifeBtn);
        waitApperanceElement("xpath", instagramImg);
    }

    public void checkInstagram()


}
