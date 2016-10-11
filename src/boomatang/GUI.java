package boomatang;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by boomatang on 27/09/16.
 */

public class GUI {

    private JFrame frame;
    private int[] frameSize;


    // Menu bar
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;

    private JMenuItem quit;
    private JMenuItem help_info;
    private JMenuItem about;

    // Required Panels
    private JPanel topPanel, midPanel, bottomPanel;

    private String display, update;
    private Container contentPane;

    // define all the buttons that are required
    private JButton selectFolder, cancelButton, showFiles, startButton;

    private JLabel subLabel, mainLabel;
    private Events events;

    private MyThread counter;
    /**
     * Here is the main constructor
     */
    public GUI() {

        events = new Events();

        frameSize = new int[2];
        frameSize[0] = 400;
        frameSize[1] = 300;
        display = "Welcome";
        update = "Progress Report 0/0";
        makeFrame();
        makeMenuBar();
        createPanels();
        makeButtons();

        // Show the required window
        frame.pack();
        setBaseSizes(frameSize);
        frame.setVisible(true);
        addEventHandlers();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


    /**
     * The following method handles the any events that may occur,
     * This is a very long method which uses the none centralised method
     * to handle the events
     */
    private void addEventHandlers() {
        // Here the quit button is handled
        counter = new MyThread(mainLabel);
        quit.addActionListener(e -> {
            //runs the quit method from the MyEvent class
            events.quit(true);

        });

        // handles the pressing of the help button
        help_info.addActionListener(actionEvent -> {
            String helpText = "This needs to be filled in";

            events.popUpMassage(helpText, "Help Information");
        });

        // handles the pressing of the about button
        about.addActionListener(actionEvent -> {
            String about1 = "This needs to be filled in";

            events.popUpMassage(about1, "About Information");
        });

        selectFolder.addActionListener(e -> {
            display = events.promptForFolder(selectFolder);
            subLabel.setText(display);

        });

        startButton.addActionListener(e -> {
            Path start = Paths.get(display);
            mainLabel.setText("Merging Files");
            counter.begin(start);
            mainLabel.setText("Finished");

        });

        cancelButton.addActionListener(e -> counter.finish());

    }


    /**
     * method makes all the buttons,
     * this should be change to make use of some kind of loop.
     */
    private void makeButtons() {

        // Main Buttons
        selectFolder = new JButton("Select Folder");
        midPanel.add(selectFolder);

        subLabel = new JLabel(display);
        midPanel.add(subLabel);

        startButton = new JButton("Start Merge");
        midPanel.add(startButton);

        mainLabel = new JLabel(update);
        midPanel.add(mainLabel);

        cancelButton = new JButton("Cancel Merge");
        midPanel.add(cancelButton);

        showFiles = new JButton("Show Files");
        midPanel.add(showFiles);


    }


    /**
     * Method makes the menus seen in the top bar
     */
    private void makeMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        file = new JMenu("File");
        help = new JMenu("Help");

        quit = new JMenuItem("Quit");
        help_info = new JMenuItem("Help");
        about = new JMenuItem("About");

        menuBar.add(file);
        menuBar.add(help);

        file.add(quit);

        help.add(help_info);
        help.add(about);

    }

    /**
     * This method will put together the main frame that
     * all buttons and screens will be added to.
     * It checks the screen size and sets the app location to the center of the screen
     */
    private void makeFrame() {
        //double[] screen = events.readScreenSize();

        // int[] xy = events.selectScreenPosition(screen, frameSize);
        int[] xy = {200, 200};
        frame = new JFrame("TSD PDF Merge");
        // TODO set the size of the screen to centre values
        frame.setLocation(xy[0], xy[1]);
        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());


    }

    /**
     * Create instances of all the panels and set their layouts
     * Copied from GUI.java
     */

    public void createPanels() {

        // create the JPanels and add to contentPane. They will now have a Border Layout


        // create instances of the JPanels
        topPanel = new JPanel(new FlowLayout());
        topPanel.setOpaque(false);

//        midPanel = new JPanel(new GridLayout(5, 0));
        midPanel = new JPanel(new GridLayout(3, 0));
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);


        // add the JPanels to the contentPane specifying the position of each
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(midPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);


    }

    /**
     * set the size of the app should change but seems to have errors
     *
     * @param baseSizes requires a list length 2 of type INT
     */
    public void setBaseSizes(int[] baseSizes) {

        frame.setSize(baseSizes[0], baseSizes[1]);
        topPanel.setSize(200, 200);

    }
}
