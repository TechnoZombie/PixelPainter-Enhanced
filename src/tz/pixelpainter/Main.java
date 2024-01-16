package tz.pixelpainter;

public class Main {
    public static void main(String[] args) {

        int width = 600;
        int height = 400;

        //for deployment, pixelSize will be hardcoded
        int pixelSize = 20;

        Canvas canvas = new Canvas();
        canvas.start(width, height, pixelSize);

    }
}