package tz.pixelpainter.utils;

import tz.pixelpainter.Coloring;

import javax.swing.*;

public class ConfirmationDialogs {

    private Messages messages;
    private Coloring coloring;
    private FileManager fileManager;

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public ConfirmationDialogs(Messages messages, Coloring coloring) {
        this.messages = messages;
        this.coloring = coloring;
    }

    public void clearConfirmationDialog() {
        try {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure?",
                    "Clear Canvas",
                    JOptionPane.YES_NO_OPTION

            );

            if (response == JOptionPane.YES_OPTION) {
                coloring.clearCanvas();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean overwriteConfirmationDialog() {
        try {

            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure?",
                    "Overwrite Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                fileManager.saveFileLogic();// User chose "Yes"
                JOptionPane.showMessageDialog(
                        null,
                        messages.imageSaved(),
                        "Image Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void loadConfirmationDialog() {

        try {

            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Load Image?",
                    "Load Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                fileManager.loadFile();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportConfirmationDialog() {
        try {

            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Export to .png?",
                    "Export Confirmation", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                fileManager.exportToPng("resources/savedImage.png");
                JOptionPane.showMessageDialog(
                        null,
                        messages.pngExported(),
                        "Export confirmation",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


