package valiant.framework.bean;

import java.lang.reflect.Method;
/**
 * 封装Action信息，一个Action有一个Controller和一个actionMethod组成
 * 即指定一个请求的响应类和方法
 * @author yuanq5
 *
 */
public class Handler {
	/**
	 * Controller类
	 */
	private Class<?> controllerClass;
	/**
	 * Action方法
	 */
	private Method actionMethod;
	
	public Handler(Class<?> controllerClass, Method actionMetod) {
		// TODO Auto-generated constructor stub
		this.controllerClass = controllerClass;
		this.actionMethod = actionMetod;
	}
	
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	
	public Method getActionMethod() {
		return actionMethod;
	}
}
