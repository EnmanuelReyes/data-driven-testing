package com.sample.beans;

import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/17/18
 * Time: 10:17 AM
 */
@Service
public class SpringTestData implements ITestData {
    @Override
    public Object[][] getData() {
        return new Object[][]{
                {"Machynlleth", true},
                {"Popleton", false}
        };
    }
}
