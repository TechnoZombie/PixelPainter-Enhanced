package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import tz.pixelpainter.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileManager {

    private final Canvas canvas;
    private Rectangle[][] individualSquaresToSave;
    private final Messages messages;


    public FileManager(Canvas canvas, Rectangle[][] individualSquaresToSave, Messages messages) {
        this.individualSquaresToSave = individualSquaresToSave;
        this.canvas = canvas;
        this.messages = messages;

    }

   /* public void saveFile() {

        //FUTURE IMPLEMENTATION: OVERWRITE PROTECTION.

        int numVerticalLines = canvas.getNumVerticalLines();
        int numHorizontalSquares = canvas.getNumHorizontalSquares();
        individualSquaresToSave = canvas.getIndividualSquares();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/image.txt"))) {
            for (int i = 0; i < numVerticalLines; i++) {
                for (int j = 0; j < numHorizontalSquares; j++) {
                    if (individualSquaresToSave[i][j].isFilled()) {
                        Color squareColor = individualSquaresToSave[i][j].getColor();

                        ColorProcessor processor = new ColorProcessor();
                        processor.decodeColor(individualSquaresToSave[i][j].getColor());

                        int red = squareColor.getRed();
                        int green = squareColor.getGreen();
                        int blue = squareColor.getBlue();

                        writer.write(individualSquaresToSave[i][j] + "RGB:");
                        writer.write(red + ",");
                        writer.write(green + ",");
                        writer.write(blue + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    saveAsPng();
        messages.imageSaved();

    }*/

    public void saveFile() {
        int numVerticalLines = canvas.getNumVerticalLines();
        int numHorizontalSquares = canvas.getNumHorizontalSquares();
        individualSquaresToSave = canvas.getIndividualSquares();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/image.txt"))) {
            for (int i = 0; i < numVerticalLines; i++) {
                for (int j = 0; j < numHorizontalSquares; j++) {
                    if (individualSquaresToSave[i][j].isFilled()) {
                        Color squareColor = individualSquaresToSave[i][j].getColor();

                        int red = squareColor.getRed();
                        int green = squareColor.getGreen();
                        int blue = squareColor.getBlue();

                        writer.write(individualSquaresToSave[i][j] + "RGB:");
                        writer.write(red + ",");
                        writer.write(green + ",");
                        writer.write(blue + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

     //   exportToPng("resources/savedImage.png");
        messages.imageSaved();
    }

    public void loadFile(){
        //HERE BE LYING CODE TO LOAD THE TXT COORDINATES
        messages.featureUnavailable();
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
                    //graphics.setColor(square.getColor());
                   //.getColor belongs to java.awt. Multi-colored saving will only work when ColorProcessor is done

                    graphics.setColor(java.awt.Color.BLACK);
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



