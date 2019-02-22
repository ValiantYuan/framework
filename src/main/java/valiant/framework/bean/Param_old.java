package valiant.framework.bean;

import java.util.Map;

import valiant.util.CastUtil;
import valiant.util.CollectionUtil;
/**
 * 请求url中的参数对象
 * @author yuanq5
 *
 */
public class Param_old {
	private Map<String, Object> paramMap;
	
	public Param_old(Map<String, Object> paramMap) {
		// TODO Auto-generated constructor stub
		this.paramMap = paramMap;
	}
	
	public boolean isEmpty() {
		return CollectionUtil.isEmpty(paramMap);
	}
	
	public String getString(String name) {
			return CastUtil.castString(paramMap.get(name));
	}
	
	public int getInt(String name) {
		return CastUtil.castInt(paramMap.get(name));
	}
	
	public double getDouble(String name) {
		return CastUtil.castDouble(paramMap.get(name));
	}
	
	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(paramMap.get(name));
	}
	
	public long getLong(String name) {
			return CastUtil.castLong(paramMap.get(name));
	}
	/**
	 * 获取所有字段信息
	 * @return
	 */
	public Map<String, Object> getMap() {
		return paramMap;
	}
}	
