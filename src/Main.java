/*Student UOW Id-w1985612
Name-Thelikada Gamage Hansala Madushani*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Puzzle puzzle = parseMap("src/map.txt");
            puzzle.findPath();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static Puzzle parseMap(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<String> lines = new ArrayList<>();
        String line;
        int startX = -1, startY = -1, finishX = -1, finishY = -1;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        int[][] puzzle = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                char c = lines.get(i).charAt(j);
                if (c == 'S') {
                    startX = j;
                    startY = i;
                } else if (c == 'F') {
                    finishX = j;
                    finishY = i;
                }
                puzzle[i][j] = c == '0' ? 1 : 0;
            }
        }

        return new Puzzle(puzzle, startX, startY, finishX, finishY);
    }
}