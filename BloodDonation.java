import java.time.LocalDateTime;

public class BloodDonation {
    private Donor donor;
    private LocalDateTime donationDateTime;
    private String bloodType;

    public BloodDonation(Donor donor, LocalDateTime donationDateTime, String bloodType) {
        this.donor = donor;
        this.donationDateTime = donationDateTime;
        this.bloodType = bloodType;
    }

    public Donor getDonor() {
        return donor;
    }

    public LocalDateTime getDonationDateTime() {
        return donationDateTime;
    }

    public String getBloodType() {
        return bloodType;
    }
}
