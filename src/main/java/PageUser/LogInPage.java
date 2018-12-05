package main.java.PageUser;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import main.java.CommonPage;

/**
 * Страница логина
 */
public class LogInPage extends CommonPage {

    private final String userLogin = "//input[@name=\"email\"]";

    private final String userPassword = "//input[@name=\"password\"]";

    private final String enterBtn = "//button[@type=\"submit\"]";

    private final String signInTab = "[href = \"/auth/signup\"]";

    private final String confirmPassword = "[name=\"confirmPassword\"]";

    //Выбор личного кабинета для работы
    public void setLoginCredentials(String credentials) {
        switch (credentials.toLowerCase()) {
            case "user": {
                sendKeysForField("xpath", userLogin, config.getConfigParameter("UserLogin"));
                sendKeysForField("xpath", userPassword, config.getConfigParameter("UserPassword"));
                break;
            }

        }
        $(By.xpath(enterBtn)).pressEnter();

    }

    public void enterSignInTab() {
        clickElement("css", signInTab);
    }

    public void setCredentials() {
        config.setProperties("newlogin", generateGuid() + "@tokensale-autotest.avalab");
        sendKeysForField("xpath", userLogin, config.getConfigParameter("newlogin"));
        sendKeysForField("xpath", userPassword, config.getConfigParameter("UserPassword"));
        sendKeysForField("css", confirmPassword, config.getConfigParameter("UserPassword"));

        clickElement("xpath", enterBtn);
        waitApperanceElement("xpath", "//*[text() = \"Please confirm your email to start\"]");
    }

    public void confirmNewAccount() {
        String confirmLink = config.getConfigParameter("UserUri") + "api/autotests/emails/" + config.getConfigParameter("newlogin") + "/verifyurl";
        open(confirmLink);
        open($("pre").getText());
    }

    public void openUserSpace() {
        waitApperanceElement("xpath", "//*[text()=\"Dashboard\"]");
    }

}
