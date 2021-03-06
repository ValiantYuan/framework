package valiant.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import valiant.framework.bean.Data;
import valiant.framework.bean.Handler;
import valiant.framework.bean.Param;
import valiant.framework.bean.View;
import valiant.framework.helper.BeanHelper;
import valiant.framework.helper.ConfigHelper;
import valiant.framework.helper.ControllerHelper;
import valiant.util.ArrayUtil;
import valiant.util.CodecUtil;
import valiant.util.JsonUtil;
import valiant.util.ReflectionUtil;
import valiant.util.StreamUtil;
import valiant.util.StringUtil;

/**
 * 请求转发器
 * @author yuanq5
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatherServlet extends HttpServlet{

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// TODO Auto-generated method stub
		//初始化相关的Helper类
		HelperLoader.init();
		//获取ServletContext对象，用于注册Servlet
		ServletContext servletContext = servletConfig.getServletContext();
		//注册处理JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		//注册处理静态资源的默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取请求方法与请求路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		//获取Action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if (handler != null) {
			//获取Controller类及其Bean实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			//创建请求参数对象
			Map<String, Object>	paramMap = new HashMap<String, Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			//获取request请求的全部内容？
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if (StringUtil.isNotEmpty(body)) {
				String[] params = StringUtil.splitString(body, "&");
				if (ArrayUtil.isNotEmpty(params)) {
					for (String param : params) {
						String[] array = StringUtil.splitString(param, "=");
						if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			//调用Action方法
			Method actionMethod = handler.getActionMethod();
			Object result;
			if (param.isEmpty()) {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
			} else {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			}
			//处理Action方法返回值
			if (result instanceof View) {
				//返回JSP页面
				View view = (View) result;
				String path = view.getPath();
				if (StringUtil.isNotEmpty(path)) {
					response.sendRedirect(request.getContextPath() + path);	
				} else {
					Map<String, Object> model = view.getModel();
					for (Map.Entry<String, Object> entry : model.entrySet()) {
						request.setAttribute(entry.getKey(), entry.getValue());
					}
					request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
					.forward(request, response);
				}
			} else if (result instanceof Data) {
				//返回JSON数据
				Data data = (Data) result;
				Object model = data.getModel();
				if (model != null) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
	}

	
	
}
