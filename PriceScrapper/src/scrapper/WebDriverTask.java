package scrapper;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import db.ProductInfo;
import db.SellThruDAO;
import utility.Utils;

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
				
				int curProductIdx = dao.getProductIdx(vendorIdx, resellerSku);
				ProductInfo curProduct = dao.getProductInfo(curProductIdx);
				double realPrice = Utils.formatPrice(curPrice.getText());
				
				if(curPrice != null) {
					if(outOfStockSign != null && outOfStockSign.getText().equalsIgnoreCase(vendorScrapper.outOfStockText)) {
						// update curPrice -
						// status OUT OF STOCK
						dao.updateProductStatus(curProductIdx, "OUT OF STOCK");
					} else {
						// curPrice
						// status ON SITE
						dao.updateProductStatus(curProductIdx, "ON SITE");
					}
					dao.updateProductPrice(curProductIdx, realPrice);
				} else {
					// keep RP
					// status OFF SITE
					dao.updateProductStatus(curProductIdx, "OFF SITE");
				}
			}
		} finally {
			if(driver != null) {
				driver.quit();
			}
		}
	}
}
