# Guess 1 out of 4

## **QUESTION 1: Understanding Arrays and Data Structures (15 marks)**

### Task 1.1: Explain the parking structure (5 marks)
**Question:** Explain how the `parkingStructure` variable works in the Parking Lot App. What type of data structure is it and how are the zones organized?

**Answer:**
The `parkingStructure` is a 2D array (multi-dimensional array) of type `int[][]`. It represents the parking lot with two zones:
- `parkingStructure[0]` = Zone A (Compact) with 10 slots
- `parkingStructure[1]` = Zone B (Standard) with 14 slots

Each slot stores:
- `0` = Available/Unoccupied
- `1` = Reserved/Occupied

### Task 1.2: Add a new zone (10 marks)
**Question:** Complete the `addZone()` method to add Zone C (Luxury) with 8 slots. The method should create a new parking structure that includes the existing zones plus the new one.

**Answer:**
```java
private static void addZone() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter zone name (C for Luxury): ");
    String zoneName = input.next().toUpperCase();
    
    if (!zoneName.equals("C")) {
        System.out.println("Invalid zone. Only Zone C (Luxury) can be added.");
        return;
    }
    
    System.out.print("Enter number of slots for Zone C: ");
    int slots = input.nextInt();
    
    // Create new parking structure with 3 zones
    int[][] newStructure = new int[3][];
    newStructure[0] = parkingStructure[0]; // Zone A
    newStructure[1] = parkingStructure[1]; // Zone B
    newStructure[2] = new int[slots];      // Zone C
    
    parkingStructure = newStructure;
    System.out.println("Zone C (Luxury) with " + slots + " slots added successfully!");
    showParkingArea();
}
```

---

## **QUESTION 2: Method Implementation and Logic (20 marks)**

### Task 2.1: Enhanced reserveSlot method (10 marks)
**Question:** Modify the existing `reserveSlot()` method to also handle Zone C (if it exists) and add validation for zone input.

**Answer:**
```java
private static void reserveSlot() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter zone (A/B" + (parkingStructure.length > 2 ? "/C" : "") + "): ");
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
            if (parkingStructure.length > 2) {
                zoneIndex = 2;
            } else {
                System.out.println("Zone C does not exist.");
                return;
            }
            break;
        default:
            System.out.println("Invalid zone. Please enter A, B" + (parkingStructure.length > 2 ? ", or C" : "") + ".");
            return;
    }

    System.out.print("Enter slot number: ");
    int slot = input.nextInt() - 1;

    if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
        System.out.println("Invalid slot number. Zone " + zoneInput + " has " + parkingStructure[zoneIndex].length + " slots.");
        return;
    }

    if (parkingStructure[zoneIndex][slot] == 0) {
        parkingStructure[zoneIndex][slot] = 1;
        System.out.println("Reservation successful for Zone " + zoneInput + ", Slot " + (slot + 1) + ".");
        showParkingArea();
    } else {
        System.out.println("This slot is already reserved.");
    }
}
```

### Task 2.2: Update showParkingArea method (10 marks)
**Question:** Modify the `showParkingArea()` method to display Zone C when it exists.

**Answer:**
```java
private static void showParkingArea() {
    System.out.println("=".repeat(60));
    System.out.println("                PARKING LOT STATUS             ");
    System.out.println("=".repeat(60));

    String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C (Luxury)"};
    
    for (int i = 0; i < parkingStructure.length; i++) {
        System.out.printf("%-20s", zoneLabels[i]);
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
    
    // Display statistics
    for (int i = 0; i < parkingStructure.length; i++) {
        int available = 0, occupied = 0;
        for (int j = 0; j < parkingStructure[i].length; j++) {
            if (parkingStructure[i][j] == 0) available++;
            else occupied++;
        }
        char zoneLetter = (char)('A' + i);
        System.out.println("Zone " + zoneLetter + ": " + available + " available, " + occupied + " occupied");
    }
    System.out.println();
}
```

---

## **QUESTION 3: Object-Oriented Programming and Encapsulation (25 marks)**

### Task 3.1: Create VehicleOwner class (15 marks)
**Question:** Create a `VehicleOwner` class with proper encapsulation that stores owner name, vehicle registration number, and contact number.

**Answer:**
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
    public void displayOwnerDetails() {
        System.out.println("Owner: " + ownerName);
        System.out.println("Vehicle Registration: " + vehicleRegistration);
        System.out.println("Contact: " + contactNumber);
    }
    
    // toString method for easy printing
    @Override
    public String toString() {
        return ownerName + " " + vehicleRegistration + " " + contactNumber;
    }
}
```

### Task 3.2: Implement registerOwner method (10 marks)
**Question:** Complete the `registerOwner()` method to collect user input and store VehicleOwner objects in a global array.

**Answer:**
First, add these global variables to the App class:
```java
// Add these global variables at the top of App class
static VehicleOwner[] owners = new VehicleOwner[50];
static int ownerCount = 0;
```

Then implement the method:
```java
private static void registerOwner() {
    if (ownerCount >= owners.length) {
        System.out.println("Maximum number of owners reached.");
        return;
    }
    
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter owner name: ");
    String ownerName = input.nextLine();
    
    System.out.print("Enter vehicle registration number: ");
    String vehicleReg = input.nextLine();
    
    System.out.print("Enter contact number: ");
    String contactNumber = input.nextLine();
    
    // Create and store the new owner
    owners[ownerCount] = new VehicleOwner(ownerName, vehicleReg, contactNumber);
    ownerCount++;
    
    System.out.println("Vehicle owner registered successfully!");
    System.out.println("Total registered owners: " + ownerCount);
}
```

---

## **QUESTION 4: File I/O and Data Management (20 marks)**

### Task 4.1: Complete saveToFile method (10 marks)
**Question:** Complete the `saveToFile()` method to save all parking data and registered owners to "ParkingData.txt".

**Answer:**
```java
private static void saveToFile() {
    try {
        FileWriter file = new FileWriter("ParkingData.txt");
        file.write("PARKING LOT DATA\n");
        file.write("================\n\n");
        
        // Save parking structure data
        file.write("PARKING STRUCTURE:\n");
        String[] zoneNames = {"Zone A (Compact)", "Zone B (Standard)", "Zone C (Luxury)"};
        
        for (int i = 0; i < parkingStructure.length; i++) {
            file.write(zoneNames[i] + ": ");
            for (int j = 0; j < parkingStructure[i].length; j++) {
                file.write(parkingStructure[i][j] == 0 ? "O" : "X");
            }
            file.write("\n");
        }
        
        file.write("\nREGISTERED OWNERS:\n");
        file.write("==================\n");
        for (int i = 0; i < ownerCount; i++) {
            file.write(owners[i].toString() + "\n");
        }
        
        // Save statistics
        file.write("\nSTATISTICS:\n");
        file.write("===========\n");
        int totalSlots = 0, totalOccupied = 0;
        
        for (int i = 0; i < parkingStructure.length; i++) {
            int zoneOccupied = 0;
            for (int j = 0; j < parkingStructure[i].length; j++) {
                totalSlots++;
                if (parkingStructure[i][j] == 1) {
                    zoneOccupied++;
                    totalOccupied++;
                }
            }
            char zoneLetter = (char)('A' + i);
            file.write("Zone " + zoneLetter + ": " + zoneOccupied + "/" + parkingStructure[i].length + " occupied\n");
        }
        
        file.write("Total: " + totalOccupied + "/" + totalSlots + " occupied\n");
        file.write("Registered owners: " + ownerCount + "\n");
        
        file.close();
        System.out.println("Data saved to ParkingData.txt successfully.");
    } catch (IOException exception) {
        System.out.println("Error while writing to file: " + exception.getMessage());
    }
}
```

### Task 4.2: Add search functionality (10 marks)
**Question:** Add a new menu option (6) to search for vehicle owners by registration number.

**Answer:**
First, update the menu in `getOption()` method:
```java
// Add this line in the menu display
System.out.println("|  6) Search owner by registration            |");
```

Add this case in the switch statement in `runMenu()`:
```java
case 6:
    searchOwnerByRegistration();
    break;
```

Implement the search method:
```java
private static void searchOwnerByRegistration() {
    if (ownerCount == 0) {
        System.out.println("No owners registered yet.");
        return;
    }
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter vehicle registration number: ");
    String regNumber = input.nextLine();
    
    boolean found = false;
    for (int i = 0; i < ownerCount; i++) {
        if (owners[i].getVehicleRegistration().equalsIgnoreCase(regNumber)) {
            System.out.println("Owner found:");
            owners[i].displayOwnerDetails();
            found = true;
            break;
        }
    }
    
    if (!found) {
        System.out.println("No owner found with registration number: " + regNumber);
    }
}
```

---

## **QUESTION 5: Error Handling and Validation (10 marks)**

### Task 5.1: Enhanced input validation (10 marks)
**Question:** Add comprehensive error handling to the `reserveSlot()` method including try-catch blocks for invalid input.

**Answer:**
```java
private static void reserveSlot() {
    Scanner input = new Scanner(System.in);
    
    try {
        System.out.print("Enter zone (A/B" + (parkingStructure.length > 2 ? "/C" : "") + "): ");
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
                if (parkingStructure.length > 2) {
                    zoneIndex = 2;
                } else {
                    System.out.println("Zone C does not exist.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid zone. Please enter A, B" + (parkingStructure.length > 2 ? ", or C" : "") + ".");
                return;
        }

        System.out.print("Enter slot number (1-" + parkingStructure[zoneIndex].length + "): ");
        int slot;
        
        try {
            slot = input.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            input.next(); // Clear buffer
            return;
        }

        if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
            System.out.println("Invalid slot number. Zone " + zoneInput + " has slots 1-" + parkingStructure[zoneIndex].length + ".");
            return;
        }

        if (parkingStructure[zoneIndex][slot] == 0) {
            parkingStructure[zoneIndex][slot] = 1;
            System.out.println("Reservation successful for Zone " + zoneInput + ", Slot " + (slot + 1) + ".");
            showParkingArea();
        } else {
            System.out.println("Slot " + (slot + 1) + " in Zone " + zoneInput + " is already reserved.");
        }
        
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }
}
```

---

## **QUESTION 6: Advanced Features (10 marks)**

### Task 6.1: Add unreserve functionality (10 marks)
**Question:** Add a new menu option (7) to unreserve a parking slot.

**Answer:**
Update menu and add switch case, then implement:
```java
private static void unreserveSlot() {
    Scanner input = new Scanner(System.in);
    
    try {
        System.out.print("Enter zone to unreserve (A/B" + (parkingStructure.length > 2 ? "/C" : "") + "): ");
        String zoneInput = input.next().toUpperCase();
        
        int zoneIndex = -1;
        switch (zoneInput) {
            case "A": zoneIndex = 0; break;
            case "B": zoneIndex = 1; break;
            case "C": 
                if (parkingStructure.length > 2) zoneIndex = 2;
                else {
                    System.out.println("Zone C does not exist.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid zone.");
                return;
        }

        System.out.print("Enter slot number to unreserve: ");
        int slot = input.nextInt() - 1;

        if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
            System.out.println("Invalid slot number.");
            return;
        }

        if (parkingStructure[zoneIndex][slot] == 1) {
            parkingStructure[zoneIndex][slot] = 0;
            System.out.println("Slot " + (slot + 1) + " in Zone " + zoneInput + " has been unreserved.");
            showParkingArea();
        } else {
            System.out.println("Slot " + (slot + 1) + " in Zone " + zoneInput + " is already available.");
        }
        
    } catch (Exception e) {
        System.out.println("Invalid input. Please try again.");
        input.next(); // Clear buffer
    }
}
```

---

## **Summary of Key Concepts Tested:**

1. **Arrays and Data Structures** - 2D arrays, initialization, manipulation
2. **Method Implementation** - Completing stub methods with proper logic
3. **Object-Oriented Programming** - Encapsulation, constructors, getters/setters
4. **File I/O** - Writing data to files with proper error handling
5. **Input Validation** - Try-catch blocks, input sanitization
6. **Search Algorithms** - Linear search through arrays
7. **Menu-Driven Programming** - Switch statements, loop control
8. **Error Handling** - Exception handling, user feedback

This comprehensive set covers all the fundamental concepts that would be tested in the Software Development II exam, following the same pattern as the Pet Clinic mock but applied to the Parking Lot case study.
