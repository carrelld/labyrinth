package ml.games.rougelike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import ml.games.rougelike.screens.Screen;
import ml.games.rougelike.screens.StartScreen;

public class ApplicationMain extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1141359254854871734L;
    private AsciiPanel terminal;
    private Screen screen;
    
    public ApplicationMain() {
        super();
        terminal = new AsciiPanel();
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }
    
    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
    
    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
