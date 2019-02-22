package valiant.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import valiant.framework.annotation.Action;
import valiant.framework.bean.Handler;
import valiant.framework.bean.Request;
import valiant.util.ArrayUtil;
import valiant.util.CollectionUtil;
/**
 * 控制器助手类
 * @author yuanq5
 *
 */
public class ControllerHelper {
	/**
	 * 用于存放请求与处理器的映射关系
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
	/**
	 * 静态初始化时就产生所有的控制器的ACTION_MAP
	 */
	static {
		//获取所有的Controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			//遍历Controller类集合
			for (Class<?> controllerClass : controllerClassSet) {
				//获取Controller类中定义的方法
				Method[] methods = controllerClass.getDeclaredMethods();
				if (ArrayUtil.isNotEmpty(methods)) {
					//遍历方法
					for (Method method : methods) {
						//判断当前方法是否有Action注解
						if (method.isAnnotationPresent(Action.class)) {
							//获取方法注解中定义的URL映射规则
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							/**
							 * \\w 匹配所有的单词字符，包括0-9所有数组，26个英文字母和下划线
							 * + 贪婪模式，表示无限的匹配下去
							 * ： 冒号
							 * / 斜杠，路径中的斜杠
							 * \\w 匹配所有的单词字符，包括0-9所有数组，26个英文字母和下划线
							 * 
							 * 本工程中设计的一个典型的Action注解
							 * @Action("get:/customer")
							 */
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (ArrayUtil.isNotEmpty(array) 
										&& array.length == 2) {
									//获取请求方法与请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									//初始化Action Map
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param requestMethod 请求方法
	 * @param requestPath 请求路径
	 * @return 返回请求的Handler,request中的requestMethod和requestPath值相同时，返回同一个Handler
	 */
	public static Handler getHandler(String requestMethod, String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
