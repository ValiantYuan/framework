package valiant.aop.staticproxy;

import valiant.aop.Hello;

public class HelloImpl implements Hello {

	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("Hello! " + name);
	}
	
	public void testyuan() {
		System.out.println("test empty");
	}

}
