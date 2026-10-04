package ch.damian.Solitaire.GUI;

import ch.damian.Solitaire.Board;
import ch.damian.Solitaire.Game;
import ch.damian.Solitaire.IBoardObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGUI extends javax.swing.JFrame implements IBoardObserver {

    private static final int GRID_SIZE = 7;
    private final JLabel[][] labels = new JLabel[GRID_SIZE][GRID_SIZE];

    private Game game;

    public BoardGUI(Game g) {
        this.game = g;

        setTitle("Solitaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        JPanel globPanel = new JPanel(new BorderLayout());

        for (int row = GRID_SIZE - 1; row >= 0 ; row--) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JLabel label = createLabel(row, col);

                final int x = col;
                final int y = row;

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        game.pointClicked(new Point(x, y));
                    }
                });

                labels[col][row] = label;
                gridPanel.add(label);
            }
        }

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            game.reset();
        });

        gridPanel.setPreferredSize(new Dimension(500, 500));
        globPanel.add(gridPanel, BorderLayout.CENTER);
        globPanel.add(resetButton, BorderLayout.SOUTH);

        add(globPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        onModelChanged();
    }

    private static JLabel createLabel(int row, int col) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);

        return label;
    }

    @Override
    public void onModelChanged() {
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                labels[x][y].setText(game.getBoard().isPinPresent(new Point(x, y)) ? "X" : "");
                //labels[x][y].setText(String.valueOf(x) + " " + String.valueOf(y));
            }
        }
    }
}
