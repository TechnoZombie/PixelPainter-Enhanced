package tz.pixelpainter;

import lombok.Getter;
import org.technozombie.simplegraphz.graphics.Rectangle;
import org.technozombie.simplegraphz.graphics.Color;
import tz.pixelpainter.hid.KeyboardController;
import tz.pixelpainter.utils.*;

public class Whiteboard {

    public Cursor cursor;

    @Getter
    private final int width;

    @Getter
    private final int height;

    @Getter
    private final int pixelSize;
    private final ColorProcessor colorProcessor;

    @Getter
    private Rectangle[][] individualSquares;
    private final FileManager fileManager;
    private final KeyboardController keyboardController;

    @Getter
    private int numberOfColumns;
    @Getter
    private int numberOfLines;
    private Messages messages;
    private Movement movement;
    private Coloring coloring;
    private ConfirmationDialogs confirmationDialogs;

    public Whiteboard(int width, int height, int pixelSize) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        this.colorProcessor = new ColorProcessor();
        this.messages = new Messages();
        this.cursor = new Cursor(pixelSize);
        this.movement = new Movement(cursor, this);
        this.coloring = new Coloring(movement, cursor, this);
        this.confirmationDialogs = new ConfirmationDialogs(messages, coloring, colorProcessor);
        this.fileManager = new FileManager(this, messages, colorProcessor, coloring, confirmationDialogs);
        this.confirmationDialogs.setFileManager(fileManager);
        this.keyboardController = new KeyboardController(movement, coloring, fileManager, confirmationDialogs);
    }

    public void start() {
        UserInterfaceManager userInterfaceManager = new UserInterfaceManager(fileManager, confirmationDialogs);
        userInterfaceManager.generateUserInterface();
        Generators generators = new Generators(width, height, pixelSize);
        generators.wallpaperGenerator();
        //generators.tableGenerator();
        gridGenerator();
        cursor.cursorFill(); //Filling the cursor needs to come last so that it over-imposes other elements of the grid
        //messages.instructionsTable();

        //Loads a picture from file
        //Picture picture = new Picture(5, 5, "resources/fff.png");

        // resizes picture
        // v = width
        // v1 = height
        //picture.grow(0, 200);

        //picture.draw();
    }

    // Creates grid of squares
    public void gridGenerator() {

        Rectangle gridBase = new Rectangle(5, 5, width + 10, height + 10);
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


