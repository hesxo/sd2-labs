Add Zone functionality

```java
private static void addZone() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter zone name (single character): ");
    char zoneName = input.next().toUpperCase().charAt(0);
    
    System.out.print("Enter number of slots to add: ");
    int slots = input.nextInt();
    
    int[][] newStructure = new int[parkingStructure.length + 1][];
    
    for (int i = 0; i < parkingStructure.length; i++) {
        newStructure[i] = parkingStructure[i];
    }
    
    newStructure[parkingStructure.length] = new int[slots]; // Corrected index
    
    parkingStructure = newStructure;

    System.out.println("New Zone added " + zoneName + ": with " + slots + " slot(s).");
}
