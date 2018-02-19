package com.sample.framework.ui;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/15/18
 * Time: 12:09 PM
 */
public class Page {

    private static final long TIMEOUT = Configuration.timeout();
    private static Page currentPage = null;

    private WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public static Page screen(String name) throws Exception {
        return screen(name, Configuration.get("pages_package"));
    }

    public static Page screen(String name, String pagesPackage) throws Exception {
        Reflections reflections = new Reflections(pagesPackage);
        Set<Class<? extends Page>> subTypes = reflections.getSubTypesOf(Page.class);
        for (Class<? extends Page> type : subTypes) {
            Alias annotation = type.getAnnotation(Alias.class);
            if (annotation != null && annotation.value().equals(name)) {
                return PageFactory.init(Driver.current(), type);
            }
        }
        return null;
    }

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(Page newPage) {
        Page.currentPage = newPage;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Page navigate() {
        return this;
    }

    public boolean isTextPresent(String text) {
        String locator = String.format("//*[text() = '%s' or contains(text(), '%s')]", text, text);
        Control element = new Control(this, By.xpath(locator));
        return element.exists();
    }

    public byte[] captureScreenShot() {
        WebDriver augmentedDriver = new Augmenter().augment(this.getDriver());
        byte[] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        return data;
    }

    public File captureScreenShot(String destination) throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(this.getDriver());
        File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        File output = new File(destination);
        FileUtils.copyFile(srcFile, output);
        return output;
    }

    public boolean isCurrent(long timeout) throws IllegalAccessException {
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            if (Control.class.isAssignableFrom(field.getType())) {
                Control control = (Control) field.get(this);
                if (!control.exists(timeout)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isCurrent() throws IllegalAccessException {
        return isCurrent(TIMEOUT);
    }

    public Control onPage(String name) throws IllegalAccessException {
        for (Field field : this.getClass().getFields()) {
            if (Control.class.isAssignableFrom(field.getType())) {
                Alias alias = field.getAnnotation(Alias.class);
                if (alias != null && name.equals(alias.value())) {{
                    return (Control) field.get(this);
                }}
            }
        }
        return null;
    }
}
