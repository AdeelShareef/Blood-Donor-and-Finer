import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class BloodDonationSystemGUI extends JFrame {
    private BloodBank bloodBank;
    private JTextField donorNameField;
    private JTextField donorBloodTypeField;
    private JTextField donorContactField;
    private JTextField recipientNameField;
    private JTextField recipientBloodTypeField;
    private JTextArea donationHistoryArea;

    public BloodDonationSystemGUI() {
        // Initialize the blood bank
        bloodBank = new BloodBank("Hospital Blood Bank", "123-456-7890", "Mon-Fri: 9am-5pm");
        bloodBank.updateBloodAvailability("A+", 10);
        bloodBank.updateBloodAvailability("B-", 5);

        // Set up the frame
        setTitle("Blood Donation System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add the panels to the tabbed pane
        tabbedPane.addTab("Donate Blood", createDonateBloodPanel());
        tabbedPane.addTab("Need Blood", createNeedBloodPanel());
        tabbedPane.addTab("View Donation History", createViewHistoryPanel());

        // Add the tabbed pane to the frame
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createDonateBloodPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Name:"));
        donorNameField = new JTextField();
        panel.add(donorNameField);
        
        panel.add(new JLabel("Cinic:"));
        donorNameField = new JTextField();
        panel.add(donorNameField);

        panel.add(new JLabel("Blood Type:"));
        donorBloodTypeField = new JTextField();
        panel.add(donorBloodTypeField);

        panel.add(new JLabel("Contact Info:"));
        donorContactField = new JTextField();
        panel.add(donorContactField);

        JButton donateButton = new JButton("Donate Blood");
        donateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                donateBlood();
            }
        });
        panel.add(donateButton);

        return panel;
    }

    private JPanel createNeedBloodPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Name:"));
        recipientNameField = new JTextField();
        panel.add(recipientNameField);

        panel.add(new JLabel("Blood Type needed:"));
        recipientBloodTypeField = new JTextField();
        panel.add(recipientBloodTypeField);

        JButton needBloodButton = new JButton("Need Blood");
        needBloodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                needBlood();
            }
        });
        panel.add(needBloodButton);

        return panel;
    }

    private JPanel createViewHistoryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        donationHistoryArea = new JTextArea();
        donationHistoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(donationHistoryArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton viewHistoryButton = new JButton("View Donation History");
        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDonationHistory();
            }
        });
        panel.add(viewHistoryButton, BorderLayout.SOUTH);

        return panel;
    }

    private void donateBlood() {
        String name = donorNameField.getText();
        String bloodType = donorBloodTypeField.getText();
        String contactInfo = donorContactField.getText();

        Donor donor = new Donor(name, bloodType, contactInfo);
        donor.scheduleDonationAppointment(LocalDateTime.now().plusDays(7));

        BloodDonation donation = new BloodDonation(donor, LocalDateTime.now(), bloodType);
        bloodBank.addDonationToHistory(donation);

        bloodBank.updateBloodAvailability(bloodType, bloodBank.getBloodAvailability(bloodType) + 1);

        JOptionPane.showMessageDialog(this, "Blood donation registered successfully!");
    }

    private void needBlood() {
        String name = recipientNameField.getText();
        String bloodTypeNeeded = recipientBloodTypeField.getText();

        int availableQuantity = bloodBank.getBloodAvailability(bloodTypeNeeded);
        if (availableQuantity > 0) {
            bloodBank.updateBloodAvailability(bloodTypeNeeded, availableQuantity - 1);
            JOptionPane.showMessageDialog(this, "Blood provided to " + name + " successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Blood type " + bloodTypeNeeded + " not available in the blood bank!");
        }
    }

    private void viewDonationHistory() {
        donationHistoryArea.setText("");
        for (BloodDonation donation : bloodBank.getDonationHistory()) {
            donationHistoryArea.append("Donor: " + donation.getDonor().getName() + ", Blood Type: " + donation.getBloodType() +
                    ", Donation Date/Time: " + donation.getDonationDateTime() + "\n");
        }
    }
}
