package scrapper;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriver {
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = "./chromedriver.exe";
	
	private ChromeDriver driver;
	private String url;
	
	public WebDriver (){
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
				
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
        
		driver = new ChromeDriver(options);
		
		url = "https://www.naver.com/";
		driver.get(url);
		System.out.println(driver.getPageSource());
	}
}
