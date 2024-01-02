package sa.pixelpainter;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Movement implements KeyboardHandler {

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
        Keyboard keyboardMovement = new Keyboard(this);

        KeyboardEvent d = new KeyboardEvent();
        d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        d.setKey(KeyboardEvent.KEY_D);
        keyboardMovement.addEventListener(d);

        KeyboardEvent a = new KeyboardEvent();
        a.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        a.setKey(KeyboardEvent.KEY_A);
        keyboardMovement.addEventListener(a);

        KeyboardEvent s = new KeyboardEvent();
        s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        s.setKey(KeyboardEvent.KEY_S);
        keyboardMovement.addEventListener(s);

        KeyboardEvent w = new KeyboardEvent();
        w.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        w.setKey(KeyboardEvent.KEY_W);
        keyboardMovement.addEventListener(w);

        KeyboardEvent z = new KeyboardEvent();
        z.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        z.setKey(KeyboardEvent.KEY_Z);
        keyboardMovement.addEventListener(z);

        KeyboardEvent x = new KeyboardEvent();
        x.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        x.setKey(KeyboardEvent.KEY_X);
        keyboardMovement.addEventListener(x);

        KeyboardEvent p = new KeyboardEvent();
        p.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        p.setKey(KeyboardEvent.KEY_P);
        keyboardMovement.addEventListener(p);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W -> {

                if (cursor.getY() > 1) {
                    cursor.translate(0, -canvas.getPixelSize());
                    cursorIndexY--;
                }
            }
            case KeyboardEvent.KEY_A -> {
                if (cursor.getX() > canvas.getPixelSize()) {
                    cursor.translate(-canvas.getPixelSize(), 0);
                    cursorIndexX--;
                }
            }
            case KeyboardEvent.KEY_S -> {

                if (cursor.getY() < canvas.getHeight() - canvas.getPixelSize()) {
                    cursor.translate(0, canvas.getPixelSize());
                    cursorIndexY++;
                }
            }
            case KeyboardEvent.KEY_D -> {
                if (cursor.getX() < canvas.getWidth() - canvas.getPixelSize()) {
                    cursor.translate(canvas.getPixelSize(), 0);
                    cursorIndexX++;
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
