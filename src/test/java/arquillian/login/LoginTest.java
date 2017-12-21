package arquillian.login;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.LoginController;
import ejbs.CommonService;
import entity.Employee;

@RunWith(Arquillian.class)
public class LoginTest {
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClasses(LoginController.class, CommonService.class)
				.addPackage(Employee.class.getPackage()).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("META-INF/persistence.xml");
		return jar;
	}

	@EJB
	CommonService commonService;

	@Test
	public void checkExpectedValue() throws Exception {
		String str = commonService.getData(null);
		Assert.assertTrue("Expected value shivam but generated value is " + str, "shivam".equals(str));
	}
}
