File Handling

Method #1

```java
    private static void saveToFile() {
        try {
            FileWriter file = new FileWriter("ParkingData.txt");
            file.write("Data:\n");
            String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)"};
            for (int i = 0; i < parkingStructure.length; i++) {
                file.write(zoneLabels[i] + "  ");
                for (int j = 0; j < parkingStructure[i].length; j++) {
                    file.write(parkingStructure[i][j] == 0 ? "O" : "X");
                }
                file.write("\n");
            }
            file.write("Owners:\n");
            for (int i = 0; i < owners.length; i++) {
                file.write(owners[i].toString() + "\n");
            }
            file.close();
            System.out.println("Data saved to file.");
        } catch (IOException exception) {
            System.out.println("Error while writing to file.");
        }
    }
```

Method #2

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
