package valiant.chapter5.controller;

import valiant.chapter5.service.CustomerService;
import valiant.framework.annotation.Controller;
import valiant.framework.annotation.Inject;

@Controller
public class CustomerController {
	@Inject
	private CustomerService customerService;
	
//	@Action("get:/customer")
//	public View index() {
//		List<Customer> customerList = customerService.
//	}
	
}
