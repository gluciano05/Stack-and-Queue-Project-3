import javax.swing.*;
import java.awt.*;

public class UIHandler extends JFrame {
    private final Simulation sim;
    private JTextArea textArea;
    private JLabel statusLabel;

    /** Constructs the UI handler and sets up the simulation window. */
    public UIHandler() {
        setTitle("Purple Mountain Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        sim = new Simulation();
        setupUI();
        setVisible(true);
    }

    /** Sets up the UI components including buttons, text area, and label.
     * This function does the following:
     * Creates the status label,
     * Creates the text area and scroll pane,
     * Creates buttons for start, open trial, and close trial,
     * Adds action listeners to handle button clicks
     */
    public void setupUI() {
        //creating label
        setLayout(new BorderLayout());
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        //creates text area
        textArea = new JTextArea(90, 1);
        textArea.setEditable(false);

        //makes scroll pane and button panel
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        //declares buttons
        JButton startButton = new JButton("Start");
        JButton openTrialButton = new JButton("Open Trial");
        JButton closeTrialButton = new JButton("Close Trial");

        openTrialButton.setEnabled(false);
        closeTrialButton.setEnabled(false);

        //handles button functionalities.
        startButton.addActionListener(e -> {
            sim.start();
            textArea.setText(sim.getAllHikersRegistered());
            statusLabel.setText("Hikers are ready to go, start the trial when you are ready.");
            //makes it so you cant click any other buttons, meaning you have to go in order
            startButton.setEnabled(false);
            openTrialButton.setEnabled(true);
        });

        openTrialButton.addActionListener(e -> {
            statusLabel.setText("Hikers are hiking! (7:00 AM - 9:00 AM)");
            textArea.setText("");
            sim.openTrial();
            //makes it so you cant click any other buttons, meaning you have to go in order
            openTrialButton.setEnabled(false);
            closeTrialButton.setEnabled(true);
        });

        closeTrialButton.addActionListener(e -> {
            sim.closeTrial();
            //prints hikers to text area
            statusLabel.setText("This is the order the hikers finished the trial: ");
            textArea.setText(sim.getListOfHikers() +
                    "\n" + sim.getEntranceBreakdown() +
                    "\n\nTotal Hikers: " + sim.getHikerCount());
            //makes it so you cant click any other buttons, meaning you have to go in order
            closeTrialButton.setEnabled(false);
        });

        //adds buttons to panel
        buttonPanel.add(startButton);
        buttonPanel.add(openTrialButton);
        buttonPanel.add(closeTrialButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}