package main.java;

import com.codeborne.selenide.SelenideElement;
import main.IAction;
import main.java.PageUser.LogInPage;
import main.java.helpers.ConfigContainer;
import org.openqa.selenium.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CommonPage extends AbstractPage {

    protected static LogInPage page;
    protected ConfigContainer config;
    private final static int DELAY_MAX = 180000;
    private final static int DELAY_INTERVAL = 500;


    private final static int nTryMax = 40;

    /*******************************************************************************************************************
     *                                        Конструктор класса
     ******************************************************************************************************************/
    public CommonPage() {
        super();
        config = ConfigContainer.getInstance();
    }


    public void sendKeysForField(String by, String path, String text) {
        SelenideElement element = null;
        switch (by) {
            case "id": {
                element = $(By.id(path));
                break;
            }
            case "css": {
                element = $(By.cssSelector(path));
                break;
            }
            case "xpath": {
                element = $(By.xpath(path));
                break;
            }
        }
        element.waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).sendKeys(text);
    }

    public void clickElement(String by, String path) {
        SelenideElement element = null;
        switch (by) {
            case "id": {
                element = $(By.id(path));
                break;
            }
            case "css": {
                element = $(By.cssSelector(path));
                break;
            }
            case "xpath": {
                element = $(By.xpath(path));
                break;
            }
        }
        element.waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).click();
    }

    public void waitApperanceElement(String by, String path) {
        SelenideElement element = null;
        switch (by) {
            case "id": {
                element = $(By.id(path));
                break;
            }
            case "css": {
                element = $(By.cssSelector(path));
                break;
            }
            case "xpath": {
                element = $(By.xpath(path));
                break;
            }
        }
        element.waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    public void doWhile(IAction action) throws InterruptedException {
        Integer count = 0;
        do {
            count++;
        } while (!action.Run() && count < nTryMax);
    }


    public void disappearForXpathElement(String path) {
        $(By.xpath(path)).waitUntil(disappear, DELAY_MAX, DELAY_INTERVAL);
    }

    public void refreshPage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        driver.navigate().refresh();
    }

    public void existForXpathElement(String path) {
        $(By.xpath(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL);
    }

    public void waitSomeSeconds(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }

    /**
     * Возвращает уникальное имя.
     *
     * @param namePrefix префикс уникального имени
     * @return уникальное имя
     */
    public String generateUniqueName(String namePrefix) {
        String dateString = new SimpleDateFormat("ddMMyyyyHHmmSS").format(new Date());
        return namePrefix != null ? namePrefix + dateString : dateString;
    }

    public String generateGuid() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

}
