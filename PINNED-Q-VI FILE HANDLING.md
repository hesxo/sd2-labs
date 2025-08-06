File Handling

Method #1

```java
    private static void saveToFile() { // :::::::::: QUESTION 6
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
