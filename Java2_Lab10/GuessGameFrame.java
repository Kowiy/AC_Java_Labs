/**
 * GuessGameFrame class implements a guessing game application where the user
 * tries to guess a randomly generated number between 1 and 1000.
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GuessGameFrame extends JFrame {
    private static Random generator = new Random();
    private int number; // number chosen by application
    private int guessCount; // number of guesses
    private int lastDistance; // distance between last guess and number
    private int currentDistance; // distance between current guess and number
    private JTextField guessInputJTextField; // for guessing
    private JLabel prompt1JLabel; // first prompt to user
    private JLabel prompt2JLabel; // second prompt to user
    private JLabel messageJLabel; // displays message of game status
    private JButton newGameJButton; // creates new game
    private Color background; // background color of application

    // set up GUI and initialize values
    public GuessGameFrame() {
        super("Guessing Game"); // set title of application
        guessCount = 0; // initialize number of guesses to 0
        background = Color.LIGHT_GRAY; // set background to light gray

        prompt1JLabel = new JLabel("I have a number between 1 and 1000."); // describe game
        prompt2JLabel = new JLabel("Can you guess my number? Enter your Guess:"); // prompt user

        guessInputJTextField = new JTextField(5); // to enter guesses
        guessInputJTextField.addActionListener(new GuessHandler());
        messageJLabel = new JLabel("Guess result appears here.");

        newGameJButton = new JButton("New Game");
        newGameJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame(); // reset the game for a new round
                repaint(); // repaint the GuessGame JFrame
            }
        });

        setLayout(new FlowLayout()); // set the layout of the container
        add(prompt1JLabel);
        add(prompt2JLabel);
        add(guessInputJTextField);
        add(messageJLabel);
        add(newGameJButton);

        theGame(); // start new game
    }

    // choose a new random number
    public void theGame() {
        number = generator.nextInt(1000) + 1; // random number between 1 and 1000
    }

    // change background color
    public void paint(Graphics g) {
        super.paint(g);
        getContentPane().setBackground(background); // set background
    }

    // react to new guess
    public void react(int guess) {
        guessCount++; // increment guesses

        if (guessCount == 1) {
            lastDistance = Math.abs(guess - number);
            if (guess > number)
                messageJLabel.setText("Too High. Try a lower number.");
            else
                messageJLabel.setText("Too Low. Try a higher number.");
        } else {
            currentDistance = Math.abs(guess - number);

            if (guess > number) {
                messageJLabel.setText("Too High. Try a lower number.");
                background = (currentDistance <= lastDistance) ? Color.RED : Color.BLUE;
                lastDistance = currentDistance;
            } else if (guess < number) {
                messageJLabel.setText("Too Low. Try a higher number.");
                background = (currentDistance <= lastDistance) ? Color.RED : Color.BLUE;
                lastDistance = currentDistance;
            } else {
                messageJLabel.setText("Correct!");
            }
            repaint();
        }
    }

    // inner class acts on user input
    class GuessHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int guess = Integer.parseInt(guessInputJTextField.getText());
            react(guess);
            guessInputJTextField.setText("");
        }
    }

    // reset the game for a new round
    private void resetGame() {
        theGame(); // choose a new random number
        guessCount = 0;
        lastDistance = 0;
        background = Color.LIGHT_GRAY;
        messageJLabel.setText("Guess result appears here.");
        guessInputJTextField.setEditable(true); // make the text field editable
    }

    public static void main(String[] args) {
        GuessGameFrame gameFrame = new GuessGameFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(300, 200);
        gameFrame.setVisible(true);
    }
}
