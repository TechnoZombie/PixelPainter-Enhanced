package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public class Movement {

    private final Cursor cursor;
    private final Canvas canvas;

    public int getCursorIndexX() {
        return cursorIndexX;
    }

    public int getCursorIndexY() {
        return cursorIndexY;
    }

    private int cursorIndexX = 0;
    private int cursorIndexY = 0;

    public Movement(Cursor cursor, Canvas canvas) {
        this.cursor = cursor;
        this.canvas = canvas;
    }

    public void moveUp() {
        if (cursor.getY() > 1) {
            cursor.translate(0, -canvas.getPixelSize());
            cursorIndexY--;
        }
    }

    public void moveLeft() {
        if (cursor.getX() > canvas.getPixelSize()) {
            cursor.translate(-canvas.getPixelSize(), 0);
            cursorIndexX--;
        }
    }

    public void moveDown() {
        if (cursor.getY() < canvas.getHeight() - canvas.getPixelSize()) {
            cursor.translate(0, canvas.getPixelSize());
            cursorIndexY++;
        }
    }

    public void moveRight() {
        if (cursor.getX() < canvas.getWidth() - canvas.getPixelSize()) {
            cursor.translate(canvas.getPixelSize(), 0);
            cursorIndexX++;
        }
    }
}
