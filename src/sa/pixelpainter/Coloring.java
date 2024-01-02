package sa.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.ArrayList;
import java.util.List;

public class Coloring implements KeyboardHandler {

    private final Movement movement;
    private final Cursor cursor;

    private final List<Rectangle> paintedList = new ArrayList<>();

    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
    }

    Color chosenColor = Color.BLACK;
    Color gridColor = Color.BLACK;
    int cursorSquareX;
    int cursorSquareY;

    public Coloring(Movement movement, Cursor cursor) {
        this.movement = movement;
        this.cursor = cursor;
        Keyboard keyboardColoring = new Keyboard(this);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);
        keyboardColoring.addEventListener(space);

        KeyboardEvent space2 = new KeyboardEvent();
        space2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        space2.setKey(KeyboardEvent.KEY_SPACE);
        keyboardColoring.addEventListener(space2);

        KeyboardEvent e = new KeyboardEvent();
        e.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        e.setKey(KeyboardEvent.KEY_E);
        keyboardColoring.addEventListener(e);

        KeyboardEvent one = new KeyboardEvent();
        one.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        one.setKey(KeyboardEvent.KEY_1);
        keyboardColoring.addEventListener(one);

        KeyboardEvent two = new KeyboardEvent();
        two.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        two.setKey(KeyboardEvent.KEY_2);
        keyboardColoring.addEventListener(two);

        KeyboardEvent three = new KeyboardEvent();
        three.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        three.setKey(KeyboardEvent.KEY_3);
        keyboardColoring.addEventListener(three);

        KeyboardEvent four = new KeyboardEvent();
        four.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        four.setKey(KeyboardEvent.KEY_4);
        keyboardColoring.addEventListener(four);

        KeyboardEvent five = new KeyboardEvent();
        five.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        five.setKey(KeyboardEvent.KEY_5);
        keyboardColoring.addEventListener(five);

        KeyboardEvent six = new KeyboardEvent();
        six.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        six.setKey(KeyboardEvent.KEY_6);
        keyboardColoring.addEventListener(six);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE -> {

                cursorSquareX = movement.getCursorIndexX();
                cursorSquareY = movement.getCursorIndexY();

                Canvas.individualSquares[cursorSquareY][cursorSquareX].setColor(chosenColor);
                Canvas.individualSquares[cursorSquareY][cursorSquareX].fill();

                cursor.cursorFill();

            }

            case KeyboardEvent.KEY_E -> {
                cursorSquareX = movement.getCursorIndexX();
                cursorSquareY = movement.getCursorIndexY();

                if (Canvas.individualSquares[cursorSquareY][cursorSquareX].isFilled()) {
                    Canvas.individualSquares[cursorSquareY][cursorSquareX].delete();
                    Canvas.individualSquares[cursorSquareY][cursorSquareX].setColor(gridColor);
                    Canvas.individualSquares[cursorSquareY][cursorSquareX].draw();


cursor.cursorFill();
                }

            }

            case KeyboardEvent.KEY_1 -> {
                setChosenColor(Color.BLACK);
            }

            case KeyboardEvent.KEY_2 -> {
                setChosenColor(Color.RED);
            }

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}

