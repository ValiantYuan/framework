package valiant.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import valiant.framework.annotation.Controller;
import valiant.framework.annotation.Service;
import valiant.util.ClassUtil;

/**
 * 类操作助手类
 * @author yuanq5
 */
public class ClassHelper {
	/**
	 * 定义类集合（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	/**
	 * 静态初始化    获取  并  加载   配置应用包名下的所有类
	 */
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	/**
	 * @return 返回包下的所有类
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}
	/**
	 * @return 返回所有的Service类
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> class1 : CLASS_SET) {
			if(class1.isAnnotationPresent(Service.class)) {
				classSet.add(class1);
			}
		}
		return classSet;
	}
	/**
	 * @return 返回所有的Controller类
	 */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> class1 : CLASS_SET) {
			if(class1.isAnnotationPresent(Controller.class)) {
				classSet.add(class1);
			}
		}
		return classSet;
	}
	/**
	 * @return 获取包下所有的bean(包括Controller和Service)
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> beanClassSet = new HashSet<>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
	
	/**
	 * 获取当前CLASS_SET中某父类或者接口的所有子类或实现类
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> cls : CLASS_SET) {
			/**
			 * isAssignableFrom 当调用类是参数类的父类或父接口或者类本身时，返回true
			 * 
			 */
			if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	/**
	 * 获取应用包名下带有某注解的所有类
	 * @param annotationClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
		Set<Class<?>> classSet = new HashSet<>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(annotationClass)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	
}
