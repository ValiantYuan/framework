package valiant.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import valiant.util.ReflectionUtil;
/**
 * Bean助手类
 * @author yuanq5
 *
 */
public final class BeanHelper {
	/**
	 * 定义Bean映射，用于存放Bean类与实例的映射关系
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
	static {
		Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
		for (Class<?> class1 : classSet) {
			BEAN_MAP.put(class1, ReflectionUtil.newInstance(class1));
		}
	}
	/**
	 * 获取Bean映射
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}
	/**
	 * @param cls
	 * @return 根据Bean Class对象获取Bean实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class: " + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
	/**
	 * 设置bean实例
	 * @param cls
	 * @param object
	 */
	public static void setBean(Class<?> cls, Object object) {
		BEAN_MAP.put(cls, object);
	}
}
