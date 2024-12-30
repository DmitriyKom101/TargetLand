package com.tagetland.service;

import java.util.*;

public class landService {


    public final int WIDTH = 400;
    public final int HEIGHT = 600;

    // Calculates the areas of unmarked land based on input rectangles.
    public List<Integer> calcArea(String input) {
        int[][] land = new int[WIDTH][HEIGHT];
        markLand(land, input);
        return computeAreas(land);
    }

    //Marks the land based on the input rectangles.

    public void markLand(int[][] land, String input) {
        String[] rectangles = input.split(",");
        // Mark the specified rectangle area as land
        for (String rect : rectangles) {
            String[] coordinates = rect.trim().split(" ");
            for (int x = Integer.parseInt(coordinates[0]); x <= Integer.parseInt(coordinates[2]); x++) {
                for (int y = Integer.parseInt(coordinates[1]); y <= Integer.parseInt(coordinates[3]); y++) {
                    land[x][y] = 1;
                }
            }
        }
    }

    // Finds all contiguous unmarked areas in the land.
    private List<Integer> computeAreas(int[][] land) {
        boolean[][] visited = new boolean[WIDTH][HEIGHT];
        List<Integer> landArea = new ArrayList<>();

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (land[x][y] == 0 && !visited[x][y]) {
                    int area = filled(land, visited, x, y);
                    landArea.add(area);
                }
            }
        }

        Collections.sort(landArea);
        return landArea;
    }

    // Performs fill to calculate the size of an area.
    public int filled(int[][] land, boolean[][] visited, int startX, int startY) {
        int area = 0;
    
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
    
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
    
            // Skip invalid or already visited cells
            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || visited[x][y] || land[x][y] == 1) {
                continue;
            }
    
            visited[x][y] = true; // Mark the cell as visited
            area++;
    
            // Add neighboring cells to the queue
            queue.offer(new int[]{x + 1, y}); // Move right
            queue.offer(new int[]{x - 1, y}); // Move left
            queue.offer(new int[]{x, y + 1}); // Move down
            queue.offer(new int[]{x, y - 1}); // Move up
        }
    
        return area;
    }
}
