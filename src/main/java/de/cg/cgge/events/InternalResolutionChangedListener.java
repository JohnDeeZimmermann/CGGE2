package de.cg.cgge.events;

import de.cg.cgge.gui.Resolution;

public interface InternalResolutionChangedListener extends EventListener {

    void onInternalResolutionChanged(Resolution newResolution, Resolution oldResolution);

}
