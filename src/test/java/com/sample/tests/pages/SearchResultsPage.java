package com.sample.tests.pages;

import com.sample.framework.ui.Alias;
import com.sample.framework.ui.Control;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Edit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/15/18
 * Time: 12:17 PM
 */
@Alias("Search Results")
public class SearchResultsPage extends Page {
    @Alias("Title")
    @FindBy(locator = "//h1")
    public Control textSubtitle;

    @FindBy(locator = "ss")
    public Edit editDestination;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
}
