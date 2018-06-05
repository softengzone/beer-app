package com.beerapp.controller.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	BeerControllerTest.class,
	BreweryControllerTest.class
})
public class TestSuiteRunner {

}
