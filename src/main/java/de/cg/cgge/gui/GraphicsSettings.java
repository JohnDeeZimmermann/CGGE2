package de.cg.cgge.gui;

import de.cg.cgge.game.GameInstance;

import java.awt.*;

public class GraphicsSettings {

    private GameInstance game;
    private Resolution internalResolution = null;
    private ScreenScalingHint screenScalingHint = ScreenScalingHint.SCALE_SMOOTH;

    public GraphicsSettings(GameInstance game) {
        this.game = game;
    }

    public Resolution getInternalResolution() {
        return internalResolution;
    }

    public void setInternalResolution(Resolution internalResolution) {
        game.getEventMapper().onInternalResolutionChanged(internalResolution, this.internalResolution);
        this.internalResolution = internalResolution;
    }

    public ScreenScalingHint getScreenScalingHint() {
        return screenScalingHint;
    }

    public void setScreenScalingHint(ScreenScalingHint screenScalingHint) {
        this.screenScalingHint = screenScalingHint;
    }

    public enum ScreenScalingHint {

        SCALE_SMOOTH(Image.SCALE_SMOOTH),
        SCALE_AREA_AVERAGING(Image.SCALE_AREA_AVERAGING),
        SCALE_DEFAULT(Image.SCALE_DEFAULT),
        SCALE_REPLICATE(Image.SCALE_REPLICATE),
        SCALE_FAST(Image.SCALE_FAST);

        private int hint;
        ScreenScalingHint(int hint) {
            this.hint = hint;
        }

        public int value() {
            return hint;
        }
    }
}
