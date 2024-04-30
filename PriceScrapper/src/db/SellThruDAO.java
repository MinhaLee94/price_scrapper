package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SellThruDAO {
	private DatabaseConnection db; 
	
	public SellThruDAO() {
		this.db = new DatabaseConnection();
	}
	
	public ArrayList<String> getSKUList(String vendor) {
		ArrayList<String> skuList = new ArrayList<>();
		int vendorIdx = getVendorIdx(vendor);
		String qry = "SELECT resellerSku FROM sellThruProduct WHERE vendorIdx = " + vendorIdx + " AND status = 'ON SITE'";
		
		try(Statement states = 	db.conn.createStatement();
			ResultSet rs = states.executeQuery(qry)) {
			
			while(rs.next()) {
				skuList.add(rs.getString("resellerSku"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skuList;
	}
	
	public int getVendorIdx(String vendor) {
		int vendorIdx = -1;
		String qry = "SELECT idx FROM sellThruVendor WHERE vendor = '" + vendor + "'";
		
		try(Statement states = 	db.conn.createStatement();
			ResultSet rs = states.executeQuery(qry);){
			vendorIdx = rs.getInt("idx");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendorIdx;
	}
	
	public int getProductIdx(int vendorIdx, String resellerSku) {
		int productIdx = -1;
		String qry = "SELECT idx FROM sellThruProduct WHERE vendorIdx = " + vendorIdx + " AND resellerSku = '" + resellerSku + "'";
		
		try(Statement states = db.conn.createStatement(); 
			ResultSet rs = states.executeQuery(qry)){
			productIdx = rs.getInt("idx");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productIdx;
	}
	
	public ProductInfo getProductInfo(int productIdx) {
		ProductInfo productInfo = new ProductInfo();
		String qry = "SELECT * FROM sellThruProduct P " +
				     "JOIN (SELECT TOP 1 * FROM sellThruPrice WHERE sellThruProductIdx = '" + productIdx + "' " +
				     "ORDER BY idx DESC) SP ON P.idx = SP.sellThruProductIdx";
		try(Statement states = db.conn.createStatement(); 
		    ResultSet rs = states.executeQuery(qry)){
				productInfo.joySku = rs.getString("joySku");
				productInfo.resellerSku = rs.getString("resellerSku");
				productInfo.upc = rs.getString("upc");
				productInfo.status = rs.getString("status");
				productInfo.cost = rs.getDouble("cost");
				productInfo.profit = rs.getDouble("profit");
				productInfo.joyPrice = rs.getDouble("joyPrice");
				productInfo.joyPrice4Percent = rs.getDouble("joyPrice4Percent");
				productInfo.realPrice = rs.getDouble("realPrice");
				productInfo.margin = rs.getDouble("margin");
				productInfo.onOffDate = rs.getString("resellerSku");
				productInfo.comment = rs.getString("resellerSku");
				productInfo.pendingJp = rs.getDouble("pendingJp");
				productInfo.lastUpdated = rs.getString("resellerSku");
				productInfo.costChangeDate = rs.getString("resellerSku");
				productInfo.costChange = rs.getString("resellerSku");
				productInfo.jpChangeDate = rs.getString("resellerSku");
				productInfo.jpChange = rs.getString("resellerSku");
				productInfo.rpDropDate = rs.getString("resellerSku");
				productInfo.rpDrop = rs.getString("resellerSku");
				productInfo.uid = rs.getString("resellerSku");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productInfo;
	}
	
	public void updateProductStatus(int productIdx, String status) {
		String qry = "UPDATE sellThruProduct SET status = '" + status + "' WHERE idx = " + productIdx;

		try(Statement states = db.conn.createStatement()){
			states.executeUpdate(qry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProductPrice(int productIdx, double realPrice) {
		String qry = "UPDATE sellThruPrice SET realPrice =" + realPrice +" , margin = (realPrice-joyPrice4Percent)/realPrice "
				   + "WHERE sellThruProductIdx = " + productIdx;
		try(Statement states = db.conn.createStatement()){
			states.executeUpdate(qry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
