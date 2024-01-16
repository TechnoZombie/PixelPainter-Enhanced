package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class ColorProcessor {

    public ColorProcessor() {
    }

    public Color encodeColor(int red, int green, int blue) {

        String colorString = red + ", " + green + ", " + blue;

        return switch (colorString) {
            case "0, 0, 0" -> Color.BLACK;
            case "255, 0, 0" -> Color.RED;
            case "0, 255, 0" -> Color.GREEN;
            case "0, 0, 255" -> Color.BLUE;
            case "255, 255, 255" -> Color.WHITE;
            case "192, 192, 192" -> Color.LIGHT_GRAY;
            case "128, 128, 128" -> Color.GRAY;
            case "64, 64, 64" -> Color.DARK_GRAY;
            case "0, 255, 255" -> Color.CYAN;
            case "255, 0, 255" -> Color.MAGENTA;
            case "255, 255, 0" -> Color.YELLOW;
            case "255, 175, 175" -> Color.PINK;
            case "255, 200, 0" -> Color.ORANGE;
            default -> Color.BLACK;
        };

    }

    public java.awt.Color decodeColor(Color squareColor) {
        int red = squareColor.getRed();
        int green = squareColor.getGreen();
        int blue = squareColor.getBlue();

        String colorString = red + ", " + green + ", " + blue;

        return switch (colorString) {
            case "0, 0, 0" -> java.awt.Color.BLACK;
            case "255, 0, 0" -> java.awt.Color.RED;
            case "0, 255, 0" -> java.awt.Color.GREEN;
            case "0, 0, 255" -> java.awt.Color.BLUE;
            case "255, 255, 255" -> java.awt.Color.WHITE;
            case "192, 192, 192" -> java.awt.Color.LIGHT_GRAY;
            case "128, 128, 128" -> java.awt.Color.GRAY;
            case "64, 64, 64" -> java.awt.Color.DARK_GRAY;
            case "0, 255, 255" -> java.awt.Color.CYAN;
            case "255, 0, 255" -> java.awt.Color.MAGENTA;
            case "255, 255, 0" -> java.awt.Color.YELLOW;
            case "255, 175, 175" -> java.awt.Color.PINK;
            case "255, 200, 0" -> java.awt.Color.ORANGE;
            default ->
                //default color if unknown color
                    java.awt.Color.BLACK;
        };
    }
}
