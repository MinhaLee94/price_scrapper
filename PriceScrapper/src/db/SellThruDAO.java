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
		String qry = "SELECT * FROM sellThruProduct WHERE vendorIdx = " + vendorIdx + " AND status = 'ON SITE'";
		
		try {
			Statement states = 	db.conn.createStatement();
			ResultSet rs = states.executeQuery(qry);
			
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
		try {
			Statement states = 	db.conn.createStatement();
			ResultSet rs = states.executeQuery(qry);
			vendorIdx = rs.getInt("idx");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendorIdx;
	}
}
