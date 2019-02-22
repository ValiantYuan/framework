package valiant.aop.cglib;

public class PersonService {
	public PersonService() {
		// TODO Auto-generated constructor stub
		System.out.println("PersonService构造函数");
	}
	
	final public PersonService getPerson(String code) {
		System.out.println("PersonService:getPerson>>" + code);
		return null;
	}
	
	public void setPerson() {
		System.out.println("PersonService:setPerson");
	}
}
