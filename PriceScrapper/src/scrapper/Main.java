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
			add(new WebDriverTask("STAPLES", staplesSKU));
			add(new WebDriverTask("TARGETPLUS", targetPlusSKU));
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