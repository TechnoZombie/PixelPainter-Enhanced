package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class ColorProcessor {

    public ColorProcessor() {
    }

    public java.awt.Color decodeColor(Color squareColor) {
        int red = squareColor.getRed();
        int green = squareColor.getGreen();
        int blue = squareColor.getBlue();

        String colorString = red + ", " + green + ", " + blue;

        switch (colorString) {
            case "0, 0, 0":
                return java.awt.Color.BLACK;

            case "255, 0, 0":
                return java.awt.Color.RED;

            case "0, 255, 0":
                return java.awt.Color.GREEN;

            case "0, 0, 255":
                return java.awt.Color.BLUE;

            case "255, 255, 255":
                return java.awt.Color.WHITE;

            case "192, 192, 192":
                return java.awt.Color.LIGHT_GRAY;

            case "128, 128, 128":
                return java.awt.Color.GRAY;

            case "64, 64, 64":
                return java.awt.Color.DARK_GRAY;

            case "0, 255, 255":
                return java.awt.Color.CYAN;

            case "255, 0, 255":
                return java.awt.Color.MAGENTA;

            case "255, 255, 0":
                return java.awt.Color.YELLOW;

            case "255, 175, 175":
                return java.awt.Color.PINK;

            case "255, 200, 0":
                return java.awt.Color.ORANGE;
        }
        //default color if unknown color
        return java.awt.Color.BLACK;
    }
}
