package de.cg.cgge.gui;

import javax.swing.*;
import de.cg.cgge.game.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.invoke.MethodHandles;

public class DrawPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Drawer drawer; 

    private BufferedImage screen;

    private Color bgColor = Color.BLACK; 

    public DrawPanel(Drawer drawer) {
        this.drawer = drawer; 
        screen = new BufferedImage(drawer.getGameInstance().getWidth(), drawer.getGameInstance().getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    /*  Rendering pipeline  */
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics g = screen.getGraphics();

        GraphicsSettings settings = drawer.getGameInstance().getGraphicsSettings();
        if (settings == null) {
            System.out.println("Graphics settings are null!");
        }

        Resolution res = settings == null || settings.getInternalResolution() == null
                ? drawer.getGameInstance().getResolution()
                : settings.getInternalResolution();

        if (screen.getHeight() != res.getHeigth() || screen.getWidth() != res.getWidth()) {
            screen = new BufferedImage(res.getWidth(), res.getHeigth(), BufferedImage.TYPE_INT_RGB);
        }

        g.setColor(bgColor);
        g.fillRect(0, 0, res.getWidth(), drawer.getGameInstance().getHeight());
        g.setColor(Color.WHITE);

        Room room = drawer.getRoom();

        if (room.usesTileMap()) {
            room.getTileMap().draw(g, 0);
        }

        for (GameObject obj : room.getObjectManager().getObjects()) {
            if (obj.isVisible()) {
                obj.draw(g);
            }
        }

        if (room.usesTileMap()) {
            room.getTileMap().draw(g, 1);
        }

        for (GameObject obj : room.getObjectManager().getObjects()) {
            if (obj.isVisible()) {
                obj.postDraw(g);
            }
        }

        if (settings == null || settings.getInternalResolution() == null) {
            graphics.drawImage(screen, 0, 0, null);
        } else {
            Resolution windowRes = drawer.getGameInstance().getResolution();
            graphics.drawImage(
                    screen.getScaledInstance(windowRes.getWidth(), windowRes.getHeigth(), settings.getScreenScalingHint().value()),
                    0,0,
                    null
            );
        }


        drawer.increasePassedFrames();

        
    }

    public void setBackgroundColor(Color color) {
        this.bgColor = color ;
    }


}