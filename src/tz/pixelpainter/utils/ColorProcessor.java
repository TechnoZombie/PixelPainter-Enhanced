package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Color;

public class ColorProcessor {

    public String encodeColor(int red, int green, int blue) {

        String colorString = red + ", " + green + ", " + blue;

        return switch (colorString) {
            case "0, 0, 0" -> "BLACK";
            case "255, 0, 0" -> "RED";
            case "0, 255, 0" -> "GREEN";
            case "0, 0, 255" -> "BLUE";
            case "255, 255, 255" -> "WHITE";
            case "192, 192, 192" -> "LIGHT_GRAY";
            case "128, 128, 128" -> "GRAY";
            case "64, 64, 64" -> "DARK_GRAY";
            case "0, 255, 255" -> "CYAN";
            case "255, 0, 255" -> "MAGENTA";
            case "255, 255, 0" -> "YELLOW";
            case "255, 175, 175" -> "PINK";
            case "255, 200, 0" -> "ORANGE";
            default -> "BLACK";
        };
    }

    public Color colorTranslator(String color) {

        return switch (color) {
            case "BLACK" -> Color.BLACK;
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            case "WHITE" -> Color.WHITE;
            case "LIGHT_GRAY" -> Color.LIGHT_GRAY;
            case "GRAY" -> Color.GRAY;
            case "DARK_GRAY" -> Color.DARK_GRAY;
            case "CYAN" -> Color.CYAN;
            case "MAGENTA" -> Color.MAGENTA;
            case "YELLOW" -> Color.YELLOW;
            case "PINK" -> Color.PINK;
            case "ORANGE" -> Color.ORANGE;
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
