package ml.games.rougelike.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public interface Screen {

    public void displayOutput(AsciiPanel terminal);
    
    public Screen respondToUserInput(KeyEvent key);
    
}
