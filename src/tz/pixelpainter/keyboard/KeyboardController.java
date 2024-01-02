package tz.pixelpainter.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import tz.pixelpainter.Movement;
import tz.pixelpainter.Coloring;

public class KeyboardController implements KeyboardHandler {

    private final Movement movement;
    private final Coloring coloring;

    public KeyboardController(Movement movement, Coloring coloring) {
        this.movement = movement;
        this.coloring = coloring;

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent d = new KeyboardEvent();
        d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        d.setKey(KeyboardEvent.KEY_D);
        keyboard.addEventListener(d);

        KeyboardEvent a = new KeyboardEvent();
        a.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        a.setKey(KeyboardEvent.KEY_A);
        keyboard.addEventListener(a);

        KeyboardEvent s = new KeyboardEvent();
        s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        s.setKey(KeyboardEvent.KEY_S);
        keyboard.addEventListener(s);

        KeyboardEvent w = new KeyboardEvent();
        w.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        w.setKey(KeyboardEvent.KEY_W);
        keyboard.addEventListener(w);

        KeyboardEvent z = new KeyboardEvent();
        z.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        z.setKey(KeyboardEvent.KEY_Z);
        keyboard.addEventListener(z);

        KeyboardEvent x = new KeyboardEvent();
        x.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        x.setKey(KeyboardEvent.KEY_X);
        keyboard.addEventListener(x);

        KeyboardEvent p = new KeyboardEvent();
        p.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        p.setKey(KeyboardEvent.KEY_P);
        keyboard.addEventListener(p);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(space);

        KeyboardEvent space2 = new KeyboardEvent();
        space2.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        space2.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(space2);

        KeyboardEvent e = new KeyboardEvent();
        e.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        e.setKey(KeyboardEvent.KEY_E);
        keyboard.addEventListener(e);

        KeyboardEvent one = new KeyboardEvent();
        one.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        one.setKey(KeyboardEvent.KEY_1);
        keyboard.addEventListener(one);

        KeyboardEvent two = new KeyboardEvent();
        two.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        two.setKey(KeyboardEvent.KEY_2);
        keyboard.addEventListener(two);

        KeyboardEvent three = new KeyboardEvent();
        three.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        three.setKey(KeyboardEvent.KEY_3);
        keyboard.addEventListener(three);

        KeyboardEvent four = new KeyboardEvent();
        four.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        four.setKey(KeyboardEvent.KEY_4);
        keyboard.addEventListener(four);

        KeyboardEvent five = new KeyboardEvent();
        five.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        five.setKey(KeyboardEvent.KEY_5);
        keyboard.addEventListener(five);

        KeyboardEvent six = new KeyboardEvent();
        six.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        six.setKey(KeyboardEvent.KEY_6);
        keyboard.addEventListener(six);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_W -> movement.moveUp();

            case KeyboardEvent.KEY_A -> movement.moveLeft();

            case KeyboardEvent.KEY_S -> movement.moveDown();

            case KeyboardEvent.KEY_D -> movement.moveRight();

            case KeyboardEvent.KEY_SPACE -> coloring.paint();

            case KeyboardEvent.KEY_E -> coloring.erase();

            case KeyboardEvent.KEY_1 -> coloring.colorBlack();

            case KeyboardEvent.KEY_2 -> coloring.colorRed();

            case KeyboardEvent.KEY_3 -> coloring.colorGreen();

            case KeyboardEvent.KEY_4 -> coloring.colorBlue();

            case KeyboardEvent.KEY_5 -> coloring.colorYellow();

            case KeyboardEvent.KEY_Z -> {
                //to save image
            }

            case KeyboardEvent.KEY_X -> {
                //to load image
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}