package valiant.aop.cglib;

import valiant.aop.Hello;
import valiant.aop.staticproxy.HelloImpl;

public class ProxyTest {
	public static void main(String[] args) {
		Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
		helloProxy.sayHello("Jack");
	}
}
