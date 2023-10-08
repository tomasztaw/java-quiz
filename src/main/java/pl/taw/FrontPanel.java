package pl.taw;

import javax.swing.*;

public class FrontPanel extends JPanel {

    public FrontPanel() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750, 750);
        frame.setTitle("JAVA QUIZ GAME");
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);

    }
}
