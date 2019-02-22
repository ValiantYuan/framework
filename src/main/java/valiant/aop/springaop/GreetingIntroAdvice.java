package valiant.aop.springaop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

import valiant.aop.Apology;
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		// TODO Auto-generated method stub
		return super.invoke(mi);
	}

	@Override
	public void saySorry(String name) {
		// TODO Auto-generated method stub
		System.out.println("Sorry " + name);
		
	}

}
