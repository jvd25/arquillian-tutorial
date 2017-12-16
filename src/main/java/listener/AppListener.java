/**
 * 
 */
package listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class AppListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent arg0) {
		System.out.println("afterPhase");
	}

	public void beforePhase(PhaseEvent arg0) {
//		System.out.println("beforePhase");
	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
