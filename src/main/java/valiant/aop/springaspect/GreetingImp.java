package valiant.aop.springaspect;

import org.springframework.stereotype.Component;

import valiant.aop.Greeting;
@Component
public class GreetingImp implements Greeting {

	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("Hello : " + name);
	}
	
	public void goodMorning(String name) {
		System.out.println("Good Morning! " + name);
	}
	
	public void goodNight(String name) {
		System.out.println("Good Night! " + name);
	}

}
