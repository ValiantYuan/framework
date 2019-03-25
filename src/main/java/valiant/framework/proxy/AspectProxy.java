/**
 * 
 */
package valiant.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuanq5
 *	切面代理
 */
public abstract class AspectProxy implements Proxy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

	/* (non-Javadoc)
	 * @see valiant.framework.proxy.Proxy#doProxy(valiant.framework.proxy.ProxyChain)
	 */
	
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		Class<?> cls = proxyChain.getTargetClass();
		Method method = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();
		begin();
		try {
			if (intercept(cls, method, params)) {
				before(cls, method, params);
				result = proxyChain.doProxyChain();
				after(cls, method, params);
			} else {
				result = proxyChain.doProxyChain();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Proxy failure", e);
			error(cls, method, params, e);
			throw e;
		} finally {
			end();
		}
		
		return result;
	}

	
	public void begin() {
		
	}
	
	public void end() {
		
	}
	
	public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
		return true;
	}
	
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable{
	
	}
	
	public void after(Class<?> cls, Method method, Object[] params) throws Throwable{
	
	}
	
	public void error(Class<?> cls, Method method, Object[] params, Throwable e) throws Throwable{
		
	}
}
