package ch.damian.Solitaire;

import java.awt.*;
import java.util.HashMap;

public class Board {

    private final HashMap<Point, PointStateEnum> boardPoints = new HashMap<>() {
        {
            put(new Point(0, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(0, 3), PointStateEnum.PIN_PRESENT);
            put(new Point(0, 4), PointStateEnum.PIN_PRESENT);

            put(new Point(1, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(1, 3), PointStateEnum.PIN_PRESENT);
            put(new Point(1, 4), PointStateEnum.PIN_PRESENT);

            put(new Point(2, 0), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 1), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 3), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 4), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 5), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 6), PointStateEnum.PIN_PRESENT);
            put(new Point(2, 7), PointStateEnum.PIN_PRESENT);

            put(new Point(3, 0), PointStateEnum.PIN_PRESENT);
            put(new Point(3, 1), PointStateEnum.PIN_PRESENT);
            put(new Point(3, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(3, 3), PointStateEnum.EMPTY);
            put(new Point(3, 4), PointStateEnum.PIN_PRESENT);
            put(new Point(3, 5), PointStateEnum.PIN_PRESENT);
            put(new Point(3, 6), PointStateEnum.PIN_PRESENT);
            put(new Point(3, 7), PointStateEnum.PIN_PRESENT);

            put(new Point(4, 0), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 1), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 3), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 4), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 5), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 6), PointStateEnum.PIN_PRESENT);
            put(new Point(4, 7), PointStateEnum.PIN_PRESENT);

            put(new Point(5, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(5, 3), PointStateEnum.PIN_PRESENT);
            put(new Point(5, 4), PointStateEnum.PIN_PRESENT);

            put(new Point(6, 2), PointStateEnum.PIN_PRESENT);
            put(new Point(6, 3), PointStateEnum.PIN_PRESENT);
            put(new Point(6, 4), PointStateEnum.PIN_PRESENT);
        }
    };

    public Board() {

    }

    /**
     * 
     * @param from
     * @param to
     * @precondition move is valid
     * @return
     */
    public void move(Point from, Point to) {
        remove(getPinBetween(from, to));
        boardPoints.put(from, PointStateEnum.EMPTY);
        boardPoints.put(to, PointStateEnum.PIN_PRESENT);
    }

    public boolean isPinPresent(Point location) {
        PointStateEnum state =  boardPoints.getOrDefault(location, PointStateEnum.EMPTY);
        return switch (state) {
            case ACTIVATED, PIN_PRESENT -> true;
            default -> false;
        };
    }

    private void remove(Point pin) {
        if (boardPoints.get(pin) == PointStateEnum.PIN_PRESENT) {
            boardPoints.put(pin, PointStateEnum.EMPTY);
        }
    }
    
    private Point getPinBetween(Point from, Point to) {
        if (from.x == to.x) {
            return new Point(from.x, from.y > to.y ? to.y + 1 : to.y - 1);
        } else {
            return new Point(from.x > to.x ? to.x + 1 : to.x - 1, from.y);
        }
    }
}
