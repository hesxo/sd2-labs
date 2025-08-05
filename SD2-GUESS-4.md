# 4COSC010C Software Development II - SIMPLE EXAM GUESS | Guess 4
## Parking Lot Reservation App - Final Assessment

**Total Marks: 100 | Duration: 2 hours**

---

## **QUESTION 1: Add Zone C (20 marks)**

### **Task 1.1: Modify initialiseZones() method (10 marks)**
Add Zone C (Luxury) with 8 slots to the parking structure.

**ANSWER:**
```java
public static void initialiseZones() {
    parkingStructure = new int[3][]; // Change from [2] to [3]
    parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
    parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
    parkingStructure[2] = new int[8];  // Zone C: Luxury (8 slots) - NEW
}
```

### **Task 1.2: Update reserveSlot() for Zone C (10 marks)**
Modify the reserveSlot() method to accept Zone C input.

**ANSWER:**
```java
private static void reserveSlot() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter zone (A/B/C): "); // Added C
    String zoneInput = input.next().toUpperCase();
    
    int zoneIndex = -1;
    if (zoneInput.equals("A")) zoneIndex = 0;
    else if (zoneInput.equals("B")) zoneIndex = 1;
    else if (zoneInput.equals("C")) zoneIndex = 2; // NEW LINE
    
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

## **QUESTION 2: VehicleOwner Class with Encapsulation (35 marks)**

### **Task 2.1: Complete VehicleOwner class (35 marks)**
Create the complete VehicleOwner class with proper encapsulation.

**ANSWER:**
```java
public class VehicleOwner {
    // Private fields
    private String ownerName;
    private String vehicleRegistration;
    private String contactNumber;
    
    // Constructor
    public VehicleOwner(String ownerName, String vehicleRegistration, String contactNumber) {
        this.ownerName = ownerName;
        this.vehicleRegistration = vehicleRegistration;
        this.contactNumber = contactNumber;
    }
    
    // Getters
    public String getOwnerName() {
        return ownerName;
    }
    
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    // Setters
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    // Display method
    public void printOwnerDetails() {
        System.out.println("Owner: " + ownerName);
        System.out.println("Registration: " + vehicleRegistration);
        System.out.println("Contact: " + contactNumber);
    }
}
```

---

## **QUESTION 3: Complete registerOwner() method (20 marks)**

### **Task 3.1: Implement registerOwner() method (20 marks)**
Complete the registerOwner() method to collect input and store VehicleOwner objects.

**First add these global variables:**
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
    
    // Create and store VehicleOwner object
    owners[ownerCount] = new VehicleOwner(fullName, vehicleReg, contactNumber);
    ownerCount++;
    
    System.out.println("Vehicle owner registered successfully!");
}
```

---

## **QUESTION 4: Complete saveToFile() method (15 marks)**

### **Task 4.1: Save data to ParkingData.txt (15 marks)**
Complete the saveToFile() method to save all registered vehicle owner details.

**ANSWER:**
```java
private static void saveToFile() {
    try {
        FileWriter file = new FileWriter("ParkingData.txt");
        file.write("Data:\n");
        
        // Save registered owners
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

## **QUESTION 5: Search Method Implementation (10 marks)**

### **Task 5.1: Add search functionality (10 marks)**
Add a method to search for vehicle owners by registration number.

**Add this method:**
```java
public static void searchOwnerByRegistration() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a vehicle registration to search: ");
    String regToSearch = input.nextLine();
    
    boolean found = false;
    for (int i = 0; i < ownerCount; i++) {
        if (owners[i].getVehicleRegistration().equalsIgnoreCase(regToSearch)) {
            owners[i].printOwnerDetails();
            found = true;
            break;
        }
    }
    
    if (!found) {
        System.out.println("No owner found for that vehicle registration.");
    }
}
```

**Add menu option 6 and case 6 in switch statement:**
```java
// In getOption() method, add:
System.out.println("|  6) Search Owner by Registration            |");

// In runMenu() switch statement, add:
case 6:
    searchOwnerByRegistration();
    break;
```

---

## **COMPLETE SOLUTION OVERVIEW**

### **Key Changes Required:**

1. **Zone C Addition**: Change array size from [2] to [3], add 8 slots
2. **VehicleOwner Class**: Complete class with encapsulation
3. **registerOwner()**: Collect input, create objects, store in array
4. **saveToFile()**: Write owner details in format: "Name Registration Contact"
5. **Search Method**: Linear search by registration number

### **Final App.java Structure:**
```java
public class App {
    // Global variables
    private static int[][] parkingStructure = null;
    static VehicleOwner[] owners = new VehicleOwner[20];
    static int ownerCount = 0;
    
    // All existing methods PLUS:
    // - Modified initialiseZones()
    // - Modified reserveSlot()
    // - Implemented registerOwner()
    // - Completed saveToFile()
    // - Added searchOwnerByRegistration()
}
```

### **Mark Distribution:**
- **Question 1**: Zone C implementation (20 marks)
- **Question 2**: VehicleOwner class (35 marks)
- **Question 3**: registerOwner method (20 marks)
- **Question 4**: saveToFile method (15 marks)
- **Question 5**: Search functionality (10 marks)
- **Total**: 100 marks

**This simple version focuses on the core requirements with minimal complexity - exactly matching the MOCK exam style and difficulty level.**
