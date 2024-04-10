package scrapper;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	// price by, outofStock by, url, out of stock message
	public String scrapPrice(String url) {
		String result = "";
		
		try {
			driver.get(url);
			WebElement curPrice = driver.findElement(By.cssSelector(".price-info__final_price_sku"));
			WebElement outOfStockSign = driver.findElement(By.xpath("//*[@id='ONE_TIME_PURCHASE']/div/div/div/div/div/div/div[2]/div"));
			
			if(curPrice == null) {
				if(outOfStockSign != null && outOfStockSign.getText().equalsIgnoreCase("This item is out of stock")) {
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
			
			result = curPrice.getText();
		} finally {
			
		}
		
		return result;
	}
}
