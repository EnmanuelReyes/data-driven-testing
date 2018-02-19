package com.sample.tests.junit;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/17/18
 * Time: 10:39 AM
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"./src/test/java/com/sample/tests/features"},
        glue = {"com/sample/tests/steps"},
        plugin = {
                "json:build/cucumber.json", "html:build/cucumber-html-report",
                "pretty:build/cucumber-pretty.txt",
                "usage:build/cucumber-usage.json",
                "junit:build/cucumber-junit-results.xml"
        }, tags = {}
)
public class SampleCucumberTest {
}
