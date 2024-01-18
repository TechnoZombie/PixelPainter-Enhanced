package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Messages {

    public void instructionsTable() {
        System.out.println(
                "+---------------------------------------------------+\n" +
                        "|             Welcome to PixelPainter!              |\n" +
                        "+----------------------+----------------------------|\n" +
                        "|HOW TO MOVE:          |FILE:                       |\n" +
                        "| W-A-S-D: MOVE        | KEY Z: SAVE IMAGE          |\n" +
                        "| SPACE: PAINT         | KEY X: LOAD IMAGE          |\n" +
                        "| KEY E: ERASE SQUARE  | KEY C: EXPORT IMAGE TO PNG |\n" +
                        "| KEY O: CLEAR CANVAS  |                            |\n" +
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
        System.out.println(
                "+-----------------------+\n" +
                        "| IMAGE SAVED!          |\n" +
                        "+-----------------------+"
        );
    }

    public void imageLoaded() {
        System.out.println(
                "+-----------------------+\n" +
                        "| IMAGE LOADED!         |\n" +
                        "+-----------------------+"
        );
    }

    public void featureUnavailable() {
        System.out.println("FEATURE IS NOT YET AVAILABLE.");
    }

    public void pngExported() {
        System.out.println(
                "+-----------------------+\n" +
                        "| IMAGE SAVED AS PNG!   |\n" +
                        "+-----------------------+"
        );
    }

    public void areYouSureClear() {
        System.out.println(
                "+-----------------------+\n" +
                        "| CLEAR CANVAS:         |\n" +
                        "|  ARE YOU SURE? (Y/N): |\n" +
                        "+-----------------------+"
        );
    }

    public void areYouSureOverwrite() {
        System.out.println(
                "+-----------------------+\n" +
                        "| OVERWRITE IMAGE:      |\n" +
                        "|  ARE YOU SURE? (Y/N): |\n" +
                        "+-----------------------+"
        );
    }

    public void canvasCleared() {
        System.out.println(
                "+-----------------------+\n" +
                        "| CANVAS CLEARED!       |\n" +
                        "+-----------------------+"
        );
    }

    public void actionCancelled() {
        System.out.println(
                "+-----------------------+\n" +
                        "| ACTION WAS CANCELLED! |\n" +
                        "+-----------------------+"
        );
    }
}
