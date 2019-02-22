package valiant.framework;

import valiant.framework.helper.AopHelper;
import valiant.framework.helper.BeanHelper;
import valiant.framework.helper.ClassHelper;
import valiant.framework.helper.ControllerHelper;
import valiant.framework.helper.IocHelper;
import valiant.util.ClassUtil;
/**
 * 加载相应的Helper类
 * ClassHelper.class 加载配置文件中指定的包名下所有的类
 * BeanHelper.class 为每个Bean类创建实例，并产生BEAN_MAP映射关系，Bean包含了Controller和Service类
 * AopHelper.class 需要在IocHelper之前加载
 * IocHelper.class 为Bean中的成员变量注入类的实例
 * ControllerHelper.class 为每个Controller类创建
 * @author yuanq5
 *
 */
public class HelperLoader {
	public static void init() {
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for (Class<?> class1 : classList) {
			ClassUtil.loadClass(class1.getName());
		}
	}
}
