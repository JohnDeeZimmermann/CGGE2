package de.cg.cgge.events;

public class WindowResizeEvent extends Event{

    private int nx, ny, nw, nh;

    public WindowResizeEvent(int nx, int ny, int nw, int nh)
    {
        this.nx = nx;
        this.ny = ny;
        this.nw = nw;
        this.nh = nh;
    }

    public int getNewX() {
        return nx;
    }

    public int getNewY() {
        return ny;
    }

    public int getNewWidth() {
        return nw;
    }

    public int getNewHeight() {
        return nh;
    }
}
