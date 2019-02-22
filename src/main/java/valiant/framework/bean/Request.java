package valiant.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * 封装请求信息
 * @author yuanq5
 *
 */
public class Request {
	/**
	 * 请求方法是指put,get,post,delete等方法
	 */
	private String requestMethod;
	/**
	 * 请求路径
	 */
	private String requestPath;
	
	public Request(String requestMethod, String requestPath) {
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public String getRequestPath() {
		return requestPath;
	}
	@Override
	/**
	 * 覆写hashCode方法，成员变量值相同的情况下，hashCode值相同
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	/**
	 * 覆写equals方法，成员变量值相同的情况下，hashCode值相同
	 */
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}
	
}
