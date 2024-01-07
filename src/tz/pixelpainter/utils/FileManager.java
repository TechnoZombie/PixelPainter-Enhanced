package tz.pixelpainter.utils;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import tz.pixelpainter.Canvas;


import java.io.*;

public class FileManager {

    private final Canvas canvas;
    private Rectangle[][] individualSquaresToSave;
    private Messages messages;

    public FileManager(Canvas canvas, Rectangle[][] individualSquaresToSave, Messages messages) {
        this.individualSquaresToSave = individualSquaresToSave;
        this.canvas = canvas;
        this.messages = messages;
    }

    public void saveFile() {

        //FUTURE IMPLEMENTATION: OVERWRITE PROTECTION.

        int numVerticalLines = canvas.getNumVerticalLines();
        int numHorizontalSquares = canvas.getNumHorizontalSquares();
        individualSquaresToSave = canvas.getIndividualSquares();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/image.txt"))) {
            for (int i = 0; i < numVerticalLines; i++) {
                for (int j = 0; j < numHorizontalSquares; j++) {
                    if (individualSquaresToSave[i][j].isFilled()) {
                        writer.write(individualSquaresToSave[i][j] + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        messages.imageSaved();

    }

}


