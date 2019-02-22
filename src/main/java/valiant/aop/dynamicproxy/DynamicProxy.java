package valiant.aop.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 动态代理提供了在运行时产生类方法的能力，但是对于没有实现任何接口的类，就无法使用
 * @author yuanq5
 *
 */
public class DynamicProxy implements InvocationHandler {
	private Object target;
	
	public DynamicProxy(Object target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	/**
	 * proxy对象的作用：
	 * 1.可以使用反射获取代理对象的信息 proxy.getClass().getName()
	 * 2.可以将代理对象返回以进行连续调用，在invoke方法中返回this是返回的InvocationHandler类而不是代理类
	 * helloProxy.sayHello("Jack").sayHello("Tony")
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
		
	}
	private void before() {
		System.out.println("before");
	}
	
	private void after() {
		System.out.println("after");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}
}
