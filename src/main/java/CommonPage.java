package main.java;


import main.java.helpers.ConfigContainer;
import org.openqa.selenium.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class CommonPage extends AbstractPage {
    protected ConfigContainer config = ConfigContainer.getInstance();
    private final static int DELAY_MAX = 180000;
    private final static int DELAY_INTERVAL = 500;

    private final int REPEAT_TIMEOUT = 80;

    private final static int nTryMax = 40;

    public static String spinnerAppear = "//div[@class='spinner spinner--full spinner-global']";
    /*
     * Для работы с javascript
     */
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String script = "";

    /*******************************************************************************************************************
     * Конструктор класса.
     ******************************************************************************************************************/
    public CommonPage() {
        super();
    }

    /*******************************************************************************************************************
     *           Метода взаиможействия с элементами по id или xpath
     * @param path
     * @return 
     ******************************************************************************************************************/

    public boolean visibilityForCssElement(String path){
       return $(By.cssSelector(path)).isDisplayed();
    }

    public boolean visibilityForXpathElement(String path){
        return $(By.xpath(path)).isDisplayed();
    }

    public boolean visibilityForIdElement(String path){
        return $(By.id(path)).isDisplayed();
    }

    public void delayAndClickForXpathElement(String path) {

        $(By.xpath(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().click();

    }

    public void delayAndSetValueForXpathElement(String path, String value) {
        $(By.xpath(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().setValue(value);
    }

    public void delayAndSetValueForCssElement(String path, String value) {
        $(By.cssSelector(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().setValue(value);
    }

    public void delayAndClickForCssElement(String path) {
        $(By.cssSelector(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().click();
    }

    public void delayAndClickWithoutScrollForCssElement(String path) {
        $(By.cssSelector(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).click();
    }

    public void delayAndClickWithoutScrollForXpathElement(String path) {
        $(By.xpath(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL);
        $(By.xpath(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).click();
    }

    public void delayAndClickWithoutScrollForIdElement(String path) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).click();
    }

    public void clickForXpathElement(String path) {
        $(By.xpath(path)).click();
    }

    public void delayAndClickForIdElement(String path) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().click();
    }

    public void delayAndWaitUntilAppearIdElement(String path) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    public void delayAndSetValueForIdElement(String path, String value) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().setValue(value);
    }

    public void delayAndSendKeysForCssElement(String path, String value) {
        $(By.cssSelector(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).sendKeys(value);
    }

    public void delayAndSetValueWithoutScrollForIdElement(String path, String value) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).setValue(value);
    }

    public void delayAndSetValueForCSSElement(String path, String value) {
        $(By.cssSelector(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL);
        $(By.cssSelector(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).setValue(value);
    }

    public void setValueForIdElement(String path, String value) {
        $(By.id(path)).setValue(value);
    }

    public void setValueForXpathElement(String path, String value) {
        $(By.xpath(path)).setValue(value);
    }

    public void delayAndSendForIdElement(String path, String document) {
        $(By.id(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).sendKeys(document);
    }

    public void delayAndSendForXpathElement(String path, String document) {
        $(By.xpath(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).scrollTo().sendKeys(document);
    }

    public String delayAndGetTextForXpathElement(String path) throws InterruptedException {
        // TODO refactor
        return $(By.xpath(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).getText();
    }

    public String delayAndGetTextFoCssElement(String path) throws InterruptedException {
        return $(By.cssSelector(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).getText();
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

    public void waitUntilDisappearForXpathElement(String path) {
        $(By.xpath(path)).waitUntil(disappear, DELAY_MAX, DELAY_INTERVAL);
    }

    public void waitVisibleForXpathElement(String path) {
        $(By.xpath(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    public void waitVisibleForCssElement(String path) {
        $(By.cssSelector(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    /**
     * scrollToTheXpathElement(String path)
     * Установка фокуса на требуемом элементе
     *
     * @param path - xpath элемента
     * @throws java.lang.InterruptedException
     */
    public void scrollToTheXpathElement(String path) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL * 10);
        WebElement element = driver.findElement(By.xpath(path));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToTheCssElement(String path) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector(path));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * executeJS(String jsScript)
     * Для выполнения любого js
     *
     * @param jsScript - подготовленный jscript
     */
    public void executeJS(String jsScript) {
        try {
            js.executeScript(jsScript);
        } catch (WebDriverException e) {
            System.out.println("Some problem with WebDriver");
        }
    }

    /**
     * executeAndReturnState(String jsScript)
     * Возвращает отвёт после испольнение jscript типа Boolean
     *
     * @param jsScript
     * @return Boolean
     */
    public Boolean executeAndReturnState(String jsScript) {
        Boolean valueOfElement = false;
        try {
            valueOfElement = (Boolean) js.executeScript(jsScript);
        } catch (WebDriverException e) {
            System.out.println("Some problem with WebDriver");
            executeAndReturnState(jsScript);
        }
        return valueOfElement;
    }

    /**
     * executeAndReturnValue(String jsScript)
     * Возвращает отвёт после испольнение jscript типа String
     *
     * @param jsScript
     * @return String
     */
    public String executeAndReturnValue(String jsScript) {
        String valueOfElement = "";
        try {
            valueOfElement = (String) js.executeScript(jsScript);
        } catch (WebDriverException e) {
            System.out.println("Some problem with WebDriver");
            executeAndReturnState(jsScript);
        }
        return valueOfElement;
    }

    public void waitSomeSeconds(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }


    public void tryToClickForXpathElement(String path) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement clickable = driver.findElement(By.xpath(path));
        try {
            js.executeScript("arguments[0].click();", clickable);
        } catch (WebDriverException e) {
            System.out.println("Some problem with WebDriver");
            tryToClickForXpathElement(path);
        }
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

}