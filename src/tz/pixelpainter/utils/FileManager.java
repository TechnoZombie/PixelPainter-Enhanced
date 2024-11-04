package tz.pixelpainter.utils;

import lombok.extern.slf4j.Slf4j;
import org.technozombie.simplegraphz.graphics.Color;
import org.technozombie.simplegraphz.graphics.Rectangle;
import tz.pixelpainter.color.ColorProcessor;
import tz.pixelpainter.color.Coloring;
import tz.pixelpainter.ui.ConfirmationDialogs;
import tz.pixelpainter.ui.Whiteboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * The FileManager class is responsible for saving, loading, and exporting images from the PixelPainter application.
 */
@Slf4j
public class FileManager {

    private final Whiteboard whiteboard;
    private final Messages messages;
    private final ColorProcessor colorProcessor;
    private final ConfirmationDialogs confirmationDialogs;
    private final Coloring coloring;

    private static final String FILE_PATH = "resources/image.txt"; // File path for saving/loading images

    /**
     * Constructs a FileManager instance with the necessary components.
     *
     * @param whiteboard         the Whiteboard instance for managing drawings
     * @param messages           the Messages instance for displaying notifications
     * @param colorProcessor     the ColorProcessor instance for handling color conversions
     * @param coloring           the Coloring instance for managing color actions
     * @param confirmationDialogs the ConfirmationDialogs instance for user confirmations
     */
    public FileManager(Whiteboard whiteboard, Messages messages, ColorProcessor colorProcessor, Coloring coloring, ConfirmationDialogs confirmationDialogs) {
        this.whiteboard = whiteboard;
        this.messages = messages;
        this.colorProcessor = colorProcessor;
        this.confirmationDialogs = confirmationDialogs;
        this.coloring = coloring;
    }

    /**
     * Saves the current drawing to a file.
     *
     * @return true if the file was saved successfully, false otherwise.
     */
    public boolean saveFile() {
        File file = new File(FILE_PATH);

        if (file.exists() && !confirmationDialogs.overwriteConfirmationDialog()) {
            return false; // User chose not to overwrite
        }

        performFileSave();
        return true; // Indicate save was successful
    }

    /**
     * Performs the logic to save the current state of the drawing to the file.
     */
    public void performFileSave() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Rectangle[] row : whiteboard.getIndividualSquares()) {
                saveFilledSquares(writer, row);
            }
        } catch (IOException e) {
            log.error("Error writing file: ", e);
        }
    }

    /**
     * Saves the filled squares of a row to the provided writer.
     *
     * @param writer the BufferedWriter to write the data to
     * @param row    the row of rectangles to save
     * @throws IOException if an error occurs while writing
     */
    private void saveFilledSquares(BufferedWriter writer, Rectangle[] row) throws IOException {
        for (Rectangle square : row) {
            if (square.isFilled()) {
                java.awt.Color color = colorProcessor.decodeColor(square.getColor());
                writer.write(String.format("%s Color:%s;\n", square, color));
            }
        }
    }

    /**
     * Loads the drawing from a file.
     */
    public void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Rectangle")) {
                    processRectangleLine(line);
                }
            }
            messages.imageLoaded();
        } catch (IOException e) {
            log.error("Error loading file: ", e);
            messages.noImageAvailableToLoad();
        }
    }

    /**
     * Processes a line representing a rectangle from the loaded file.
     *
     * @param line the line to process
     */
    private void processRectangleLine(String line) {
        int x = extractCoordinate(line, "x=", ",");
        int y = extractCoordinate(line, "y=", ",");
        int width = extractCoordinate(line, "width=", ",");

        // Extract color components
        int r = extractCoordinate(line, "r=", ",");
        int g = extractCoordinate(line, "g=", ",");
        int b = extractCoordinate(line, "b=", "]");

        int iX = convertToIndex(x, width);
        int iY = convertToIndex(y, width);

        // Set the color and fill the rectangle
        whiteboard.getIndividualSquares()[iY][iX].setColor(coloring.customColor(r, g, b));
        whiteboard.getIndividualSquares()[iY][iX].fill();
    }

    /**
     * Extracts a coordinate from a string based on start and stop delimiters.
     *
     * @param line  the line to extract from
     * @param start the starting delimiter
     * @param stop  the stopping delimiter
     * @return the extracted integer value
     */
    public int extractCoordinate(String line, String start, String stop) {
        return Integer.parseInt(line.split(start)[1].split(stop)[0]);
    }

    /**
     * Converts a coordinate into a 2D array index.
     *
     * @param n the coordinate value
     * @param width the width of the area
     * @return the calculated index
     */
    public int convertToIndex(int n, int width) {
        return (n - 1) / width;
    }

    /**
     * Exports the current drawing to a PNG file.
     *
     * @param filePath the file path where the PNG will be saved
     */
    public void exportToPng(String filePath) {
        if (!saveFile()) {
            return; // Ensure the drawing is saved before exporting
        }

        Rectangle boundingBox = findPaintedBoundingBox();

        if (boundingBox != null) {
            createAndSaveImage(boundingBox, filePath);
        }
    }

    /**
     * Creates an image from the painted rectangles and saves it to a file.
     *
     * @param boundingBox the bounding box of the painted area
     * @param filePath    the file path where the image will be saved
     */
    private void createAndSaveImage(Rectangle boundingBox, String filePath) {
        BufferedImage image = new BufferedImage(boundingBox.getWidth(), boundingBox.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        // Translate graphics to match the bounding box position
        graphics.translate(-boundingBox.getX(), -boundingBox.getY());

        // Draw the filled rectangles onto the image
        drawFilledSquares(graphics);

        graphics.dispose();

        saveImageToFile(image, filePath);
    }

    /**
     * Draws filled squares onto the graphics context.
     *
     * @param graphics the Graphics2D context to draw on
     */
    private void drawFilledSquares(Graphics2D graphics) {
        for (Rectangle[] row : whiteboard.getIndividualSquares()) {
            for (Rectangle square : row) {
                if (square.isFilled()) {
                    Color colorToDecode = square.getColor();
                    graphics.setColor(colorProcessor.decodeColor(colorToDecode));
                    graphics.fillRect(square.getX(), square.getY(), square.getWidth(), square.getHeight());
                }
            }
        }
    }

    /**
     * Saves the image to a file in PNG format.
     *
     * @param image    the BufferedImage to save
     * @param filePath the file path to save the image to
     */
    private void saveImageToFile(BufferedImage image, String filePath) {
        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            log.error("Error exporting to PNG: ", e);
        }
    }

    /**
     * Finds the bounding box of all painted squares in the whiteboard.
     *
     * @return a Rectangle representing the bounding box of painted squares, or null if none found
     */
    private Rectangle findPaintedBoundingBox() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Rectangle[] row : whiteboard.getIndividualSquares()) {
            for (Rectangle square : row) {
                if (square.isFilled()) {
                    updateBoundingBox(square, minX, minY, maxX, maxY);
                }
            }
        }

        return createBoundingBox(minX, minY, maxX, maxY);
    }

    /**
     * Updates the bounding box coordinates based on the given square.
     *
     * @param square the square to consider for bounding box updates
     * @param minX   the current minimum x-coordinate
     * @param minY   the current minimum y-coordinate
     * @param maxX   the current maximum x-coordinate
     * @param maxY   the current maximum y-coordinate
     */
    private void updateBoundingBox(Rectangle square, int minX, int minY, int maxX, int maxY) {
        minX = Math.min(minX, square.getX());
        minY = Math.min(minY, square.getY());
        maxX = Math.max(maxX, square.getX() + square.getWidth());
        maxY = Math.max(maxY, square.getY() + square.getHeight());
    }

    /**
     * Creates a bounding box rectangle based on the min and max coordinates.
     *
     * @param minX the minimum x-coordinate
     * @param minY the minimum y-coordinate
     * @param maxX the maximum x-coordinate
     * @param maxY the maximum y-coordinate
     * @return a Rectangle representing the bounding box, or null if no squares were found
     */
    private Rectangle createBoundingBox(int minX, int minY, int maxX, int maxY) {
        if (minX == Integer.MAX_VALUE || minY == Integer.MAX_VALUE) {
            // No painted squares found
            return null;
        }
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    /**
     * Logs information about individual painted squares.
     */
    public void getInfo() {
        int numVerticalLines = whiteboard.getNumberOfRows();
        int numHorizontalSquares = whiteboard.getNumberOfColumns();
        Rectangle[][] individualSquares = whiteboard.getIndividualSquares();

        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                if (individualSquares[i][j].isFilled()) {
                    logSquareInfo(individualSquares[i][j]);
                }
            }
        }
    }

    /**
     * Logs information about a specific square.
     *
     * @param square the square whose information is to be logged
     */
    private void logSquareInfo(Rectangle square) {
        Color color = square.getColor();
        String colorString = "R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue();
        log.info("X: " + square.getX() + ", Y: " + square.getY() + ", Color: " + colorString);
    }
}
