package step_definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.es.Dado;
import support.ConfigFileReader;
import support.WebDriverManager;

public class Comum {
	WebDriverManager wdm = new WebDriverManager();
	WebDriver driver = wdm.getDriver();
	ConfigFileReader cfr = new ConfigFileReader();
	
	@Dado("^que eu esteja logado$")
	public void que_eu_esteja_logado() throws Throwable {
		driver.get(cfr.getApplicationUrl());
		driver.findElement(By.xpath("//*[@placeholder='Digite seu RA']")).sendKeys(cfr.getRa());
		driver.findElement(By.id("add")).click();
	}
}
