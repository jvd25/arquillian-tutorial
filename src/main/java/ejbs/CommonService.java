package ejbs;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Employee;

@Stateless
public class CommonService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CommonService.class.toString());
	@PersistenceContext(unitName = "mydb")
	EntityManager em;

	public String getData(String input) throws Exception {
		LOGGER.warning("CommonService.getData called");
		Employee employee = em.find(Employee.class, 1l);
		LOGGER.warning("CommonService.getData : " + employee.getEmployeeName());
		return employee.getEmployeeName();
	}

}
