package tz.pixelpainter.hid;

import org.technozombie.simplegraphz.keyboard.Keyboard;
import org.technozombie.simplegraphz.keyboard.KeyboardEvent;
import org.technozombie.simplegraphz.keyboard.KeyboardEventType;
import org.technozombie.simplegraphz.keyboard.KeyboardHandler;
import tz.pixelpainter.movement.Movement;
import tz.pixelpainter.color.Coloring;
import tz.pixelpainter.ui.ConfirmationDialogs;
import tz.pixelpainter.utils.FileManager;

/**
 * The KeyboardController class handles keyboard input events for the PixelPainter application.
 * It maps specific keys to actions such as movement, coloring, and file management.
 */
public class KeyboardController implements KeyboardHandler {

    private final Movement movement;
    private final Coloring coloring;
    private final FileManager fileManager;
    private final ConfirmationDialogs confirmationDialogs;

    /**
     * Constructs a KeyboardController instance with the provided dependencies.
     *
     * @param movement            the Movement instance for controlling movements
     * @param coloring            the Coloring instance for handling colors
     * @param fileManager         the FileManager instance for managing files
     * @param confirmationDialogs  the ConfirmationDialogs instance for handling user confirmations
     */
    public KeyboardController(Movement movement, Coloring coloring, FileManager fileManager, ConfirmationDialogs confirmationDialogs) {
        this.movement = movement;
        this.coloring = coloring;
        this.fileManager = fileManager;
        this.confirmationDialogs = confirmationDialogs;

        initializeKeyboard();
    }

    /**
     * Initializes the keyboard and sets up key event listeners.
     */
    private void initializeKeyboard() {
        Keyboard keyboard = new Keyboard(this);
        char[] keys = {
                'D', 'A', 'S', 'W', 'C', 'Z', 'X', 'O', 'P', ' ',
                'E', 'Y', 'N', 'I', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
        };

        for (char key : keys) {
            addKeyListener(keyboard, key);
        }

        addKeyListener(keyboard, (char) KeyboardEvent.KEY_UP);
    }

    /**
     * Adds a key event listener for the specified key.
     *
     * @param keyboard the keyboard instance
     * @param key      the key to add the listener for
     */
    private void addKeyListener(Keyboard keyboard, char key) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        event.setKey(key);
        keyboard.addEventListener(event);
    }

    /**
     * Handles the key press events and performs the corresponding action.
     *
     * @param keyboardEvent the keyboard event containing the key pressed
     */
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
            case KeyboardEvent.KEY_Z -> fileManager.saveFile(); // TODO: REMOVE
            case KeyboardEvent.KEY_X -> confirmationDialogs.loadConfirmationDialog(); // TODO: REMOVE
        }
    }

    /**
     * Handles key release events. Currently does not perform any action.
     *
     * @param keyboardEvent the keyboard event containing the key released
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        // No action needed on key release for now
    }
}
