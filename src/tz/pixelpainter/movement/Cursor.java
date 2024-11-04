package tz.pixelpainter.movement;


import org.technozombie.simplegraphz.graphics.Color;
import org.technozombie.simplegraphz.graphics.Rectangle;

public class Cursor {
    private final Rectangle cursor;

    public Cursor(int cursorSize) {
        cursor = new Rectangle(10, 10, cursorSize, cursorSize);
    }

    public void cursorFill() {
        cursor.setColor(Color.GRAY);
        cursor.fill();
    }

    public void cursorReFill() {
        cursor.delete();
        cursor.setColor(Color.GRAY);
        cursor.fill();
    }

    public int getX() {
        return cursor.getX();
    }

    public int getY() {
        return cursor.getY();
    }

    public void translate(int x, int y) {
        cursor.translate(x, y);
    }

//    public void setPosition(int x, int y) {
//        // Set the position of the cursor to the specified coordinates
//        cursor.translate(x - cursor.getX(), y - cursor.getY());
//    }
}
