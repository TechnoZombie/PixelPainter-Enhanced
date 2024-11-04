package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Rectangle;
import org.technozombie.simplegraphz.graphics.Color;
import org.technozombie.simplegraphz.graphics.Text;

/**
 * The Generators class is responsible for creating graphical elements such as wallpapers and instruction tables
 * for the PixelPainter application.
 */
public class Generators {
    private final int width;
    private final int height;
    private final int pixelSize;

    private static final int MARGIN_X = 20; // Horizontal margin
    private static final int MARGIN_Y = 10; // Vertical margin
    private static final int TABLE_CELL_WIDTH = 170; // Width of each table cell
    private static int TABLE_CELL_HEIGHT = 0; // Height of each table cell is determined by pixelSize

    /**
     * Constructs a Generators instance with specified dimensions and pixel size.
     *
     * @param width      the width of the canvas
     * @param height     the height of the canvas
     * @param pixelSize  the size of individual pixels in the table
     */
    public Generators(int width, int height, int pixelSize) {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        TABLE_CELL_HEIGHT = pixelSize; // Initialize cell height from pixel size
    }

    /**
     * Generates a wallpaper for the application.
     * This method creates a light gray rectangle to serve as the background.
     */
    public void wallpaperGenerator() {
        Rectangle wallpaper = new Rectangle(0, 0, width + MARGIN_X, height + MARGIN_Y);
        wallpaper.setColor(Color.LIGHT_GRAY);
        wallpaper.draw();
        wallpaper.fill();
    }

    /**
     * Generates a table with an outline and instructional text.
     */
    public void tableGenerator() {
        drawTableOutline();
        drawTableText();
    }

    /**
     * Draws the outline of the table, creating individual cells based on defined parameters.
     */
    private void drawTableOutline() {
        int cols = 1; // Fixed number of columns
        int rows = 12; // Fixed number of rows

        Rectangle[][] tableCells = new Rectangle[rows][cols];

        // Generate the table cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tableCells[row][col] = new Rectangle(
                        (width + MARGIN_X) + (col * TABLE_CELL_WIDTH),
                        MARGIN_Y + (row * TABLE_CELL_HEIGHT),
                        TABLE_CELL_WIDTH,
                        TABLE_CELL_HEIGHT
                );
                tableCells[row][col].setColor(Color.WHITE);
                tableCells[row][col].draw();
            }
        }
    }

    /**
     * Draws the instructional text on the table.
     */
    private void drawTableText() {
        String welcomeMessage = "Welcome to PixelPainter!";
        Text welcomeText = new Text(300, (double) height / 2, welcomeMessage);
        welcomeText.setColor(Color.ORANGE);
        welcomeText.draw();
        welcomeText.grow(200, 50); // Grow the text to make it more visible

        // Instruction items to be displayed
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

        int heightAlignment = 11; // Starting vertical alignment for text

        // Draw each instruction item
        for (String instructionItem : instructionItems) {
            Text menuItem = new Text(width + 25, heightAlignment, instructionItem);
            menuItem.setColor(Color.WHITE);
            menuItem.draw();
            heightAlignment += 20; // Increment the alignment for the next item
        }
    }
}
