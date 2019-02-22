package valiant.aop.springaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import valiant.aop.Apology;


@Aspect
@Component
public class GreetingAspect {
	
	@DeclareParents(value = "valiant.aop.springaspect.GreetingImp",
					defaultImpl = ApologyImpl.class)
	private Apology apology1;
	/**
	 * execution()表示拦截方法，括号中定义的匹配规则
	 * 第一个* 表示方法返回值是任意的；第二个*表示匹配该类中的所有方法
	 * (..)表示方法的参数是任意的
	 * @param pjp 连接点，可以通过该对象获取方法的任何信息
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* valiant.aop.springaspect.GreetingImp.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		before();
		Object result = pjp.proceed();
		after();
		return result;
	}
	
	private void before() {
		System.out.println("Before");
		/**
		 * 调用发生nullPointerException，因此注释掉
		 */
//		apology1.saySorry("Betty");
	}
	
	private void after() {
		System.out.println("After");
	}
}
