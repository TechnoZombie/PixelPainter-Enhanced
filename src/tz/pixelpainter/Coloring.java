package tz.pixelpainter;

import lombok.Getter;
import lombok.Setter;
import org.technozombie.simplegraphz.graphics.Color;

public class Coloring {

    private final Movement movement;
    private final Cursor cursor;
    private final Whiteboard whiteboard;

    @Getter
    @Setter
    private Color chosenColor = Color.BLACK;
    private final Color gridColor = Color.BLACK;
    private int cursorSquareX;
    private int cursorSquareY;

   /* private boolean dragging = false;

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
        System.out.println("Drag painting is: " + dragging);
    }*/

    public Coloring(Movement movement, Cursor cursor, Whiteboard whiteboard) {
        this.movement = movement;
        this.cursor = cursor;
        this.whiteboard = whiteboard;
    }

    public void paint() {
        cursorSquareX = movement.getCursorIndexX();
        cursorSquareY = movement.getCursorIndexY();

        whiteboard.getIndividualSquares()[cursorSquareY][cursorSquareX].setColor(chosenColor);
        whiteboard.getIndividualSquares()[cursorSquareY][cursorSquareX].fill();

        cursor.cursorReFill();
    }

    public void erase() {
        cursorSquareX = movement.getCursorIndexX();
        cursorSquareY = movement.getCursorIndexY();

        if (whiteboard.getIndividualSquares()[cursorSquareY][cursorSquareX].isFilled()) {
            whiteboard.getIndividualSquares()[cursorSquareY][cursorSquareX].delete();
            whiteboard.getIndividualSquares()[cursorSquareY][cursorSquareX].setColor(gridColor);
            whiteboard.getIndividualSquares()[cursorSquareY][cursorSquareX].draw();
        }
        cursor.cursorReFill();
    }

    public void clearCanvas() {
        int canvasWidth = whiteboard.getNumberOfColumns();
        int canvasHeight = whiteboard.getNumberOfLines();

        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                whiteboard.getIndividualSquares()[i][j].delete();
                whiteboard.getIndividualSquares()[i][j].setColor(gridColor);
                whiteboard.getIndividualSquares()[i][j].draw();
            }
        }
        cursor.cursorReFill();
    }

public Color customColor(int red, int green, int blue) {
    Color customColor = new Color(red, green, blue);
    setChosenColor(customColor);
    return customColor;
}

    public void colorBlack() {
        setChosenColor(Color.BLACK);
    }

    public void colorRed() {
        setChosenColor(Color.RED);
    }

    public void colorGreen() {
        setChosenColor(Color.GREEN);
    }

    public void colorBlue() {
        setChosenColor(Color.BLUE);
    }

    public void colorCyan() {
        setChosenColor(Color.CYAN);
    }

    public void colorYellow() {
        setChosenColor(Color.YELLOW);
    }

    public void colorOrange() {
        setChosenColor(Color.ORANGE);
    }

    public void colorPink() {
        setChosenColor(Color.PINK);
    }

    public void colorMagenta() {
        setChosenColor(Color.MAGENTA);
    }

    public void colorWhite() {
        setChosenColor(Color.WHITE);
    }

    public void colorLightGray() {
        setChosenColor(Color.LIGHT_GRAY);
    }
}



