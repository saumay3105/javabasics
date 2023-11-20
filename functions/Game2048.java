import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;

public class Game2048 {
    private JFrame frame;
    private JPanel gridPanel;
    private JLabel[][] gridLabels;
    private boolean win;

    private static final int SIZE = 4;
    private int[][] grid;
    private Random random;
    private boolean moved;

    public Game2048() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        initializeFrame();
        initializeGridPanel();
        initializeInfoPanel();
        initializeKeyListener();
        initializeGrid();
        updateGridLabels();
    }

    private void initializeFrame() {
        frame = new JFrame("2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
    }

    private void initializeGridPanel() {
        gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        gridLabels = new JLabel[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                initializeLabel(i, j);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeLabel(int i, int j) {
        gridLabels[i][j] = new JLabel("", JLabel.CENTER);
        gridLabels[i][j].setFont(new Font("Arial", Font.BOLD, 24));
        gridLabels[i][j].setOpaque(true);
        gridLabels[i][j].setBackground(Color.GREEN);
        gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gridPanel.add(gridLabels[i][j]);
    }

    private void initializeInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        frame.add(infoPanel, BorderLayout.NORTH);
    }

    private void initializeKeyListener() {
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
                updateGridLabels();
                if (isGameOver()) {
                    showGameOverDialog();
                }
            }
        });

        frame.setFocusable(true);
        frame.requestFocus();
        frame.setVisible(true);
    }

    private void handleKeyPress(int keyCode) {
        moved = false;

        switch (keyCode) {
            case KeyEvent.VK_UP -> moveUp();
            case KeyEvent.VK_DOWN -> moveDown();
            case KeyEvent.VK_LEFT -> moveLeft();
            case KeyEvent.VK_RIGHT -> moveRight();
        }
    }

    private void moveUp() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (grid[i][j] != 0) {
                    int row = i;
                    while (row > 0 && (grid[row - 1][j] == 0 || grid[row - 1][j] == grid[row][j])) {
                        if (grid[row - 1][j] == grid[row][j]) {
                            grid[row - 1][j] *= 2;
                            grid[row][j] = 0;
                            moved = true;
                            break;
                        } else if (grid[row - 1][j] == 0) {
                            grid[row - 1][j] = grid[row][j];
                            grid[row][j] = 0;
                            moved = true;
                        }
                        row--;
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    private void moveDown() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (grid[i][j] != 0) {
                    int row = i;
                    while (row < SIZE - 1 && (grid[row + 1][j] == 0 || grid[row + 1][j] == grid[row][j])) {
                        if (grid[row + 1][j] == grid[row][j]) {
                            grid[row + 1][j] *= 2;
                            grid[row][j] = 0;
                            moved = true;
                            break;
                        } else if (grid[row + 1][j] == 0) {
                            grid[row + 1][j] = grid[row][j];
                            grid[row][j] = 0;
                            moved = true;
                        }
                        row++;
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    private void moveLeft() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (grid[i][j] != 0) {
                    int col = j;
                    while (col > 0 && (grid[i][col - 1] == 0 || grid[i][col - 1] == grid[i][col])) {
                        if (grid[i][col - 1] == grid[i][col]) {
                            grid[i][col - 1] *= 2;
                            grid[i][col] = 0;
                            moved = true;
                            break;
                        } else if (grid[i][col - 1] == 0) {
                            grid[i][col - 1] = grid[i][col];
                            grid[i][col] = 0;
                            moved = true;
                        }
                        col--;
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    private void moveRight() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    int col = j;
                    while (col < SIZE - 1 && (grid[i][col + 1] == 0 || grid[i][col + 1] == grid[i][col])) {
                        if (grid[i][col + 1] == grid[i][col]) {
                            grid[i][col + 1] *= 2;
                            grid[i][col] = 0;
                            moved = true;
                            break;
                        } else if (grid[i][col + 1] == 0) {
                            grid[i][col + 1] = grid[i][col];
                            grid[i][col] = 0;
                            moved = true;
                        }
                        col++;
                    }
                }
            }
        }
        if (moved) {
            addNewNumber();
        }
    }

    private boolean isGameOver() {
        if (win) {
            return true;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0 ||
                        (i > 0 && grid[i][j] == grid[i - 1][j]) ||
                        (i < SIZE - 1 && grid[i][j] == grid[i + 1][j]) ||
                        (j > 0 && grid[i][j] == grid[i][j - 1]) ||
                        (j < SIZE - 1 && grid[i][j] == grid[i][j + 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showGameOverDialog() {
        String result;
        if (win) {
            result = "Congratulations! You reached the 2048 tile!";
        } else {
            result = "Game over! Would you like to play again?";
        }
        int choice = JOptionPane.showConfirmDialog(frame, result, "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            restart();
        } else {
            System.exit(0);
        }
    }

    private void restart() {
        win = false;
        initializeGrid();
        updateGridLabels();
    }

    private void addNewNumber() {
        int row, col;
        do {
            row = random.nextInt(SIZE);
            col = random.nextInt(SIZE);
        } while (grid[row][col] != 0);
        grid[row][col] = (random.nextInt(2) + 1) * 2;
    }

    private void updateGridLabels() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    gridLabels[i][j].setText("");
                    gridLabels[i][j].setBackground(Color.GREEN);
                } else if (grid[i][j] == 2048) {
                    win = true;
                    gridLabels[i][j].setText(String.valueOf(grid[i][j]));
                } else {
                    gridLabels[i][j].setText(String.valueOf(grid[i][j]));
                }
            }
        }
    }

    private void initializeGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = 0;
            }
        }
        addNewNumber();
        addNewNumber();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game2048::new);
    }
}