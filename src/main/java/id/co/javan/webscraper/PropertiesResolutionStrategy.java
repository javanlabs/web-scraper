package id.co.javan.webscraper;

import java.util.Properties;

/**
 * This strategy reads phantomjs location from properties file.
 */
public class PropertiesResolutionStrategy implements PhantomJsResolutionStrategy {

    public String getPath() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("phantomjs.properties"));
            return properties.getProperty("path");
        } catch (Exception e) {
            // theres nothing we can do except hope that its already in PATH
            return "phantomjs";
        }
    }
}
