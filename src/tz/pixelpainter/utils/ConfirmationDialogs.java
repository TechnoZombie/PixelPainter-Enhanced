package tz.pixelpainter.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tz.pixelpainter.Coloring;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class ConfirmationDialogs {

    private final ColorProcessor colorProcessor;
    private Messages messages;
    private Coloring coloring;

    @Setter
    private FileManager fileManager;

    public ConfirmationDialogs(Messages messages, Coloring coloring, ColorProcessor colorProcessor) {
        this.messages = messages;
        this.coloring = coloring;
        this.colorProcessor = colorProcessor;
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
                fileManager.saveFileLogic(); // User chose "Yes"
                JOptionPane.showMessageDialog(
                        null,
                        messages.imageSaved(),
                        "Image Saved",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return true; // Successfully saved
            } else if (response == JOptionPane.NO_OPTION) {
                return false; // User chose "No"
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

    /*
     * Having swingColorSelector() makes this redundant. To be removed
     * Might be useful for button-based selections in the future.
     */
    public void promptForColor() {
        while (true) {
            JPanel panel = new JPanel(new GridLayout(3, 2));

            JTextField redField = new JTextField();
            JTextField greenField = new JTextField();
            JTextField blueField = new JTextField();

            panel.add(new JLabel("Red (0-255):"));
            panel.add(redField);
            panel.add(new JLabel("Green (0-255):"));
            panel.add(greenField);
            panel.add(new JLabel("Blue (0-255):"));
            panel.add(blueField);

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Enter RGB Values",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                if (validateAndSetColor(redField, greenField, blueField)) {
                    break;
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                break;
            }
        }
    }

    private boolean validateAndSetColor(JTextField redField, JTextField greenField, JTextField blueField) {
        try {
            int red = parseColorValue(redField.getText().trim(), "Red");
            int green = parseColorValue(greenField.getText().trim(), "Green");
            int blue = parseColorValue(blueField.getText().trim(), "Blue");

            coloring.customColor(red, green, blue);
            log.debug("Using custom color: " + "R=" + red + " G=" + green + " B=" + blue);
            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private int parseColorValue(String input, String colorName) throws NumberFormatException {
        int value = Integer.parseInt(input);
        if (value < 0 || value > 255) {
            throw new NumberFormatException(colorName + " value must be between 0 and 255.");
        }
        return value;
    }

    /*
     * Hardcoded kind of color selector. It sucks. Using swing's color chooser is better
     */
    public void showColorPanel() {
        JButton yellowButton = createColorButton(Color.YELLOW);
        JButton redButton = createColorButton(Color.RED);
        JButton blueButton = createColorButton(Color.BLUE);

        yellowButton.addActionListener(e -> log.info("Yellow selected"));
        redButton.addActionListener(e -> log.info("Red selected"));
        blueButton.addActionListener(e -> log.info("Blue selected"));

        JPanel panel = new JPanel();
        panel.add(yellowButton);
        panel.add(redButton);
        panel.add(blueButton);

        JOptionPane.showMessageDialog(
                null,
                panel,
                "Select a Color",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private JButton createColorButton(Color color) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    public void swingColorSelector() {
        Color initialColor = colorProcessor.decodeColor(coloring.getChosenColor());
        log.info("Current color: " + initialColor);

        Color selectedColor = JColorChooser.showDialog(null, "Select a Color", initialColor);

        if (selectedColor != null) {
            int r = selectedColor.getRed();
            int g = selectedColor.getGreen();
            int b = selectedColor.getBlue();
            coloring.customColor(r, g, b);
            log.info("Color selected: " + selectedColor);
        } else {
            log.info("Cancelled. Keeping color: " + initialColor);
        }
    }
}

