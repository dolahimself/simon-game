import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JFrame implements ActionListener {
    private List<Integer> sequence;
    private List<JButton> buttons;
    private int currentStep;
    private boolean platerTurn;
    private Timer timer;
    private int currentFlash;

    public Game() {
        setTitle("Simon Game by Dola");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,2));

        sequence = new ArrayList<>();
        buttons = new ArrayList<>();
        currentStep = 0;
        platerTurn = false;

        for (int i = 0; i < 4; i++) {
            JButton button = new SimonButton(i);
            button.addActionListener(this);
            buttons.add(button);
            add(button);
        }

        newRound();
        setVisible(true);
    }

    private void newRound() {
        Random random = new Random();
        sequence.add(random.nextInt(4));
        currentStep = 0;
        platerTurn = false;
        playSequence();
    }

    private void playSequence() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFlash < sequence.size()){
                    buttons.get(sequence.get(currentFlash)).setBackground(Color.WHITE);
                    Timer pause = new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttons.get(sequence.get(currentFlash)).setBackground(getButtonColor(sequence.get(currentFlash)));
                            currentFlash++;
                        }
                    });
                    pause.setRepeats(false);
                    pause.start();
                } else {
                    timer.stop();
                    platerTurn = true;
                }
            };
        });
        currentFlash = 0;
        timer.setRepeats(true);
        timer.start();
    }

    private Color getButtonColor(int index) {
        return  switch (index) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 4 -> Color.YELLOW;
            default -> Color.BLACK;
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!platerTurn) return;

        SimonButton button = (SimonButton) e.getSource();
        if(button.getIndex() == sequence.get(currentStep)){
            currentStep++;
            if(currentStep == sequence.size()){
                platerTurn = false;
                newRound();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Game over!Your score: " + (sequence.size() -1));
            sequence.clear();
            newRound();
        }
    }
}
