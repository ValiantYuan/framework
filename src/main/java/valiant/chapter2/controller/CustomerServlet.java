package valiant.chapter2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import valiant.chapter2.model.Customer;
import valiant.chapter2.service.CustomerService;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private CustomerService customerService;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		customerService = new CustomerService();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Customer> customerList = customerService.getCustomerList();
		req.setAttribute("customerList", customerList);
		req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
	}

}
