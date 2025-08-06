# Creating new parking structure along with modification of size of structure

```java
    public static void initialiseZones() {
        parkingStructure = new int[3][]; // :::::: Q1
        parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
        parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
        parkingStructure[2] = new int[12]; // Zone C :::::: Q1
    }
```

# New class called VehicleOwner

```java
public class VehicleOwner { // ::::::: Q3
  private String name;
  private String vehicleRegNo;
  private String contactNo;
  
  public VehicleOwner(String name, String vehicleRegNo, String contactNo) {
    this.name = name;
    this.vehicleRegNo = vehicleRegNo;
    this.contactNo = contactNo;
  }
  
  public String getName() {
    return name;
  }
  
  public String getVehicleRegNo() {
    return vehicleRegNo;
  }
  
  public String getContactNo() {
    return contactNo;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setVehicleRegNo() {
    this.vehicleRegNo = vehicleRegNo;
  }
  
  public void setContactNo() {
    this.contactNo = contactNo;
  }
  
  public void printOwnerDetails() {
    System.out.println("Name: " + name);
    System.out.println("Vehicle Reg No: " + vehicleRegNo);
    System.out.println("Contact No: " + contactNo);
  }
}
```

# Register owner functionality

before creating the method
```java
    // Global variables
    private static VehicleOwner[] owners = new VehicleOwner[20]; 
    private static ownerCounter = 0; 
```
then update the method
```java
    private static void registerOwner() {
        // method not implemented
        Scanner sc = new Scanner(System.in);
        if (ownerCounter > owners.length) {
          System.out.println("Owner limit reached cannot add more owners.");
          return;
        }
        // String vehicleRegNo, String contactNo
        System.out.print("Enter owner's name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter vehicle reg no: ");
        String vehicleRegNo = sc.nextLine().trim().toLowerCase();
        System.out.print("Enter owner's contact number");
        String contactNo = sc.nextLine().trim();
        
        VehicleOwner newOwner = new VehicleOwner(name, vehicleRegNo, contactNo);
        owners[ownerCounter++] = newOwner;
        System.out.println("New owner added successfully.");
    }
```

# Search Owner by Vehicle Registration Number

```java
    private static void searchOwnerByVehicleRegNo() {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter Vehicle Reg No. : ");
      String search = sc.nextLine().trim().toLowerCase();
      for (int i = 0; i < owners.length; i++) {
        if (owners[i].getVehicleRegNo() == search) {
          owners[i].printOwnerDetails();
          break;
        }
      }
    }
```

# Implementing addZone() Method

```java
    private static void addZone() { // Name of the Zone, Zone Slots
        // method not implemented
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the zone: ");
        String zoneInput = sc.nextLine(); // Z
        System.out.print("Enter the number of slots: ");
        int zoneSlots = sc.nextInt(); // 50
        int index = parkingStructure.length + 1; // 3 + 1
        int[][] newStructure = new int[index][]; // 2D Array ROWS 4 => {0,1,2,3}
        for (int i = 0; i < parkingStructure.length; i++) {
          newStructure[i] = parkingStructure[i]; // {0,1,2} OLD
        }
        newStructure[index] = new int[zoneSlots]; // {3} NEW
        parkingStructure = newStructure; // UPDATE parkingStructure
        System.out.println(zoneInput + " zone added with " + zoneSlots + " slots");
    }
```

# Lastly File Handling implementation

```java
     private static void saveToFile() {
        try {
            FileWriter file = new FileWriter("ParkingData.txt");
            file.write("Data:\n");
            // Implement save data here based on test task
            String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C"}; // :::::: Q2
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
            file.write("OWNERS \n")
            for (int i = 0; i < owners.length; i++) {
              file.write(owners[i].getName() + " " + owners[i].getVehicleRegNo() + " " + owners[i].getContactNo());
            }
            file.close();
            System.out.println("Data saved to file.");
        } catch (IOException exception) {
            System.out.println("Error while writing to file.");
        }
    }
```
