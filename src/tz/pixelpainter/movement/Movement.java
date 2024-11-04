package tz.pixelpainter.movement;

import lombok.Getter;
import tz.pixelpainter.ui.Whiteboard;

/**
 * The Movement class is responsible for managing the movement of the cursor
 * on the whiteboard. It serves as an abstraction layer that delegates cursor
 * movement commands to the WhiteboardController.
 */
@Getter
public class Movement {

    // The cursor being controlled
    private final Cursor cursor;

    // The controller that handles the logic for moving the cursor within the whiteboard
    private final WhiteboardController whiteboardController;

    /**
     * Constructs a Movement instance with the specified cursor and whiteboard.
     *
     * @param cursor The Cursor object representing the current position of the cursor.
     * @param whiteboard The Whiteboard object that defines the boundaries for cursor movement.
     */
    public Movement(Cursor cursor, Whiteboard whiteboard) {
        this.cursor = cursor;
        this.whiteboardController = new WhiteboardController(cursor, whiteboard);
    }

    /**
     * Moves the cursor one step upwards if within bounds.
     * This method delegates the movement logic to the WhiteboardController.
     */
    public void moveUp() {
        whiteboardController.moveCursor(Direction.UP);
    }

    /**
     * Moves the cursor one step to the left if within bounds.
     * This method delegates the movement logic to the WhiteboardController.
     */
    public void moveLeft() {
        whiteboardController.moveCursor(Direction.LEFT);
    }

    /**
     * Moves the cursor one step downwards if within bounds.
     * This method delegates the movement logic to the WhiteboardController.
     */
    public void moveDown() {
        whiteboardController.moveCursor(Direction.DOWN);
    }

    /**
     * Moves the cursor one step to the right if within bounds.
     * This method delegates the movement logic to the WhiteboardController.
     */
    public void moveRight() {
        whiteboardController.moveCursor(Direction.RIGHT);
    }

    /**
     * Retrieves the current X index of the cursor within the whiteboard.
     *
     * @return The X index of the cursor.
     */
    public int getCursorIndexX() {
        return whiteboardController.getCursorIndexX();
    }

    /**
     * Retrieves the current Y index of the cursor within the whiteboard.
     *
     * @return The Y index of the cursor.
     */
    public int getCursorIndexY() {
        return whiteboardController.getCursorIndexY();
    }
}
