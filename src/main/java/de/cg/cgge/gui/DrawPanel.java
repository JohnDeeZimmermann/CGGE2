package de.cg.cgge.gui;

import javax.swing.*;
import de.cg.cgge.game.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Drawer drawer; 

    private BufferedImage screen;

    private Color bgColor = Color.BLACK; 

    public DrawPanel(Drawer drawer) {
        this.drawer = drawer; 
        screen = new BufferedImage(drawer.getGameInstance().getWidth(), drawer.getGameInstance().getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics g = screen.getGraphics();
                
        g.setColor(bgColor);
        g.fillRect(0, 0, drawer.getGameInstance().getWidth(), drawer.getGameInstance().getHeight());
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
        
        graphics.drawImage((Image) screen, 0, 0, null); 

        room.getGameInstance().getDrawer().increasePassedFrames();

        
    }

    public void setBackgroundColor(Color color) {
        this.bgColor = color ;
    }

}