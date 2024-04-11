package scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverTask implements Runnable {
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = "./chromedriver.exe";
	
	private String vendor;
	private String url;
	private By priceSelector;
	private By outOfStockSelector;
	private String outOfStockText;
	
	public WebDriverTask(String vendor, String url, By priceSelector, By outOfStockSelector, String outOfStockText) {
		this.vendor = vendor;
		this.url = url;
		this.priceSelector = priceSelector;
		this.outOfStockSelector = outOfStockSelector;
		this.outOfStockText = outOfStockText;
	}
	
	private ChromeOptions configChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		
		return options;
	}

	@Override
	public void run() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = configChromeOptions();
		ChromeDriver driver = new ChromeDriver(options);
		
		try {
			driver.get(url);
			WebElement curPrice = driver.findElement(priceSelector);
			WebElement outOfStockSign = driver.findElement(outOfStockSelector);
			
			if(curPrice == null) {
				if(outOfStockSign != null && outOfStockSign.getText().equalsIgnoreCase(outOfStockText)) {
					// curPrice
					// status OUT OF STOCK
				} else {
					// curPrice
					// status ON SITE
				}
			} else {
				// keep RP
				// status OFF SITE
			}
		} finally {
			if(driver != null) {
				driver.quit();
			}
		}
	}
}
