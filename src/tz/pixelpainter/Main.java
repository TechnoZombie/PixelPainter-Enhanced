package tz.pixelpainter;

import tz.pixelpainter.keyboard.MouseController;

public class Main {
    public static void main(String[] args) {

        int width = 600;
        int height = 400;
        int pixelSize = 20;

        Canvas canvas = new Canvas();

        canvas.start(width, height, pixelSize);

    }
}