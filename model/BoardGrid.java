package model;

public class BoardGrid {
    private int row;
    private int col;
    private Player occupant = null;

    public BoardGrid(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Player getOccupant() {
        return this.occupant;
    }

    public String getOccupantMarker() {
        if (!isOccupied()) return "#";
        return this.occupant.getStringMarker();
    }

    public boolean isOccupied() {
        return this.occupant != null;
    }

    public void setOccupant(Player occupant) {
        this.occupant = occupant;
    }
}
