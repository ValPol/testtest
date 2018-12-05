package main.java.PageAdmin;

import main.java.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class KycRequestPage extends CommonPage {

    private final String kycTab = "//div[text()='KYC requests']";

    private final String kycRequestPendingBtn = "//td[text()='testestestfortest1@yopmail.com']//..//span[contains(text(), 'Pending')]//..//..//..//i[text()='pageview']";

    private final String kycStatusField = "//input[@role=\"combobox\"]";

    private final String saveKycStatusBtn = "//*[contains(text(),'Save')]//..//..//button";

    private final String kycStateApproveLabel = "//div[@class=\"flex xs6 sm3 md3\" and contains(text(),\"Approved\")]";

    public void openKYCPage() {
        clickElement("xpath", kycTab);
    }

    public void openKYCrequest() {
        clickElement("xpath",kycRequestPendingBtn);
    }

    public void setStatusKYCRequest(String status) {
        sendKeysForField("xpath", kycStatusField, status);
        driver.findElement(By.xpath("//input[@role=\"combobox\"]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//*[contains(text(),'Save')]//..//..//button")).click();
    }


    public void declineKYCRequest(){

    }


}
