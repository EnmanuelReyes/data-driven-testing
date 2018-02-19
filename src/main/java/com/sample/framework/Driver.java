package com.sample.framework;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/12/18
 * Time: 4:10 PM
 */
public class Driver {

    private Driver() {

    }

    private static WebDriver driver;
    private static final HashMap<String, Class<?>> driverMap = new HashMap<String, Class<?>>() {
        {
            put("chrome", ChromeDriver.class);
            put("firefox", FirefoxDriver.class);
            put("ie", InternetExplorerDriver.class);
            put("safari", SafariDriver.class);
            put("opera", OperaDriver.class);
        }
    };

    public static void add(String browser, Capabilities capabilities) throws Exception {
        Class<?> driverClass = driverMap.get(browser);
        driver = (WebDriver) driverClass.getConstructor(Capabilities.class).newInstance(capabilities);
    }

    public static WebDriver current() {
        return driver;
    }
}