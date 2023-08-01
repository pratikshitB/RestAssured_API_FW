package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Pratikshit Bansal
 *
 * 1. Create private static variable ConfigManager
 * 2. Create private static Properties variable
 * 3. Create private constructor -
 *      a. select properties file based on "env" value
 *      b. load properties file
 * 4. Create public static method getInstance for returning ConfigManager object
 * 5. Create public method that returns value of any key in properties file
 */
public class ConfigManager {
    private static ConfigManager configManager;
    private static Properties properties=new Properties();


    private ConfigManager() throws IOException {
        String baseFilePath=System.getProperty("user.dir");
        System.out.println(baseFilePath);
        String propertiesFile;
        if(System.getenv("env")!=null && System.getenv("env").toString().equalsIgnoreCase("dev")){
            propertiesFile=baseFilePath+"/src/main/resources/config_dev.properties";
        } else {
            propertiesFile=baseFilePath+"/src/main/resources/config_qa.properties";
        }
        properties.load(new FileInputStream(propertiesFile));
    }

    public static ConfigManager getInstance(){
        if(configManager==null){
            synchronized (ConfigManager.class){
                try {
                    configManager=new ConfigManager();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return configManager;
    }

    public String getPropertyValue(String key){
        return properties.get(key).toString();
    }
}
