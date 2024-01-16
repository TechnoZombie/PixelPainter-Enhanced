package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import tz.pixelpainter.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

public class FileManager {

    private final Canvas canvas;
    private Rectangle[][] individualSquaresToSave;
    private final Messages messages;

    private ColorProcessor colorProcessor;


    public FileManager(Canvas canvas, Rectangle[][] individualSquaresToSave, Messages messages, ColorProcessor colorProcessor) {
        this.individualSquaresToSave = individualSquaresToSave;
        this.canvas = canvas;
        this.messages = messages;
        this.colorProcessor = colorProcessor;
    }

    public void saveFile() {
        int numVerticalLines = canvas.getNumVerticalLines();
        int numHorizontalSquares = canvas.getNumHorizontalSquares();
        individualSquaresToSave = canvas.getIndividualSquares();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/image.txt"))) {
            for (int i = 0; i < numVerticalLines; i++) {
                for (int j = 0; j < numHorizontalSquares; j++) {
                    if (individualSquaresToSave[i][j].isFilled()) {
                        Color squareColor = individualSquaresToSave[i][j].getColor();

                        writer.write(individualSquaresToSave[i][j] + "R:");
                        writer.write(squareColor.getRed() + ",G:");
                        writer.write(squareColor.getGreen() + ",B:");
                        writer.write(squareColor.getBlue() + ";\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        messages.imageSaved();
    }

    public void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/image.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Rectangle")) {

                    // Extract coordinates
                    int x = Integer.parseInt(line.split("x=")[1].split(",")[0]);
                    int y = Integer.parseInt(line.split("y=")[1].split(",")[0]);
                    int w = Integer.parseInt(line.split("width=")[1].split(",")[0]);

                    // Extract RGB values
                    int red = Integer.parseInt(line.split("R:")[1].split(",")[0]);
                    int green = Integer.parseInt(line.split("G:")[1].split(",")[0]);
                    int blue = Integer.parseInt(line.split("B:")[1].split(";")[0]);

                    // Convert coordinates into 2D array index
                    int iX = coordinateConverter(x, w);
                    int iY = coordinateConverter(y, w);

                    // Update canvas with the loaded data
                    canvas.getIndividualSquares()[iY][iX].setColor(colorProcessor.encodeColor(red, green, blue));
                    canvas.getIndividualSquares()[iY][iX].fill();
                }
            }

            messages.imageLoaded();

        } catch (IOException e) {
            e.printStackTrace();
            messages.featureUnavailable();
        }
    }

    //Converts coordinates into 2D array positions
    public int coordinateConverter(int n, int w) {
        return (n - 1) / w;
    }

    public void exportToPng(String filePath) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        saveFile(); //Needs to call saveFile() first to prevent empty individualSquaresToSave from throwing nullPointerException;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        // Drawing individual squares to the image
        for (Rectangle[] row : individualSquaresToSave) {
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
        messages.pngExported();

        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Print information about individual painted squares by pressing key I
    // To comment or remove on final version
    public void getInfo() {
        int numVerticalLines = canvas.getNumVerticalLines();
        int numHorizontalSquares = canvas.getNumHorizontalSquares();
        Rectangle[][] individualSquares = canvas.getIndividualSquares();

        for (int i = 0; i < numVerticalLines; i++) {
            for (int j = 0; j < numHorizontalSquares; j++) {
                if (individualSquares[i][j].isFilled()) {
                    Rectangle square = individualSquares[i][j];
                    System.out.println("X: " + square.getX() + ", Y: " + square.getY() + ", Color: " + square.getColor());
                }
            }

        }

    }

}



