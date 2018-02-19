package com.sample.tests.junit;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.Control;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/12/18
 * Time: 10:25 AM
 */
@RunWith(Parameterized.class)
public class SampleSearchTest {

    private WebDriver driver;

    private String destination;
    private boolean isBusiness;

    public SampleSearchTest(String destination, boolean isBusiness) {
        this.destination = destination;
        this.isBusiness = isBusiness;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        driver = Driver.current();
        driver.get(Configuration.get("url"));
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(
                new Object[][] {
                        {"London", true},
                        {"Manchester", true},
                }
        );
    }

    @Test
    public void testSampleSearch() throws Exception {

        SearchPage searchPage = PageFactory.init(driver, SearchPage.class);

        searchPage.editDestination.setText(destination);
        searchPage.buttonDownShevron.click();
        searchPage.buttonTodaysDate.click();


        SearchResultsPage searchResultsPage = searchPage.buttonSubmit.click(SearchResultsPage.class);;

        searchResultsPage.editDestination.click();

        Assert.assertTrue(searchResultsPage.isTextPresent(destination));
        searchResultsPage.captureScreenShot("./image-"+ destination +".png");
    }
}
