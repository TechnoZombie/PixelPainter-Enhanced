package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;

//CONSIDER USEFULNESS OF THIS CLASS. COULD BE FOR DELETION

public class ColorProcessor{

    private Color squareColor;
    public ColorProcessor() {
    }

    public void decodeColor(Color squareColor){
        this.squareColor = squareColor;
        int red = squareColor.getRed();
        int green = squareColor.getGreen();
        int blue = squareColor.getBlue();

    }
}
