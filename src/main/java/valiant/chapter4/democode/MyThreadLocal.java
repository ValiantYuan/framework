package valiant.chapter4.democode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 自己实现的ThreadLocal
 * @author yuanq5
 *
 * @param <T> ThreadLocal变量的类型
 */
public class MyThreadLocal<T> {
	
	private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<>()); 
	/**
	 * 放入变量值
	 * @param value
	 */
	public void set(T value) {
		container.put(Thread.currentThread(), value);
	}
	/**
	 * 获取变量值
	 * @return
	 */
	public T get() {
		Thread thread = Thread.currentThread();
		T value = container.get(thread);
		if (value == null && !container.containsKey(thread)) {
			value = initialValue();
			container.put(thread, value);
		}
		return value;
	}
	/**
	 * 删除ThreadLocal中的变量
	 */
	public void remove() {
		container.remove(Thread.currentThread());
	}
	/**
	 * 返回初始值
	 * @return
	 */
	protected T initialValue() {
		return null;
	}
}
