package valiant.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 反射工具类，用来获取所加载类的实例
 * @author yuanq5
 *
 */
public final class ReflectionUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * @param cls Class对象
	 * @return 返回创建的类实例
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance;
		try {
			instance = cls.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("new instance failure", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	/**
	 * 调用方法
	 * @param object 调用方法的对象
	 * @param method 需要执行的方法
	 * @param args 方法执行的参数
	 * @return 返回在object中按指定参数分发的method的执行结果
	 */
	public static Object invokeMethod(Object object, Method method, Object... args) {
		Object result;
		try {
			method.setAccessible(true);
			result = method.invoke(object, args);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("invoke method failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 设置成员变量的值
	 * @param object 成员变量所属的对象
	 * @param field	   成员变量
	 * @param value  设置的值
	 */
	public static void setField(Object object, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("set field failure", e);
			throw new RuntimeException(e);
		}
	}
}
