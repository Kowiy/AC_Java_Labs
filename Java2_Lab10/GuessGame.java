/**
 * GuessGame class is the entry point for the Guessing Game application.
 */

import javax.swing.JFrame;

public class GuessGame {
    public static void main(String args[]) {
        GuessGameFrame guessGameFrame = new GuessGameFrame(); 
        guessGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guessGameFrame.setSize(300, 150); // set frame size
        guessGameFrame.setVisible(true); // display frame
    } // end main
} // end class GuessGame
