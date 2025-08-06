Search Owner by registration number of vehicle

```java
private static void searchOwnerByRegistration() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter vehicle registration to search: ");
    String searchReg = input.nextLine();
    
    boolean found = false;
    for (int i = 0; i < ownerCount; i++) {
        if (owners[i].getVehicleRegistration().equalsIgnoreCase(searchReg)) {
            System.out.println("Owner found:");
            owners[i].displayDetails();
            found = true;
            break;
        }
    }
    
    if (!found) {
        System.out.println("No owner found with registration: " + searchReg);
    }
}
```

without failsafe

```java
private static void searchOwnerByRegistration() {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter vehicle registration to search: ");
    String searchReg = input.nextLine();
    
    for (int i = 0; i < ownerCount; i++) {
        if (owners[i].getVehicleRegistration().equalsIgnoreCase(searchReg)) {
            System.out.println("Owner found:");
            owners[i].displayDetails();
            break;
        }
    }
    
}

```
