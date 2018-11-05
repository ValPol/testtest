package main.java.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

public class ConfigContainer implements Serializable {

    // Относительный путь к файлу с настройками тестовой среды (параметризованный конфигурационный файл)
    private static final String PROPERTIES_FILE_NAME = "/src/test/resources/config/";

    public static final String uploadFileUnformalizedDocumentExchange = "/src/test/resources/attachements/exchangeDocumentForTest.txt";

    //------------------------------------------------------------------------------------------------------------------
    /**
     * ****************************************************************************************************************
     *
     * Поля класса
     *
     *****************************************************************************************************************
     */
    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static ConfigContainer instance;

    // Настройки тестовой среды (считываются из файла config.properties и используются во всех тестовых сценариях)
    private Properties properties = new Properties();

    /**
     * Методы доступа к экземпляру этого класса
     */
    public static synchronized ConfigContainer getInstance() {
        if (instance == null) {
            instance = new ConfigContainer();
        }
        return instance;
    }

    // region Относительный путь к файлу с настройками тестовой среды
    private String getPropertiesFileName(String configName) {
        String propertiesName = System.getProperty("user.dir") + PROPERTIES_FILE_NAME + configName + ".properties";
        return propertiesName;
    }

    public String getConfigParameter(String key) {
        // Encode to utf8 here
        return properties.getProperty(key);
    }

    /**
     * ****************************************************************************************************************
     *
     * Методы класса
     *
     *****************************************************************************************************************
     */
    /**
     * Загружает настройки тестовой среды из файла [config.properties].
     *
     * @param configName
     */
    public void loadConfig(String configName) {
        InputStreamReader input = null;
        try {
            input = new InputStreamReader(new FileInputStream(getPropertiesFileName(configName)), "utf-8");
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Выбор файла конфигурации "stable_servicing" - для стандартного прогона
     * тестов "production" - смоук на бою "entranceSettings" - входные конфиги,
     * которые формирует множество кабинетов и организаций в новом окружении
     *
     * @return имя файла конфигурации
     */
    public String getConfigName() {
        String configName = System.getenv("EDO_AUTOTEST_CONFIG");
        System.out.println(" [-]: SYSTEM ENV variable EDO_AUTOTEST_CONFIG is : '" + configName + "'.");
        if (configName == null) {
            configName = "stable_servicing";
        }
        return configName;
    }

    public void setProperties(String field, String newValue) {
        properties.setProperty(field, newValue);
    }

}
