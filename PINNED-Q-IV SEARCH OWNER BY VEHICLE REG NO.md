Search owner by vehicle registration number functionolity

```java
private static void searchOwnerByVehicleRegNo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle reg number: ");
        String regNumber = sc.nextLine().trim().toLowerCase();
        for (int i = 0; i < owners.length; i++) {
            if (regNumber.equalsIgnoreCase(owners[i].getVehicleRegNo())) {
                owners[i].printVehicleOwnerDetails();
                return;
            }
        }

        System.out.println("Invalid vehicle reg number.");
        return;
    }
```
