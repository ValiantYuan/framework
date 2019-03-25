package valiant.aop.cglib;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyIntercepter implements MethodInterceptor {

	@Override
	/**
	 *  sub cglib生成的代理对象
	 *  method 被代理对象方法
	 *  args 方法入参
	 *  methodProxy 代理方法
	 */
	public Object intercept(Object sub, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("执行前...");
		Object result = methodProxy.invokeSuper(sub, args);
//		Object result = methodProxy.invoke(sub, args);
		System.out.println("执行后...");
		return result;
	}



}
