package valiant.aop.springaop;

import org.springframework.aop.framework.ProxyFactory;

import valiant.aop.Greeting;

public class SpringAopTest {
	public static void main(String[] args) {
		//创建代理工厂
		ProxyFactory	proxyFactory = new ProxyFactory();
		//放入代理目标
		proxyFactory.setTarget(new GreetingImp());
		//添加增强
		proxyFactory.addAdvice(new GreetingBeforeAdvice());
		proxyFactory.addAdvice(new GreetingAfterAdvice());
		//从代理工厂中获取代理
		Greeting greeting = (Greeting)proxyFactory.getProxy();
		//调用代理的方法
		try {
			greeting.sayHello("Jack");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		ProxyFactory proxyFactory2 = new ProxyFactory();
		proxyFactory2.setTarget(new GreetingImp());
		proxyFactory2.addAdvice(new GreetingSurroundAdvice());
		Greeting greeting2 = (Greeting)proxyFactory2.getProxy();
//		GreetingImp greeting2 = (GreetingImp)proxyFactory2.getProxy();
		try {
			greeting2.sayHello("Pony");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

	}
}
