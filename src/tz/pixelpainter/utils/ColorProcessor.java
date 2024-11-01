package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Color;

public class ColorProcessor {

    /**
     * @param red   the red component of the color (0-255)
     * @param green the green component of the color (0-255)
     * @param blue  the blue component of the color (0-255)
     * @return a string representing the color name (e.g., "BLACK", "RED") if matched, or "BLACK" if the color is not recognized.
     * @deprecated This method was previously used in {@code saveFileLogic()} to convert RGB color values
     * into a color name string for saving files in .txt format.
     * <p>
     * This functionality has been replaced by {@code decodeColor()}, which performs color conversion
     * with enhanced flexibility.
     * <p>
     * The method returns "BLACK" for any color values that do not match predefined colors.
     */
    @Deprecated
    public String encodeColor(int red, int green, int blue) {
        String colorString = red + ", " + green + ", " + blue;

        return switch (colorString) {
            case "0, 0, 0" -> "BLACK";
            case "255, 0, 0" -> "RED";
            case "0, 255, 0" -> "GREEN";
            case "0, 0, 255" -> "BLUE";
            case "255, 255, 255" -> "WHITE";
            case "192, 192, 192" -> "LIGHT_GRAY";
            case "128, 128, 128" -> "GRAY";
            case "64, 64, 64" -> "DARK_GRAY";
            case "0, 255, 255" -> "CYAN";
            case "255, 0, 255" -> "MAGENTA";
            case "255, 255, 0" -> "YELLOW";
            case "255, 175, 175" -> "PINK";
            case "255, 200, 0" -> "ORANGE";
            default -> "BLACK";
        };
    }

    /**
     * @param color the name of the color as a string (e.g., "BLACK", "RED")
     * @return the corresponding {@link Color} constant if matched, or {@link Color#BLACK} if the color name is unknown.
     * @deprecated This method was used in {@code processRectangleLine()} as part of {@code loadFile()}
     * to convert color names from a text file to {@link Color} objects.
     * <p>
     * This functionality has been replaced by {@code customColor()}, which extracts colors using
     * {@code coordinateExtractor()} for a more flexible color handling approach.
     * <p>
     * The method now returns a default color, {@link Color#BLACK}, for unrecognized inputs.
     */
    @Deprecated
    public Color colorTranslator(String color) {

        return switch (color) {
            case "BLACK" -> Color.BLACK;
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            case "WHITE" -> Color.WHITE;
            case "LIGHT_GRAY" -> Color.LIGHT_GRAY;
            case "GRAY" -> Color.GRAY;
            case "DARK_GRAY" -> Color.DARK_GRAY;
            case "CYAN" -> Color.CYAN;
            case "MAGENTA" -> Color.MAGENTA;
            case "YELLOW" -> Color.YELLOW;
            case "PINK" -> Color.PINK;
            case "ORANGE" -> Color.ORANGE;
            default -> Color.BLACK;
        };
    }

    /**
     * Converts a {@code Color} object from the simplegraphz color model to a {@link java.awt.Color}
     * object for use in Java AWT graphics.
     *
     * @param squareColor the {@code Color} object from simplegraphz that needs to be converted
     * @return a {@link java.awt.Color} object with the same RGB values as {@code squareColor}
     */
    public java.awt.Color decodeColor(Color squareColor) {
        int r = squareColor.getRed();
        int g = squareColor.getGreen();
        int b = squareColor.getBlue();

        return new java.awt.Color(r, g, b);
    }
}
