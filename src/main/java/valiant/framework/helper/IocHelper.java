package valiant.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import valiant.framework.annotation.Inject;
import valiant.util.ArrayUtil;
import valiant.util.CollectionUtil;
import valiant.util.ReflectionUtil;
/**
 * 依赖注入助手类
 * @author yuanq5
 *
 */
public final class IocHelper {
	static {
		//获取所有的Bean类和实例的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			//遍历Bean Map
			for (Map.Entry<Class<?>, Object>  beanEntry : beanMap.entrySet()) {
				//获取Bean的类和实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取Bean类定义的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtil.isNotEmpty(beanFields)) {
					//遍历Bean类的成员变量
					for (Field field : beanFields) {
						//如果成员变量上有@Inject注解，则设置该Bean类对应对象的成员变量为Bean Map中的对象
						if (field.isAnnotationPresent(Inject.class)) {
							//获取成员变量的类型
							Class<?> beanFieldClass = field.getType();
							//获取Bean Map中对应类型的实例
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							//通过反射初始化beanField的值
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
				
			}
		}
	}
}
