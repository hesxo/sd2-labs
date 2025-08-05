# X out of X

## **QUESTION 1: Complete VehicleOwner Class (40 marks)**

**Task:** Create the VehicleOwner class with encapsulation. Include private fields, constructor, and all getter/setter methods.

**ANSWER:**
```java
public class VehicleOwner {
    private String ownerName;
    private String vehicleRegistration;
    private String contactNumber;
    
    public VehicleOwner(String ownerName, String vehicleRegistration, String contactNumber) {
        this.ownerName = ownerName;
        this.vehicleRegistration = vehicleRegistration;
        this.contactNumber = contactNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public void printOwnerDetails() {
        System.out.println("Owner: " + ownerName);
        System.out.println("Registration: " + vehicleRegistration);
        System.out.println("Contact: " + contactNumber);
    }
}
```

---

## **QUESTION 2: Complete registerOwner() Method (25 marks)**

**Task:** Complete the registerOwner() method to collect user input and store VehicleOwner objects in the global array.

**Add these global variables first:**
```java
static VehicleOwner[] owners = new VehicleOwner[20];
static int ownerCount = 0;
```

**ANSWER:**
```java
private static void registerOwner() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter your full name: ");
    String fullName = input.nextLine();
    
    System.out.print("Enter your vehicle registration: ");
    String vehicleReg = input.nextLine();
    
    System.out.print("Enter a contact number: ");
    String contactNumber = input.nextLine();
    
    owners[ownerCount] = new VehicleOwner(fullName, vehicleReg, contactNumber);
    ownerCount++;
    
    System.out.println("Vehicle owner registered successfully!");
}
```

---

## **QUESTION 3: Complete addZone() Method (20 marks)**

**Task:** Complete the addZone() method to add Zone C (Luxury) with 8 slots to the parking structure.

**ANSWER:**
```java
private static void addZone() {
    // Create new parking structure with 3 zones
    int[][] newStructure = new int[3][];
    
    // Copy existing zones A and B
    newStructure[0] = parkingStructure[0]; // Zone A
    newStructure[1] = parkingStructure[1]; // Zone B
    newStructure[2] = new int[8];          // Zone C: 8 luxury slots
    
    // Update the parking structure
    parkingStructure = newStructure;
    
    System.out.println("Zone C (Luxury) with 8 slots added successfully!");
    showParkingArea();
}
```

---

## **QUESTION 4: Complete saveToFile() Method (15 marks)**

**Task:** Complete the saveToFile() method to save all registered vehicle owner details to "ParkingData.txt" in the format: Name Registration Contact

**ANSWER:**
```java
private static void saveToFile() {
    try {
        FileWriter file = new FileWriter("ParkingData.txt");
        file.write("Data:\n");
        
        // Write each owner's details
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

## **COMPLETE UPDATED FILES**

### **Updated App.java (Key Changes Only):**

```java
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    // Global variables
    private static int[][] parkingStructure = null;
    static VehicleOwner[] owners = new VehicleOwner[20];  // ADD THIS
    static int ownerCount = 0;                            // ADD THIS

    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot Reservation App!");
        initialiseZones();
        runMenu();
    }

    // All existing methods remain the same...
    // Just implement the 4 methods above
}
```

### **Complete VehicleOwner.java:**
```java
// Use the complete class from Question 1 above
```

---

## **SUMMARY OF CHANGES NEEDED:**

### **Files to Create/Modify:**
1. **VehicleOwner.java** - Create complete class (40 marks)
2. **App.java** - Add 2 global variables + implement 3 methods (60 marks)

### **Methods to Implement:**
- `registerOwner()` - Collect input, create objects (25 marks)
- `addZone()` - Add Zone C with 8 slots (20 marks)  
- `saveToFile()` - Write data to text file (15 marks)

### **Global Variables to Add:**
```java
static VehicleOwner[] owners = new VehicleOwner[20];
static int ownerCount = 0;
```

### **Core Concepts Tested:**
- **Encapsulation** (private fields, getters/setters)
- **Object Creation** (constructor, instantiation)
- **Array Management** (storing objects, expanding arrays)
- **File I/O** (writing to text files)
- **User Input** (Scanner usage)

### **Mark Breakdown:**
- **VehicleOwner Class**: 40 marks (largest portion - OOP focus)
- **registerOwner() Method**: 25 marks (input handling + object creation)
- **addZone() Method**: 20 marks (array manipulation)
- **saveToFile() Method**: 15 marks (file operations)

**This ultra-simple version focuses on the 4 most essential tasks with clean, straightforward implementations - perfect for a 2-hour lab assessment!**
