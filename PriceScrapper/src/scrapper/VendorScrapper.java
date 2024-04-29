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
			case "NEWEGG":
				this.url = "https://www.newegg.com/p/" + resellerSku + "?item=" + resellerSku;
				this.outOfStockText = "OUT OF STOCK";
				this.priceSelector = new By.ByCssSelector(".product-buy-box .price-current");
				this.outOfStockSelector = new By.ByCssSelector(".product-buy-box .btn-message");
				break;
			case "OFFICE DEPOT":
				this.url = "https://www.officedepot.com/catalog/catalogSku.do?id=" + resellerSku;
				this.outOfStockText = "OUT OF STOCK";
				this.priceSelector = new By.ByCssSelector(".od-graphql-price-big-price");
				this.outOfStockSelector = new By.ByCssSelector(".od-fulfillment-option-in-stock-message-text-oos");
				break;
			case "BESTBUY":
				this.url = "https://www.bestbuy.com/site/searchpage.jsp?st=" + resellerSku;
				this.outOfStockText = "SOLD OUT";
				this.priceSelector = new By.ByCssSelector(".pricing-price .priceView-customer-price > span:first-child");
				this.outOfStockSelector = new By.ByCssSelector(".fulfillment-fulfillment-summary>div>div>div:nth-child(2)>div");
				break;
		}
	}
}