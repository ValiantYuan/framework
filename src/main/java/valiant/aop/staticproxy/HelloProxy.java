package valiant.aop.staticproxy;

import valiant.aop.Hello;

public class HelloProxy implements Hello {

	Hello hello;
	public HelloProxy() {
		// TODO Auto-generated constructor stub
		hello = new HelloImpl();
	}
	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		before();
		hello.sayHello(name);
		after();
	}
	
	private void before() {
		System.out.println("before");
	}
	
	private void after() {
		System.out.println("after");
	}
	

}
