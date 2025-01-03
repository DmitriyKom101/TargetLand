package com.tagetland;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tagetland.service.landService;

@SpringBootApplication
public class App implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
        @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        landService landService = new landService();
        while (true) {
            System.out.println("Enter rectangle points or type 'exit' to quit:");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input.trim())) {
                System.out.println("Thank you, Goodbye!");
                break;
            }

            try {

                List<Integer> landArea = landService.calcArea(sanitizeInput(input));
                landArea.forEach(area -> System.out.print(area + " "));
                System.out.println();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        scanner.close();
    }
    public String sanitizeInput(String input) {
        // Remove double quotes
        input = input.replace("\"", "");
        
        // Normalize spaces around and within each rectangle definition
        StringBuilder sanitized = new StringBuilder();
        String[] rectangles = input.split(",");

        for (String rect : rectangles) {
            String[] coordinates = rect.trim().split("\\s+");
            if (coordinates.length == 4) {
                sanitized.append(String.join(" ", coordinates)).append(",");
            } else {
                throw new IllegalArgumentException("Each rectangle must have exactly four coordinates.");
            }
        }

        // Remove trailing comma
        if (sanitized.length() > 0 && sanitized.charAt(sanitized.length() - 1) == ',') {
            sanitized.setLength(sanitized.length() - 1);  // Remove the last comma
        }

        return sanitized.toString();
    }
}
