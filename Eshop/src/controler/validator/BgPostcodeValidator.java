package controler.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BgPostcodeValidator {
	private static final String BULGARIAN_POSTCODE_PATTERN = "[1-9]{1}[0-9]{3}";

	public static boolean validatePostcode(String postcode) {
		if (postcode != null && !postcode.isEmpty()) {
			postcode = postcode.trim();
			Pattern namePattern = Pattern.compile(BULGARIAN_POSTCODE_PATTERN);
			Matcher matcher = namePattern.matcher(postcode);
			return matcher.matches();
		}
		return false;
	}

}
