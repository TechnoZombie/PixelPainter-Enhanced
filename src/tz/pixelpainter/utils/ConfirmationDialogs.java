package tz.pixelpainter.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tz.pixelpainter.Coloring;

import javax.swing.*;

@Slf4j
public class ConfirmationDialogs {

    private Messages messages;
    private Coloring coloring;

    @Setter
    private FileManager fileManager;

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
            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }

    public boolean overwriteConfirmationDialog() {
        try {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure?",
                    "Overwrite Confirmation", JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                fileManager.saveFileLogic();// User chose "Yes"
                JOptionPane.showMessageDialog(
                        null,
                        messages.imageSaved(),
                        "Image Saved",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
        return false;
    }

    public void loadConfirmationDialog() {
        try {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Load Image?",
                    "Load Confirmation", JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                fileManager.loadFile();
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }

    public void exportConfirmationDialog() {
        try {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Export to .png?",
                    "Export Confirmation", JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                fileManager.exportToPng("resources/savedImage.png");
                JOptionPane.showMessageDialog(
                        null,
                        messages.pngExported(),
                        "Export confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }

    public void showKeyBinds() {
        JOptionPane.showMessageDialog(
                null,
                messages.keyBindsMessage(),
                "Keybinds",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}

