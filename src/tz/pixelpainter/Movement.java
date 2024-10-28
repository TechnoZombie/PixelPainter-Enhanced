package tz.pixelpainter;

public class Movement {

    private final Cursor cursor;
    private final Whiteboard whiteboard;

    public int getCursorIndexX() {
        return cursorIndexX;
    }

    public int getCursorIndexY() {
        return cursorIndexY;
    }

    private int cursorIndexX = 0;
    private int cursorIndexY = 0;

    public Movement(Cursor cursor, Whiteboard whiteboard) {
        this.cursor = cursor;
        this.whiteboard = whiteboard;
    }

    public void moveUp() {
        if (cursor.getY() > 10) {
            cursor.translate(0, -whiteboard.getPixelSize());
            cursorIndexY--;
        }
    }

    public void moveLeft() {
        if (cursor.getX() > whiteboard.getPixelSize()) {
            cursor.translate(-whiteboard.getPixelSize(), 0);
            cursorIndexX--;
        }
    }

    public void moveDown() {
        if (cursor.getY() < whiteboard.getHeight() - whiteboard.getPixelSize()) {
            cursor.translate(0, whiteboard.getPixelSize());
            cursorIndexY++;
        }
    }

    public void moveRight() {
        if (cursor.getX() < whiteboard.getWidth() - whiteboard.getPixelSize()) {
            cursor.translate(whiteboard.getPixelSize(), 0);
            cursorIndexX++;
        }
    }
}
