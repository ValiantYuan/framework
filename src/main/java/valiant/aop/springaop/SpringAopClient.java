package valiant.aop.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import valiant.aop.Apology;
import valiant.aop.Greeting;

public class SpringAopClient {
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("valiant/aop/applicationcontext.xml");
		Greeting greeting = (Greeting) context.getBean("greetingProxy");
		try {
			greeting.sayHello("valiant");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		Apology apology = (Apology) context.getBean("greetingApologyProxy");
		apology.saySorry("jack");
		GreetingImp greeting2 = (GreetingImp) context.getBean("greetingProxyWithAdvisor");
		try {
			greeting2.sayHello("Betty");
		} catch (Exception e) {
			// TODO: handle exception
		}
		greeting2.goodMorning("Betty");
		greeting2.goodNight("Betty");
		
		context = new ClassPathXmlApplicationContext("valiant/aop/springaop/aopcontext.xml");
		
	}
}
