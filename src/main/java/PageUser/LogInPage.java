package PageUser;

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
            case "1": {
                delayAndSetValueForXpathElement(userLogin, "testestestfortest1@yopmail.com");
                delayAndSetValueForXpathElement(userPassword, "qwe123QWE");
                break;
            }
            case "2": {
                delayAndSetValueForIdElement(userLogin, config.getConfigParameter("LoginOrganization"));
                delayAndSetValueForIdElement(userPassword, config.getConfigParameter("PasswordOrganization"));
                break;
        }
        }
        $(By.xpath(entrBtm)).pressEnter();
        
    }




}