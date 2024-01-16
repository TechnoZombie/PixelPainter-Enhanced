package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Auxiliaries {

    private static final int MIN_SIZE = 100;
    private static final int MARGIN = 10;
    private static final int LOCATION_OFFSET = 120;
    private static Auxiliaries auxiliaries = new Auxiliaries();
    private ArrayList<Shape> shapes = new ArrayList();
    private BufferedImage background;
    private JFrame frame = new JFrame();
    private AuxiliariesComponent component = new AuxiliariesComponent();

    public Auxiliaries() {
        this.frame.add(this.component);
        this.frame.pack();
        this.frame.setLocation(120, 120);
        this.frame.setVisible(true);
    }

    public static Auxiliaries getInstance() {
        return auxiliaries;
    }

    public static void pause() {
        JFrame var0 = getInstance().frame;
        if (var0 != null) {
            JOptionPane.showMessageDialog(var0, "Click Ok to continue");
        }
    }

    public static void snapshot() {
        Dimension var0 = getInstance().component.getPreferredSize();
        Rectangle var1 = new Rectangle(0, 0, var0.width, var0.height);
        BufferedImage var2 = new BufferedImage(var1.width, var1.height, 1);
        Graphics var3 = var2.getGraphics();
        var3.setColor(Color.WHITE);
        var3.fillRect(0, 0, var1.width, var1.height);
        var3.setColor(Color.BLACK);
        getInstance().component.paintComponent(var3);
        float var4 = 0.8F;
        float var5 = 255.0F * (1.0F - var4);
        RescaleOp var6 = new RescaleOp(var4, var5, (RenderingHints) null);
        BufferedImage var7 = new BufferedImage(var2.getWidth(), var2.getHeight(), var2.getType());
        var6.filter(var2, var7);
        getInstance().background = var7;
        getInstance().component.repaint();
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    public ArrayList<Shape> getShapesInArea(int var1, int var2, int var3, int var4) {
        ArrayList var5 = new ArrayList();
        Iterator var6 = this.shapes.iterator();

        while (var6.hasNext()) {
            Shape var7 = (Shape) var6.next();
            if (var7.getX() >= var1 && var7.getX() <= var3 && var7.getY() >= var2 && var7.getY() <= var4) {
                var5.add(var7);
            }
        }

        return var5;
    }

    public void show(Shape var1) {
        if (!this.shapes.contains(var1)) {
            this.shapes.add(var1);
        }

        this.repaint();
    }

    public void hide(Shape var1) {
        if (this.shapes.contains(var1)) {
            this.shapes.remove(var1);
        }

        this.repaint();
    }

    public void repaint() {
        if (this.frame != null) {
            Dimension var1 = this.component.getPreferredSize();
            if (!(var1.getWidth() > (double) this.component.getWidth()) && !(var1.getHeight() > (double) this.component.getHeight())) {
                this.frame.repaint();
            } else {
                this.frame.pack();
            }

        }
    }

    public void saveToDisk(String var1) {
        Dimension var2 = this.component.getPreferredSize();
        Rectangle var3 = new Rectangle(0, 0, var2.width, var2.height);
        BufferedImage var4 = new BufferedImage(var3.width, var3.height, 1);
        Graphics2D var5 = (Graphics2D) var4.getGraphics();
        var5.setColor(Color.WHITE);
        var5.fill(var3);
        var5.setColor(Color.BLACK);
        this.component.paintComponent(var5);
        String var6 = var1.substring(var1.lastIndexOf(46) + 1);

        try {
            ImageIO.write(var4, var6, new File(var1));
        } catch (IOException var8) {
            System.err.println("Was unable to save the image to " + var1);
        }

        var5.dispose();
    }

    public void addKeyListener(KeyListener var1) {
        this.frame.addKeyListener(var1);
    }

    public void addMouseListener(MouseListener var1) {
        this.frame.addMouseListener(var1);
    }

    public void addMouseMotionListener(MouseMotionListener var1) {
        this.frame.addMouseMotionListener(var1);
    }

    class AuxiliariesComponent extends JComponent {
        AuxiliariesComponent() {
        }

        public void paintComponent(Graphics var1) {
            var1.setColor(Color.WHITE);
            var1.fillRect(0, 0, this.getWidth(), this.getHeight());
            var1.setColor(Color.BLACK);
            if (Auxiliaries.this.background != null) {
                var1.drawImage(Auxiliaries.this.background, 0, 0, (ImageObserver) null);
            }

            Iterator var2 = (new ArrayList(Auxiliaries.this.shapes)).iterator();

            while (var2.hasNext()) {
                Shape var3 = (Shape) var2.next();
                Graphics2D var4 = (Graphics2D) var1.create();
                var3.paintShape(var4);
                var4.dispose();
            }

        }

        public Dimension getPreferredSize() {
            int var1 = 100;
            int var2 = 100;
            if (Auxiliaries.this.background != null) {
                var1 = Math.max(var1, Auxiliaries.this.background.getWidth());
                var2 = Math.max(var1, Auxiliaries.this.background.getHeight());
            }

            Shape var4;
            for (Iterator var3 = Auxiliaries.this.shapes.iterator(); var3.hasNext(); var2 = Math.max(var2, var4.getY() + var4.getHeight())) {
                var4 = (Shape) var3.next();
                var1 = Math.max(var1, var4.getX() + var4.getWidth());
            }

            return new Dimension(var1 + 10, var2 + 10);
        }
    }
}

