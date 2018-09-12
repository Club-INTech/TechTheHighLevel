package junit;

import keyboard.KeyboardHandler;

public class JUnit_KeyboardInput {

    public static void main(String[] args){
        KeyboardHandler keyboard= new KeyboardHandler();
        keyboard.start();

        Thread keyboardThread = new Thread(() -> {
            try {
                while (true){

                    if (keyboard.isDownPressed()){
                        System.out.println("down");
                    }
                    if (keyboard.isUpPressed()){
                        System.out.println("up");
                    }
                    if (keyboard.isLeftPressed()){
                        System.out.println("left");
                    }
                    if (keyboard.isRightPressed()){
                        System.out.println("right");
                    }
                    if (keyboard.isEscapePressed()){
                        keyboard.getFrame().setVisible(false);
                        keyboard.getFrame().dispose();
                        throw new InterruptedException();
                    }

                    
                    Thread.sleep(20);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        keyboardThread.start();
    }
}
