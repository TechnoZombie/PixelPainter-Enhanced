package tz.pixelpainter;

import tz.pixelpainter.ui.Whiteboard;

/**
 * The Main class serves as the entry point for the Pixel Painter application.
 * It initializes the application by creating a Whiteboard instance and starting it.
 */
public class Main {

    /**
     * The main method that runs the Pixel Painter application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Define the dimensions of the whiteboard
        int width = 800;      // Width of the whiteboard in pixels
        int height = 400;     // Height of the whiteboard in pixels

        // Define the size of each pixel square on the whiteboard
        int pixelSize = 20;   // Hardcoded for deployment purposes

        // Initialize the whiteboard with the specified dimensions and pixel size
        Whiteboard whiteboard = new Whiteboard(width, height, pixelSize);

        // Start the whiteboard, which will set up the user interface and other components
        whiteboard.start();
    }
}
