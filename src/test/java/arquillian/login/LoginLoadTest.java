package arquillian.login;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
public class LoginLoadTest {
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
	public void loadTest() throws Exception {
		LocalDateTime time = LocalDateTime.now();
		methodToTest();
		long duration = Duration.between(time, LocalDateTime.now()).getSeconds();
		Assert.assertTrue("Method takes more than a second.", duration <= 1);
	}

	private void methodToTest() throws Exception {
		int concurrentThread = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(concurrentThread);
		for (int i = 0; i < concurrentThread; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					try {
//						synchronized (lock) {
							commonService.getData(null);
//						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("Asynchronous task");
				}
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	}

	public Object lock = new Object();
}
