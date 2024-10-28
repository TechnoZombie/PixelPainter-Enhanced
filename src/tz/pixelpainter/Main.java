package tz.pixelpainter;

import org.technozombie.simplegraphz.graphics.Canvas;
import tz.pixelpainter.utils.FileManager;

public class Main {
    public static void main(String[] args) {

        Canvas canvas = Canvas.getInstance();
        canvas.addToFileMenu("Test", e -> System.out.println("Test!!!"));


        canvas.addMenuAndItem("Pirocas", "Caralhos", e -> System.out.println("TUDO GAITAS!!"));

        int width = 600;
        int height = 400;

        //for deployment, pixelSize will be hardcoded
        int pixelSize = 20;

        //Splashscreen splashscreen = new Splashscreen();
        //splashscreen.load();
        Whiteboard whiteboard = new Whiteboard();
        whiteboard.start(width, height, pixelSize);

    }
}