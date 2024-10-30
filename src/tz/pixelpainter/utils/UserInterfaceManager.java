package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Canvas;

public class UserInterfaceManager {

    FileManager fileManager;
    ConfirmationDialogs confirmationDialogs;
    Canvas canvas = Canvas.getInstance();

    public UserInterfaceManager(FileManager fileManager, ConfirmationDialogs confirmationDialogs) {
        this.fileManager = fileManager;
        this.confirmationDialogs = confirmationDialogs;
    }

    public void generateUserInterface() {
        canvas.addMenu(MenuConstants.FILE);
        canvas.addItemToMenu(MenuConstants.FILE, MenuConstants.EXIT, e -> System.exit(0)); // Exit the program when "Exit" is clicked);

        canvas.addMenu(MenuConstants.EDIT);
        canvas.addItemToMenu(MenuConstants.EDIT, MenuConstants.CLEAR_CANVAS, e -> confirmationDialogs.clearConfirmationDialog());

        canvas.addMenu(MenuConstants.MANAGER);
        canvas.addMenuSeparator(MenuConstants.MANAGER);
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.LOAD_FILE, e -> confirmationDialogs.loadConfirmationDialog());
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.SAVE_FIE, e -> fileManager.saveFile());
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.EXPORT_PNG, e -> fileManager.exportToPng("resources/savedImageFromMenu.png"));
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.TAKE_SCREENSHOT, e -> canvas.saveToDisk("resources/screenshot.png"));

        canvas.addMenu(MenuConstants.HELP);
        canvas.addItemToMenu(MenuConstants.HELP, MenuConstants.KEYBINDS, e -> confirmationDialogs.showKeyBinds());
    }
}
