package com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.cucumber.*;

@CucumberOptions(features = "src/test/java/feature/HondaBike.feature", 
				glue = "steps", 
				monochrome = true,
				snippets = SnippetType.CAMELCASE,
				dryRun = false
				)

public class RunnerHondaBike extends AbstractTestNGCucumberTests{

}
