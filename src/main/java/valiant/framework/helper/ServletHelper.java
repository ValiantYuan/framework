package valiant.framework.helper;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestAttribute;

/**
 * Servlet助手类，为Action方法，提供线程安全的HttpRequestServlet和HttpResponseServlet操作
 * @author yuanq5
 *
 */
public final class ServletHelper {
	private HttpServletRequest request;
	private HttpServletResponse response;

	private static final  Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

	public static final ThreadLocal<ServletHelper> SERVLET_HOLDER = new ThreadLocal<>();

	private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 */
	public static void init(HttpServletRequest request, HttpServletResponse response) {
		SERVLET_HOLDER.set(new ServletHelper(request, response));
	}
	
	public static void destory() {
		SERVLET_HOLDER.remove();
	}
	/**
	 * 返回请求
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return SERVLET_HOLDER.get().request;
	}
	/**
	 * 返回响应
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return SERVLET_HOLDER.get().response;
	}
	/**
	 * 获取Session对象
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 获取ServletContext对象
	 */
	public static ServletContext getServletContext() {
		return getRequest().getServletContext();
	}
	
	/**
	 * 设置Request属性
	 */
	public static void setRequestAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
	
	/**
	 * 获取Request属性
	 * @param key
	 * @return
	 */
	public static Object getRequsetAttribute(String key) {
		return getRequest().getAttribute(key);
	}
	
	/**
	 * 移除Request属性
	 * @param key
	 */
	public static void removeRequestAttribute(String key) {
		getRequest().removeAttribute(key);
	}
	
	/**
	 * 重新定向，定向的是相对地址
	 * 重定向室友response发起的
	 * @param location
	 */
	public static void sendRedirect(String location) {
		try {
			getResponse().sendRedirect(getRequest().getContextPath() + location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("redirect failure", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置Session属性
	 */
	public static void setSessionAttribute(String key, Object value) {
		getRequest().getSession().setAttribute(key, value);
	}
	
	/**
	 * 获取Session属性
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(String key) {
		return getRequest().getSession().getAttribute(key);
	}
	
	/**
	 * 移除Session属性
	 * @param key
	 */
	public static void removeSessionAttribute(String key) {
		getRequest().getSession().removeAttribute(key);
	}	
	
	/**
	 * 使Session失效
	 */
	public static void invalidateSession() {
		getRequest().getSession().invalidate();
	}
	
	
	
}
