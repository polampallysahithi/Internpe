package Week3;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    final char PLAYER_X = 'X';
    final char PLAYER_O = 'O';

    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Verdana", Font.BOLD, 50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 600, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(100, 100, 100));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 80));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(200, 200, 200));
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("Computer's turn");
                        check();
                        if (!player1_turn) {
                            computerMove();
                        }
                    }
                }
            }
        }
    }

    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("Your turn");
        } else {
            player1_turn = false;
            textfield.setText("Computer's turn");
            computerMove();
        }
    }

    public void computerMove() {
        int bestMove = findBestMove();
        buttons[bestMove].setForeground(new Color(0, 0, 255));
        buttons[bestMove].setText("O");
        player1_turn = true;
        textfield.setText("Your turn");
        check();
    }

    private int findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                buttons[i].setText("O");
                int moveVal = minimax(false);
                buttons[i].setText("");
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMax) {
        int score = evaluate();
        if (score == 10) return score;
        if (score == -10) return score;
        if (isBoardFull()) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("O");
                    best = Math.max(best, minimax(false));
                    buttons[i].setText("");
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setText("X");
                    best = Math.min(best, minimax(true));
                    buttons[i].setText("");
                }
            }
            return best;
        }
    }

    private int evaluate() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals("X") && buttons[i * 3 + 1].getText().equals("X") && buttons[i * 3 + 2].getText().equals("X"))
                return -10;
            if (buttons[i * 3].getText().equals("O") && buttons[i * 3 + 1].getText().equals("O") && buttons[i * 3 + 2].getText().equals("O"))
                return 10;
        }
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals("X") && buttons[i + 3].getText().equals("X") && buttons[i + 6].getText().equals("X"))
                return -10;
            if (buttons[i].getText().equals("O") && buttons[i + 3].getText().equals("O") && buttons[i + 6].getText().equals("O"))
                return 10;
        }
        if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X"))
            return -10;
        if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O"))
            return 10;
        if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X"))
            return -10;
        if (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O"))
            return 10;
        return 0;
    }

    public void check() {
        // Check X win conditions
        if (
            (buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) ||
            (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) ||
            (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) ||
            (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) ||
            (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) ||
            (buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X")) ||
            (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) ||
            (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X"))
        ) {
            highlightWinningCombination("X");
            xWins();
        }
        // Check O win conditions
        else if (
            (buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) ||
            (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) ||
            (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) ||
            (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) ||
            (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) ||
            (buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O")) ||
            (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) ||
            (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O"))
        ) {
            highlightWinningCombination("O");
            oWins();
        }
        // Check for a tie
        else if (isBoardFull()) {
            tie();
        }
    }

    public void highlightWinningCombination(String winner) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals(winner) &&
                buttons[i * 3 + 1].getText().equals(winner) &&
                buttons[i * 3 + 2].getText().equals(winner)) {
                buttons[i * 3].setBackground(Color.GREEN);
                buttons[i * 3 + 1].setBackground(Color.GREEN);
                buttons[i * 3 + 2].setBackground(Color.GREEN);
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(winner) &&
                buttons[i + 3].getText().equals(winner) &&
                buttons[i + 6].getText().equals(winner)) {
                buttons[i].setBackground(Color.GREEN);
                buttons[i + 3].setBackground(Color.GREEN);
                buttons[i + 6].setBackground(Color.GREEN);
            }
        }
        // Check diagonals
        if (buttons[0].getText().equals(winner) &&
            buttons[4].getText().equals(winner) &&
            buttons[8].getText().equals(winner)) {
            buttons[0].setBackground(Color.GREEN);
            buttons[4].setBackground(Color.GREEN);
            buttons[8].setBackground(Color.GREEN);
        }
        if (buttons[2].getText().equals(winner) &&
            buttons[4].getText().equals(winner) &&
            buttons[6].getText().equals(winner)) {
            buttons[2].setBackground(Color.GREEN);
            buttons[4].setBackground(Color.GREEN);
            buttons[6].setBackground(Color.GREEN);
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void xWins() {
        textfield.setText("Congratulations !!! You won");
        disableButtons();
    }

    public void oWins() {
        textfield.setText("Computer wins!");
        disableButtons();
    }

    public void tie() {
        textfield.setText("It's a Tie");
        disableButtons();
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
