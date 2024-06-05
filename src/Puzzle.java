/*Student UOW Id-w1985612
Name-Thelikada Gamage Hansala Madushani*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Puzzle {
    int[][] puzzle;
    int startX;
    int startY;
    int finishX;
    int finishY;

    public Puzzle(int[][] puzzle, int startX, int startY, int finishX, int finishY) {
        this.puzzle = puzzle;
        this.startX = startX;
        this.startY = startY;
        this.finishX = finishX;
        this.finishY = finishY;
    }

    public void findPath() {
        Location startLocation = new Location(startX, startY, null, null);

        LinkedList<Location> locationList = new LinkedList<>();
        Location[][] puzzleLocations = new Location[puzzle.length][puzzle[0].length];

        locationList.addLast(new Location(startX, startY, null, null));
        puzzleLocations[startY][startX] = startLocation;

        if (startX == finishX && startY == finishY) {
            printPath(startLocation);
            return;
        }
        if (locationList.isEmpty()) {
            System.out.println("No path found!");
            return;
        }

        while (!locationList.isEmpty()) {
            Location currentLocation = locationList.pollFirst();
            for (Direction direction : Direction.values()) {
                Location nextLocation = move(puzzleLocations, currentLocation, direction);
                if (nextLocation != null) {
                    locationList.addLast(nextLocation);
                    puzzleLocations[nextLocation.getY()][nextLocation.getX()] = nextLocation;
                    if (nextLocation.getY() == finishY && nextLocation.getX() == finishX) {
                        printPath(nextLocation);
                        return;
                    }
                }
            }
        }
        System.out.println("No path found!");
    }

    public void printPath(Location finishLocation) {
        List<String> steps = new ArrayList<>();
        Location currentLocation = finishLocation;
        int totalSteps = 0;
        while (currentLocation.getPreviousLocation() != null) {
            totalSteps++;
            currentLocation = currentLocation.getPreviousLocation();
        }
        currentLocation = finishLocation;
        System.out.println("1. Start at (" + (startX + 1) + "," + (startY + 1) + ")");
        int stepNumber = totalSteps;
        while (currentLocation.getPreviousLocation() != null) {
            steps.add((stepNumber + 1) + ". Move " + currentLocation.getDirectionFromPrevious().toString().toLowerCase() + " to (" + (currentLocation.getX() + 1) + "," + (currentLocation.getY() + 1) + ")");
            currentLocation = currentLocation.getPreviousLocation();
            stepNumber--;
        }
        for (int i = steps.size() - 1; i >= 0; i--) {
            System.out.println(steps.get(i));
        }
        System.out.println((totalSteps + 2) + ". Done!");
    }

    public Location move(Location[][] puzzleLocations, Location currentLocation, Direction direction) {
        int x = currentLocation.getX();
        int y = currentLocation.getY();

        int diffX = (direction == Direction.LEFT ? -1 : (direction == Direction.RIGHT ? 1 : 0));
        int diffY = (direction == Direction.UP ? -1 : (direction == Direction.DOWN ? 1 : 0));

        int i = 1;
        while (x + i * diffX >= 0
                && x + i * diffX < puzzle[0].length
                && y + i * diffY >= 0
                && y + i * diffY < puzzle.length
                && puzzle[y + i * diffY][x + i * diffX] != 1) {
            if (x + i * diffX == finishX && y + i * diffY == finishY) {
                return new Location(x + i * diffX, y + i * diffY, currentLocation, direction);
            }
            i++;
        }

        i--;

        if (puzzleLocations[y + i * diffY][x + i * diffX] != null) {
            return null;
        }

        return new Location(x + i * diffX, y + i * diffY, currentLocation, direction);
    }


}
