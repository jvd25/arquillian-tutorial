package controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejbs.CommonService;

@SessionScoped
@Named("loginController")
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LoginController.class.toString());
	private String value;
	
	@EJB
	CommonService commonService;

	@PostConstruct
	public void initialize() {
		LOGGER.warning("LoginController.initialize");
		this.value = "INIT:";
	}

	public void clickAction() {
		LOGGER.warning("LoginController.clickAction");
		try {
			String value = commonService.getData(getValue());
			setValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}