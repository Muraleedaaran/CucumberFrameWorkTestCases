package com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/java/feature/BigBasket.feature", 
				glue = "steps", 
				monochrome = true
				//snippets = SnippetType.CAMELCASE
				)

public class RunnerBigBasket extends AbstractTestNGCucumberTests{

}
