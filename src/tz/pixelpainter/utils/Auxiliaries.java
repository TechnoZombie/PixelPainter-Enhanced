package tz.pixelpainter.utils;

import tz.pixelpainter.Coloring;

import java.util.Scanner;

public class Auxiliaries {

    private Messages messages;
    private Coloring coloring;

    public Auxiliaries(Messages messages, Coloring coloring) {
        this.messages = messages;
        this.coloring = coloring;
    }

    public void confirmationScanner() {
        try (Scanner scanner = new Scanner(System.in)) {
            messages.areYouSureClear();

            char response = scanner.next().charAt(0);

            if (response == 'Y' || response == 'y') {
                coloring.clearCanvas();
            } else {
                messages.actionCancelled();
            }
        }
    }
}
