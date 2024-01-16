package tz.pixelpainter.utils;

import tz.pixelpainter.Coloring;

import java.util.Scanner;

public class Auxiliaries {

    private Messages messages;
    private Coloring coloring;
    private Scanner scanner;
    private FileManager fileManager;

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public Auxiliaries(Messages messages, Coloring coloring) {
        this.messages = messages;
        this.coloring = coloring;
        this.scanner = new Scanner(System.in);
    }

    public void clearConfirmationScanner() {
        try {
            messages.areYouSureClear();

            char response = scanner.next().charAt(0);

            if (response == 'Y' || response == 'y') {
                coloring.clearCanvas();
                messages.canvasCleared();

            } else {
                messages.actionCancelled();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void overwriteConfirmationScanner() {
        try {
            messages.areYouSureOverwrite();

            char response = scanner.next().charAt(0);

            if (response == 'Y' || response == 'y') {
                fileManager.saveFileLogic();
            } else {
                messages.actionCancelled();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
