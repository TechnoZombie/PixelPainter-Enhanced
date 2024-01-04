package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class Coloring {

    private final Movement movement;
    private final Cursor cursor;
    private final Canvas canvas;
    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
    }

    Color chosenColor = Color.BLACK;
    Color gridColor = Color.BLACK;
    int cursorSquareX;
    int cursorSquareY;

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

    public void colorYellow() {
        setChosenColor(Color.YELLOW);
    }


}



