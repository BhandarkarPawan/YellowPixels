import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class INSERTHotel extends JFrame {
    private JPanel root;
    private JPanel menu;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField hotelTextField;
    private JRadioButton vegRadioButton;
    private JRadioButton nonVegRadioButton;
    private JCheckBox homeDeliveryCheckBox;
    private JButton INSERTButton;
    private JTextField phoneNumberTextField;

    INSERTHotel(Statement stm){
        //TODO: Use stm for all the further code


        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO: Form a relevant INSERT statement.
                // Make sure The phone number doesn't already exist, using the Main_table
                // Add to Main_table first (because FK constraint)
                // Check to see if any spurious data is being entered
                // If everything is OK, add to the  table

                int choice = JOptionPane.showConfirmDialog(
                        INSERTHotel.this,
                        "Add details to the database? This action cannot be undone!\n " +
                                "The newly inserted Hotel will be available for all future queries",
                        "Add a new Hotel?",
                        JOptionPane.YES_NO_OPTION);

                if(choice == 0){
                    // Use chose "YES"
                    // TODO: commit changes to the database

                }
                else
                {
                    // User chose "NO"
                    //TODO: rollback()
                }
            }
        });

        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

    }
}
