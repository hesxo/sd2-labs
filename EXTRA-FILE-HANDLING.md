```java
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
```
