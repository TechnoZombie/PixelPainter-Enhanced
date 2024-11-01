package tz.pixelpainter;

import org.technozombie.simplegraphz.graphics.Canvas;
import tz.pixelpainter.utils.FileManager;

public class Main {
    public static void main(String[] args) {



        int width = 800;
        int height = 400;

        //for deployment, pixelSize will be hardcoded
        int pixelSize = 20;

        //Splashscreen splashscreen = new Splashscreen();
        //splashscreen.load();
        Whiteboard whiteboard = new Whiteboard();
        whiteboard.start(width, height, pixelSize);

    }
}