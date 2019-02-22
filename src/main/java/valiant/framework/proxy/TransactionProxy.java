package valiant.framework.proxy;

import java.lang.reflect.Method;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import valiant.framework.annotation.Transaction;
import valiant.framework.helper.DatabaseHelper;

public class TransactionProxy implements Proxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};
	
	
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if (!flag && method.isAnnotationPresent(Transaction.class)) {
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransaction();
				LOGGER.debug("begin transaction");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTransaction();
				LOGGER.debug("commit transaction");
			} catch (Exception e) {
				// TODO: handle exception
				DatabaseHelper.rollbackTransaction();
				LOGGER.debug("rollback transaction");
			} finally {
				FLAG_HOLDER.remove();
			}
		} else {
			result = proxyChain.doProxyChain();
		}
		return result;
	}

}
