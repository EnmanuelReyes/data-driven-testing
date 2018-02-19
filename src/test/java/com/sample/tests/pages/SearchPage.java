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
@Alias("Search")
public class SearchPage extends Page{

    @Alias("Destination")
    @FindBy(locator = "ss")
    public Edit editDestination;
    @Alias("Down Shevron")
    @FindBy(locator = "css=i.sb-date-field__chevron.bicon-downchevron")
    public Control buttonDownShevron;
    @Alias("Today's Date")
    @FindBy(locator = "//table[@class='c2-month-table']//td[contains(@class, 'c2-day-s-today')]")
    public Control buttonTodaysDate;
    @Alias("Search")
    @FindBy(locator = "//button[@type='submit']")
    public Control buttonSubmit;

    public SearchPage(WebDriver driver) {
        super(driver);

    }
}
