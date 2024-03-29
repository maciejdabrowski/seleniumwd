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
				"Sprawdzenie ile lat b璠zie mia這 najstarsze dziecko, kt鏎e nie zostanie zakwalifikowane (b璠zie zbyt m這de)");
		//Test 2
		wykonajTest("01-02-2009", true, true, "Mlodzik",
				"Sprawdzenie najm這dszej osoby w kategorii m這dzik");
		//Test 3
		wykonajTest("03-04-2006", true, true, "Mlodzik",
				"Sprawdzenie najstarszej osoby w kategorii m這dzik");
		//Test 4
		wykonajTest("04-02-2005", true, true, "Junior",
				"Sprawdzenie najm這dszej osoby w kategorii junior");
		//Test 5
		wykonajTest("01-01-2002", true, true, "Junior",
				"Sprawdzenie najstarszej osoby w kategorii junior");
		//Test 6
		wykonajTest("01-02-2002", false, true, "Blad danych",
				"Sprawdzenie wymagania zgody rodzic闚 osoby niepe軟oletniej");
		//Test 7
		wykonajTest("03-04-2002", true, false, "Blad danych",
					"Sprawdzenie wymagania za�wiadczenie od lekarza dla osoby niepe軟oletniej");
		//Test 8
		wykonajTest("04-02-2001", false, false, "Dorosly",
					"Kwalifikacja najm這dszej osoby do kategorii doros造. Sprawdzenie braku zale積o�ci kwalifikacji do kategorii doros造 od za�wiadczenia lekarskiego dla osoby w wieku 18-64.");
		//Test 9
		wykonajTest("01-01-1955", false, false, "Dorosly",
					"Kwalifikacja najstarszej osoby do kategorii doros造, kt鏎a nie potrzebuje za�wiadczenia lekarskiego");
		//Test 10
		wykonajTest("01-02-1954", false, false, "Blad danych",
					"Kwalifikacja osoby powy瞠j 65 roku 篡cia do kategorii doros造 bez zgody lekarza");
		//Test 11
		wykonajTest("01-01-1954", false, true, "Dorosly",
					"Kwalifikacja osoby powy瞠j 65 roku 篡cia do kategorii doros造 ");		
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
