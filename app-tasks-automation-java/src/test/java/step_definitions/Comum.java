package step_definitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.es.Dado;
import support.ConfigFileReader;
import support.WebDriverManager;

public class Comum {
	WebDriverManager wdm = new WebDriverManager();
	WebDriver driver = wdm.getDriver();
	ConfigFileReader cfr = new ConfigFileReader();
	
	@Dado("^que eu esteja na home do site$")
	public void que_eu_esteja_na_home_do_site() throws Throwable {
		driver.get(cfr.getApplicationUrl());
	}
}
