import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; // [NEW] For dynamic zone labels and owner list
import java.util.Scanner;

public class Main { // [CHANGED] Class name changed from App to Main

    // Global variables
    private static int[][] parkingStructure = null;

    // New variables
    private static String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)"}; // [NEW]
    private static ArrayList<String> extraZoneLabels = new ArrayList<>(); // [NEW]
    private static ArrayList<VehicleOwner> owners = new ArrayList<>(); // [NEW]

    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot Reservation App!");
        initialiseZones();
        runMenu();
    }

    public static void initialiseZones() {
        parkingStructure = new int[2][];
        parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
        parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
    }

    public static void runMenu() {
        int option;
        boolean cont = true;
        while (cont) {
            option = getOption();
            switch (option) {
                case 0:
                    cont = false;
                    break;
                case 1:
                    reserveSlot();
                    break;
                case 2:
                    showParkingArea();
                    break;
                case 3:
                    addZone(); // [NEW]
                    break;
                case 4:
                    registerOwner(); // [NEW]
                    break;
                case 5:
                    saveToFile(); // [MODIFIED]
                    break;
                default:
                    System.out.println("Option not available. Please select a valid option:");
            }
        }
    }

    private static int getOption() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int option = -1;
        do {
            System.out.println();
            System.out.println("+---------------------------------------------+");
            System.out.println("|                 MAIN MENU                   |");
            System.out.println("+---------------------------------------------+");
            System.out.println("|  1) Reserve a parking slot                  |");
            System.out.println("|  2) Show parking area                       |");
            System.out.println("|  3) Add new parking zone                    |");
            System.out.println("|  4) Register vehicle owner                  |");
            System.out.println("|  5) Save information to file                |");
            System.out.println("|  0) Quit                                    |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Please select an option: ");
            try {
                option = input.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("This option is not valid.");
                input.next(); // Clear buffer
            }
        } while (!valid);
        return option;
    }

    private static void reserveSlot() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter zone (A/B): ");
        String zoneInput = input.next().toUpperCase();
        int zoneIndex = zoneInput.equals("A") ? 0 : zoneInput.equals("B") ? 1 : -1;

        if (zoneIndex == -1) {
            System.out.println("Invalid zone. Please enter A or B.");
            return;
        }

        System.out.print("Enter slot number: ");
        int slot = input.nextInt() - 1;

        if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
            System.out.println("Invalid slot number.");
            return;
        }

        if (parkingStructure[zoneIndex][slot] == 0) {
            parkingStructure[zoneIndex][slot] = 1;
            System.out.println("Reservation successful.");
            showParkingArea();
        } else {
            System.out.println("This slot is already reserved.");
        }
    }

    private static void showParkingArea() {
        System.out.println("=".repeat(60));
        System.out.println("                PARKING LOT STATUS             ");
        System.out.println("=".repeat(60));

        for (int i = 0; i < parkingStructure.length; i++) {
            System.out.print(getZoneLabel(i) + "  "); // [MODIFIED] Replaced static label array
            for (int j = 0; j < parkingStructure[i].length; j++) {
                if (parkingStructure[i][j] == 0) {
                    System.out.print("[O]");
                } else {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("LEGEND: [O] = Available | [X] = Reserved");
        System.out.println("=".repeat(60));
        System.out.println();
    }

    private static void addZone() { // [NEW]
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new zone name (e.g., Zone C): ");
        String zoneName = input.nextLine();
        System.out.print("Enter number of slots in this zone: ");
        int numSlots = input.nextInt();

        int[][] newStructure = new int[parkingStructure.length + 1][];
        for (int i = 0; i < parkingStructure.length; i++) {
            newStructure[i] = parkingStructure[i];
        }
        newStructure[parkingStructure.length] = new int[numSlots];
        parkingStructure = newStructure;

        extraZoneLabels.add(zoneName + " (Custom)"); // [NEW]

        System.out.println(zoneName + " added successfully with " + numSlots + " slots.");
    }

    private static void registerOwner() { // [NEW]
        Scanner input = new Scanner(System.in);
        System.out.print("Enter owner's full name: ");
        String name = input.nextLine();
        System.out.print("Enter vehicle registration number: ");
        String reg = input.nextLine();

        owners.add(new VehicleOwner(name, reg)); // [NEW]
        System.out.println("Vehicle owner registered successfully.");
    }

    private static void saveToFile() { // [MODIFIED]
        try {
            FileWriter file = new FileWriter("ParkingData.txt"); // [MODIFIED]
            file.write("=== Parking Slot Status ===\n"); // [MODIFIED]
            for (int i = 0; i < parkingStructure.length; i++) {
                file.write(getZoneLabel(i) + ": "); // [MODIFIED]
                for (int j = 0; j < parkingStructure[i].length; j++) {
                    file.write(parkingStructure[i][j] == 0 ? "[O]" : "[X]"); // [MODIFIED]
                }
                file.write("\n");
            }

            file.write("\n=== Registered Vehicle Owners ===\n"); // [NEW]
            for (VehicleOwner owner : owners) { // [NEW]
                file.write("Name: " + owner.name + ", Reg#: " + owner.regNo + "\n"); // [NEW]
            }

            file.close();
            System.out.println("Data saved to file.");
        } catch (IOException exception) {
            System.out.println("Error while writing to file.");
        }
    }

    private static String getZoneLabel(int index) { // [NEW]
        if (index < zoneLabels.length) {
            return zoneLabels[index];
        } else {
            return extraZoneLabels.get(index - zoneLabels.length);
        }
    }

    // Inner class for vehicle owner [NEW]
    static class VehicleOwner {
        String name;
        String regNo;

        VehicleOwner(String name, String regNo) {
            this.name = name;
            this.regNo = regNo;
        }
    }
}
