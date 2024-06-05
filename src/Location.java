/*Student UOW Id-w1985612
Name-Thelikada Gamage Hansala Madushani*/

public class Location {
    int x;
    int y;
    Location previousLocation;
    Direction directionFromPrevious;

    public Location(int x, int y, Location previousLocation, Direction directionFromPrevious) {
        this.x = x;
        this.y = y;
        this.previousLocation = previousLocation;
        this.directionFromPrevious = directionFromPrevious;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public Direction getDirectionFromPrevious() {
        return directionFromPrevious;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + (x + 1) +
                ", y=" + (y + 1) +
                ", directionFromPrevious=" + directionFromPrevious +
                '}';
    }
}
