package ml.games.rougelike.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class LoseScreen implements Screen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("you lost.", 1, 1);
        terminal.writeCenter("-- press [enter] to restart --", 22);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }

}
