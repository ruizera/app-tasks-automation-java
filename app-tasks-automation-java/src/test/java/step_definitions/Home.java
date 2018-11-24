package step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import support.WebDriverManager;

public class Home {
	private List<String> tarefas = new ArrayList<String>();
	private List<String> tarefasConcluidas = new ArrayList<String>();

	WebDriverManager wdm = new WebDriverManager();
	WebDriver driver = wdm.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 4);

	@Quando("^eu adicionar (\\d+) tarefa valida$")
	public void eu_adicionar_tarefa_valida(int arg1) throws Throwable {
		String tarefa = "Tarefa n";
		String nomeTarefa = "";
		for (int i = 1; i <= arg1; i++) {
			nomeTarefa = tarefa.replace("n", String.valueOf(i));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Nova tarefa...']"))).sendKeys(nomeTarefa);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("data"))).sendKeys("01012019");
			driver.findElement(By.id("add")).click();
			tarefas.add(nomeTarefa);
		}
	}

	@Entao("^as tarefas retornadas devem ser exibidas corretamente$")
	public void as_tarefas_retornadas_devem_ser_exibidas_corretamente() throws Throwable {
		assertTrue(driver.findElements(By.xpath("//*[@id='tasks']/div")).size() == tarefas.size());
	}

	@Quando("^eu adicionar (\\d+) tarefa inválida$")
	public void eu_adicionar_tarefa_inválida(int arg1) throws Throwable {
		String nomeTarefa = "";
		for (int i = 1; i <= arg1; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Nova tarefa...']"))).sendKeys(nomeTarefa);
			driver.findElement(By.id("add")).click();
			tarefas.add(nomeTarefa);
		}
	}

	@Entao("^não será adicionado nenhuma tarefa$")
	public void não_será_adicionado_nenhuma_tarefa() throws Throwable {
		assertEquals(0, driver.findElements(By.xpath("//*[@id='tasks']/div")).size());
	}

	@Entao("^a quantidade de tarefas deve ser igual a (\\d+)$")
	public void a_quantidade_de_tarefas_deve_ser_igual_a(int arg1) throws Throwable {
		assertTrue(driver.findElements(By.xpath("//*[@id='tasks']/div")).size() == arg1);
	}

	@Quando("^eu remover (\\d+) tarefa$")
	public void eu_remover_tarefa(int arg1) throws Throwable {
		List<WebElement> remover = driver.findElements(By.id("remove"));
		for (int i = 0; i < arg1; i++) {
			if(remover.isEmpty()) {
				break;
			}
			remover.get(remover.size() - i - 1).click();
			tarefas.remove(remover.size() - i - 1);
		}
	}

	@Quando("^eu marcar (\\d+) tarefas como concluidas$")
	public void eu_marcar_tarefas_como_concluidas(int arg1) throws Throwable {
		List<WebElement> concluir = driver.findElements(By.id("close-open"));
		for (int i = 0; i < arg1; i++) {
			if(tarefas.size()==0) {
				break;
			}
			concluir.get(concluir.size() - 1 - i).click();
			tarefasConcluidas.add(tarefas.get(concluir.size() - 1 - i));
			tarefas.remove(concluir.size() - 1 - i);
		}
	}

	@Entao("^as tarefas marcadas como concluidas devem estar corretas$")
	public void as_tarefas_marcadas_como_concluidas_devem_estar_corretas() throws Throwable {
		assertTrue(driver.findElements(By.xpath("//*[contains(@class,'checked')]")).size() == tarefasConcluidas.size());
	}
	
}
