```java
public class VehicleOwner {
    // Private fields for encapsulation
    private String ownerName;
    private String vehicleRegistration;
    private String contactNumber;
    
    // Constructor
    public VehicleOwner(String ownerName, String vehicleRegistration, String contactNumber) {
        this.ownerName = ownerName;
        this.vehicleRegistration = vehicleRegistration;
        this.contactNumber = contactNumber;
    }
    
    // Getter methods
    public String getOwnerName() {
        return ownerName;
    }
    
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    // Setter methods
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    // Method to display owner details
    public void displayDetails() {
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Vehicle Registration: " + vehicleRegistration);
        System.out.println("Contact Number: " + contactNumber);
    }
    
    // toString method for file operations
    @Override
    public String toString() {
        return ownerName + " " + vehicleRegistration + " " + contactNumber;
    }
}
