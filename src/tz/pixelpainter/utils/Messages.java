package tz.pixelpainter.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Messages {

    public void instructionsTable() {
        System.out.println(
                """
                        +---------------------------------------------------+
                        |             Welcome to PixelPainter!              |
                        +----------------------+----------------------------|
                        |HOW TO MOVE:          |FILE:                       |
                        | W-A-S-D: MOVE        | KEY Z: SAVE IMAGE          |
                        | SPACE: PAINT         | KEY X: LOAD IMAGE          |
                        | KEY E: ERASE SQUARE  | KEY C: EXPORT IMAGE TO PNG |
                        | KEY O: CLEAR CANVAS  |                            |
                        +---------------------------------------------------+
                        |COLOR SELECTION:                                   |
                        | KEY 1: BLACK         | KEY 6: YELLOW              |
                        | KEY 2: RED           | KEY 7: ORANGE              |
                        | KEY 3: GREEN         | KEY 8: PINK                |
                        | KEY 4: BLUE          | KEY 9: MAGENTA             |
                        | KEY 5: CYAN          | KEY 0: WHITE               |
                        +---------------------------------------------------+
                        """
        );
    }

    public String imageSaved() {
        return "Image Saved!";
    }

    public void imageLoaded() {
        log.info("Image Loaded!");
    }

    public void featureUnavailable() {
        log.info("FEATURE IS NOT YET AVAILABLE.");
    }

    public String pngExported() {
        return ".png exported!";
    }

    public String actionCancelled() {
        return "Action cancelled.";
    }

    public String savingCancelled() {
        return "Image not saved.";
    }

    public void noImageAvailableToLoad() {
        log.error("No image available to load");
    }

    public String keyBindsMessage() {
        return """      
                MOVEMENT AND ACTIONS:
                W-A-S-D: Move
                SPACE: Paint
                KEY E: Erase Square

                COLOR SELECTION:
                KEY 1: Black        KEY 6: Yellow
                KEY 2: Red          KEY 7: Orange
                KEY 3: Green        KEY 8: Pink
                KEY 4: Blue         KEY 9: Magenta
                KEY 5: Cyan         KEY 0: White
                """;
    }
}
