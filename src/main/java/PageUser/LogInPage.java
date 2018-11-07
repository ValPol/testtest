package main.java.PageUser;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import main.java.CommonPage;

/**
 * Страница логина
 */
public class LogInPage extends CommonPage {

    private final String userLogin = "//input[@name=\"email\"]";

    private final String userPassword = "//input[@name=\"password\"]";

    private final String entrBtm = "//button[@type=\"submit\"]";

    //Выбор личного кабинета для работы
    public void setLoginCredentials(String credentials) throws Exception {
        switch (credentials) {
            case "1": {
                delayAndSetValueForXpathElement(userLogin,config.getConfigParameter("UserLogin"));
                delayAndSetValueForXpathElement(userPassword, config.getConfigParameter("UserPassword"));
                break;
            }
            case "2": {
                delayAndSetValueForXpathElement(userLogin, config.getConfigParameter("LoginOrganization"));
                delayAndSetValueForXpathElement(userPassword, config.getConfigParameter("PasswordOrganization"));
                break;
            }
        }
        $(By.xpath(entrBtm)).pressEnter();

    }

}
