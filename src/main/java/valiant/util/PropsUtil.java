package valiant.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	/**
	 * 
	 * 加载属性文件
	 */
	public static Properties loadProps(String fileName) {

		Properties properties = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + " file is not found");
			}
			properties = new Properties();
			properties.load(is);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("load properties file failure", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e2) {
					// TODO: handle exception
					LOGGER.error("close input stream failure", e2);
				}
			}
		}
		return properties;
		
	}

	/**
	 * 获取字符型属性（默认值为空字符串）
	 */
	public static String  getString(Properties props, String key) {
		return getString(props, key, "");
	}
	/**
	 * 获取字符型属性（可指定默认值）
	 */
	public static String  getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}
	
	public static int getInt(Properties properties, String key) {
		return getInt(properties, key, 0);
	}
	
	public static int getInt(Properties properties, String key, int defaultValue) {
		int value = defaultValue;
		if (properties.containsKey(key)) {
			value = CastUtil.castInt(properties.getProperty(key));
		}
		return value;
	}
	
	
	public static boolean getBoolean(Properties properties, String key) {
		return getBoolean(properties, key, false);
	}
	public static boolean getBoolean(Properties properties, String key, Boolean defaultValue) {
		boolean value = defaultValue;
		if (properties.containsKey(key)) {
			value = CastUtil.castBoolean(properties.getProperty(key));
		}
		return value;
	}
}
