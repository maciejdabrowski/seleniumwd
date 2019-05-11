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
		wykonajTest("01-01-2010", true, true, "Brak kwalifikacji",
				"Sprawdzenie ile lat bêdzie mia³o najstarsze dziecko, które nie zostanie zakwalifikowane (bêdzie zbyt m³ode)");
		//Test 2
		wykonajTest("01-02-2009", true, true, "Mlodzik",
				"Sprawdzenie najm³odszej osoby w kategorii m³odzik");
		//Test 3
		wykonajTest("03-04-2006", true, true, "Mlodzik",
				"Sprawdzenie najstarszej osoby w kategorii m³odzik");
		//Test 4
		wykonajTest("04-02-2005", true, true, "Junior",
				"Sprawdzenie najm³odszej osoby w kategorii junior");
		//Test 5
		wykonajTest("01-01-2002", true, true, "Junior",
				"Sprawdzenie najstarszej osoby w kategorii junior");
		//Test 6
		wykonajTest("01-02-2002", false, true, "Blad danych",
				"Sprawdzenie wymagania zgody rodziców osoby niepe³noletniej");
		//Test 7
		wykonajTest("03-04-2002", true, false, "Blad danych",
					"Sprawdzenie wymagania zaœwiadczenie od lekarza dla osoby niepe³noletniej");
		//Test 8
		wykonajTest("04-02-2001", false, false, "Dorosly",
					"Kwalifikacja najm³odszej osoby do kategorii doros³y. Sprawdzenie braku zale¿noœci kwalifikacji do kategorii doros³y od zaœwiadczenia lekarskiego dla osoby w wieku 18-64.");
		//Test 9
		wykonajTest("01-01-1955", false, false, "Dorosly",
					"Kwalifikacja najstarszej osoby do kategorii doros³y, która nie potrzebuje zaœwiadczenia lekarskiego");
		//Test 10
		wykonajTest("01-02-1954", false, false, "Blad danych",
					"Kwalifikacja osoby powy¿ej 65 roku ¿ycia do kategorii doros³y bez zgody lekarza");
		//Test 11
		wykonajTest("01-01-1954", false, true, "Dorosly",
					"Kwalifikacja osoby powy¿ej 65 roku ¿ycia do kategorii doros³y ");		
		webDriver.quit();
		
	}
	
	private void wykonajTest(String dataUr, boolean rodzice, boolean lekarz, String wynik, String nazwaTestu) {
		webDriver.get(link);
		zapiszDaneNaStronie(dataUr, rodzice, lekarz);
		wyslijFormularz();
		sprawdzWynik(wynik,nazwaTestu);

	}
	
	private void sprawdzWynik(String wynik2, String nazwaTestu) {
		String wynikLabel = webDriver.findElement(By.id(wynik)).getText();
		String wynik = wynikLabel.substring(wynikLabel.lastIndexOf("ii") +2).trim(); //Label konczy sie zawsze na "kategorii" - stad  "ii"
		
		if(wynik.equals(wynik2)) {
			System.out.println("Test "+nazwaTestu+" przeszedl pozytywnie");
		}
		else {
			System.out.println("Test "+nazwaTestu+" przeszedl negatywnie \nWynik: "+wynik+" \nSpodziewany wynik: "+wynik2);
		}
	}


	private void wyslijFormularz() {
		
		webDriver.findElement(By.className(wyslij)).click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webDriver.switchTo().alert().accept();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
