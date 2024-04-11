package scrapper;

import db.DatabaseConnection;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;

import scrapper.WebDriverTask;
import db.SellThruDAO;

public class Main {
	private final static String[] vendorList = {"Staples", "Target Plus"};
	private static ArrayList<String> staplesSKU = null;
	private static ArrayList<String> targetPlusSKU = null;
	
	private static DatabaseConnection db = new DatabaseConnection();
	private static SellThruDAO dao = new SellThruDAO();
	
	public static void initSKUListForVendors() {
		for (String vendor : vendorList) {
			switch(vendor.toUpperCase()) {
				case "STAPLES":
					staplesSKU = dao.getSKUList(vendor);
					break;
				case "TARGET PLUS":
					targetPlusSKU = dao.getSKUList(vendor);
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		initSKUListForVendors();
		
		ArrayList<WebDriverTask> tasks = new ArrayList<WebDriverTask>() {{
			add(new WebDriverTask(
					"STAPLES", 
					"https://www.staples.com/24516278/directory_24516278", 
					new By.ByCssSelector(".price-info__final_price_sku"), 
					new By.ByXPath("//*[@id='ONE_TIME_PURCHASE']/div/div/div/div/div/div/div[2]/div"), 
					"This item is out of stock"));
			add(new WebDriverTask(
					"TARGETPLUS",
					"https://www.target.com/s?searchTerm=85405483",
					new By.ByCssSelector(".h-padding-r-tiny"),
					new By.ByCssSelector("#addToCartButtonOrTextIdFor85405483"),
					"SOLD OUT"));
		}};
		
		try {
			
			
			for(WebDriverTask task : tasks) {
				executor.submit(task);
			}
		} finally {
			executor.shutdown();
		}
		
		//String result = wd.scrapPrice("https://www.staples.com/24516278/directory_24516278");
		return;
	}
}