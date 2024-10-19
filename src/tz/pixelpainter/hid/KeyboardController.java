package tz.pixelpainter.hid;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import tz.pixelpainter.Movement;
import tz.pixelpainter.Coloring;
import tz.pixelpainter.utils.ConfirmationDialogs;
import tz.pixelpainter.utils.FileManager;

public class KeyboardController implements KeyboardHandler {

    private final Movement movement;
    private final Coloring coloring;
    private final FileManager fileManager;
    private final ConfirmationDialogs confirmationDialogs;

    public KeyboardController(Movement movement, Coloring coloring, FileManager fileManager, ConfirmationDialogs confirmationDialogs) {
        this.movement = movement;
        this.coloring = coloring;
        this.fileManager = fileManager;
        this.confirmationDialogs = confirmationDialogs;

        Keyboard keyboard = new Keyboard(this);

        char[] keys = {'D', 'A', 'S', 'W', 'C', 'Z', 'X', 'O', 'P', ' ', 'E', 'Y', 'N', 'I', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

        for (char key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            event.setKey(key);
            keyboard.addEventListener(event);
        }

        KeyboardEvent event = new KeyboardEvent();
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        event.setKey(KeyboardEvent.KEY_UP);
        keyboard.addEventListener(event);
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

            case KeyboardEvent.KEY_C -> confirmationDialogs.exportConfirmationDialog();

            case KeyboardEvent.KEY_I -> fileManager.getInfo();

            // TODO: CHANGE ME
            case KeyboardEvent.KEY_UP -> coloring.colorLightGray();

            case KeyboardEvent.KEY_O -> confirmationDialogs.clearConfirmationDialog();

            case KeyboardEvent.KEY_1 -> coloring.colorBlack();

            case KeyboardEvent.KEY_2 -> coloring.colorRed();

            case KeyboardEvent.KEY_3 -> coloring.colorGreen();

            case KeyboardEvent.KEY_4 -> coloring.colorBlue();

            case KeyboardEvent.KEY_5 -> coloring.colorCyan();

            case KeyboardEvent.KEY_6 -> coloring.colorYellow();

            case KeyboardEvent.KEY_7 -> coloring.colorOrange();

            case KeyboardEvent.KEY_8 -> coloring.colorPink();

            case KeyboardEvent.KEY_9 -> coloring.colorMagenta();

            case KeyboardEvent.KEY_0 -> coloring.colorWhite();

            case KeyboardEvent.KEY_Z -> fileManager.saveFile();

            case KeyboardEvent.KEY_X -> confirmationDialogs.loadConfirmationDialog();

        }
        }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
   }

   /* public void toggleKey(){
        if (!active){
            active = true;
            coloring.setDragging(true);
        }
        else {
            active = false;
            coloring.setDragging(false);
        }
    } */
}