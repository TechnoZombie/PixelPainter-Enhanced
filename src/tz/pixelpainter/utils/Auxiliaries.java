package tz.pixelpainter.utils;

import tz.pixelpainter.Coloring;

import javax.swing.*;
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

    public void overwriteConfirmationDialog() {
        try {

            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to overwrite?",
                    "Overwrite Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                fileManager.saveFileLogic();// User chose "Yes"
                JOptionPane.showMessageDialog(
                        null,
                        messages.imageSaved(),
                        "Image Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        messages.actionCancelled(),
                        "Action Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
