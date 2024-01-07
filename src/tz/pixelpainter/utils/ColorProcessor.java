package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;

//CONSIDER USEFULNESS OF THIS CLASS. COULD BE FOR DELETION

public class ColorProcessor {

    public ColorProcessor() {
    }

    public String decodeColor(Color squareColor) {
        int red = squareColor.getRed();
        int green = squareColor.getGreen();
        int blue = squareColor.getBlue();

        String colorString = red + ", " + green + ", " + blue;

        switch (colorString) {
            case "0, 0, 0":
                System.out.println("BLACK");
                return "BLACK";


            case "255, 0, 0":
                System.out.println("RED");
                return "RED";
        }

        return "ERROR: UNKNOWN COLOR!";
    }
}
