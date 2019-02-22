package valiant.smart4j.chapter2.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import valiant.chapter2.model.Customer;
import valiant.chapter2.service.CustomerService;
import valiant.framework.helper.DatabaseHelper;

public class CustomerServiceTest {
	private final CustomerService customerService;
	
	public CustomerServiceTest() {
		// TODO Auto-generated constructor stub
		customerService = new CustomerService();
	}
	
	@Before
	public void init() throws Exception{
		DatabaseHelper.excuteSqlFile("sql/customer_init.sql");
	}
	
	@Test
	public void getCustomerListTest() throws Exception {
		List<Customer> customerList = customerService.getCustomerList();
		Assert.assertEquals(2, customerList.size());
	}
	
	@Test
	public void getCustomerTest() throws Exception {
		long id = 1;
		Customer customer = customerService.getCustomer(id);
		Assert.assertNotNull(customer);
	}
	
	@Test 
	public void createCustomerTest() throws Exception {
		Map<String, Object> fieldMap = new HashMap<>();
		fieldMap.put("name", "customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "12345678901");
		boolean result = customerService.createCustomer(fieldMap);
		Assert.assertTrue(result);
	}

	@Test 
	public void updateCustomerTest() throws Exception {
		long id = 1;
		Map<String, Object> fieldMap = new HashMap<>();
		
		fieldMap.put("contact", "Eric");
		
		boolean result = customerService.updateCustomer(id, fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test 
	public void deleteCustomerTest() throws Exception {
		long id = 1;	
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
}
