package com.sample.framework.ui.controls;

import com.sample.framework.ui.Control;
import com.sample.framework.ui.Page;
import org.openqa.selenium.By;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/15/18
 * Time: 11:54 AM
 */
public class Edit extends Control {

    public Edit(Page parentValue, By locator) {
        super(parentValue, locator);
    }

    public void setText(String text) {
        this.click();
        this.element().clear();
        this.element().sendKeys(text);
    }
}
