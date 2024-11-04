package tz.pixelpainter.ui;

import lombok.Getter;
import org.technozombie.simplegraphz.graphics.Rectangle;
import org.technozombie.simplegraphz.graphics.Color;
import tz.pixelpainter.color.ColorProcessor;
import tz.pixelpainter.color.Coloring;
import tz.pixelpainter.movement.Cursor;
import tz.pixelpainter.hid.KeyboardController;
import tz.pixelpainter.movement.Movement;
import tz.pixelpainter.utils.*;

/**
 * Represents the main Whiteboard in the pixel painting application.
 * Responsible for setting up and managing the grid of squares and user interactions.
 */
@Getter
public class Whiteboard {

    private final int width;
    private final int height;
    private final int pixelSize;
    private int numberOfColumns;
    private int numberOfRows;
    private Rectangle[][] individualSquares;

    private final Cursor cursor;
    private final ColorProcessor colorProcessor;
    private final FileManager fileManager;
    private final KeyboardController keyboardController;
    private Messages messages;
    private Movement movement;
    private Coloring coloring;
    private ConfirmationDialogs confirmationDialogs;

    /**
     * Initializes the Whiteboard with specified width, height, and pixel size.
     * Sets up all necessary utilities and controllers.
     *
     * @param width     The width of the whiteboard in pixels.
     * @param height    The height of the whiteboard in pixels.
     * @param pixelSize The size of each pixel square.
     */
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

    /**
     * Starts the whiteboard setup by initializing the user interface,
     * generating the grid, and preparing the cursor for use.
     */
    public void start() {
        UserInterfaceManager userInterfaceManager = new UserInterfaceManager(fileManager, confirmationDialogs);

        Generators generators = new Generators(width, height, pixelSize);
        generators.wallpaperGenerator();
        // Optionally generate other templates
        // generators.tableGenerator();

        gridGenerator();
        cursor.cursorFill(); // Ensures cursor appears above other elements of the grid
    }

    /**
     * Generates the grid of squares that make up the whiteboard canvas.
     * Configures grid dimensions and initializes each square.
     */
    public void gridGenerator() {
        // Draw base rectangle for the grid background
        Rectangle gridBase = new Rectangle(5, 5, width + 10, height + 10);
        gridBase.setColor(Color.WHITE);
        gridBase.fill();

        // Calculate grid dimensions based on pixel size
        numberOfColumns = this.width / pixelSize;
        numberOfRows = this.height / pixelSize;

        // Initialize the grid array to store individual squares
        individualSquares = new Rectangle[numberOfRows][numberOfColumns];

        // Populate the grid with squares
        initializeGrid(numberOfRows, numberOfColumns, pixelSize);
    }

    /**
     * Initializes a grid of squares and stores each square in the individualSquares array.
     * Each square is a Rectangle object, drawn at a calculated position based on the grid layout.
     *
     * @param numberOfRows   The total number of rows in the grid.
     * @param numberOfColumns The total number of columns in the grid.
     * @param pixelSize      The size of each square in pixels.
     */
    public void initializeGrid(int numberOfRows, int numberOfColumns, int pixelSize) {
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            initializeRowOfSquares(rowIndex, numberOfColumns, pixelSize);
        }
    }

    /**
     * Initializes and draws a single row of squares within the grid.
     * This method is called for each row in the grid by initializeGrid.
     *
     * @param rowIndex       The index of the row being initialized.
     * @param numberOfColumns The number of squares in the row.
     * @param pixelSize      The size of each square in pixels.
     */
    private void initializeRowOfSquares(int rowIndex, int numberOfColumns, int pixelSize) {
        for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
            // Calculate the x and y position of the square
            int xPosition = 10 + (columnIndex * pixelSize);
            int yPosition = 10 + (rowIndex * pixelSize);

            // Create and draw the square at the calculated position
            Rectangle square = createSquare(xPosition, yPosition, pixelSize);
            drawSquare(square);

            // Store the square in the array for future reference
            individualSquares[rowIndex][columnIndex] = square;
        }
    }

    /**
     * Creates a new square (Rectangle) at the specified position and with the specified size.
     *
     * @param x    The x-coordinate of the top-left corner of the square.
     * @param y    The y-coordinate of the top-left corner of the square.
     * @param size The size of each side of the square in pixels.
     * @return A new Rectangle object representing the square.
     */
    private Rectangle createSquare(int x, int y, int size) {
        return new Rectangle(x, y, size, size);
    }

    /**
     * Sets the color of a square to black and draws it on the screen.
     *
     * @param square The square (Rectangle) to be drawn.
     */
    private void drawSquare(Rectangle square) {
        square.setColor(Color.BLACK);
        square.draw();
    }
}
