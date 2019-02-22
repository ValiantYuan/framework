package valiant.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * 将POJO转为JSON
	 */
	
	public static <T> String toJson(T obj) {
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("conver POJO to JSON failure", e);
			throw new RuntimeException(e);
		}
		return json;
	}
	/**
	 * 将POJO转为JSON
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("conver JSON to POJO failure", e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
