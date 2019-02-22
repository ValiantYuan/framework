package valiant.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;
/**
 * 代理链
 * @author yuanq5
 *
 */
public class ProxyChain {
	private final Class<?> targetClass;
	private final Object targetObject;
	private final Method targetMethod;
	private final MethodProxy methodProxy;
	private final Object[] methodParams;
	
	private List<Proxy> proxyList = new ArrayList<Proxy>();
	/**
	 * 代理索引
	 */
	private int proxyIndex = 0;
	/**
	 * 
	 * @param targetClass 		目标类
	 * @param targetObject 		目标对象
	 * @param targetMethod 		目标方法
	 * @param methodProxy		方法代理
	 * @param methodParams		方法参数
	 * @param proxyList			代理列表
	 */
	public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,
			Object[] methodParams, List<Proxy> proxyList) {
		super();
		this.targetClass = targetClass;
		this.targetObject = targetObject;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.proxyList = proxyList;
	}
	public Class<?> getTargetClass() {
		return targetClass;
	}
	public Method getTargetMethod() {
		return targetMethod;
	}
	public Object[] getMethodParams() {
		return methodParams;
	}

	public Object doProxyChain() throws Throwable{
		Object methodResult;
		if (proxyIndex < proxyList.size()) {
			methodResult = proxyList.get(proxyIndex++).doProxy(this);
		} else {
			methodResult = methodProxy.invokeSuper(targetObject, methodParams);
		}
		return methodResult;
	}
}
