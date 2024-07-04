package de.cg.cgge.gui;

public class Resolution {

    private final int heigth;
    private final int width;

    public Resolution(int width, int heigth) {
        this.heigth = heigth;
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWidth() {
        return width;
    }
}
