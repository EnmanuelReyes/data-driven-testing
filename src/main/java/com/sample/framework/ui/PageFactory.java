package com.sample.framework.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/16/18
 * Time: 10:16 AM
 */
public class PageFactory {

    private static By toLocator(String input) {
        if (input.matches("^xpath=|/(.*)")) {
            return By.xpath(input.replaceAll("^xpath=", ""));
        } else if (input.matches("^id=|/(.*)")) {
            return By.xpath(input.replaceAll("^id=", ""));
        } else if (input.matches("^name=|/(.*)")) {
            return By.xpath(input.replaceAll("^name=", ""));
        } else if (input.matches("^css=|/(.*)")) {
            return By.xpath(input.replaceAll("^css=", ""));
        } else if (input.matches("^class=|/(.*)")) {
            return By.xpath(input.replaceAll("^class=", ""));
        } else {
            return By.id(input);
        }
    }

    public static <T extends Page> T init(WebDriver driver, Class<T> pageClass) throws Exception {
        T page = pageClass.getConstructor(WebDriver.class).newInstance(driver);
        for (Field field : pageClass.getFields()) {
            FindBy locator = field.getAnnotation(FindBy.class);
            if (locator != null) {
                Object control = field.getType().getConstructor(Page.class, By.class)
                        .newInstance(page, toLocator(locator.locator()));
                field.set(page, control);
            }
        }
        return page;
    }
}
