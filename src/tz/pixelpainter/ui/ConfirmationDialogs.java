package tz.pixelpainter.ui;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tz.pixelpainter.color.ColorProcessor;
import tz.pixelpainter.color.Coloring;
import tz.pixelpainter.utils.FileManager;
import tz.pixelpainter.utils.Messages;

import javax.swing.*;
import java.awt.*;

/**
 * The ConfirmationDialogs class manages various confirmation dialogs
 * and interactions with the user regarding actions that affect the
 * state of the PixelPainter application.
 */
@Slf4j
@Setter
public class ConfirmationDialogs {

    private final ColorProcessor colorProcessor;
    private final Messages messages;
    private final Coloring coloring;
    private FileManager fileManager;

    /**
     * Constructs a ConfirmationDialogs instance with the specified parameters.
     *
     * @param messages       the Messages instance for user notifications
     * @param coloring       the Coloring instance to manage colors
     * @param colorProcessor the ColorProcessor instance to handle color operations
     */
    public ConfirmationDialogs(Messages messages, Coloring coloring, ColorProcessor colorProcessor) {
        this.messages = messages;
        this.coloring = coloring;
        this.colorProcessor = colorProcessor;
    }

    /**
     * Displays a confirmation dialog for clearing the canvas.
     */
    public void clearConfirmationDialog() {
        if (showConfirmationDialog("Are you sure?", "Clear Canvas")) {
            coloring.clearCanvas();
        }
    }

    /**
     * Displays a confirmation dialog for overwriting an existing file.
     *
     * @return true if the user confirms the overwrite; false otherwise.
     */
    public boolean overwriteConfirmationDialog() {
        if (showConfirmationDialog("Are you sure?", "Overwrite Confirmation")) {
            fileManager.performFileSave(); // User chose "Yes"
            showInfoDialog(messages.imageSaved(), "Image Saved");
            return true; // Successfully saved
        }
        return false; // User chose "No"
    }

    /**
     * Displays a confirmation dialog for loading an image.
     */
    public void loadConfirmationDialog() {
        if (showConfirmationDialog("Load Image?", "Load Confirmation")) {
            fileManager.loadFile();
        }
    }

    /**
     * Displays a confirmation dialog for exporting an image to PNG.
     */
    public void exportConfirmationDialog() {
        if (showConfirmationDialog("Export to .png?", "Export Confirmation")) {
            fileManager.exportToPng("resources/savedImage.png");
        }
    }

    /**
     * Displays the key bindings to the user.
     */
    public void showKeyBinds() {
        showInfoDialog(messages.keyBindsMessage(), "Keybinds");
    }

    /**
     * Prompts the user to enter RGB values for a custom color.
     */
    public void promptForColor() {
        while (true) {
            JPanel panel = createColorInputPanel();
            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Enter RGB Values", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION && validateAndSetColor(panel)) {
                break;
            } else if (result == JOptionPane.CANCEL_OPTION) {
                break;
            }
        }
    }

    /**
     * Displays a Swing color selector dialog for selecting a color.
     */
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

    /**
     * Shows a confirmation dialog with the given message and title.
     *
     * @param message the message to display in the dialog
     * @param title   the title of the dialog
     * @return true if the user selects "Yes"; false otherwise
     */
    private boolean showConfirmationDialog(String message, String title) {
        try {
            int response = JOptionPane.showConfirmDialog(null, message, title,
                    JOptionPane.YES_NO_OPTION);
            return response == JOptionPane.YES_OPTION;
        } catch (Exception e) {
            log.error("Error during confirmation dialog: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Shows an information dialog with the given message and title.
     *
     * @param message the message to display in the dialog
     * @param title   the title of the dialog
     */
    private void showInfoDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Validates RGB values from the provided panel and sets the custom color.
     *
     * @param panel the panel containing the color input fields
     * @return true if the color was successfully set; false otherwise
     */
    private boolean validateAndSetColor(JPanel panel) {
        JTextField redField = (JTextField) panel.getComponent(1);   // Red input field
        JTextField greenField = (JTextField) panel.getComponent(3); // Green input field
        JTextField blueField = (JTextField) panel.getComponent(5);  // Blue input field

        try {
            int red = parseColorValue(redField.getText().trim(), "Red");
            int green = parseColorValue(greenField.getText().trim(), "Green");
            int blue = parseColorValue(blueField.getText().trim(), "Blue");

            coloring.customColor(red, green, blue);
            log.debug("Using custom color: R=" + red + " G=" + green + " B=" + blue);
            return true;

        } catch (NumberFormatException e) {
            showInfoDialog(e.getMessage(), "Invalid Input");
            return false;
        }
    }

    /**
     * Parses a color value from a string and ensures it is within the valid range (0-255).
     *
     * @param input     the input string to parse
     * @param colorName the name of the color for error messaging
     * @return the parsed integer value
     * @throws NumberFormatException if the input is invalid
     */
    private int parseColorValue(String input, String colorName) throws NumberFormatException {
        int value = Integer.parseInt(input);
        if (value < 0 || value > 255) {
            throw new NumberFormatException(colorName + " value must be between 0 and 255.");
        }
        return value;
    }

    /**
     * Creates a JPanel for RGB color input.
     *
     * @return the JPanel containing color input fields
     */
    private JPanel createColorInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Red (0-255):"));
        panel.add(new JTextField());
        panel.add(new JLabel("Green (0-255):"));
        panel.add(new JTextField());
        panel.add(new JLabel("Blue (0-255):"));
        panel.add(new JTextField());
        return panel;
    }
}
