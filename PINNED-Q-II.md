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
