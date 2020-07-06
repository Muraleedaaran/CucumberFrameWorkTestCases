package com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/feature/Trivago.feature", 
				glue = "steps", 
				monochrome = true,
				snippets = SnippetType.CAMELCASE
				)

public class RunnerTrivago extends AbstractTestNGCucumberTests{

}
