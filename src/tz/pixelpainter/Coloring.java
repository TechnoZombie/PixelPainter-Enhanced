package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class Coloring {

    private final Movement movement;
    private final Cursor cursor;
    private final Canvas canvas;
    Color chosenColor = Color.BLACK;
    Color gridColor = Color.BLACK;
    int cursorSquareX;
    int cursorSquareY;

   /* private boolean dragging = false;

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
        System.out.println("Drag painting is: " + dragging);
    }*/

    public Coloring(Movement movement, Cursor cursor, Canvas canvas) {
        this.movement = movement;
        this.cursor = cursor;
        this.canvas = canvas;
    }

    public void paint() {
        cursorSquareX = movement.getCursorIndexX();
        cursorSquareY = movement.getCursorIndexY();

        canvas.getIndividualSquares()[cursorSquareY][cursorSquareX].setColor(chosenColor);
        canvas.getIndividualSquares()[cursorSquareY][cursorSquareX].fill();

        cursor.cursorReFill();
    }

    public void erase() {
        cursorSquareX = movement.getCursorIndexX();
        cursorSquareY = movement.getCursorIndexY();

        if (canvas.getIndividualSquares()[cursorSquareY][cursorSquareX].isFilled()) {
            canvas.getIndividualSquares()[cursorSquareY][cursorSquareX].delete();
            canvas.getIndividualSquares()[cursorSquareY][cursorSquareX].setColor(gridColor);
            canvas.getIndividualSquares()[cursorSquareY][cursorSquareX].draw();
        }
        cursor.cursorReFill();
    }

    public void clearCanvas() {
        int canvasWidth = canvas.getNumberOfColumns();
        int canvasHeight = canvas.getNumVerticalLines();

        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                canvas.getIndividualSquares()[i][j].delete();
                canvas.getIndividualSquares()[i][j].setColor(gridColor);
                canvas.getIndividualSquares()[i][j].draw();
            }
        }
        cursor.cursorReFill();
    }

    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
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



