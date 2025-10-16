import javax.swing.*;
import java.awt.*;

public class UIHandler extends JFrame {
    private final Simulation sim;
    private JTextArea textArea;
    private JLabel statusLabel;

    public UIHandler() {
        setTitle("Purple Mountain Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        sim = new Simulation();
        setupUI();
        setVisible(true);
    }

    public void setupUI() {
        setLayout(new BorderLayout());
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        textArea = new JTextArea(90, 1);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton startButton = new JButton("Start");
        JButton openTrialButton = new JButton("Open Trial");
        JButton closeTrialButton = new JButton("Close Trial");

        openTrialButton.setEnabled(false);
        closeTrialButton.setEnabled(false);

        startButton.addActionListener(e -> {
            sim.start();
            textArea.setText(sim.getAllHikersRegistered());
            statusLabel.setText("Hikers are ready to go, start the trial when you are ready.");
            startButton.setEnabled(false);
            openTrialButton.setEnabled(true);
        });

        openTrialButton.addActionListener(e -> {
            statusLabel.setText("Hikers are hiking! (7:00 AM - 9:00 AM)");
            textArea.setText("");
            sim.openTrial();
            openTrialButton.setEnabled(false);
            closeTrialButton.setEnabled(true);
        });

        closeTrialButton.addActionListener(e -> {
            sim.closeTrial();
            statusLabel.setText("This is the order the hikers finished the trial: ");
            textArea.setText(sim.getListOfHikers() +
                    "\n" + sim.getEntranceBreakdown() +
                    "\n\nTotal Hikers: " + sim.getHikerCount());
            closeTrialButton.setEnabled(false);
        });

        buttonPanel.add(startButton);
        buttonPanel.add(openTrialButton);
        buttonPanel.add(closeTrialButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
