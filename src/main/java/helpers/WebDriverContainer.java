package main.java.helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverContainer {

    //
    /**
     * *****************************************************************************************************************
     * Поля класса.
     * ****************************************************************************************************************
     */
    // Статический экземпляр этого класса
    private static WebDriverContainer instance;

    // Статический экземпляр Selenium WebDriver
    private static WebDriver driver;

    /**
     * *****************************************************************************************************************
     *********** Методы класса ***************
     * ****************************************************************************************************************
     */
    /**
     * Возвращает статический экземпляр этого класса (если класс еще не имеет
     * экземпляра, то создает новый экземпляр).
     *
     * @return Статический экземпляр этого класса
     */
    public static synchronized WebDriverContainer getInstance() {
        if (instance == null) {
            instance = new WebDriverContainer();
        }
        return instance;
    }

    /**
     * Возвращает статический экземпляр WebDriver (инициализирует его если он
     * еще не инициализирован).
     *
     * @return Статический экземпляр Selenium WebDriver
     */
    public WebDriver getWebDriver() {
        return driver;
    }

    /**
     * Инициализирует статический экземпляр WebDriver.
     *
     * @throws java.net.MalformedURLException
     */
    public void setDrivers() throws MalformedURLException {
        File driverexe = new File("src/test/resources/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        String host = System.getProperty("host");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setBrowserName(BrowserType.CHROME);

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        HashMap<String, Object> prefs = new HashMap();
        // для автоматического скачивания файлов
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("download.default_directory", System.getProperty("user.dir") + "/src/test/resources/download");
        options.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("--start-maximized");
        options.merge(capabilities);

        if (SystemUtils.IS_OS_LINUX && host != null) {
            driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), options);
        } else {
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(driverexe)
                    .usingAnyFreePort()
                    .build();
            driver = new ChromeDriver(service, options);
        }
        WebDriverRunner.setWebDriver(driver);
    }

    public static void CloseDrivers() {
        driver.quit();
    }

}
