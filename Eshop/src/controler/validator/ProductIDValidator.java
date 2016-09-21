package controler.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductIDValidator {
	private static final String PRODUCT_ID_PATTERN = "[1-9][0-9]{6,}";
	private static final String QUANTITY_PATTERN = "[0-9]+";
	private static final String PRICE_PATTERN = "[0-9]+[.]?[0-9]+";

	public static boolean validateProductID(String productID) {
		if (productID != null && !productID.isEmpty()) {
			productID = productID.trim();
			Pattern namePattern = Pattern.compile(PRODUCT_ID_PATTERN);
			Matcher matcher = namePattern.matcher(productID);
			return matcher.matches();
		}
		return false;
	}
	
	public static boolean validateQuantity(String quantity) {
		if (quantity != null && !quantity.isEmpty()) {
			quantity = quantity.trim();
			Pattern quantityPattern = Pattern.compile(QUANTITY_PATTERN);
			Matcher matcher = quantityPattern.matcher(quantity);
			return matcher.matches();
		}
		return false;
	}
	
	public static boolean validatePrice(String price) {
		if (price != null && !price.isEmpty()) {
			price = price.trim();
			Pattern pricePattern = Pattern.compile(PRICE_PATTERN);
			Matcher matcher = pricePattern.matcher(price);
			return matcher.matches();
		}
		return false;
	}
	
}
