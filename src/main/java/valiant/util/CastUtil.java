package valiant.util;

public class CastUtil {
	
	public static String castString(Object obj) {
		return castString(obj, "");
	}
	
	public static String  castString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}
	
	
	public static double castDouble(Object object) {
		return CastUtil.castDouble(object, 0);
	}
	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					doubleValue = defaultValue;
				}
			}
		}
		return defaultValue;
	}
	
	public static long castLong(Object object) {
		return CastUtil.castLong(object, 0);
	}
	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					longValue = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					longValue = defaultValue;
				}
			}
		}
		return defaultValue;
	}
	public static int castInt(Object object) {
		return CastUtil.castInt(object, 0);
	}
	public static int castInt(Object obj, int defaultValue) {
		int intValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					intValue = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					intValue = defaultValue;
				}
			}
		}
		return defaultValue;
	}
	
	public static boolean castBoolean(Object object) {
		return castBoolean(object, false);
	}
	
	public static boolean castBoolean(Object object, boolean defaultValue) {
		boolean booleanValue = defaultValue;
		if (object != null) {
			booleanValue = Boolean.parseBoolean(castString(object));
		}
		return booleanValue;
	}
}
