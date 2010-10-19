package ai.liga.microurl.util;

public class BaseConverter {

	private static final String baseDigits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String toBase62(long decimalNumber) {
		return fromDecimalToOtherBase(62, decimalNumber);
	}

	public static String toBase36(long decimalNumber) {
		return fromDecimalToOtherBase(36, decimalNumber);
	}

	public static String toBase16(long decimalNumber) {
		return fromDecimalToOtherBase(16, decimalNumber);
	}

	public static String toBase8(int decimalNumber) {
		return fromDecimalToOtherBase(8, decimalNumber);
	}

	public static String toBase2(int decimalNumber) {
		return fromDecimalToOtherBase(2, decimalNumber);
	}

	public static long fromBase62(String base62Number) {
		return fromOtherBaseToDecimal(62, base62Number);
	}

	public static long fromBase36(String base36Number) {
		return fromOtherBaseToDecimal(36, base36Number);
	}

	public static long fromBase16(String base16Number) {
		return fromOtherBaseToDecimal(16, base16Number);
	}

	public static long fromBase8(String base8Number) {
		return fromOtherBaseToDecimal(8, base8Number);
	}

	public static long fromBase2(String base2Number) {
		return fromOtherBaseToDecimal(2, base2Number);
	}

	private static String fromDecimalToOtherBase(int base, long decimalNumber) {
		String tempVal = decimalNumber == 0 ? "0" : "";
		int mod = 0;

		while (decimalNumber != 0) {
			mod = (int) (decimalNumber % base);
			tempVal = baseDigits.substring(mod, mod + 1) + tempVal;
			decimalNumber =  decimalNumber / base;
		}

		return tempVal;
	}

	private static long fromOtherBaseToDecimal(int base, String number) {
		int iterator = number.length();
		int returnValue = 0;
		int multiplier = 1;

		while (iterator > 0) {
			returnValue = returnValue + (baseDigits.indexOf(number.substring(iterator - 1, iterator)) * multiplier);
			multiplier = multiplier * base;
			--iterator;
		}
		return returnValue;
	}

}
