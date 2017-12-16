package arquillian.login;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.LoginController;

@RunWith(Arquillian.class)
public class LoginTest {
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClass(LoginController.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		return jar;
	}

	@Inject
	LoginController loginController;

	@Test
	public void should_create_greeting() {
		// Assert.fail("Not yet implemented");
		System.out.println(loginController.clickAction());
	}
}
