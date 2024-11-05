package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Canvas;

public class UserInterfaceManager {

    private final FileManager fileManager;
    private final ConfirmationDialogs confirmationDialogs;

    private final Canvas canvas = Canvas.getInstance();

    public UserInterfaceManager(FileManager fileManager, ConfirmationDialogs confirmationDialogs) {
        this.fileManager = fileManager;
        this.confirmationDialogs = confirmationDialogs;
    }

    public void generateUserInterface() {
        canvas.setAppIcon(Constants.APP_ICON);
        canvas.setAppTitle(Constants.APP_TITLE);

        canvas.addMenu(Constants.FILE);
        canvas.addItemToMenu(Constants.FILE, Constants.EXIT, e -> System.exit(0)); // Exit the program when "Exit" is clicked);

        canvas.addMenu(Constants.EDIT);
        canvas.addItemToMenu(Constants.EDIT, Constants.CLEAR_CANVAS, e -> confirmationDialogs.clearConfirmationDialog());
        canvas.addItemToMenu(Constants.EDIT, Constants.CREATE_CUSTOM_COLOR, e -> confirmationDialogs.promptForColor());
        canvas.addItemToMenu(Constants.EDIT, Constants.COLOR_SELECTOR, e -> confirmationDialogs.swingColorSelector());

        canvas.addMenu(Constants.MANAGER);
        canvas.addMenuSeparator(Constants.MANAGER);
        canvas.addItemToMenu(Constants.MANAGER, Constants.LOAD_FILE, e -> confirmationDialogs.loadConfirmationDialog());
        canvas.addItemToMenu(Constants.MANAGER, Constants.SAVE_FIE, e -> fileManager.saveFile());
        canvas.addItemToMenu(Constants.MANAGER, Constants.EXPORT_PNG, e -> fileManager.exportToPng("resources/savedImage.png"));
        canvas.addItemToMenu(Constants.MANAGER, Constants.TAKE_SCREENSHOT, e -> canvas.saveToDisk("resources/screenshot.png"));

        canvas.addMenu(Constants.HELP);
        canvas.addItemToMenu(Constants.HELP, Constants.KEYBINDS, e -> confirmationDialogs.showKeyBinds());
        canvas.addItemToMenu(Constants.HELP, "[DEV] Show image info", e -> fileManager.getInfo());
    }
}
