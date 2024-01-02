package sa.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Canvas {

    public Cursor cursor;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int width;
    private int height;

    public Canvas() {

    }


    public int getPixelSize() {
        return pixelSize;
    }

    private int pixelSize;
    private Rectangle canvas;

    public Rectangle[][] getIndividualSquares() {
        return individualSquares;
    }

    private static Rectangle[][] individualSquares;

    public void start(int width, int height, int pixelSize) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        cursor = new Cursor(pixelSize);
        Movement movement = new Movement(cursor, this);
        Coloring coloring = new Coloring(movement, cursor, this);

        // Creates canvas as a big rectangle
        canvas = new Rectangle(1, 1, this.width, this.height);
        canvas.setColor(Color.BLACK);
        canvas.draw();

        // Calculate the number of horizontal squares and vertical lines
        int numHorizontalSquares = this.width / pixelSize;
        int numVerticalLines = this.height / pixelSize;

        // Initialize the two-dimensional array with the correct size
        individualSquares = new Rectangle[numVerticalLines][numHorizontalSquares];

        // Creates grid of pixels
        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                individualSquares[i][j] = new Rectangle(1 + (j * pixelSize), 1 + (i * pixelSize), pixelSize, pixelSize);
                individualSquares[i][j].setColor(Color.BLACK);
                individualSquares[i][j].draw();
            }
        }
        //Filling the cursor needs to come last so that it over-imposes other elements of the grid
        cursor.cursorFill();

        userText();

/*        // Print information about individual squares
        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                Rectangle square = individualSquares[i][j];
                System.out.println("X: " + square.getX() + ", Y: " + square.getY() +
                        ", Color: " + square.getColor());
            }
        }*/
    }


    public void userText() {
        System.out.println("+------------------------------+");
        System.out.println("| Welcome to PixelPainter!     |");
        System.out.println("+------------------------------+");
        System.out.println("| Use WASD to move the cursor. |");
        System.out.println("+------------------------------+");
        System.out.println("| CHOOSE YOUR COLOR!           |");
        System.out.println("| KEY 1: BLACK                 |");
        System.out.println("| KEY 2: RED                   |");
        System.out.println("| KEY 3: GREEN                 |");
        System.out.println("| KEY 4: BLUE                  |");
        System.out.println("| KEY 5: YELLOW                |");
        System.out.println("| KEY Z: SAVE IMAGE            |");
        System.out.println("| KEY X: LOAD IMAGE            |");
        System.out.println("| KEY C: CLEAR SQUARE          |");
        System.out.println("| KEY P: IN TESTING            |");
        System.out.println("+------------------------------+");
    }
}
