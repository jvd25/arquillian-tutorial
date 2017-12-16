package ejbs;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 * 
 * @author peeyush
 */
@Stateless
public class CommonService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CommonService.class.toString());

	public String getData(String input) throws Exception {
		LOGGER.warning("CommonService.getData called");
		return "A";
	}

}
