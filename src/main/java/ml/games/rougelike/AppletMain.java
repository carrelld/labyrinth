package ml.games.rougelike;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import asciiPanel.AsciiPanel;
import ml.games.rougelike.screens.Screen;
import ml.games.rougelike.screens.StartScreen;

public class AppletMain extends Applet implements KeyListener {

    private static final long serialVersionUID = -152329775212289908L;
    private AsciiPanel terminal;
    private Screen screen;
    
    public AppletMain() {
        super();
        terminal = new AsciiPanel();
        terminal.write("test", 1, 1);
        add(terminal);
        this.screen = new StartScreen();
        addKeyListener(this);
        this.repaint();
    }
    
    @Override
    public void init() {
        super.init();
        this.setSize(terminal.getWidth() + 20, terminal.getHeight() + 20);
    }
    
    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
    
}
