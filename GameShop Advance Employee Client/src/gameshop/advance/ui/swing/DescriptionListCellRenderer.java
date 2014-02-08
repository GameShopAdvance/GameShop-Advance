/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameshop.advance.ui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Lorenzo Di Giuseppe <lorenzo.digiuseppe88@gmail.com>
 */
public class DescriptionListCellRenderer implements ListCellRenderer<Object> {

    private final BufferedImage image;

    private int width = 400;
    private int height = 30;
    public DescriptionListCellRenderer(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.image = this.resize(ImageIO.read(new File("/src/gameshop/advance/ui/swing/employee/icons/delete.png")), this.width, this.height);
        
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //recupera l'indice se è valido
        int selectedIndex = ((Integer)value).intValue();
        
        JLabel box = new JLabel(value.toString());
        ImageIcon icon = new ImageIcon(this.image);
        box.setIcon(icon);
        //scelta condizionale, se l'oggetto è selezionato oppure no
        if(isSelected)
        {
             box.setBackground(Color.ORANGE);
             box.setDisabledIcon(icon);
             box.setForeground(Color.white);
        }
        else
        {
            box.setBackground(Color.lightGray);
            box.setForeground(Color.black);
        }
        return box;
        
    }
    
    private BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
}
