package utils.keyboard;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class KeyboardHandler extends Thread {

    private static InputFrame frame;
    public KeyboardHandler() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                frame = new InputFrame();
                frame.setFocusable(true);
                frame.setFocusTraversalKeysEnabled(false);
                frame.setTitle("Robot control interface");
                frame.setResizable(false);
                frame.setSize(300, 200);
                frame.setMinimumSize(new Dimension(300, 200));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
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
    public boolean isSpacePressed(){ return frame.isSpacePressed; }
    public boolean isCPressed(){ return frame.isCPressed; }
    public boolean isPPressed(){ return frame.isPPressed; }
    public boolean isVPressed(){ return frame.isVPressed; }
    public boolean isWPressed(){ return frame.isWPressed; }
    public boolean isXPressed(){ return frame.isXPressed; }

    public boolean isEscapePressed(){ return frame.isEscapePressed; }

}
