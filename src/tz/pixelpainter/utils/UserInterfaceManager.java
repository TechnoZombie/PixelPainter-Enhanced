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
        canvas.addMenu("File");
        canvas.addItemToMenu("File", "Exit", e -> System.exit(0)); // Exit the program when "Exit" is clicked);

        canvas.addMenu("Edit");
        canvas.addItemToMenu("Edit", "Clear Canvas", e -> confirmationDialogs.clearConfirmationDialog());

        canvas.addMenu("Manager");
        canvas.addMenuSeparator("Manager");
        canvas.addItemToMenu("Manager", "Load file", e -> confirmationDialogs.loadConfirmationDialog());
        canvas.addItemToMenu("Manager", "Save file", e -> fileManager.saveFile());
        canvas.addItemToMenu("Manager", "Export as PNG", e -> fileManager.exportToPng("resources/savedImageFromMenu.png"));
    }
}
