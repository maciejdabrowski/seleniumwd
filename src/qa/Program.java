package qa;

public class Program {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");
		SeleniumTestZawody sTest = new SeleniumTestZawody("https://lamp.ii.us.edu.pl/~mtdyd/zawody/");
		sTest.run();

	}

}
