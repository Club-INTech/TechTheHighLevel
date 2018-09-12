package keyboard;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Cette classe ne sert uniquement qu'à changer des booléens en fonctions des inputs utilisés */
class KeyboardInputFrame extends JFrame implements KeyListener {

    boolean isUpPressed = false;
    boolean isDownPressed = false;
    boolean isLeftPressed = false;
    boolean isRightPressed = false;
    boolean isEscapePressed = false;


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP) {
            this.isUpPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            this.isDownPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            this.isLeftPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.isRightPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.isEscapePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP) {
            this.isUpPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            this.isDownPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            this.isLeftPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.isRightPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.isEscapePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    KeyboardInputFrame(){
        addKeyListener(this);
    }
}
