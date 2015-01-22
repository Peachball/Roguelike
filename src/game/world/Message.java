package game.world;

import java.awt.Color;

public class Message {

    public String message;
    public Color foreground;
    public Color background;

    public Message(String message, Color text, Color background) {
        this.message = message;
        this.foreground = text;
        this.background = background;
    }

    public Message(String message, Color text) {
        this(message, text, Color.BLACK);
    }
}
