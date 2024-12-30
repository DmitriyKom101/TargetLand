package com.tagetland;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tagetland.service.landService;


public class AppTest 
{
    private landService landService;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    @Before
    public void setup() {
        landService = new landService();
    }

@Test
public void testSanitizeInput() {
    String input = "  0   292    399   307  ,   10   10    20    20  ";
    String expected = "0 292 399 307,10 10 20 20";

    // Call sanitizeInput method from Application class
    App app = new App();
    String result = app.sanitizeInput(input);

    // Verify the result
    assertEquals(expected, result);
}

@Test
public void testCalculateAreas() {
    String input = "  0   292    399   307  ,   10   10    20    20  ";
    List<Integer> expected = Arrays.asList(116800, 236400);  // Expecting two areas

    // Test the calculation of areas
    List<Integer> areas = landService.calcArea(input);
    assertNotNull(areas);
    assertEquals(expected, areas);
}

@Test
public void testMarkLand() {
    int[][] land = new int[WIDTH][HEIGHT];
    String input = "0 292 399 307,10 10 20 20";
    
    // Mark  land based on the input.
    landService.markLand(land, input);

    // Assert that land is correctly marked 
    assertEquals(1, land[0][292]);
    assertEquals(1, land[399][307]);
    assertEquals(1, land[10][10]);
    assertEquals(1, land[20][20]);
}

@Test
public void testfilled() {
    int[][] land = new int[WIDTH][HEIGHT];
    boolean[][] visited = new boolean[WIDTH][HEIGHT];
    String input = "0 292 399 307,10 10 20 20";
    
    // Mark land
    landService.markLand(land, input);

    // Test the flood fill on  land
    int area = landService.filled(land, visited, 100, 100); // Starting point in  land
    assertTrue(area > 0);  // The area should be greater than 0 for  land
}

@Test
public void testEmptyland() {
    int[][] land = new int[WIDTH][HEIGHT];
    boolean[][] visited = new boolean[WIDTH][HEIGHT];

    // No  land, so the entire land is .
    int area = landService.filled(land, visited, 0, 0);
    assertEquals(WIDTH * HEIGHT, area);  // All the land is 
}

}
