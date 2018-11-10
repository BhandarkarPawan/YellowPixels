import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
    private JTextField urltextField1;
    ButtonGroup bg;

    ResultSet rs;

    INSERTHotel(Statement stm)throws Exception{
        String message="REMEMBER! You must insert the URL for your location.\n"
                +"You can do it by following these steps :-\n"+
                "-> Open google maps in your browser\n"+
                "-> Find your location and drop a pin\n"+
                "-> Copy the url and paste it in the url text field\n"+
                "-> The browser opens automatically when you press INSERT button\n"+
                "-> If your URL contains a single quote character,replace it with two continuous single quotes, ''\n "+
                "-> You may ignore this step if you don't want your location to be stored";
        JOptionPane.showMessageDialog(null, message);
        bg=new ButtonGroup();
        bg.add(vegRadioButton);
        bg.add(nonVegRadioButton);
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.google.com/maps/@12.9128295,74.8505745,12.78z"));
        //TODO: Use stm for all the further code


        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long phone_no = Long.parseLong(phoneNumberTextField.getText());
                    String name = hotelTextField.getText();
                    String location = locationTextField.getText();
                    String url = urltextField1.getText();
                    int has_veg = 1;
                    int has_nonveg = 0;
                    int has_homedelivery = 0;
                    if (vegRadioButton.isSelected())
                    {   has_veg = 1;
                        has_nonveg = 0;}
                    if (nonVegRadioButton.isSelected())
                    { has_nonveg = 1;
                      has_veg = 1;}
                    if (homeDeliveryCheckBox.isSelected())
                        has_homedelivery = 1;
                    int flag = 0;
                    int choice = JOptionPane.showConfirmDialog(
                            INSERTHotel.this,
                            "Add details to the database? This action cannot be undone!\n " +
                                    "The newly inserted Hospital will be available for all future queries",
                            "Add a new Hospital?",
                            JOptionPane.YES_NO_OPTION);

                    if (choice == 0) {
                        // Use chose "YES"
                        // TODO: commit changes to the database
                        String s = "select phone_num from Main_table";
                        rs = stm.executeQuery(s);
                        flag = 0;
                        while (rs.next()) {
                            if (phone_no == rs.getLong(1)) {
                                System.out.println(rs.getLong(1));
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 0) {
                            String x = "insert into Main_table values(" + phone_no + ", 'Hotel', '" + location + "', 'Mangalore', '" + url + "')";
                            stm.executeUpdate(x);
                            x = "insert into Hotel values(" + phone_no + ", '" + name + "' ," + has_nonveg + ", " + has_homedelivery + ")";
                            stm.executeUpdate(x);
                        } else {
                            JOptionPane.showMessageDialog(null, "This number already exists");
                            phoneNumberTextField.setText("");
                            hotelTextField.setText("");
                            locationTextField.setText("");
                            urltextField1.setText("");
                            bg.clearSelection();
                            homeDeliveryCheckBox.setSelected(false);
                        }
                    } else {
                        phoneNumberTextField.setText("");
                        hotelTextField.setText("");
                        locationTextField.setText("");
                        urltextField1.setText("");
                        bg.clearSelection();
                        homeDeliveryCheckBox.setSelected(false);
                    }
                } catch (Exception ae) {
                    ae.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error Occurred");

                    //TODO: Form a relevant INSERT statement.
                    // Make sure The phone number doesn't already exist, using the Main_table
                    // Add to Main_table first (because FK constraint)
                    // Check to see if any spurious data is being entered
                    // If everything is OK, add to the  table
                }
            }});

        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

    }
}
