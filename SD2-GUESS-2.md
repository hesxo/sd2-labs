# Guess 2 out of 4

## **QUESTION 1: Array Implementation and Zone Management (20 marks)**

### **Q1 - Task 1: Add Zone C (Rabbit Slots equivalent)** (10 marks)
**Based on MOCK: "rabbitSlots – 10 appointment slots for rabbits (you are required to add and use this)"**

**Question:** The parking system currently has Zone A and Zone B. Add Zone C (Luxury) with 8 parking slots. Modify the `initialiseZones()` method to include this third zone.

**Answer:**
```java
public static void initialiseZones() {
    parkingStructure = new int[3][]; // Changed from 2 to 3
    parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
    parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
    parkingStructure[2] = new int[8];  // Zone C: Luxury (8 slots) - ADDED
}
```

### **Q1 - Task 2: Update reserveSlot() for Zone C** (10 marks)
**Question:** Modify the `reserveSlot()` method to handle Zone C input.

**Answer:**
```java
private static void reserveSlot() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter zone (A/B/C): "); // Added C
    String zoneInput = input.next().toUpperCase();
    
    int zoneIndex = -1;
    if (zoneInput.equals("A")) zoneIndex = 0;
    else if (zoneInput.equals("B")) zoneIndex = 1;
    else if (zoneInput.equals("C")) zoneIndex = 2; // ADDED
    
    if (zoneIndex == -1) {
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

## **QUESTION 2: Object-Oriented Programming - VehicleOwner Class (25 marks)**

### **Q2 - Task: Complete VehicleOwner class with encapsulation** (25 marks)
**Based on MOCK: "PetOwner.java – a class representing the pet owner, which students are required to complete using encapsulation"**

**Question:** Complete the `VehicleOwner.java` class using proper encapsulation principles. The class should store owner name, vehicle registration, and contact number.

**Answer:**
```java
public class VehicleOwner {
    // Private fields (encapsulation)
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
    
    // Method to print owner details (like printOwnerDetails() in mock)
    public void printOwnerDetails() {
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Vehicle Registration: " + vehicleRegistration);
        System.out.println("Contact Number: " + contactNumber);
    }
}
```

---

## **QUESTION 3: Method Implementation (30 marks)**

### **Q3 - Task 1: Complete registerOwner() method** (10 marks)
**Based on MOCK: "The registerOwner() method is called when the user selects option 2"**

**Question:** Complete the `registerOwner()` method to collect user input and store VehicleOwner objects.

**Answer:**
First, add global variables at the top of App class:
```java
// Add these global variables
static VehicleOwner[] owners = new VehicleOwner[20];
static int ownerCount = 0;
```

Then implement the method:
```java
private static void registerOwner() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter owner name: ");
    String ownerName = input.nextLine();
    
    System.out.print("Enter vehicle registration: ");
    String vehicleReg = input.nextLine();
    
    System.out.print("Enter contact number: ");
    String contactNumber = input.nextLine();
    
    // Create and store the owner
    owners[ownerCount] = new VehicleOwner(ownerName, vehicleReg, contactNumber);
    ownerCount++;
    
    System.out.println("Vehicle owner registered successfully!");
}
```

### **Q3 - Task 2: Implement addZone() method** (10 marks)
**Based on MOCK pattern of completing stub methods**

**Question:** Complete the `addZone()` method to add a new parking zone dynamically.

**Answer:**
```java
private static void addZone() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter zone letter (D, E, etc.): ");
    String zoneLetter = input.next().toUpperCase();
    
    System.out.print("Enter number of slots: ");
    int numSlots = input.nextInt();
    
    // Create new parking structure with one more zone
    int[][] newStructure = new int[parkingStructure.length + 1][];
    
    // Copy existing zones
    for (int i = 0; i < parkingStructure.length; i++) {
        newStructure[i] = parkingStructure[i];
    }
    
    // Add new zone
    newStructure[parkingStructure.length] = new int[numSlots];
    
    parkingStructure = newStructure;
    
    System.out.println("Zone " + zoneLetter + " with " + numSlots + " slots added successfully!");
    showParkingArea();
}
```

### **Q3 - Task 3: Add search functionality** (10 marks)
**Based on MOCK: "searchOwnerByPetName() method"**

**Question:** Add a new menu option and method to search for vehicle owners by registration number.

**Answer:**
Update the menu in `getOption()` method:
```java
System.out.println("|  6) Search Owner by Registration            |");
```

Add case in `runMenu()`:
```java
case 6:
    searchOwnerByRegistration();
    break;
```

Implement the search method:
```java
private static void searchOwnerByRegistration() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter vehicle registration to search: ");
    String regToSearch = input.next();
    
    boolean found = false;
    for (int i = 0; i < ownerCount; i++) {
        if (owners[i].getVehicleRegistration().equalsIgnoreCase(regToSearch)) {
            System.out.println("Owner found:");
            owners[i].printOwnerDetails();
            found = true;
            break;
        }
    }
    
    if (!found) {
        System.out.println("No owner found for registration: " + regToSearch);
    }
}
```

---

## **QUESTION 4: File Operations (15 marks)**

### **Q4 - Task: Complete saveToFile() method** (15 marks)
**Based on MOCK: "All registered pet owner details are saved in ClinicData.txt"**

**Question:** Complete the `saveToFile()` method to save all registered vehicle owner details to "ParkingData.txt". Each line should follow the format: `OwnerName VehicleRegistration ContactNumber`

**Answer:**
```java
private static void saveToFile() {
    try {
        FileWriter file = new FileWriter("ParkingData.txt");
        
        // Write parking structure data
        file.write("Parking Structure Data:\n");
        String[] zoneNames = {"Zone A", "Zone B", "Zone C"};
        
        for (int i = 0; i < parkingStructure.length && i < zoneNames.length; i++) {
            file.write(zoneNames[i] + ": ");
            for (int j = 0; j < parkingStructure[i].length; j++) {
                file.write(parkingStructure[i][j] == 0 ? "O" : "X");
            }
            file.write("\n");
        }
        
        file.write("\nRegistered Owners:\n");
        // Save each owner's details in the specified format
        for (int i = 0; i < ownerCount; i++) {
            file.write(owners[i].getOwnerName() + " " + 
                      owners[i].getVehicleRegistration() + " " + 
                      owners[i].getContactNumber() + "\n");
        }
        
        file.close();
        System.out.println("Data saved to file.");
    } catch (IOException exception) {
        System.out.println("Error while writing to file.");
    }
}
```

---

## **QUESTION 5: Update showParkingArea() for Zone C (10 marks)**

### **Q5 - Task: Modify showParkingArea() method**
**Question:** Update the `showParkingArea()` method to display Zone C when it exists.

**Answer:**
```java
private static void showParkingArea() {
    System.out.println("=".repeat(60));
    System.out.println("                PARKING LOT STATUS             ");
    System.out.println("=".repeat(60));

    String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C (Luxury)"};
    
    for (int i = 0; i < parkingStructure.length; i++) {
        if (i < zoneLabels.length) {
            System.out.print(zoneLabels[i] + "  ");
        } else {
            System.out.print("Zone " + (char)('A' + i) + "  ");
        }
        
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

## **EXACT MAPPING FROM MOCK TO ACTUAL:**

| **MOCK (Pet Clinic)** | **ACTUAL (Parking Lot)** |
|----------------------|---------------------------|
| `dogSlots`, `catSlots`, `rabbitSlots` arrays | `parkingStructure[0]`, `[1]`, `[2]` zones |
| Add `rabbitSlots` array | Add Zone C to parking structure |
| `PetOwner` class with encapsulation | `VehicleOwner` class with encapsulation |
| `registerOwner()` - collect pet owner data | `registerOwner()` - collect vehicle owner data |
| `bookAppointment()` - book pet slots | `reserveSlot()` - reserve parking slots |
| `searchOwnerByPetName()` | Search by vehicle registration |
| Save to `ClinicData.txt` | Save to `ParkingData.txt` |
| Format: `Name PetName ContactNumber` | Format: `OwnerName VehicleRegistration ContactNumber` |

**This version follows the EXACT same structure, complexity level, and question types as the MOCK exam, just adapted to the parking lot context.**
