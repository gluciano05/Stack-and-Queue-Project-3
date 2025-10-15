import javax.swing.*;

public class UIHandler extends JFrame {
    private Simulation sim;

    public UIHandler() {
        setTitle("Purple Mountain Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setupUI();
        setVisible(true);
    }

    public void setupUI() {
        JPanel panel = new JPanel();

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {sim.populateQueues();});

        JButton openTrialButton = new JButton("Open Trial");
        JButton closeTrialButton = new JButton("Close Trial");

        panel.add(startButton);
        panel.add(openTrialButton);
        panel.add(closeTrialButton);

        add(panel);
    }
}
