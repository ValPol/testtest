package main.java.PageAdmin;

import main.java.CommonPage;

public class kycRequestPage extends CommonPage {

    private final String kycTab = "//div[text()='KYC requests']";

    private final String kycRequestPendingBtn = "//td[text()='testestestfortest1@yopmail.com']//..//span[contains(text(), 'Pending')]//..//..//..//i[text()='pageview']";

    public void openKYCPage(){
        delayAndClickForXpathElement(kycTab);

    }




}
