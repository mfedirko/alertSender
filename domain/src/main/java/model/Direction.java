package model;

public enum Direction {
    N,
    S,
    E,
    W;

    public Direction fromString(String str){
        return Direction.valueOf(str);
    }
}