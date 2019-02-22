package valiant.aop.springaspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import valiant.aop.Apology;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = 
							new ClassPathXmlApplicationContext("valiant/aop/springaspect"
							+ "/applicationcontext.xml");
		GreetingImp greeting = (GreetingImp)applicationContext.getBean(GreetingImp.class);
		try {
			greeting.sayHello("Valiant");
			greeting.goodMorning("Betty");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Apology apology = (Apology) greeting;
		apology.saySorry("Betty");
	}
}
