package ar.com.javacuriosities.jsf.lifecycle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MyPhaseListener implements PhaseListener {

    private static final Logger LOGGER = Logger.getLogger(MyPhaseListener.class.getName());

    @Override
    public void afterPhase(PhaseEvent event) {
        LOGGER.log(Level.INFO, "Después:{0}", event.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        LOGGER.log(Level.INFO, "Antes:{0}", event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}