package com.kce.logistics.main;

import com.kce.logistics.model.Customer;
import com.kce.logistics.service.LogisticService;

import java.util.*;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LogisticService service = new LogisticService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Logistics Management ---");
            System.out.println("1. Create Parcel");
            System.out.println("2. Create Shipment");
            System.out.println("3. Add Hub");
            System.out.println("4. Record Scan");
            System.out.println("5. Record Delivery Attempt");
            System.out.println("6. Show Parcel Status");
            System.out.println("7. Show Shipment Summary");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Parcel ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("Customer ID: ");
                    String cid = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Address: ");
                    String addr = scanner.nextLine();
                    service.createParcel(pid, new Customer(cid, name, addr));
                    System.out.println("Parcel Created.");
                }
                case 2 -> {
                    System.out.print("Shipment ID: ");
                    String sid = scanner.nextLine();
                    System.out.print("Enter Parcel IDs (comma-separated): ");
                    String[] ids = scanner.nextLine().split(",");
                    service.createShipment(sid, Arrays.asList(ids));
                    System.out.println("Shipment Created.");
                }
                case 3 -> {
                    System.out.print("Hub ID: ");
                    String hid = scanner.nextLine();
                    System.out.print("Hub Name: ");
                    String hname = scanner.nextLine();
                    service.addHub(hid, hname);
                    System.out.println("Hub Added.");
                }
                case 4 -> {
                    System.out.print("Parcel ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("Hub ID: ");
                    String hid = scanner.nextLine();
                    service.recordScan(pid, hid);
                    System.out.println("Scan Recorded.");
                }
                case 5 -> {
                    System.out.print("Parcel ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("Successful? (true/false): ");
                    boolean success = scanner.nextBoolean();
                    scanner.nextLine(); // consume newline
                    service.recordDeliveryAttempt(pid, success);
                    System.out.println("Delivery Attempt Recorded.");
                }
                case 6 -> {
                    System.out.print("Parcel ID: ");
                    String pid = scanner.nextLine();
                    System.out.println(service.getParcelStatus(pid));
                }
                case 7 -> {
                    System.out.print("Shipment ID: ");
                    String sid = scanner.nextLine();
                    System.out.println(service.getShipmentSummary(sid));
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
