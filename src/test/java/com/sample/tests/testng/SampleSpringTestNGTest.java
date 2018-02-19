package com.sample.tests.testng;

import com.sample.beans.ITestData;
import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/17/18
 * Time: 10:19 AM
 */
@Test
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SampleSpringTestNGTest extends AbstractTestNGSpringContextTests{

    private WebDriver driver;

    @Autowired
    ITestData testData;

    @DataProvider(name = "spring_provider")
    public Object[][] createData() {
        return testData.getData();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        driver = Driver.current();
        driver.get(Configuration.get("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "spring_provider")
    public void sampleSearch(String destination) throws Exception {
        SearchPage searchPage = PageFactory.init(driver, SearchPage.class);

        searchPage.editDestination.setText(destination);
        searchPage.buttonDownShevron.click();
        searchPage.buttonTodaysDate.click();


        SearchResultsPage searchResultsPage = searchPage.buttonSubmit.click(SearchResultsPage.class);
        ;

        searchResultsPage.editDestination.click();

        Assert.assertTrue(searchResultsPage.isTextPresent(destination));
        searchResultsPage.captureScreenShot("./image-" + destination + ".png");
    }
}
