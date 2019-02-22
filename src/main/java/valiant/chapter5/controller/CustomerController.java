package valiant.chapter5.controller;

import java.util.List;

import valiant.chapter2.model.Customer;
import valiant.chapter5.service.CustomerService;
import valiant.framework.annotation.Action;
import valiant.framework.annotation.Controller;
import valiant.framework.annotation.Inject;
import valiant.framework.bean.View;

@Controller
public class CustomerController {
	@Inject
	private CustomerService customerService;
	
//	@Action("get:/customer")
//	public View index() {
//		List<Customer> customerList = customerService.
//	}
	
}
