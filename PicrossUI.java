import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hassan Salem on 2023-02-03.
 *
 * This class is the main class of the Picross game. It contains the main method
 * and the showGameGUI method. The showGameGUI method is responsible for creating
 * the GUI of the game. It also contains the timer method which is responsible
 * for the timer of the game.
 *
 */
public class PicrossUI  {

        private static JButton[][] squares;
        static JLabel counterLabel;
        private static Timer timer;
        static int second;
        static int minute;
        static String ddSecond;
        static String ddMinute;
        static DecimalFormat dFormat = new DecimalFormat("00");


    /**
     * This method is responsible for running the game. It is calls in the
     * showGameGUI method.
     *
     * @param args
     */
        public static void main(String[] args) {

            showGameGUI();//This method is responsible for creating the GUI of the game.
        }

        /**
         * This method is responsible for creating the GUI of the game. It creates
         * the menu bar, the grid, the timer, the hints, and the footer.
         */
    private static void showGameGUI()  {

        JFrame frame = new JFrame("Hassan's Picross"); //Create the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit the program when the frame is closed
        frame.setSize(800, 600);//Set the size of the frame
        frame.setLayout(new BorderLayout());//Set the layout of the frame
        frame.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.jpg"); // load the image to a imageIcon
        frame.setIconImage(icon);//Set the icon of the frame

        JPanel gridPanel = new JPanel(new GridLayout(10, 10)); //Create the grid
        int rows = 10;
        int cols = 10;
        squares = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) { //Add the buttons to the grid
            for (int j = 0; j < cols; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setBackground(Color.WHITE);
                gridPanel.add(squares[i][j]);
            }
        }

        JMenuBar bar = new JMenuBar();//Create the menu bar

        JMenu help = new JMenu("Help");//Create the help menu
        JMenuItem controls = new JMenuItem("Controls");//Create the controls menu item

        JMenuItem rules = new JMenuItem("Rules");//Create the rules menu item

        help.add(rules);//Add the rules menu item to the help menu
        help.add(controls);//Add the controls menu item to the help menu

        JMenu game = new JMenu("Game");//Create the game menu

        JMenuItem five = new JMenuItem("New 5x5 game");//Create the new 5x5 game menu item
        JMenuItem ten = new JMenuItem("New 10x10 game");//Create the new 10x10 game menu item
        JMenuItem fifteen = new JMenuItem("New 15x15 game");//Create the new 15x15 game menu item
        JMenuItem reset = new JMenuItem("Reset");//Create the reset menu item

        game.add(five); //Add the new 5x5 game menu item to the game menu
        game.add(ten);//Add the new 10x10 game menu item to the game menu
        game.add(fifteen); //Add the new 15x15 game menu item to the game menu
        game.add(reset);//Add the reset menu item to the game menu

        JMenu languages = new JMenu("Languages");//Create the languages menu
        JMenuItem english = new JMenuItem("English");//Create the english menu item
        JMenuItem french = new JMenuItem("French");//Create the french menu item
        JMenuItem arabic = new JMenuItem("Arabic");//Create the arabic menu item
        languages.add(english);//Add the english menu item to the languages menu
        languages.add(french);//Add the french menu item to the languages menu
        languages.add(arabic);//Add the arabic menu item to the languages menu

        JMenu view = new JMenu("View");//Create the view menu

        bar.add(game);//Add the game menu to the menu bar
        bar.add(view);//Add the view menu to the menu bar
        bar.add(help);//Add the help menu to the menu bar
        bar.add(languages);//Add the languages menu to the menu bar
        frame.setJMenuBar(bar);//Add the menu bar to the frame

        JPanel footer = new JPanel(new GridLayout(1, 2));//Create the footer
        footer.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));//Set the border of the footer
        footer.add(new JLabel("Made by Hassan Salem"));
        frame.add(footer, BorderLayout.PAGE_END);//Add the footer to the frame at the bottom

        JPanel panelTop = new JPanel(new BorderLayout());//Create the top panel
        panelTop.setBackground(Color.WHITE);
        panelTop.setPreferredSize(new Dimension(100, 100));//Set the size of the top panel

        JLabel logoLabel = new JLabel();//Create the logo label
        ImageIcon logo = new ImageIcon("logo.jpg"); // load the image to a imageIcon
        Image logoB = logo.getImage();//Get the imageicon of the logo and converted it to an image
        Image newImg = logoB.getScaledInstance(100, 100, Image.SCALE_SMOOTH);//Resize the image
        ImageIcon newIcon = new ImageIcon(newImg);
        Border logoBorder = BorderFactory.createLineBorder(Color.BLACK, 3);//Set the border of the logo
        logoLabel.setBorder(logoBorder);
        logoLabel.setPreferredSize(new Dimension(100, 100));//Set the size of the logo
        logoLabel.setIcon(newIcon);
        panelTop.add(logoLabel, BorderLayout.WEST);//Add the logo to the top panel at the left


        counterLabel = new JLabel("00:00");//Create the counter label
        counterLabel.setPreferredSize(new Dimension(100, 100));//Set the size of the counter label
        counterLabel.setHorizontalAlignment(JLabel.CENTER);//Set the alignment of the counter label
        panelTop.add(counterLabel, BorderLayout.EAST);//Add the counter label to the top panel at the right

        startTimer();//Start the timer

        JPanel topHint = new JPanel(new GridLayout(3    , 10));//Create the top hint panel
        int topHintRows = 3;
        int topHintCols = 10;
        for (int i = 0; i < topHintRows*topHintCols; i++) {//Add the labels to the top hint panel
            topHint.add(new JLabel(String.valueOf(i + 1), JLabel.CENTER));
        }
        panelTop.add(topHint, BorderLayout.CENTER);//Add the top hint panel to the top panel at the center



        JPanel panelLeft = new JPanel(new GridLayout(10, 3));//Create the left panel
        panelLeft.setBackground(Color.WHITE);
        panelLeft.setPreferredSize(new Dimension(100, 100));//Set the size of the left panel

        int leftHintRows = 10;
        int leftHintCols = 3;
        for (int i = 0; i < leftHintRows*leftHintCols; i++) {//Add the labels to the left panel
            panelLeft.add(new JLabel(String.valueOf(i + 1), JLabel.CENTER));
        }

        JPanel panelRight = new JPanel(new GridLayout(3, 2));//Create the right panel
        panelRight.setBackground(Color.WHITE);//Set the background color of the right panel
        panelRight.setPreferredSize(new Dimension(100, 100));//Set the size of the right panel

        ImageIcon heart = new ImageIcon("Heart.png");//Create the heart image icon
        Image heartB = heart.getImage();
        Image smallHeart = heartB.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon newHeart = new ImageIcon(smallHeart);
        for (int i = 0; i < 6; i++) {//Add the hearts to the right panel
            panelRight.add(new JLabel(newHeart));
        }

        frame.add(gridPanel, BorderLayout.CENTER);//Add the grid panel to the frame at the center
        frame.add(panelTop, BorderLayout.NORTH);//Add the top panel to the frame at the top
        frame.add(panelLeft, BorderLayout.WEST);//Add the left panel to the frame at the left
        frame.add(panelRight, BorderLayout.EAST);//Add the right panel to the frame at the right



        frame.setVisible(true);//Make the frame visible
    }


    /**
     * This method starts the timer.
     */
    private static void startTimer() {//Start the timer
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {//Start the timer
            @Override
            public void run() {
                second++;
                if (second >= 60) {//If the seconds are more than 60, add 1 to the minutes and reset the seconds
                    second = 0;
                    minute++;
                }
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                counterLabel.setText(ddMinute + ":" + ddSecond);
            }
        }, 1000, 1000);//Start the timer after 1 second and repeat it every 1 second
    }

}