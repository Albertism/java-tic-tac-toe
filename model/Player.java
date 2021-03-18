package model;

public class Player {
    private final PlayerMarker marker;

    public Player(PlayerMarker marker) {
        this.marker = marker;
    }

    public String getStringMarker() {
        return marker == PlayerMarker.O ? "O" : "X";
    }
}
