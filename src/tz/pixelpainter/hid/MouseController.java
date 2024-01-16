package tz.pixelpainter.hid;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

import tz.pixelpainter.Coloring;
import tz.pixelpainter.Cursor;

import java.awt.event.MouseMotionListener;


public class MouseController implements MouseHandler, MouseMotionListener {

    private Mouse mouse;
    private Cursor cursor;
    private Coloring coloring;

    public MouseController(Cursor cursor, Coloring coloring) {
        this.coloring = coloring;
        this.cursor = cursor;
        int cursorX = cursor.getX();
        int cursorY = cursor.getY();

        this.mouse = new Mouse(this);

       // MouseEvent event = new MouseEvent(cursorX,cursorY, MouseEventType.MOUSE_CLICKED);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        switch (mouseEvent.getEventType()) {
            case MOUSE_CLICKED:
                coloring.paint();
                break;
        }
    }


    @Override
    public void mouseMoved(MouseEvent mouseEvent) {


    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }
}
