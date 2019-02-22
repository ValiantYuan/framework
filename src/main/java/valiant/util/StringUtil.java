package valiant.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	/**
	 * 字符串分割符
	 */
	public static final String SEPARATOR = String.valueOf((char) 29);
	
	public static boolean isEmpty(String string) {
		if (string != null) {
			string = string.trim();
		}
		return StringUtils.isEmpty(string);
	}
	
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}
	
	public static String[] splitString (String string, String separatorChar) {
		return StringUtils.split(string, separatorChar);
	}
}
