package valiant.aop.cglib;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.management.InstanceAlreadyExistsException;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor{
	
	/**
	 * 简陋单例模式
	 */
	private static CGLibProxy instance = new CGLibProxy();
	
	public static CGLibProxy getInstance() {
		return instance;
	}
	
	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		before();
		Object result = proxy.invokeSuper(object, args);
		after();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls) {
		return (T) Enhancer.create(cls, this);
	}
	
	private void before() {
		System.out.println("before");
	}
	
	private void after() {
		System.out.println("after");
	}
	

}
