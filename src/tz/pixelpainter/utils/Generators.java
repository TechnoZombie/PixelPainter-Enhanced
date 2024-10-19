package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Generators {
    private final int width;
    private final int height;
    private final int pixelSize;

    public Generators(int width, int height, int pixelSize) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
    }

    public void wallpaperGenerator() {
        //v = margin left to right
        //v1 = margin to top bottom
        Rectangle wallpaper = new Rectangle(0, 0, width + 200, height + 20);
        wallpaper.setColor(Color.LIGHT_GRAY);
        wallpaper.draw();
        wallpaper.fill();
    }

    public void tableGenerator() {
        tableOutlineGenerator();
        tableTextGenerator();
    }

    private void tableOutlineGenerator() {
int cols = 1;
int lines = 12;
int cellWidth = 170;
int cellHeight = pixelSize;
        // Initialize the two-dimensional array with the correct size
        Rectangle[][] tableCells = new Rectangle[lines][cols];

        /*Rectangle:
        v: margin left right
        v1: margin top bottom
        v2: cell width
        v3: cell height
        */

        //  Generate the table
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                tableCells[i][j] = new Rectangle(((width + 20) + (j * cellWidth)), 10 + (i * cellHeight), cellWidth, cellHeight);
                tableCells[i][j].setColor(Color.WHITE);
                tableCells[i][j].draw();

            }
        }
    }

    private void tableTextGenerator() {
        String splashscreen = "Welcome to PixelPainter!";

        // v = lef and right
        // v1 = up and down
        Text text = new Text(300, (double) height / 2, splashscreen);

        text.setColor(Color.ORANGE);
        text.draw();

        // v = width
        // v1 = height
        text.grow(200, 50);

        String[] instructionItems = {
                "PIXELPAINTER!",
                "  ",
                "MOVEMENT AND PAINTING",
                "W-A-S-D: Move",
                "SPACE: Paint",
                "E: Erase square",
                "O: Clear Canvas",
                "  ",
                "FILE",
                "Z: Save Image",
                "X: Load Image",
                "C: Export to PNG"
        };

        int heightAlignment = 11;

        for (String instructionItem : instructionItems) {
            Text menuItem = new Text(width + 25, heightAlignment, instructionItem);
            menuItem.setColor(Color.WHITE);
            menuItem.draw();
            heightAlignment = heightAlignment + 20;
            //System.out.println(heightAlignment);
        }


        // v = margin left to right
        // v1 = margin to to bottom
        // Text menu = new Text(width + 10, 10, "INSTRUCTIONS:");
        // menu.setColor(Color.BLUE);
        // menu.draw();
    }
}
