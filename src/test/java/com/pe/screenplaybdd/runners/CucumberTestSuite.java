package com.pe.screenplaybdd.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features"},glue = {"com.pe.screenplaybdd.steps"})
public class CucumberTestSuite {
}
