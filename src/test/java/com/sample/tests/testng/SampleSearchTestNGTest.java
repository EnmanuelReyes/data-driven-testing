package com.sample.tests.testng;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/12/18
 * Time: 3:53 PM
 */
public class SampleSearchTestNGTest {

//    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        Driver.current().get(Configuration.get("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.current().quit();
    }

    @DataProvider(name = "inclass_provider")
    public Object[][] createData() {
        return new Object[][]{
                {"London", true},
                {"Manchester", true},
        };
    }

    public static class StaticProvider {
        @DataProvider(name = "sample_search")
        public static Object[][] staticData() {
            return new Object[][]{
                    {"Leeds", true},
                    {"Newcastle", true},
            };
        }
    }

    @DataProvider(name = "file_provider")
    public Object[][] getDataFromFile() throws IOException {
        List<String> content = FileUtils.readLines(new File("./src/test/resources/data.csv"), Charset.defaultCharset());
        Object[][] data = new Object[][]{};
        for (String line : content) {
            data = (Object[][]) ArrayUtils.add(data, line.split("\t"));
        }
        return data;

    }

    @DataProvider(name = "service_provider")
    public Object[][] getDataFromService() throws IOException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("http://localhost:4999/test");
        String result = "";

        try {
            int statusCode = client.executeMethod(method);
            result = method.getResponseBodyAsString();
            method.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] content = result.split("\n");
        Object[][] data = new Object[][]{};
        for (String line : content) {
            data = (Object[][]) ArrayUtils.add(data, line.split("\t"));
        }
        return data;

    }

    @DataProvider(name = "excel_provider")
    public Object[][] getDataFromExcel() throws IOException, InvalidFormatException {
        Object[][] data = new Object[][]{};
        Workbook book = new XSSFWorkbook(new File("./src/test/resources/book.xlsx"));
        Sheet sheet = book.getSheet("Locations");
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String city = row.getCell(0).getStringCellValue();
            data = (Object[][]) ArrayUtils.add(data, new Object[]{city});
        }
        book.close();
        return data;
    }

    private void sampleSearch(String destination) throws Exception {
        SearchPage searchPage = PageFactory.init(Driver.current(), SearchPage.class);

        searchPage.editDestination.setText(destination);
        searchPage.buttonDownShevron.click();
        searchPage.buttonTodaysDate.click();


        SearchResultsPage searchResultsPage = searchPage.buttonSubmit.click(SearchResultsPage.class);
        ;

        searchResultsPage.editDestination.click();

        Assert.assertTrue(searchResultsPage.isTextPresent(destination));
        searchResultsPage.captureScreenShot("./image-" + destination + ".png");
    }

    //    @Test(dataProvider = "inclass_provider")
    public void testSampleSearchFromTheSameClass(String destination) throws Exception {
        sampleSearch(destination);

    }

    //    @Test(dataProvider = "sample_provider", dataProviderClass = StaticProvider.class)
    public void testSampleSearchClassProvider(String destination) throws Exception {
        sampleSearch(destination);

    }

    @Parameters({"destination"})
//    @Test
    public void testSampleSearchClassProvider(String destination, String papa) throws Exception {
        sampleSearch(destination);

    }

    @Test(dataProvider = "file_provider")
    public void testSampleSearchFromFile(String destination, String papa) throws Exception {
        sampleSearch(destination);

    }

    @Test(dataProvider = "service_provider")
    public void testSampleSearchFromFile(String destination) throws Exception {
        sampleSearch(destination);

    }

    @Test(dataProvider = "excel_provider")
    public void testSampleSearchFromExcel(String destination) throws Exception {
        sampleSearch(destination);
    }
}

