package step_definitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import support.ConfigFileReader;
import support.WebDriverManager;

public class Comum {
	WebDriverManager wdm = new WebDriverManager();
	WebDriver driver = wdm.getDriver();
	ConfigFileReader cfr = new ConfigFileReader();
	
	@Before
	public void setUp() {
		driver.get(cfr.getApplicationUrl());
		driver.findElement(By.xpath("//*[@placeholder='Digite seu RA']")).sendKeys(cfr.getRa());
		driver.findElement(By.id("add")).click();

		if(driver.findElements(By.xpath("//*[@id='tasks']/div")).size()>0) {
			List<WebElement> remover = driver.findElements(By.id("remove"));
			for (int i = 0; i <remover.size(); i++) {
				if(remover.isEmpty()) {
					break;
				}
				remover.get(remover.size() - i - 1).click();
			}
		}
	}

	
	@Dado("^que eu esteja logado$")
	public void que_eu_esteja_logado() throws Throwable {
		driver.get(cfr.getApplicationUrl());
		driver.findElement(By.xpath("//*[@placeholder='Digite seu RA']")).sendKeys(cfr.getRa());
		driver.findElement(By.id("add")).click();
		
	}
	
	@After
	public void tearDown() {
		if(driver.findElements(By.xpath("//*[@id='tasks']/div")).size()>0) {
			
			List<WebElement> remover = driver.findElements(By.id("remove"));
			int qtdTarefas = remover.size();
			for (int i = 0; i <qtdTarefas; i++) {
				if(remover.isEmpty()) {
					break;
				}
				
				
				remover.get(remover.size() - i - 1).click();
			}
		}
	}

	
}
