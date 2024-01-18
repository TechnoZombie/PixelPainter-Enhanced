package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import tz.pixelpainter.hid.KeyboardController;
import tz.pixelpainter.utils.*;

public class Canvas {

    public Cursor cursor;
    private int width;
    private int height;
    private int pixelSize;
    private ColorProcessor colorProcessor;
    private Rectangle[][] individualSquares;
    private FileManager fileManager;
    private KeyboardController keyboardController;
    private int numberOfColumns;
    private int numberOfLines;
    private Messages messages;
    private Movement movement;
    private Coloring coloring;
    private Auxiliaries auxiliaries;

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

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumVerticalLines() {
        return numberOfLines;
    }


    public void start(int width, int height, int pixelSize) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;

        Generators generators = new Generators(width,height,pixelSize);
        generators.wallpaperGenerator();
        generators.tableGenerator(1,12,170,pixelSize);

        loadTools();
        gridGenerator();


        //Loads a picture from file
        //Picture picture = new Picture(5, 5, "resources/fff.png");

        // resizes picture
        // v = width
        // v1 = height
        //picture.grow(0, 200);

        //picture.draw();

        //Filling the cursor needs to come last so that it over-imposes other elements of the grid
        cursor.cursorFill();

        messages.instructionsTable();
    }

    public void loadTools() {
        cursor = new Cursor(pixelSize);
        movement = new Movement(cursor, this);
        coloring = new Coloring(movement, cursor, this);
        messages = new Messages();
        colorProcessor = new ColorProcessor();
        auxiliaries = new Auxiliaries(messages, coloring);
        fileManager = new FileManager(this, individualSquares, messages, colorProcessor, auxiliaries);
        auxiliaries.setFileManager(fileManager);
        keyboardController = new KeyboardController(movement, coloring, fileManager, auxiliaries);
    }

    // Creates grid of squares
    public void gridGenerator() {

        Rectangle gridBase = new Rectangle(5, 5, width +10, height +10);
        gridBase.setColor(Color.WHITE);
        gridBase.fill();

        // Calculate the number of horizontal squares and vertical lines
        numberOfColumns = this.width / pixelSize;
        numberOfLines = this.height / pixelSize;

        // Initialize the two-dimensional array with the correct size
        individualSquares = new Rectangle[numberOfLines][numberOfColumns];

        //  Generate the grid
        for (int i = 0; i < numberOfLines; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                individualSquares[i][j] = new Rectangle(10 + (j * pixelSize), 10 + (i * pixelSize), pixelSize, pixelSize);
                individualSquares[i][j].setColor(Color.BLACK);
                individualSquares[i][j].draw();

            }
        }
    }


}


