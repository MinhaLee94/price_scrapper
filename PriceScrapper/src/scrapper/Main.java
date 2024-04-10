package scrapper;

import db.DatabaseConnection;
import scrapper.WebDriver;

public class Main {
	public static void main(String[] args) {
		DatabaseConnection db = new DatabaseConnection();
		WebDriver wd = new WebDriver();
		String result = wd.scrapPrice("https://www.staples.com/24516278/directory_24516278");
		return;
	}
}