package tz.pixelpainter.utils;

import org.technozombie.simplegraphz.graphics.Canvas;
import tz.pixelpainter.Coloring;

public class UserInterfaceManager {

    private FileManager fileManager;
    private ConfirmationDialogs confirmationDialogs;
    private Coloring coloring;
    private Canvas canvas = Canvas.getInstance();

    public UserInterfaceManager(FileManager fileManager, ConfirmationDialogs confirmationDialogs, Coloring coloring) {
        this.fileManager = fileManager;
        this.confirmationDialogs = confirmationDialogs;
        this.coloring = coloring;
    }

    public void generateUserInterface() {
        canvas.setAppIcon("resources/FlatPixel.png");
        canvas.setAppTitle("PixelPainter!");

        canvas.addMenu(MenuConstants.FILE);
        canvas.addItemToMenu(MenuConstants.FILE, MenuConstants.EXIT, e -> System.exit(0)); // Exit the program when "Exit" is clicked);

        canvas.addMenu(MenuConstants.EDIT);
        canvas.addItemToMenu(MenuConstants.EDIT, MenuConstants.CLEAR_CANVAS, e -> confirmationDialogs.clearConfirmationDialog());
        canvas.addItemToMenu(MenuConstants.EDIT, MenuConstants.CREATE_CUSTOM_COLOR, e -> confirmationDialogs.promptForColor());

        canvas.addMenu(MenuConstants.MANAGER);
        canvas.addMenuSeparator(MenuConstants.MANAGER);
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.LOAD_FILE, e -> confirmationDialogs.loadConfirmationDialog());
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.SAVE_FIE, e -> fileManager.saveFile());
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.EXPORT_PNG, e -> fileManager.exportToPng("resources/savedImageFromMenu.png"));
        canvas.addItemToMenu(MenuConstants.MANAGER, MenuConstants.TAKE_SCREENSHOT, e -> canvas.saveToDisk("resources/screenshot.png"));

        canvas.addMenu(MenuConstants.HELP);
        canvas.addItemToMenu(MenuConstants.HELP, MenuConstants.KEYBINDS, e -> confirmationDialogs.showKeyBinds());
        canvas.addItemToMenu(MenuConstants.HELP, "[DEV] Show image info", e -> fileManager.getInfo());
    }
}
