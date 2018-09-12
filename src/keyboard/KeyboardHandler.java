package keyboard;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class KeyboardHandler extends Thread {

    private static KeyboardInputFrame frame;
    public KeyboardHandler() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run () {
                    frame = new KeyboardInputFrame();
                    frame.setFocusable(true);
                    frame.setFocusTraversalKeysEnabled(false);
                    frame.setTitle("Robot control interface");
                    frame.setResizable(false);
                    frame.setSize(300, 200);
                    frame.setMinimumSize(new Dimension(300, 200));
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public JFrame getFrame(){
        return frame;
    }

    public boolean isUpPressed(){
        return frame.isUpPressed;
    }
    public boolean isDownPressed(){ return frame.isDownPressed; }
    public boolean isLeftPressed(){ return frame.isLeftPressed; }
    public boolean isRightPressed(){
        return frame.isRightPressed;
    }
    public boolean isEscapePressed(){ return frame.isEscapePressed; }

}
