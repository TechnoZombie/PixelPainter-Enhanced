package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Color;
import tz.pixelpainter.Coloring;

public class ColorProcessor {
    private final Coloring coloring;

    public ColorProcessor(Coloring coloring) {
        this.coloring = coloring;
    }

    public String encodeColor(int red, int green, int blue) {
        // TODO: Enhancement - remove switch case to allow saving with custom colors;
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

    /*
    Convert simplegraphz colors to java.awt.Color colors
     */
    public java.awt.Color decodeColor(Color squareColor) {
        int r = squareColor.getRed();
        int g = squareColor.getGreen();
        int b = squareColor.getBlue();

        return new java.awt.Color(r, g, b);
    }

}
