package main.java.PageUser;

import main.java.CommonPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница логина
 */
public class LogInPage extends CommonPage {

    private final String userLogin = "//input[@name=\"email\"]";

    private final String userPassword = "//input[@name=\"password\"]";

    private final String entrBtm = "//button[@type=\"submit\"]";

    //Выбор личного кабинета для работы
    public void setLoginCredentials(String credentials) {
        switch (credentials) {
            case "user": {
                delayAndSetValueForXpathElement(userLogin, "testestestfortest1@yopmail.com");
                delayAndSetValueForXpathElement(userPassword, "qwe123QWE");
                break;
            }
            case "admin": {
                delayAndSetValueForIdElement(userLogin, "admin@tokensale.local");
                delayAndSetValueForIdElement(userPassword, "123");
                break;
            }
        }
        $(By.xpath(entrBtm)).pressEnter();

    }

}
