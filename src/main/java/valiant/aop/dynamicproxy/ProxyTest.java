package valiant.aop.dynamicproxy;

import valiant.aop.Hello;
import valiant.aop.staticproxy.HelloImpl;

public class ProxyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Hello hello = new HelloImpl();
//		InvocationHandler dynamicProxy = new DynamicProxy(hello);
//		/**
//		 * 这一步操作是生成了一个Proxy对象，
//		 * 该对象使用指定的类加载器加载
//		 * 实现指定的接口数组，这些接口的实现可以理解为由指定的InvocationHandler中的invoke方法代理
//		 * 该invoke方法指定了动态代理对象实例，调用方法名称和执行方法的参数
//		 * 调用该代理对象的所有方法是都会被替换成调用Invocation handler的invoke方法
//		 * 但该代理类向上转型时只能转型为它实现过的接口，所以向上转型后无法调用接口中没有的方法
//		 */
//		Hello helloProxy = (Hello) Proxy.
//				newProxyInstance(hello.getClass().getClassLoader(),
//								hello.getClass().getInterfaces(), dynamicProxy);
		
		Hello helloProxy = new DynamicProxy(new HelloImpl()).getProxy();
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		helloProxy.sayHello("Jack");
		
	}

}
