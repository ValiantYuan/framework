package valiant.chapter4.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import valiant.framework.annotation.Aspect;
import valiant.framework.proxy.AspectProxy;

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	private long begin;
	@Override
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
		// TODO Auto-generated method stub
		super.before(cls, method, params);
		LOGGER.debug("--------begin--------");
		LOGGER.debug(String.format("class: %s", cls.getName()));
		LOGGER.debug(String.format("method: %s", method.getName()));
		begin = System.currentTimeMillis();
		
	}
	@Override
	public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
		// TODO Auto-generated method stub
		super.after(cls, method, params);
		LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
		LOGGER.debug("--------end--------");
	}
	
	
}
