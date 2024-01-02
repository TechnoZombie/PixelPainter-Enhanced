package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import tz.pixelpainter.utils.Messages;

public class Canvas {

    public Cursor cursor;
    private int width;
    private int height;
    private int pixelSize;
    private Rectangle canvas;
    private Rectangle[][] individualSquares;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPixelSize() {
        return pixelSize;
    }

    public Rectangle[][] getIndividualSquares() {
        return individualSquares;
    }

    public void start(int width, int height, int pixelSize) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        cursor = new Cursor(pixelSize);
        Movement movement = new Movement(cursor, this);
        Coloring coloring = new Coloring(movement, cursor, this);
        Messages messages = new Messages();

        // Creates canvas as a big rectangle
        canvas = new Rectangle(1, 1, this.width, this.height);
        canvas.setColor(Color.BLACK);
        canvas.draw();

        // Calculate the number of horizontal squares and vertical lines
        int numHorizontalSquares = this.width / pixelSize;
        int numVerticalLines = this.height / pixelSize;

        // Initialize the two-dimensional array with the correct size
        individualSquares = new Rectangle[numVerticalLines][numHorizontalSquares];

        // Creates grid of squares
        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                individualSquares[i][j] = new Rectangle(1 + (j * pixelSize), 1 + (i * pixelSize), pixelSize, pixelSize);
                individualSquares[i][j].setColor(Color.BLACK);
                individualSquares[i][j].draw();
            }
        }
        //Filling the cursor needs to come last so that it over-imposes other elements of the grid
        cursor.cursorFill();

        messages.userText();

/*        // Print information about individual squares
        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                Rectangle square = individualSquares[i][j];
                System.out.println("X: " + square.getX() + ", Y: " + square.getY() +
                        ", Color: " + square.getColor());
            }
        }*/
    }



}
