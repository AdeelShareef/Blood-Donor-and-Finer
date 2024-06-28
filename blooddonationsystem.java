public class blooddonationsystem {
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BloodDonationSystemGUI frame = new BloodDonationSystemGUI();
                frame.setVisible(true);
            }
        });
    }
}
