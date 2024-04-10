package scrapper;

import db.DatabaseConnection;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;

import scrapper.WebDriverTask;

public class Main {
	public static void main(String[] args) {
		DatabaseConnection db = new DatabaseConnection();
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		// string, by , by, string object
		ArrayList<WebDriverTask> tasks = new ArrayList<WebDriverTask>() {{
			add(new WebDriverTask(
					"STAPLES", 
					"https://www.staples.com/24516278/directory_24516278", 
					new By.ByClassName(".price-info__final_price_sku"), 
					new By.ByXPath("//*[@id='ONE_TIME_PURCHASE']/div/div/div/div/div/div/div[2]/div"), 
					"This item is out of stock"));
		}};
		
		for(WebDriverTask task : tasks) {
			executor.submit(task);
		}
		
		executor.shutdown();
		
		//String result = wd.scrapPrice("https://www.staples.com/24516278/directory_24516278");
		return;
	}
}