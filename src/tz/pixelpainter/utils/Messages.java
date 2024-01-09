package tz.pixelpainter.utils;

public class Messages {

    public void userText() {
        System.out.println(
                        "+---------------------------------------------------+\n" +
                        "|             Welcome to PixelPainter!              |\n" +
                        "+----------------------+----------------------------|\n" +
                        "|HOW TO MOVE:          |FILE:                       |\n" +
                        "| W-A-S-D: MOVE        | KEY Z: SAVE IMAGE          |\n" +
                        "| SPACE: PAINT         | KEY X: LOAD IMAGE          |\n" +
                        "| KEY E: ERASE SQUARE  | KEY C: EXPORT IMAGE TO PNG |\n" +
                        "+---------------------------------------------------+\n" +
                        "|COLOR SELECTION:                                   |\n" +
                        "| KEY 1: BLACK         | KEY 6: YELLOW              |\n" +
                        "| KEY 2: RED           | KEY 7: ORANGE              |\n" +
                        "| KEY 3: GREEN         | KEY 8: PINK                |\n" +
                        "| KEY 4: BLUE          | KEY 9: MAGENTA             |\n" +
                        "| KEY 5: CYAN          | KEY 0: WHITE               |\n" +
                        "+---------------------------------------------------+\n"

        );

    }

    public void imageSaved() {
        System.out.println("IMAGE SAVED!");
    }

    public void imageLoaded() {
        System.out.println("IMAGE LOADED!");
    }

    public void featureUnavailable() {
        System.out.println("FEATURE IS NOT YET AVAILABLE.");
    }

    public void pngExported() {
        System.out.println("IMAGE SAVED AS PNG!");
    }

    public void areYouSure() {
        System.out.println("ARE YOU SURE? Y/N");
    }
}
