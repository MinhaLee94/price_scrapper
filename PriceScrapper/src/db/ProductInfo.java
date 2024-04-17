package db;

public class ProductInfo {
	String joySku;
	String resellerSku;
	String upc;
    String status;
    double cost;
    double profit;
    double joyPrice;
    double joyPrice4Percent;
    double realPrice;
    double margin;
    String onOffDate;
    String comment;
    double pendingJp;
    String lastUpdated;
    String costChangeDate;
    String costChange;
    String jpChangeDate;
    String jpChange;
    String rpDropDate;
    String rpDrop;
    String uid;
    
    public ProductInfo(){
    	joySku = null;
    	resellerSku = null;
    	upc = null;
        status = null;
        cost = 0;
        profit = 0;
        joyPrice = 0;
        joyPrice4Percent = 0;
        realPrice = 0;
        margin = 0;
        onOffDate = null;
        comment = null;
        pendingJp = 0;
        lastUpdated = null;
        costChangeDate = null;
        costChange = null;
        jpChangeDate = null;
        jpChange = null;
        rpDropDate = null;
        rpDrop = null;
        uid = null;
	}
}
