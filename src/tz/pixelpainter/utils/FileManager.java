package tz.pixelpainter.utils;

import lombok.extern.slf4j.Slf4j;
import org.technozombie.simplegraphz.graphics.Color;
import org.technozombie.simplegraphz.graphics.Rectangle;
import tz.pixelpainter.Coloring;
import tz.pixelpainter.Whiteboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
public class FileManager {

    private final Whiteboard whiteboard;
    private final Messages messages;
    private final ColorProcessor colorProcessor;
    private final ConfirmationDialogs confirmationDialogs;
    private final Coloring coloring;

    public FileManager(Whiteboard whiteboard, Messages messages, ColorProcessor colorProcessor, Coloring coloring, ConfirmationDialogs confirmationDialogs) {
        this.whiteboard = whiteboard;
        this.messages = messages;
        this.colorProcessor = colorProcessor;
        this.confirmationDialogs = confirmationDialogs;
        this.coloring = coloring;
    }


    public boolean saveFile() {
        File file = new File(Constants.FILE_PATH_NAME);

        if (file.exists() && !confirmationDialogs.overwriteConfirmationDialog()) {
            return false; // If user chooses not to overwrite, exit
        }
        saveFileLogic();
        return true; // Indicate save was successful
    }

    void saveFileLogic() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH_NAME))) {
            for (Rectangle[] row : whiteboard.getIndividualSquares()) {
                for (Rectangle square : row) {
                    if (square.isFilled()) {
                        java.awt.Color c = colorProcessor.decodeColor(square.getColor());
                        writer.write(String.format("%s Color:%s;\n", square, c));
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error writing file: ", e);
        }
    }

    public void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH_NAME))) {
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

    private void processRectangleLine(String line) {
        // Coordinates extractors
        int x = coordinateExtractor(line, "x=", ",");
        int y = coordinateExtractor(line, "y=", ",");
        int w = coordinateExtractor(line, "width=", ",");

        // Color extractors
        int r = coordinateExtractor(line, "r=", ",");
        int g = coordinateExtractor(line, "g=", ",");
        int b = coordinateExtractor(line, "b=", "]");

        int iX = coordinateConverter(x, w);
        int iY = coordinateConverter(y, w);

        whiteboard.getIndividualSquares()[iY][iX].setColor(coloring.customColor(r, g, b));
        whiteboard.getIndividualSquares()[iY][iX].fill();
    }

    // Extracts coordinates from a string using regex
    public int coordinateExtractor(String line, String start, String stop) {
        return Integer.parseInt(line.split(start)[1].split(stop)[0]);
    }

    //Converts coordinates into 2D array positions
    public int coordinateConverter(int n, int w) {
        return (n - 1) / w;
    }


    public void exportToPng(String filePath) {
        if (!saveFile()) {
            return;
        }
        ; // Needs to call saveFile() first to prevent empty individualSquaresToSave from throwing nullPointerException;

        /* Find the bounding box of the painted squares
           This is done so that the image is exported without the full canvas size.
         */
        Rectangle boundingBox = findPaintedBoundingBox();

        if (boundingBox != null) {
            // Create an image with the size of the bounding box
            BufferedImage image = new BufferedImage(boundingBox.getWidth(), boundingBox.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();

            // Translate the graphics context to match the bounding box
            graphics.translate(-boundingBox.getX(), -boundingBox.getY());

            // Drawing individual squares to the image within the bounding box
            for (Rectangle[] row : whiteboard.getIndividualSquares()) {
                for (Rectangle square : row) {
                    if (square.isFilled()) {
                        Color colorToDecode = square.getColor();
                        graphics.setColor(colorProcessor.decodeColor(colorToDecode));
                        graphics.fillRect(square.getX(), square.getY(), square.getWidth(), square.getHeight());
                    }
                }
            }

            graphics.dispose();

            try {
                ImageIO.write(image, "png", new File(filePath));
            } catch (IOException e) {
                log.error("Error exporting to PNG: ", e);
            }
        }
    }

    private Rectangle findPaintedBoundingBox() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Rectangle[] row : whiteboard.getIndividualSquares()) {
            for (Rectangle square : row) {
                if (square.isFilled()) {
                    minX = Math.min(minX, square.getX());
                    minY = Math.min(minY, square.getY());
                    maxX = Math.max(maxX, square.getX() + square.getWidth());
                    maxY = Math.max(maxY, square.getY() + square.getHeight());
                }
            }
        }

        if (minX == Integer.MAX_VALUE || minY == Integer.MAX_VALUE) {
            // No painted squares found
            return null;
        }

        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    // Print information about individual painted squares by pressing key I
    // To comment or remove on final version
    public void getInfo() {
        int numVerticalLines = whiteboard.getNumberOfLines();
        int numHorizontalSquares = whiteboard.getNumberOfColumns();
        Rectangle[][] individualSquares = whiteboard.getIndividualSquares();

        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                if (individualSquares[i][j].isFilled()) {
                    Rectangle square = individualSquares[i][j];
                    Color color = square.getColor();
                    String colorString = "R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue();
                    log.info("X: " + square.getX() + ", Y: " + square.getY() + ", Color: " + colorString);
                }
            }
        }
    }
}



