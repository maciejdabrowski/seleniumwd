package qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class SeleniumTestZawody {

	private String link;
	private String imie = "inputEmail3";
	private String nazwisko = "inputPassword3";
	private String dataUr = "dataU";
	private String rodzice = "rodzice";
	private String zaswiadczenie = "lekarz";
	private String wyslij = "btn";
	private String wynik = "returnSt";
	private WebDriver webDriver;
	
	
	public SeleniumTestZawody(String link) {
		this.link=link;
		webDriver = new FirefoxDriver();
	}


	public void run() {
		

		
		//Test 1
		wykonajTest("04-02-2011", true, true, "Brak kwalifikacji");
		//ustawPola(pt.getImie(), pt.getNazwisko(), pt.getDataU(), pt.isZgodaRodziow(), pt.isZaswiadczenie());

		
		
	}
	
	private void wykonajTest(String dataUr, boolean rodzice, boolean lekarz, String wynik) {
		webDriver.get(link);
		zapiszDaneNaStronie(dataUr, rodzice, lekarz);
		wyslijFormularz();
		sprawdzWynik(wynik);
		webDriver.quit();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sprawdzWynik(String wynik2) {
		String wynikLabel = webDriver.findElement(By.id(wynik)).getText();
		String wynik = wynikLabel.substring(wynikLabel.lastIndexOf("ii") +2).trim(); //Label konczy sie zawsze na "kategorii" - stad  "ii"
		
		if(wynik.equals(wynik2)) {
			System.out.println("Test przeszedl pozytywnie");
		}
		else {
			System.out.println("Test przeszedl negatywnie \nWynik: "+wynik+" \nSpodziewany wynik: "+wynik2);
		}
	}


	private void wyslijFormularz() {
		
		webDriver.findElement(By.className(wyslij)).click();
		
		webDriver.switchTo().alert().accept();
		webDriver.switchTo().alert().accept();
	}


	public void zapiszDaneNaStronie(String dataU, boolean zgodaRodzicow, boolean zaswiadczenie) {
		webDriver.findElement(By.id(imie)).sendKeys("Maciej");
		webDriver.findElement(By.id(nazwisko)).sendKeys("Dabrowski");//imie i nazwisko moga sie powtarzac
		
		webDriver.findElement(By.id(dataUr)).sendKeys(dataU);
		
		if (zaswiadczenie) {
			webDriver.findElement(By.id(this.zaswiadczenie)).click();
		}
		if (zgodaRodzicow) {
			webDriver.findElement(By.id(rodzice)).click();
		}
	}

	

}
