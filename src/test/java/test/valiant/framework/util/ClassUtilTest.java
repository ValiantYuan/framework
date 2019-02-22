package test.valiant.framework.util;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import valiant.util.ClassUtil;

public class ClassUtilTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtilTest.class);
	
	
	public ClassUtilTest() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Test
	public void getClassSetTest() throws Exception{
		String packageName = "valiant.framework";
//		String packageName = "jstl-1.2.jar";
		Set<Class<?>> classSet = ClassUtil.getClassSet(packageName);
		int i = 1;
		for (Class<?> class1 : classSet) {
//			System.out.println(class1.getName());
			LOGGER.info("class numer : " + i + " " + class1.getName());
			i++;
		}
		assertEquals(29, classSet.size());
	}
}
