package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controler.validator.BgPostcodeValidator;

public class JUnitPostcodeValidator {
	
	@Test
	public void testPostcodeValidator() {
		assertTrue("Valid postcode", BgPostcodeValidator.validatePostcode("1000"));
		assertTrue("Valid postcode", BgPostcodeValidator.validatePostcode("5987"));
		assertTrue("Valid postcode", BgPostcodeValidator.validatePostcode("8080"));
		assertTrue("Valid postcode", BgPostcodeValidator.validatePostcode("  1000"));

		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode("0158"));//start with 0
		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode("11581"));//invalid lenght
		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode("[1-9]{4}"));//with symbols
		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode("k158"));//content letter
		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode("558"));//invalid lenght
		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode(null));//null
		assertFalse("Invalid postcode", BgPostcodeValidator.validatePostcode(""));//empty String
	}

}
