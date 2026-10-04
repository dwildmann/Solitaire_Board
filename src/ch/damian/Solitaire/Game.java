package ch.damian.Solitaire;

import ch.damian.Solitaire.GUI.BoardGUI;

import java.awt.*;

public class Game {

    private Board board;
    private BoardGUI gui;

    private Point activePoint = null;

    public Game() {
        board = new Board();
        gui = new BoardGUI(this);
    }

    public void pointClicked(Point p) {
        if (null == activePoint) {
            activePoint = p;
        } else {
            move(activePoint, p);
            activePoint = null;
        }
    }

    public void move(Point from, Point to) {
        if (validateMove(from, to)) {
            board.move(from, to);
            gui.onModelChanged();
        }
    }

    public void reset() {
        board = new Board();
        gui.onModelChanged();
    }

    private boolean validateMove(Point from, Point to) {
        // at least one of the two locations do not exist on the board
        if (! (board.isPinPresent(from) && !board.isPinPresent(to))) {
            return false;
        }

        if (from.x == to.x) {
            if (Math.abs(from.y - to.y) != 2) {
                return false;
            } else {
                Point pinToRemove = new Point(from.x, from.y > to.y ? to.y + 1 : to.y - 1);
                return board.isPinPresent(pinToRemove);
            }
        } else if (from.y == to.y) {
            if (Math.abs(from.x - to.x) != 2) {
                return false;
            } else {
                Point pinToRemove = new Point(from.x > to.x ? to.x + 1 : to.x - 1, from.y);
                return board.isPinPresent(pinToRemove);
            }
        } else {
            return false;
        }
    }

    public Board getBoard() {
        return board;
    }
}
