package com.sample.tests.steps;

import com.sample.framework.ui.Control;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.SearchPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/17/18
 * Time: 10:38 AM
 */
public class SampleSteps {
    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_page(String name) throws Throwable {
        SearchPage page = (SearchPage) Page.screen(name);
        Assert.assertNotNull("Unable to find the page for name: '" + name + "'", page);
        page.navigate();
        Page.setCurrentPage(page);
    }

    @When("^I enter \"([^\"]*)\" text into the \"([^\"]*)\" field$")
    public void i_enter_text_into_the_field(String text, String fieldName) throws Throwable {
        Edit control = (Edit) Page.getCurrentPage().onPage(fieldName);
        Assert.assertNotNull("Unable to find control for name: '" + fieldName + "' for current page", control);
        control.setText(text);
    }

    @When("^click on the \"([^\"]*)\" (?:element|button)$")
    public void click_on_the_element(String fieldName) throws Throwable {
        Control control = Page.getCurrentPage().onPage(fieldName);
        Assert.assertNotNull("Unable to find control for name: '" + fieldName + "' for current page", control);
        control.click();
    }


    @Then("^I should see the \"([^\"]*)\" screen$")
    public void i_should_see_the_screen(String name) throws Throwable {
        Page page = Page.screen("name");
        Assert.assertNotNull("Unable to find the page for name: '" + name + "'", page);
        Assert.assertTrue("The '" + name + "' is not current", page.isCurrent());
        page.navigate();
        Page.setCurrentPage(page);
    }

    @Then("^the \"([^\"]*)\" field has \"([^\"]*)\" text$")
    public void the_field_has_text(String fieldName, String text) throws Throwable {
        Control control = Page.getCurrentPage().onPage(fieldName);
        Assert.assertNotNull("Unable to find control for name: '" + fieldName + "' for current page", control);
        Assert.assertTrue(control.getText().contains(text));
    }

}
