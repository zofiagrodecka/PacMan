package project;

public enum GhostColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    public String toString(){
        return switch(this){
            case RED -> "red";
            case BLUE -> "blue";
            case GREEN -> "green";
            case YELLOW -> "yellow";
        };
    }
}
