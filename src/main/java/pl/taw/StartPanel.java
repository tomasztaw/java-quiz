package pl.taw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    public StartPanel(ActionListener startButtonListener) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Witaj w Mojej Grze");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Rozpocznij grę");
        startButton.addActionListener(startButtonListener);
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
