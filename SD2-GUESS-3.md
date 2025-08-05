# 4COSC010C Software Development II - Lab-based Assessment | Guess 3
## Parking Lot Reservation App - FINAL EXAM QUESTIONS & ANSWERS

**Duration:** 2 hours  
**Total Marks:** 100  
**Instructions:** Complete all questions. Provide complete working code for all programming tasks.

---

## **SECTION A: Array Management and Data Structures (25 marks)**

### **Question 1: Understanding the Parking Structure (5 marks)**
**Explain how the `parkingStructure` 2D array works in the given code. What do the values 0 and 1 represent?**

**ANSWER:**
The `parkingStructure` is a 2D array of integers that represents the parking lot:
- `parkingStructure[0]` represents Zone A (Compact) with 10 slots
- `parkingStructure[1]` represents Zone B (Standard) with 14 slots
- Value `0` = Available/Empty slot
- Value `1` = Reserved/Occupied slot

### **Question 2: Add Zone C Implementation (20 marks)**
**The parking lot needs to expand. Add Zone C (Luxury) with 8 slots.**

**(a) Modify the `initialiseZones()` method to include Zone C (10 marks)**

**ANSWER:**
```java
public static void initialiseZones() {
    parkingStructure = new int[3][]; // Changed from [2] to [3]
    parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
    parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
    parkingStructure[2] = new int[8];  // Zone C: Luxury (8 slots)
}
```

**(b) Update the `reserveSlot()` method to handle Zone C (10 marks)**

**ANSWER:**
```java
private static void reserveSlot() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter zone (A/B/C): ");
    String zoneInput = input.next().toUpperCase();
    
    int zoneIndex = -1;
    switch (zoneInput) {
        case "A":
            zoneIndex = 0;
            break;
        case "B":
            zoneIndex = 1;
            break;
        case "C":
            zoneIndex = 2;
            break;
        default:
            System.out.println("Invalid zone. Please enter A, B, or C.");
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
```

---

## **SECTION B: Object-Oriented Programming (30 marks)**

### **Question 3: VehicleOwner Class with Encapsulation (30 marks)**
**Create a complete `VehicleOwner` class following encapsulation principles.**

**Requirements:**
- Private fields: `ownerName`, `vehicleRegistration`, `contactNumber`
- Constructor to initialize all fields
- Getter and setter methods for all fields
- A method `displayDetails()` to print owner information

**ANSWER:**
```java
public class VehicleOwner {
    // Private fields for encapsulation
    private String ownerName;
    private String vehicleRegistration;
    private String contactNumber;
    
    // Constructor
    public VehicleOwner(String ownerName, String vehicleRegistration, String contactNumber) {
        this.ownerName = ownerName;
        this.vehicleRegistration = vehicleRegistration;
        this.contactNumber = contactNumber;
    }
    
    // Getter methods
    public String getOwnerName() {
        return ownerName;
    }
    
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    // Setter methods
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    // Method to display owner details
    public void displayDetails() {
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Vehicle Registration: " + vehicleRegistration);
        System.out.println("Contact Number: " + contactNumber);
    }
    
    // toString method for file operations
    @Override
    public String toString() {
        return ownerName + " " + vehicleRegistration + " " + contactNumber;
    }
}
```

---

## **SECTION C: Method Implementation (30 marks)**

### **Question 4: Complete the registerOwner() method (15 marks)**
**Implement the `registerOwner()` method to collect user input and store VehicleOwner objects in a global array.**

**First, add these global variables to the App class:**
```java
static VehicleOwner[] owners = new VehicleOwner[20];
static int ownerCount = 0;
```

**ANSWER:**
```java
private static void registerOwner() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter owner name: ");
    String ownerName = input.nextLine();
    
    System.out.print("Enter vehicle registration number: ");
    String vehicleReg = input.nextLine();
    
    System.out.print("Enter contact number: ");
    String contactNumber = input.nextLine();
    
    // Create new VehicleOwner object and store it
    owners[ownerCount] = new VehicleOwner(ownerName, vehicleReg, contactNumber);
    ownerCount++;
    
    System.out.println("Vehicle owner registered successfully!");
}
```

### **Question 5: Implement searchOwnerByRegistration() method (15 marks)**
**Add a search functionality to find vehicle owners by their registration number.**

**(a) Add menu option 6 in the `getOption()` method (5 marks)**

**ANSWER:**
```java
// Add this line in the menu display
System.out.println("|  6) Search Owner by Registration            |");
```

**(b) Add case 6 in the `runMenu()` switch statement (5 marks)**

**ANSWER:**
```java
case 6:
    searchOwnerByRegistration();
    break;
```

**(c) Implement the `searchOwnerByRegistration()` method (5 marks)**

**ANSWER:**
```java
private static void searchOwnerByRegistration() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter vehicle registration to search: ");
    String searchReg = input.nextLine();
    
    boolean found = false;
    for (int i = 0; i < ownerCount; i++) {
        if (owners[i].getVehicleRegistration().equalsIgnoreCase(searchReg)) {
            System.out.println("Owner found:");
            owners[i].displayDetails();
            found = true;
            break;
        }
    }
    
    if (!found) {
        System.out.println("No owner found with registration: " + searchReg);
    }
}
```

---

## **SECTION D: File Operations (10 marks)**

### **Question 6: Complete the saveToFile() method (10 marks)**
**Modify the `saveToFile()` method to save all registered vehicle owner details to "ParkingData.txt". Each owner should be saved in the format: `OwnerName VehicleRegistration ContactNumber`**

**ANSWER:**
```java
private static void saveToFile() {
    try {
        FileWriter file = new FileWriter("ParkingData.txt");
        
        file.write("PARKING LOT DATA\n");
        file.write("================\n\n");
        
        // Save parking structure
        file.write("Parking Structure:\n");
        String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C (Luxury)"};
        
        for (int i = 0; i < parkingStructure.length; i++) {
            file.write(zoneLabels[i] + ": ");
            for (int j = 0; j < parkingStructure[i].length; j++) {
                file.write(parkingStructure[i][j] == 0 ? "O" : "X");
            }
            file.write("\n");
        }
        
        file.write("\nRegistered Owners:\n");
        file.write("==================\n");
        
        // Save each owner in the specified format
        for (int i = 0; i < ownerCount; i++) {
            file.write(owners[i].toString() + "\n");
        }
        
        file.close();
        System.out.println("Data saved to file.");
    } catch (IOException exception) {
        System.out.println("Error while writing to file.");
    }
}
```

---

## **SECTION E: Display Updates (5 marks)**

### **Question 7: Update showParkingArea() for Zone C (5 marks)**
**Modify the `showParkingArea()` method to display Zone C when it exists.**

**ANSWER:**
```java
private static void showParkingArea() {
    System.out.println("=".repeat(60));
    System.out.println("                PARKING LOT STATUS             ");
    System.out.println("=".repeat(60));

    String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C (Luxury)"};
    
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
```

---

## **COMPLETE UPDATED App.java CLASS**

**Here is the complete App.java with all modifications:**

```java
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    // Global variables
    private static int[][] parkingStructure = null;
    static VehicleOwner[] owners = new VehicleOwner[20];
    static int ownerCount = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot Reservation App!");
        initialiseZones();
        runMenu();
    }

    public static void initialiseZones() {
        parkingStructure = new int[3][];
        parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
        parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
        parkingStructure[2] = new int[8];  // Zone C: Luxury (8 slots)
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
                case 6:
                    searchOwnerByRegistration();
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
            System.out.println("|  6) Search Owner by Registration            |");
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

    // All other methods as implemented above...
}
```

---

## **MARKING SCHEME SUMMARY:**

| **Section** | **Question** | **Marks** | **Key Concepts Tested** |
|-------------|--------------|-----------|-------------------------|
| **A** | Q1: Parking Structure | 5 | Array understanding |
| **A** | Q2a: Initialize Zones | 10 | Array initialization |
| **A** | Q2b: Update reserveSlot | 10 | Array manipulation, switch statements |
| **B** | Q3: VehicleOwner Class | 30 | Encapsulation, constructors, methods |
| **C** | Q4: registerOwner method | 15 | Input handling, object creation |
| **C** | Q5: Search functionality | 15 | Linear search, menu updates |
| **D** | Q6: File operations | 10 | File I/O, data formatting |
| **E** | Q7: Display updates | 5 | Array traversal, output formatting |
| | **TOTAL** | **100** | |

**This structured approach ensures comprehensive coverage of all Software Development II fundamental concepts while maintaining the exact same difficulty level and style as the MOCK exam.**
