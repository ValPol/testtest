package main.java;

import com.codeborne.selenide.SelenideElement;
import main.java.helpers.ConfigContainer;
import main.java.helpers.WebDriverContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Абстрактная страница, от которой наследуются все реальные страницы приложения.
 * Created by Evgeniy Glushko on 26.04.2016.
 */
public abstract class AbstractPage {
    /*******************************************************************************************************************
     *                                             Поля класса
     ******************************************************************************************************************/
    public static WebDriver driver;
    protected ConfigContainer config;
    protected int delayTimeMs = 100000;
    protected int delayTime300 = 300000; //30000
    protected long pollingIntervalMs = 50;
    protected int timeForIsDisplay1 = 5;
    protected int timeForIsDisplay2 = 15; //30

    /*******************************************************************************************************************
     * Конструктор класса. Отвечает за инициализацию всех полей класса
     ******************************************************************************************************************/

    public AbstractPage() {
        this.driver = WebDriverContainer.getInstance().getWebDriver();
        this.config = ConfigContainer.getInstance();
    }

    /*******************************************************************************************************************
     * Методы класса
     ******************************************************************************************************************/

    /**
     * Возвращается на предыдущую страницу.
     */
    public void goToBackTab() throws Throwable {
        driver.navigate().back();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(" [-]: произведен возврат на предыдущую страницу");
    }

    /**
     * Устанавливает значение текстового поля.
     *
     * @param by    как искать поле на странице (id, xpath, css и т.д.)
     * @param value значение поля, которое следует установить
     */
    protected void setValueToTextField(By by, String value) throws InterruptedException {
        // Число попыток установки значения поля
        Integer i = 0;
        // Пытаемся установить значение поля (как минимум 1 раз, но до 10 попыток)
        $(by).waitUntil(visible, delayTimeMs, pollingIntervalMs);
        do {
            $(by).clear();
            $(by).setValue(value);
            $(by).pressTab();
            $(by).waitUntil(visible, delayTimeMs, pollingIntervalMs);
            i++;
            System.out.println(" [-]: кол-во попыток установки поля: " + i + " значение: " + $(by).getValue());
        }
        while (!$(by).getValue().equals(value) && i < 10);

        // Проверяем условие выхода из цикла (удалось ли  установить значение поля)
        $(by).shouldHave(value(value));
        if (i > 1) System.out.println(" [-]: the value [" + value + "] was set from " + i + " try");
    }

    protected void setValueToTextField(SelenideElement element, String value) throws InterruptedException {
        // Число попыток установки значения поля
        Integer i = 0;
        // Пытаемся установить значение поля (как минимум 1 раз, но до 10 попыток)
        element.waitUntil(visible, delayTimeMs, pollingIntervalMs);
        do {
            element.clear();
            element.setValue(value);
            element.waitUntil(visible, delayTimeMs, pollingIntervalMs);
            i++;
            System.out.println(" [-]: кол-во попыток установки поля: " + i + " значение: "
                    + element.getValue());
        }
        while (!element.getValue().equals(value) && i < 10);

        // Проверяем условие выхода из цикла (удалось ли  установить значение поля)
        element.shouldHave(value(value));
        if (i > 1) System.out.println(" [-]: the value <" + value + "> was set from " + i + " try");
    }

    /**
     * Выбирает значение из раскрывающегося списка.
     *
     * @param openList      поле со свернутым списком, отображающее выбранное значение
     * @param input         раскрытый список возможных значений поля
     * @param valueToSelect значение поля, которое требуется выбрать в списке
     */
    protected void selectValueFromList(SelenideElement openList, SelenideElement input,
                                       String valueToSelect) throws Exception {
        openList.waitUntil(visible, delayTimeMs, pollingIntervalMs).click();
        driver.manage().timeouts().implicitlyWait(timeForIsDisplay1, TimeUnit.SECONDS);
        if (!openList.isDisplayed()) openList.click();
        driver.manage().timeouts().implicitlyWait(timeForIsDisplay2, TimeUnit.SECONDS);
        input.waitUntil(visible, delayTimeMs, pollingIntervalMs).val(valueToSelect);
        TimeUnit.SECONDS.sleep(3);
        input.sendKeys(Keys.ENTER);
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Возвращает текущую дату в виде строки заданного формата.
     */
    protected String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HHmmSS");
        return dateFormat.format(new Date());
    }

    /**
     * Возвращает текущую дату в виде строки заданного формата.
     */
    protected String getCurrentDateTimeWithoutSeconds() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 3);
        return dateFormat.format(cal.getTime());

    }

    /**
     * Возвращает текущую дату в виде строки заданного формата.
     */
    protected String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(new Date());
    }

    /**
     * Увеличивает значение часов и месяца в текущей дате на требуемую величину (дата в будущем).
     *
     * @param changeHour  - значение в часах
     * @param changeMonth - значение в месяцах
     * @return - возвращает дату
     */
    protected String changeDate(int changeHour, int changeMonth) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy HH:mm");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        // your date (java.util.Date)
        cal.setTime(date);
        if (changeHour != 0) cal.add(Calendar.HOUR, changeHour);
        else if (changeMonth != 0) cal.add(Calendar.MONTH, changeMonth);
        // New date
        return dateFormat.format(cal.getTime());
    }

    /**
     * Уменьшает значение даты
     *
     * @param amountOfDays - кол-во дней на которое уменьшается дата
     * @return - возвращает дату
     */
    protected String getDateAgo(int amountOfDays) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -amountOfDays);
        System.out.println("[getDateAgo]: полученная дата: " + dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }


    /**
     * Ожидает появления кнопки и нажимает ее.
     *
     * @param button локатор нажимаемой кнопки
     */
    protected void clickButton(By button) throws Exception {
        $(button).waitUntil(exist, delayTimeMs, pollingIntervalMs).click();
    }

    /**
     * Ожидает появления кнопки и нажимает ее.
     *
     * @param button локатор нажимаемой кнопки
     */
    protected void clickButton(SelenideElement button) throws Exception {
        button.waitUntil(exist, delayTimeMs, pollingIntervalMs).click();
    }

    /*******************************************************************************************************************
     *
     * JavaScript - методы
     *
     ******************************************************************************************************************/

    /**
     * Устанавливает фокус в поля типа "kendoNumericTextBox" с помощью JS.
     *
     * @param idLocator - id локатор элемента
     */
    protected void setFocusInKendoNumericTextBoxJS(String idLocator) throws Exception {
        String script = "$(\"#" + idLocator + "\").data(\"kendoNumericTextBox\").focus();";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    /**
     * Делает клик по элементу страницы используя By с помощью JS.
     *
     * @param by элемент по которому следует кликнуть
     */
    protected void clickInElementJS(By by) throws Exception {
        SelenideElement element = $(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Делает нажатие по элементу страницы используя SelenideElement с помощью JS.
     *
     * @param element - элемент по которому следует кликнуть
     */
    protected void clickInElementJS(SelenideElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        System.out.println(" [-]: выполнен JS: arguments[0].click();");
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * Делает скролл к элементу страницы используя By с помощью JS.
     *
     * @param element - элемент по которому следует кликнуть
     */
    protected void scrollToElementJS(SelenideElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        System.out.println(" [-]: выполнен JS: arguments[0].scrollIntoView();");
    }

    /**
     * Устанавливаем значение с помощью JS
     *
     * @param element - элемент SelenideElement
     * @param value   - значение
     */
    protected void setValueInElementJS(SelenideElement element, String value) throws Exception {
        String script = String.format("arguments[0].value = '%s';", value);
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    /**
     * Устанавливает значение элемента страницы используя строковый Id с помощью JS.
     *
     * @param idLocator строковый Id элемента для которого следует установить значение
     * @param value     требуемое значение элемента
     */
    protected void setValueInElementJS(String idLocator, String value) throws Exception {
        String script = "document.getElementById(\"" + idLocator + "\").value=\"" + value + "\";";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    protected void checkTextOfElementJQ(String idLocator, String expectedValue) throws Exception {
        waitForPageLoad();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return $(\"" + idLocator + "\").text();";
        if (js.executeScript(script).toString().equals(expectedValue)) {
            System.out.println("Ожидаемое и реальное значения совпадают");
        } else {
            System.out.println("Текущее значение: " + js.executeScript(script).toString());
            throw new AssertionError();
        }
    }

    protected void clickElementWithJQ(String idLocator) throws Exception {
        waitForPageLoad();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "$('" + idLocator + "').click()";
        js.executeScript(script);
        System.out.println("Произведено нажатие по элементу с помощтю JQ");
    }

    /**
     * Устанавливает значение элемента страницы используя строковый Id с помощью JS.
     *
     * @param element   тип элемента для установки значения
     * @param attribute аттрибут для поиска такого элемента
     * @param idLocator значение аттрибута
     * @param value     значение для установки
     */
    protected void setValueInElementJQ(String element, String attribute, String idLocator, String value) throws Exception {
        String script = "$(\"" + element + "[" + attribute + "='" + idLocator + "']\").val('" + value + "');";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    protected void setValueInElementJQ(String idLocator, String value) throws Exception {
        String script = "$('" + idLocator + "').val('" + value + "')";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    protected void initiateOnChangeEventJQ(String element, String attribute, String idLocator) throws Exception {
        String script = "$(\"" + element + "[" + attribute + "='" + idLocator + "']\").trigger(\"change\");";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    protected void initiateOnChangeEventJQ(String idLocator) throws Exception {
        String script = "$(\"" + idLocator + "\").trigger(\"change\");";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public void waitForPageLoad() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            System.out.println(" [-] страница загружена. URL: " + url());
            return;
        }

        // This loop will rotate for 120 times to check If page Is ready after every 1 second.
        // You can replace your value with 120 If you wants to Increase or decrease wait time.
        int totalTime = 0;
        int numberOfRepetitions = 0;

        for (int i = 1; i < 120; i++) {
            try {
                Thread.sleep(500); //1000
                totalTime = totalTime + 1;
                numberOfRepetitions = numberOfRepetitions + 1;
            } catch (InterruptedException e) {
            }
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) break;
        }
        System.out.println(" [-] ждали открытия страницы: " + totalTime + " сек," +
                " кол-во повторений: " + numberOfRepetitions);
    }
}