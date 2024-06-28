import java.time.LocalDateTime;

public class Donor {
    private String name;
    private String bloodType;
    private String contactInfo;
    private LocalDateTime registrationDate;

    public Donor(String name, String bloodType, String contactInfo) {
        this.name = name;
        this.bloodType = bloodType;
        this.contactInfo = contactInfo;
        this.registrationDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void scheduleDonationAppointment(LocalDateTime dateTime) {
    // Implementation here
    System.out.println("Donation appointment scheduled for: " + dateTime);
}
}
