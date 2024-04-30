package utility;

public class Utils {
	public static double formatPrice(String price) {
		double result = 0;
		
		try {
			String priceWithoutComma = price.replace(",", "");
			result = Double.parseDouble(priceWithoutComma);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
