/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trackpassengers.second.week;

import java.util.Scanner;


public class TrackPassengersSecondWeek {

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter the number of stops on the route: ");
        int numStops = scanner.nextInt();

        System.out.print("Enter the bus's seating capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); 

        String[] stopNames = new String[numStops];
        int[] boarding = new int[numStops];
        int[] alighting = new int[numStops];
        int[] occupancy = new int[numStops];

        for (int i = 0; i < numStops; i++) {
            System.out.println("\n--- Stop " + (i + 1) + " ---");
            System.out.print("Enter stop name: ");
            stopNames[i] = scanner.nextLine();

            System.out.print("Enter passengers boarding: ");
            boarding[i] = scanner.nextInt();

            System.out.print("Enter passengers alighting (getting off): ");
            alighting[i] = scanner.nextInt();
            scanner.nextLine(); 
        }
        int currentPassengers = 0;
        int overCapacityStopCount = 0;

        for (int i = 0; i < numStops; i++) {
            
            if (currentPassengers - alighting[i] < 0) {
                System.out.println("Data error at " + stopNames[i] + ": cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
                currentPassengers = 0;
            } else {
                currentPassengers -= alighting[i];
            }

            currentPassengers += boarding[i];
            occupancy[i] = currentPassengers;

            
            if (currentPassengers > capacity) {
                System.out.println("Warning: Bus is over capacity at " + stopNames[i] + "!");
                overCapacityStopCount++;
            }
        }
        System.out.println("\n==========================================");
        System.out.println("             ROUTE SUMMARY");
        System.out.println("==========================================");
        System.out.printf("%-20s %-10s %-10s %-10s%n", "Stop Name", "Boarding", "Alighting", "Occupancy");
        System.out.println("------------------------------------------");
        for (int i = 0; i < numStops; i++) {
            System.out.printf("%-20s %-10d %-10d %-10d%n", stopNames[i], boarding[i], alighting[i], occupancy[i]);
        }

        
        System.out.println("\n==========================================");
        System.out.println("               STATISTICS");
        System.out.println("==========================================");

        
        int maxBoarding = -1;
        String busiestStop = "";
        int totalOccupancy = 0;

        for (int i = 0; i < numStops; i++) {
            if (boarding[i] > maxBoarding) {
                maxBoarding = boarding[i];
                busiestStop = stopNames[i];
            }
            totalOccupancy += occupancy[i];
        }

        double averageOccupancy = (double) totalOccupancy / numStops;

        System.out.println("Busiest stop (highest boarding): " + busiestStop + " (" + maxBoarding + " passengers)");
        System.out.printf("Average bus occupancy across all stops: %.2f%n", averageOccupancy);
        System.out.println("Total stops exceeding bus capacity: " + overCapacityStopCount);

        
        int finalOccupancy = occupancy[numStops - 1];
        if (finalOccupancy != 0) {
            System.out.println("Warning: " + finalOccupancy + " passengers still on the bus after the final stop — please check your data.");
        } else {
            System.out.println("Route completed successfully. All passengers have alighted.");
        }

        scanner.close();
    }
}
        
        
    
    

