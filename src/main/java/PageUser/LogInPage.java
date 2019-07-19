package main.java.PageUser;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import main.java.CommonPage;

/**
 * Страница логина
 */
public class LogInPage extends CommonPage {

    private final String userLogin = "//input[@name=\"username\"]";

    private final String userPassword = "//input[@name=\"password\"]";

    private final String enterBtn = "//button[@type=\"submit\"]";

    private final String signInTab = "";

    private final String confirmPassword = "";

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
        waitApperanceElement("xpath", "//h1[text()=\"Dashboard\"]");
    }

}
