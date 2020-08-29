package assignment2.model;

import java.util.concurrent.atomic.AtomicBoolean;

public class Screen {

    private Long screenId;
    private AtomicBoolean seatLocks[][];
    private Boolean seats[][];
    private int rows, columns;

    public Screen(Long screenId, Boolean[][] seats) {

        this.screenId = screenId;
        this.rows = seats.length;
        this.columns = seats[0].length;
        this.seatLocks = new AtomicBoolean[rows][columns];
        this.seats = new Boolean[rows][columns];

        // Initialize seat locks
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < columns; c++)
                if(seats[r][c]) {
                    this.seatLocks[r][c] = new AtomicBoolean(false);
                    this.seats[r][c] = false;

                } else {
                    this.seatLocks[r][c] = null;
                    this.seats[r][c] = null;
                }
    }

    public Long getScreenId() {
        return screenId;
    }

    public AtomicBoolean getSeat(long seatId) {

        seatId--;
        int x = (int)(seatId / columns);
        int y = (int)(seatId % columns);
        return seatLocks[x][y];
    }

}
