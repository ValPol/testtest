package main.java.PageAdmin;

import main.java.CommonPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница логина
 */
public class LogInAdminPage extends CommonPage {

    private final String adminLogin = "[name=\"email\"]";

    private final String adminPassword = "[name=\"password\"]";

    private final String entrBtm = "//button[@type=\"submit\"]";

    //Выбор личного кабинета для работы
    public void setLoginAdminCredentials(String credentials) {
        switch (credentials) {
            case "admin": {
                sendKeysForField("css", adminLogin, "admin@tokensale.local");
                sendKeysForField("css",adminPassword, "123");
                break;
            }
        }
        $(By.xpath(entrBtm)).pressEnter();

    }

}
