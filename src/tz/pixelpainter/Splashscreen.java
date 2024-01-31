package tz.pixelpainter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import tz.pixelpainter.utils.Generators;

public class Splashscreen {
    Rectangle splashscreenBackground;

    public Splashscreen() {
    }

    public void load() {
        Generators generators = new Generators(800, 420, 20);
        generators.wallpaperGenerator();

        String welcome = "Welcome to PixelPainter!";
        String pressToStartText = "Press SPACE to start!";
        splashscreenBackground = new Rectangle(1, 1, 600, 400);
        splashscreenBackground.setColor(Color.BLACK);
        splashscreenBackground.draw();

        Text welcomeText = new Text(300, 100, welcome);
        welcomeText.setColor(Color.ORANGE);
        welcomeText.draw();
        welcomeText.grow(200, 33);

        //BEST TEXT SIZE IS IN 6:1 PROPORTION!

        Text pressToStart = new Text(270, 250, pressToStartText);
        pressToStart.setColor(Color.BLUE);
        pressToStart.draw();
        ;
        pressToStart.grow(80, 13);
    }

}
