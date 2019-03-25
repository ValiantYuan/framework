package valiant.aop.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "/Users/chenlianda/code/framework/src/main/java/valiant/aop/cglib";
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonService.class);
		enhancer.setCallback(new CglibProxyIntercepter());
		PersonService proxy = (PersonService)enhancer.create();
		proxy.setPerson();
		proxy.getPerson("1");
	}

}
