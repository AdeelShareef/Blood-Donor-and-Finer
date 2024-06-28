import java.util.ArrayList;
import java.util.List;

public class BloodBank {
    private String location;
    private String contact;
    private String openHours;
    private List<String> bloodTypes;
    private List<Integer> bloodQuantities;
    private List<BloodDonation> donationHistory;

    public BloodBank(String location, String contact, String openHours) {
        this.location = location;
        this.contact = contact;
        this.openHours = openHours;
        this.bloodTypes = new ArrayList<>();
        this.bloodQuantities = new ArrayList<>();
        this.donationHistory = new ArrayList<>();
    }

    public void registerBloodBank(String location, String contact, String openHours) {
        this.location = location;
        this.contact = contact;
        this.openHours = openHours;
    }

    public void updateBloodAvailability(String bloodType, int quantity) {
        int index = bloodTypes.indexOf(bloodType);
        if (index != -1) {
            bloodQuantities.set(index, quantity);
        } else {
            bloodTypes.add(bloodType);
            bloodQuantities.add(quantity);
        }
    }

    public int getBloodAvailability(String bloodType) {
        int index = bloodTypes.indexOf(bloodType);
        return index != -1 ? bloodQuantities.get(index) : 0;
    }

    public List<BloodDonation> getDonationHistory() {
        return donationHistory;
    }

    public void addDonationToHistory(BloodDonation donation) {
        donationHistory.add(donation);
    }
}
