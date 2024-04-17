package scrapper;

import org.openqa.selenium.By;

public class VendorScrapper {
	protected String url;
	protected String outOfStockText;
	protected By priceSelector;
	protected By outOfStockSelector;
	
	public VendorScrapper(String vendor, String resellerSku) {
		switch(vendor.toUpperCase()) {
			case "STAPLES":
				this.url = "https://www.staples.com/" + resellerSku + "/directory_" + resellerSku;
				this.outOfStockText = "This item is out of stock";
				this.priceSelector = new By.ByCssSelector(".price-info__final_price_sku");
				this.outOfStockSelector = new By.ByXPath("//*[@id='ONE_TIME_PURCHASE']/div/div/div/div/div/div/div[2]/div");
				break;
			case "TARGET PLUS":
				this.url = "https://www.target.com/s?searchTerm=" + resellerSku;
				this.outOfStockText = "SOLD OUT";
				this.priceSelector = new By.ByCssSelector(".h-padding-r-tiny");
				this.outOfStockSelector = new By.ByCssSelector("#addToCartButtonOrTextIdFor" + resellerSku);
				break;
		}
	}
}
