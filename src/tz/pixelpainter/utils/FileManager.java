package tz.pixelpainter.utils;

import lombok.extern.slf4j.Slf4j;
import org.technozombie.simplegraphz.graphics.Color;
import org.technozombie.simplegraphz.graphics.Rectangle;
import tz.pixelpainter.Whiteboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
public class FileManager {

    private final Whiteboard whiteboard;
    private final Messages messages;
    private ColorProcessor colorProcessor;
    private ConfirmationDialogs confirmationDialogs;
    String filePath = "resources/image.txt";

    public FileManager(Whiteboard whiteboard, Messages messages, ColorProcessor colorProcessor, ConfirmationDialogs confirmationDialogs) {
        this.whiteboard = whiteboard;
        this.messages = messages;
        this.colorProcessor = colorProcessor;
        this.confirmationDialogs = confirmationDialogs;
    }


    public void saveFile() {
        File file = new File(filePath);

        if (file.exists() && !confirmationDialogs.overwriteConfirmationDialog()) {
            return; // If user chooses not to overwrite, exit
        }
        saveFileLogic();
    }

    void saveFileLogic() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Rectangle[] row : whiteboard.getIndividualSquares()) {
                for (Rectangle square : row) {
                    if (square.isFilled()) {
                        String color = colorProcessor.encodeColor(square.getColor().getRed(), square.getColor().getGreen(), square.getColor().getBlue());
                        writer.write(String.format("%s Color:%s;\n", square, color));
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error writing file: ", e);
        }
    }

    public void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/image.txt"))) {
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
        int x = coordinateExtractor("x=", ",", line);
        int y = coordinateExtractor("y=", ",", line);
        int w = coordinateExtractor("width=", ",", line);
        String color = line.split("Color:")[1].split(";")[0];

        int iX = coordinateConverter(x, w);
        int iY = coordinateConverter(y, w);

        whiteboard.getIndividualSquares()[iY][iX].setColor(colorProcessor.colorTranslator(color));
        whiteboard.getIndividualSquares()[iY][iX].fill();
    }

    // Extracts coordinates from a string using regex
    public int coordinateExtractor(String start, String stop, String line) {
        return Integer.parseInt(line.split(start)[1].split(stop)[0]);
    }

    //Converts coordinates into 2D array positions
    public int coordinateConverter(int n, int w) {
        return (n - 1) / w;
    }


    public void exportToPng(String filePath) {
        saveFile(); // Needs to call saveFile() first to prevent empty individualSquaresToSave from throwing nullPointerException;

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
                        ColorProcessor processor = new ColorProcessor();
                        Color colorToDecode = square.getColor();

                        graphics.setColor(processor.decodeColor(colorToDecode));

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
        int numVerticalLines = whiteboard.getNumVerticalLines();
        int numHorizontalSquares = whiteboard.getNumberOfColumns();
        Rectangle[][] individualSquares = whiteboard.getIndividualSquares();

        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                if (individualSquares[i][j].isFilled()) {
                    Rectangle square = individualSquares[i][j];
                    Color color = square.getColor();
                    String colorString = "R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue();
                    log.info("X: " + square.getX() + ", Y: " + square.getY() + ", Color: " + colorString);
                    System.out.println();
                }
            }

        }

    }

}



