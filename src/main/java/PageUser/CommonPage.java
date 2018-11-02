package main.java.PageUser;

import main.java.helpers.ConfigContainer;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.event.KeyEvent;
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
        waitForAjaxControls();
        $(By.xpath(path)).click();
    }

    public void delayAndClickForIdElement(String path) {
        waitForAjaxControls();
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().click();
    }

    public void delayAndWaitUntilAppearIdElement(String path) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    public void delayAndSetValueForIdElement(String path, String value) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().setValue(value);
    }

    public void delayAndSendKeysForIdElement(String path, String value) {
        $(By.id(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL).scrollTo().sendKeys(value);
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

    public void delayAndSendFileForIdElement(String path, String document) {
        $(By.id(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).sendKeys(document);
    }

    public void delayAndSendFileForXpathElement(String path, String document) {
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
        waitForAjaxControls();
        $(By.xpath(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    public void waitVisibleForCssElement(String path) {
        waitForAjaxControls();
        $(By.cssSelector(path)).waitUntil(visible, DELAY_MAX, DELAY_INTERVAL);
    }

    /**
     * Ожидание окончания выполнения всех AJAX
     */
    public void waitForAjaxControls() {
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
                for (int i = 0; i < REPEAT_TIMEOUT; i++) {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long) numberOfAjaxConnections;
                        if (n.longValue() == 0L)
                            break;
                    }
                    TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL);
                }
            } else {
                System.out.println("Web driver: " + driver + " can't run javascript.");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (WebDriverException exception) {
            waitForAjaxControls();
        }
    }

    /**
     * scrollToTheXpathElement(String path)
     * Установка фокуса на требуемом элементе
     *
     * @param path - xpath элемента
     */
    public void scrollToTheXpathElement(String path) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL * 10);
        WebElement element = driver.findElement(By.xpath(path));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToTheCssElement(String path) throws InterruptedException {
        waitForAjaxControls();
        WebElement element = driver.findElement(By.cssSelector(path));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /*******************************************************************************************************************
     *       Методы, возвращающие существование элемента через nTryMax*DELAY_INTERVAL mc или по по готовности эдемента
     *       Для элементов, которые находятся на проблемных для отрисовки страницах
     ******************************************************************************************************************/

    public boolean isExistForXpathElement(String path) throws InterruptedException {
        int nTry = 0;
        while ((!($(By.xpath(path)).exists())) && (nTry != nTryMax)) {
            nTry++;
            TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL);
        }
        return $(By.xpath(path)).exists();
    }

    /*******************************************************************************************************************
     *       Методы, возвращающие видимость элемента через nTryMax*DELAY_INTERVAL mc или по по готовности эдемента
     *       Для элементов, для которых имеются проблемы с отображением
     ******************************************************************************************************************/

    public boolean isVisibleForXpathElement(String path) throws InterruptedException {
        int nTry = 0;
        while ((!$(By.xpath(path)).isDisplayed()) && (nTry < nTryMax)) {
            TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL);
            nTry++;
        }
        return $(By.xpath(path)).isDisplayed();
    }

    public boolean isVisibleForIdElement(String path) throws InterruptedException {
        int nTry = 0;
        while ((!$(By.id(path)).isDisplayed()) && (nTry < nTryMax)) {
            TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL);
            nTry++;
        }
        return $(By.id(path)).isDisplayed();
    }

    public boolean isVisibleForCssElement(String path) throws InterruptedException {
        int nTry = 0;
        while ((!$(By.cssSelector(path)).isDisplayed()) && (nTry < nTryMax)) {
            TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL);
            nTry++;
        }
        return $(By.cssSelector(path)).isDisplayed();
    }

    /**
     * makeElementAvailable(String path)
     * С помощью js делать требуемый элемент доступным
     *
     * @param path - css для заблокированного элемента
     * @throws InterruptedException
     */

    public void makeElementAvailable(String path) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        try {
            script = String.format("$('%s').attr('disabled', false)", path);
            js.executeScript(script);
        } catch (WebDriverException e) {
            System.out.println("Some problem with WebDriver");
            makeElementAvailable(path);
        }
    }

    public void pageUp() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }

    /**
     * executeJS(String jsScript)
     * Для выполнения любого js
     *
     * @param jsScript - подготовленный jscript
     */
    public void executeJS(String jsScript) {
        waitForAjaxControls();
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



    public int waitForDaDataResponse(String path, String value, String pathResponse, String otherValue) throws InterruptedException {
        int nTry = 0;
        while (!$(By.xpath(otherValue)).isDisplayed() && (nTry != nTryMax)) {
            while (!isVisibleForXpathElement(pathResponse)) {
                $(By.id(path)).sendKeys(Keys.BACK_SPACE);
                TimeUnit.MILLISECONDS.sleep(DELAY_INTERVAL * 10);
                $(By.id(path)).clear();
                $(By.id(path)).sendKeys(value);
                $(By.id(path)).click();
                nTry++;
            }
            $(By.xpath(pathResponse)).click();
        }
        return nTry;
    }

    /**
     * waitForListResponse - ожидание появление ответа от поля с автокомплитом
     *
     * @param path -  id поля для ввода значения для поиска
     * @param value - значение строки для поиска
     * @param pathResponse - xpath-локатор для строки с ответом
     * @return
     * @throws InterruptedException
     */

    public int waitForListResponse(String path, String value, String pathResponse) throws InterruptedException {
        int nTry = 0;
        $(By.id(path)).waitUntil(exist, DELAY_MAX, DELAY_INTERVAL).isDisplayed();
        while (!isExistForXpathElement(pathResponse) && (nTry != nTryMax)) {
            driver.navigate().refresh();
            waitForAjaxControls();
            $(By.id(path)).click();
            $(By.id(path)).sendKeys(value);
            $(By.id(path)).sendKeys(Keys.BACK_SPACE);
        }
        waitForAjaxControls();
        $(By.xpath(pathResponse)).click();
        return nTry;
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