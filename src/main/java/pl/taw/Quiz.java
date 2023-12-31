package pl.taw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz implements ActionListener {

    String[] questions = {
            "Which company created Java?",
            "Which year was Java created?",
            "What was Java originally called?",
            "Who is credited with creating Java?",
            "Does pineapple belong on Pizza?"
    };

    String[][] options = {
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alphabet"},
            {"1989", "1996", "1972", "1492"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"Steve Jobs", "Bill Gates", "James Gosling", "Mark Zuckerberg"},
            {"Yes!", "No!!!", "Maybe", "Who cares!"}
    };

    private static final char[] answers = {'A', 'B', 'C', 'C', 'D'};

    private char guess;
    private char answer;
    private int index;
    private int correct_guesses = 0;
    private final int total_question = questions.length;
    private int seconds = 10;

    private final JFrame frame = new JFrame();
    private final JTextField textField = new JTextField();
    private final JTextArea textArea = new JTextArea();

    private final JButton buttonA = new JButton();
    private final JButton buttonB = new JButton();
    private final JButton buttonC = new JButton();
    private final JButton buttonD = new JButton();
    private final JLabel answer_labelA = new JLabel();
    private final JLabel answer_labelB = new JLabel();
    private final JLabel answer_labelC = new JLabel();
    private final JLabel answer_labelD = new JLabel();
    private final JLabel time_label = new JLabel();
    private final JLabel seconds_left = new JLabel();
    private final JTextField number_right = new JTextField();
    private final JTextField percentage = new JTextField();
    private final Font ubuntuMonoTextArea = new Font("Ubuntu Mono", Font.BOLD, 25);
    private final Font ubuntuMonoButton = new Font("Ubuntu Mono", Font.BOLD, 35);
    private final Color darkColor = new Color(50, 50, 50);
    private final Color greenLight = new Color(25, 255, 0);
    private final Color blackBlack = new Color(25, 25, 25);

    Timer timer = new Timer(1000,
            e -> {
                seconds--;
                seconds_left.setText(String.valueOf(seconds));
                if (seconds <= 5) {
                    Sounds.playSound(Sounds.SECONDS_LEFT);
                }
                if (seconds <= 0) {
                    displayAnswer();
                    Sounds.playSound(Sounds.TIME_IS_UP);
                }
            });


    public Quiz() {

        FrontPanel frontPanel = new FrontPanel();


        //startGame();
    }

    public void startGame() {
        initFrame();
        initTextField();
        initTextArea();
        initAllButtons();
        initAllLabels();
        initTimer();
        initResult();
        addAllToFrame();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        nextQuestion();
    }


    public void nextQuestion() {

        if (index >= total_question) {
            showResults();
        } else {
            textField.setText("Question " + (index + 1));
            textArea.setText(questions[index]);

            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);

            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        disableButtons(false);

        if (e.getSource() == buttonA) answer = 'A';
        if (e.getSource() == buttonB) answer = 'B';
        if (e.getSource() == buttonC) answer = 'C';
        if (e.getSource() == buttonD) answer = 'D';

        if (answer == answers[index]) {
            correct_guesses++;
            Sounds.playSound(Sounds.CORRECT_ANSWER);
        } else {
            Sounds.playSound(Sounds.WRONG_ANSWER);
        }

        displayAnswer();
    }

    public void displayAnswer() {

        timer.stop();
        disableButtons(false);

        if (answers[index] != 'A') {
            answer_labelA.setForeground(Color.red);
        }
        if (answers[index] != 'B') {
            answer_labelB.setForeground(Color.red);
        }
        if (answers[index] != 'C') {
            answer_labelC.setForeground(Color.red);
        }
        if (answers[index] != 'D') {
            answer_labelD.setForeground(Color.red);
        }

        Timer pause = getTimer();
        pause.start();
    }

    private Timer getTimer() {
        Timer pause = new Timer(2000,
                e -> {
                    setColorForAllLabel(greenLight);

                    answer = ' ';
                    seconds = 10;
                    seconds_left.setText(String.valueOf(seconds));

                    disableButtons(true);
                    index++;
                    nextQuestion();
                });

        pause.setRepeats(false);
        return pause;
    }


    public void showResults() {

        Sounds.playSound(Sounds.END_GAME);
        disableButtons(false);

        int result = (int) ((correct_guesses / (double) total_question) * 100);
        textField.setText("RESULTS!");
        clearFrame();

        number_right.setText("(%s/%s)".formatted(correct_guesses, total_question));
        percentage.setText(result + "%");

        frame.add(number_right);
        frame.add(percentage);
    }

    private void clearFrame() {
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");
    }

    private void disableButtons(boolean b) {
        buttonA.setEnabled(b);
        buttonB.setEnabled(b);
        buttonC.setEnabled(b);
        buttonD.setEnabled(b);
    }

    private void setColorForAllLabel(Color color) {
        answer_labelA.setForeground(color);
        answer_labelB.setForeground(color);
        answer_labelC.setForeground(color);
        answer_labelD.setForeground(color);
    }

    private void addAllToFrame() {
        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
    }


    private void initResult() {
        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(blackBlack);
        number_right.setForeground(Color.green);
        number_right.setFont(new Font("Ubuntu Mono", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(blackBlack);
        percentage.setForeground(Color.green);
        percentage.setFont(new Font("Ubuntu Mono", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);
    }

    private void initTimer() {
        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(blackBlack);
        seconds_left.setForeground(Color.red);
        seconds_left.setFont(new Font("Roboto", Font.ITALIC, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535, 475, 100, 25);
        time_label.setBackground(darkColor);
        time_label.setForeground(Color.red);
        time_label.setFont(new Font("Roboto", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer >:D");
    }

    private void initAllLabels() {
        answer_labelA.setBounds(125, 100, 500, 100);
        answer_labelA.setBackground(darkColor);
        answer_labelA.setForeground(greenLight);
        answer_labelA.setFont(ubuntuMonoButton);

        answer_labelB.setBounds(125, 200, 500, 100);
        answer_labelB.setBackground(darkColor);
        answer_labelB.setForeground(greenLight);
        answer_labelB.setFont(ubuntuMonoButton);

        answer_labelC.setBounds(125, 300, 500, 100);
        answer_labelC.setBackground(darkColor);
        answer_labelC.setForeground(greenLight);
        answer_labelC.setFont(ubuntuMonoButton);

        answer_labelD.setBounds(125, 400, 500, 100);
        answer_labelD.setBackground(darkColor);
        answer_labelD.setForeground(greenLight);
        answer_labelD.setFont(ubuntuMonoButton);
    }

    private void initAllButtons() {
        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(ubuntuMonoButton);
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(ubuntuMonoButton);
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(ubuntuMonoButton);
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(ubuntuMonoButton);
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");
    }

    private void initTextArea() {
        textArea.setBounds(0, 50, 650, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(blackBlack);
        textArea.setForeground(greenLight);
        textArea.setFont(ubuntuMonoTextArea);
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
    }

    private void initTextField() {
        textField.setBounds(0, 0, 650, 50);
        textField.setBackground(blackBlack);
        textField.setForeground(greenLight);
        textField.setFont(new Font("Roboto", Font.BOLD, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
    }

    private void initFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(darkColor);
        frame.setLayout(null);
        frame.setResizable(false);
    }

}
