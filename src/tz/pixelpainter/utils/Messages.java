package tz.pixelpainter.utils;

import javax.sound.midi.Soundbank;

public class Messages {

    public void userText() {
        System.out.println(
                "+------------------------------+\n" +
                        "| Welcome to PixelPainter!     |\n" +
                        "+------------------------------+\n" +
                        "| Use WASD to move the cursor. |\n" +
                        "| KEY E: ERASE SQUARE          |\n" +
                        "+------------------------------+\n" +
                        "| COLOR SELECTION:             |\n" +
                        "| KEY 1: BLACK                 |\n" +
                        "| KEY 2: RED                   |\n" +
                        "| KEY 3: GREEN                 |\n" +
                        "| KEY 4: BLUE                  |\n" +
                        "| KEY 5: YELLOW                |\n" +
                        "+------------------------------+\n" +
                        "| FILE:                        |\n" +
                        "| KEY Z: SAVE IMAGE            |\n" +
                        "| KEY X: LOAD IMAGE            |\n" +
                        "| KEY C: EXPORT IMAGE          |\n" +
                        "+------------------------------+"
        );

    }

    public void imageSaved(){
        System.out.println("IMAGE SAVED!");
    }
}
