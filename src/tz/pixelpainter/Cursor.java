package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cursor {
    Rectangle cursor;

    public Cursor(int cursorSize) {
        cursor = new Rectangle(1, 1, cursorSize, cursorSize);
    }

    public void cursorFill(){
        cursor.setColor(Color.GRAY);
        cursor.fill();
    }
    public void cursorReFill(){
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


}
