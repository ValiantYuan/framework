package test.valiant.framework.bean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import valiant.framework.bean.Request;

public class RequestTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestTest.class);
	public RequestTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void hashCodeTest() {
		String requestMethod1 = new String("methond");
		String requestMethod2 = new String("methond");
		String requestPath1 = new String("path");
		String requestPath2= new String("path");
		Request request = new Request(requestMethod1, requestPath1);
		LOGGER.info(request.hashCode() + "");
		Request request2 = new Request(requestMethod2, requestPath2);
		LOGGER.info(request2.hashCode() + "");
		LOGGER.info(request.equals(request2) + "");
		if (request == request2) {
			LOGGER.info("true");
		} else {
			LOGGER.info("false");
		}
	}
}
