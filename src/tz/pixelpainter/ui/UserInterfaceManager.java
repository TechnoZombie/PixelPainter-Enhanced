package tz.pixelpainter.ui;

import lombok.Getter;
import org.technozombie.simplegraphz.graphics.Canvas;
import tz.pixelpainter.utils.FileManager;
import tz.pixelpainter.utils.MenuConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UserInterfaceManager class is responsible for setting up and managing
 * the user interface of the PixelPainter application. This includes configuring
 * the application icon and title, creating menus, and handling menu actions.
 */
public class UserInterfaceManager {

    // Dependencies for file management and confirmation dialogs
    private final FileManager fileManager;
    private final ConfirmationDialogs confirmationDialogs;

    // The singleton canvas instance used for UI rendering
    private final Canvas canvas;

    /**
     * Constructs a UserInterfaceManager with the specified FileManager and
     * ConfirmationDialogs instances.
     *
     * @param fileManager          the FileManager instance for file operations
     * @param confirmationDialogs   the ConfirmationDialogs instance for dialog management
     */
    public UserInterfaceManager(FileManager fileManager, ConfirmationDialogs confirmationDialogs) {
        this.fileManager = fileManager;
        this.confirmationDialogs = confirmationDialogs;
        this.canvas = Canvas.getInstance();
        initializeUI();
    }

    /**
     * Initializes the user interface by setting up the application icon/title
     * and creating the menus.
     */
    private void initializeUI() {
        setupApplicationIconAndTitle();
        createMenus();
    }

    /**
     * Sets up the application icon and title on the canvas.
     */
    private void setupApplicationIconAndTitle() {
        canvas.setAppIcon("resources/FlatPixel.png");
        canvas.setAppTitle("PixelPainter!");
    }

    /**
     * Creates the main menus and their associated actions.
     */
    private void createMenus() {
        addMenu(MenuConstants.FILE,
                new MenuItemAction(MenuConstants.EXIT, e -> System.exit(0))); // Exit the application

        addMenu(MenuConstants.EDIT,
                new MenuItemAction(MenuConstants.CLEAR_CANVAS, e -> confirmationDialogs.clearConfirmationDialog()),
                new MenuItemAction(MenuConstants.CREATE_CUSTOM_COLOR, e -> confirmationDialogs.promptForColor()),
                new MenuItemAction(MenuConstants.COLOR_SELECTOR, e -> confirmationDialogs.swingColorSelector()));

        addMenu(MenuConstants.MANAGER,
                new MenuItemAction(MenuConstants.LOAD_FILE, e -> confirmationDialogs.loadConfirmationDialog()),
                new MenuItemAction(MenuConstants.SAVE_FILE, e -> fileManager.saveFile()),
                new MenuItemAction(MenuConstants.EXPORT_PNG, e -> fileManager.exportToPng("resources/savedImage.png")),
                new MenuItemAction(MenuConstants.TAKE_SCREENSHOT, e -> canvas.saveToDisk("resources/screenshot.png")));

        addMenu(MenuConstants.HELP,
                new MenuItemAction(MenuConstants.KEYBINDS, e -> confirmationDialogs.showKeyBinds()),
                new MenuItemAction("[DEV] Show image info", e -> fileManager.getInfo()));
    }

    /**
     * Adds a menu to the canvas with the specified name and menu items.
     *
     * @param menuName the name of the menu to add
     * @param items    the menu items to add to the menu
     */
    private void addMenu(String menuName, MenuItemAction... items) {
        canvas.addMenu(menuName);
        for (MenuItemAction item : items) {
            canvas.addItemToMenu(menuName, item.getName(), item); // Use item directly as ActionListener
        }
    }

    /**
     * A static inner class representing a menu item action.
     * This class implements ActionListener to handle action events.
     */
    private static class MenuItemAction implements ActionListener {
        @Getter
        private final String name; // The name of the menu item
        private final ActionListener action; // The action to perform when the menu item is selected

        /**
         * Constructs a MenuItemAction with the specified name and action.
         *
         * @param name   the name of the menu item
         * @param action the ActionListener to handle the action
         */
        public MenuItemAction(String name, ActionListener action) {
            this.name = name;
            this.action = action;
        }

        /**
         * Handles the action event by delegating it to the specified ActionListener.
         *
         * @param e the ActionEvent triggered by the menu item selection
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            action.actionPerformed(e); // Delegate the action to the provided ActionListener
        }
    }
}
