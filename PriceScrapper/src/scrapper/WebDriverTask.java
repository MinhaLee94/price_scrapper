package scrapper;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import db.SellThruDAO;

public class WebDriverTask implements Runnable {
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = "./chromedriver.exe";
	
	private String vendor;
	private ArrayList<String> skuList;
	
	public WebDriverTask(String vendor, ArrayList<String> skuList) {
		this.vendor = vendor;
		this.skuList = skuList;
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
		SellThruDAO dao = new SellThruDAO();
		
		try {
			int vendorIdx = dao.getVendorIdx(vendor);
			
			for(String resellerSku : skuList) {
				VendorScrapper vendorScrapper = new VendorScrapper(vendor, resellerSku);
				driver.get(vendorScrapper.url);
				WebElement curPrice = driver.findElement(vendorScrapper.priceSelector);
				WebElement outOfStockSign = driver.findElement(vendorScrapper.outOfStockSelector);
				
				// pull product info
				
				if(curPrice != null) {
					if(outOfStockSign != null && outOfStockSign.getText().equalsIgnoreCase(vendorScrapper.outOfStockText)) {
						// update curPrice
						// status OUT OF STOCK
					} else {
						// curPrice
						// status ON SITE
					}
				} else {
					// keep RP
					// status OFF SITE
				}
			}
		} finally {
			if(driver != null) {
				driver.quit();
			}
		}
	}
}
