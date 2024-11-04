package tz.pixelpainter.movement;

import lombok.Getter;
import tz.pixelpainter.ui.Whiteboard;

/**
 * The WhiteboardController class manages cursor movement within the whiteboard.
 * It handles all logic related to moving the cursor and ensuring it's within bounds.
 */
@Getter
public class WhiteboardController {

    // The cursor being controlled
    private final Cursor cursor;

    // The whiteboard that defines the movement boundaries for the cursor
    private final Whiteboard whiteboard;

    // The current X index of the cursor within the whiteboard
    private int cursorIndexX = 0;

    // The current Y index of the cursor within the whiteboard
    private int cursorIndexY = 0;

    /**
     * Constructs a WhiteboardController instance with the specified cursor and whiteboard.
     *
     * @param cursor The Cursor object representing the current position of the cursor.
     * @param whiteboard The Whiteboard object that defines the boundaries for cursor movement.
     */
    public WhiteboardController(Cursor cursor, Whiteboard whiteboard) {
        this.cursor = cursor;
        this.whiteboard = whiteboard;
    }

    /**
     * Moves the cursor in the specified direction if it is within the grid bounds.
     * Updates the cursor's position and index accordingly.
     *
     * @param direction The direction to move the cursor: UP, LEFT, DOWN, RIGHT.
     */
    protected void moveCursor(Direction direction) {
        int pixelSize = whiteboard.getPixelSize();

        // Determine translation values based on direction
        int deltaX = getDeltaX(direction, pixelSize);
        int deltaY = getDeltaY(direction, pixelSize);

        // Check if the movement is within bounds and perform it
        if (isMoveWithinBounds(direction)) {
            cursor.translate(deltaX, deltaY);
            updateCursorIndex(direction);
        }
    }

    /**
     * Calculates the horizontal translation value based on the specified direction.
     *
     * @param direction The movement direction.
     * @param pixelSize The size of each pixel.
     * @return The x-translation value.
     */
    private int getDeltaX(Direction direction, int pixelSize) {
        return direction == Direction.LEFT ? -pixelSize : direction == Direction.RIGHT ? pixelSize : 0;
    }

    /**
     * Calculates the vertical translation value based on the specified direction.
     *
     * @param direction The movement direction.
     * @param pixelSize The size of each pixel.
     * @return The y-translation value.
     */
    private int getDeltaY(Direction direction, int pixelSize) {
        return direction == Direction.UP ? -pixelSize : direction == Direction.DOWN ? pixelSize : 0;
    }

    /**
     * Checks if the cursor movement in the specified direction is within the grid bounds.
     *
     * @param direction The movement direction.
     * @return true if movement is within bounds, false otherwise.
     */
    private boolean isMoveWithinBounds(Direction direction) {
        int pixelSize = whiteboard.getPixelSize();
        int cursorX = cursor.getX();
        int cursorY = cursor.getY();

        return switch (direction) {
            case UP -> cursorY > 10;
            case LEFT -> cursorX > pixelSize;
            case DOWN -> cursorY < whiteboard.getHeight() - pixelSize;
            case RIGHT -> cursorX < whiteboard.getWidth() - pixelSize;
            default -> false;
        };
    }

    /**
     * Updates the cursor's index based on the movement direction.
     *
     * @param direction The movement direction.
     */
    private void updateCursorIndex(Direction direction) {
        switch (direction) {
            case UP -> cursorIndexY--;
            case DOWN -> cursorIndexY++;
            case LEFT -> cursorIndexX--;
            case RIGHT -> cursorIndexX++;
        }
    }
}
