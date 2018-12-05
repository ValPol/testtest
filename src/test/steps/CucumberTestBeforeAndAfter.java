package test.steps;

import com.codeborne.selenide.junit.BrowserStrategy;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import main.java.helpers.ConfigContainer;
import main.java.helpers.WebDriverContainer;
import org.junit.ClassRule;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

/**
 * Код, который выполняется до и после каждого сценария (Hooks).
 */
public class CucumberTestBeforeAndAfter {
    /******************************************************************************************************************
     *
     * Методы класса
     *
     *****************************************************************************************************************/

    @ClassRule
    public static BrowserStrategy perClass = new BrowserStrategy();

    public static ConfigContainer configContainer = new ConfigContainer();

    /**
     * Код, который выполняется до каждого сценария.
     * @throws java.net.MalformedURLException
     * @throws java.net.UnknownHostException
     */
    @Before
    public void setUp() throws MalformedURLException, UnknownHostException {
        String configName = configContainer.getConfigName();
        ConfigContainer.getInstance().loadConfig(configName);
        WebDriverContainer.getInstance().setDrivers();
    }

    /**
     * Код, который выполняется после каждого сценария.
     * @param scenario
     * @throws java.lang.Exception
     */
    @After
    public void tearDown(Scenario scenario) throws Exception {
        WebDriver driver = WebDriverContainer.getInstance().getWebDriver();
        // Делаем скриншот в случае аварийного завершения теста
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        WebDriverContainer.CloseDrivers();

    }
}
