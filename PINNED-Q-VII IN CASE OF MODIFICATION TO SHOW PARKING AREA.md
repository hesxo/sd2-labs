Simply change this line
```java
String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)"};
```
to
```java
String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)", "Zone C"};
```

```java
    private static void showParkingArea() {
        System.out.println("=".repeat(60));
        System.out.println("                PARKING LOT STATUS             ");
        System.out.println("=".repeat(60));

        String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)"};
        for (int i = 0; i < parkingStructure.length; i++) {
            System.out.print(zoneLabels[i] + "  ");
            for (int j = 0; j < parkingStructure[i].length; j++) {
                if (parkingStructure[i][j] == 0) {
                    System.out.print("[O]");
                } else {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("LEGEND: [O] = Available | [X] = Reserved");
        System.out.println("=".repeat(60));
        System.out.println();
    }
```
