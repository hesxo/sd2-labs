```java
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    // Global variables
    private static int[][] parkingStructure = null;
    private static VehicleOwner[] owners = new VehicleOwner[20];
    private static int ownerCounter = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot Reservation App!");
        initialiseZones();
        runMenu();
    }

    public static void initialiseZones() {
        parkingStructure = new int[3][];
        parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
        parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
        parkingStructure[2] = new int[8]; // Zone C: Luxury (8 slots)
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
                    addZone();
                    break;
                case 4:
                    registerOwner();
                    break;
                case 5:
                    saveToFile();
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
        int zoneIndex = zoneInput.equals("A") ? 0 : zoneInput.equals("B") ? 1 : zoneInput.equals("C") ? 2 : -1;
        

        if (zoneIndex == -1) {
            System.out.println("Invalid zone. Please enter A or B or C.");
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

        String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)"};
        for (int i = 0; i < parkingStructure.length; i++) {
            System.out.print(zoneLabels[i] + "  ");
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

    private static void addZone() {
        // method not implemented
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter zone name using single character: "); // F
        char zoneInput = sc.nextLine().trim().charAt(0);
        System.out.print("Enter number of slots for zone: ");
        String zoneSlots = sc.nextInt();
        int[][] newStructure = new int[parkingStructure.length + 1][];
        for (int i = 0; i < parkingStructure.length; i++) {
          newStructure[i] = parkingStructure[i];
        }
        newStructure[parkingStructure.length + 1] = new int[zoneSlots];
        parkingStructure = newStructure
        System.out.println(zoneInput + " zone added with " + zoneSlots + " slots"); // F zone added with 10 slots
    }

    private static void registerOwner() {
        // method not implemented
        Scanner sc = new Scanner(System.in);
        
        if (ownerCounter > owners.length) {
          System.out.println("Owner limit has reached cannot register...");
          return;
        }
        // String name, String vehicleRegNo, String contactNumber
        System.out.print("Enter name: "); // HASAL     
        String name = sc.nextLine().trim();
        System.out.print("Enter vehicle reg no");
        String vehicleRegNo = sc.nextLine().trim(); // AbC1231 => abc1231 
        System.out.print("Enter contact no: ");
        String contactNumber = sc.nextLine().trim();
        
        VehicleOwner newOwner = new VehicleOwner(name, vehicleRegNo, contactNumber);
        owners[ownerCounter++] = newOwner;
        System.out.println("New vehicle owner registered.");
    }
    
    private static void searchOwnerByVehicleRegNo() {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Vehicle Reg No. : ");
      String search = sc.nextLine().trim();
      boolean found = false;
      for (int i = 0; i < owners.length; i++) {
        if (owners[i].getVehicleRegNo().equalsIgnoreCase(search)) {
          owners[i].printOwnerDetails();
          found = true;
          break;
        }
      }
      
      if (!found) {
        System.out.println("No owner match found.");
      }
    }

    private static void saveToFile() {
        try {
            FileWriter file = new FileWriter("ParkingData.txt");
            file.write("Data:\n");
            // Implement save data here based on test task
            String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C (Luxury)"};
            for (int i = 0; i < parkingStructure.length; i++) {
                file.write(zoneLabels[i] + "  ");
                for (int j = 0; j < parkingStructure[i].length; j++) {
                    if (parkingStructure[i][j] == 0) {
                        file.write("[O]");
                    } else {
                        file.write("[X]");
                    }
                }
                file.write("\n");
            }
            for (int j = 0; j < owners.length; j++) {
              fw.write(owners[j].getName() + " " + owners[j].getVehicleRegNo() + " " + owners[j].getContactNumber());
            }
            file.close();
            System.out.println("Data saved to file.");
        } catch (IOException exception) {
            System.out.println("Error while writing to file.");
        }
    }
}

public class VehicleOwner {
  private String name;
  private String vehicleRegNo;
  private String contactNumber;
  
  public VehicleOwner(String name, String vehicleRegNo, String contactNumber) {
    this.name = name;
    this.vehicleRegNo = vehicleRegNo;
    this.contactNumber = contactNumber;
  }
  
  public String getName() {
    return name;
  }
  
  public String getVehicleRegNo() {
    return vehicleRegNo;
  }
  
  public String getContactNumber() {
    return contactNumber;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setVehicleRegNo(String vehicleRegNo) {
    this.vehicleRegNo = vehicleRegNo;
  }
  
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }
  
  public void printOwnerDetails() {
    System.out.println("Name : " + name);
    System.out.println("Vehicle Reg No. : " + vehicleRegNo);
    System.out.println("Contact Number : " + contactNumber);
  }
}
```
