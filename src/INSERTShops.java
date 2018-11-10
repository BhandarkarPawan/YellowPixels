import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class INSERTShops extends JFrame {
    private JPanel root;
    private JPanel menu;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField shopTextField;
    private JButton INSERTButton;
    private JTextField phoneNumberTextField;
    private JTextField urltextField1;
    private JRadioButton mallRadioButton;
    private JRadioButton floristRadioButton;
    private JRadioButton groceryradioButton;
    private JRadioButton electronicsradioButton;
    private JRadioButton jewelleryradioButton;

    ButtonGroup bg;
   ResultSet rs;

    INSERTShops(Statement stm)throws Exception{
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
        bg.add(jewelleryradioButton);
        bg.add(electronicsradioButton);
        bg.add(floristRadioButton);
        bg.add(groceryradioButton);
        bg.add(mallRadioButton);
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.google.com/maps/@12.9128295,74.8505745,12.78z"));
        //TODO: Use stm for all the further code


        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    long phone_no = Long.parseLong(phoneNumberTextField.getText());
                    String name = shopTextField.getText();
                    String location = locationTextField.getText();
                    String url = urltextField1.getText();
                    String z="";
                    if (jewelleryradioButton.isSelected())
                        z = "Jewellery";
                    if (electronicsradioButton.isSelected())
                        z = "Electronics";
                    if (floristRadioButton.isSelected())
                        z = "Florist";
                    if (groceryradioButton.isSelected())
                        z = "Grocery";
                    if (mallRadioButton.isSelected())
                        z = "Mall";
                    int flag = 0;
                    int choice = JOptionPane.showConfirmDialog(
                            INSERTShops.this,
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
                            String x = "insert into Main_table values(" + phone_no + ", 'Shops', '" + location + "', 'Mangalore', '" + url + "')";
                            stm.executeUpdate(x);
                            x = "insert into Shops values(" + phone_no + ", '" + name + "' ,'" + z + "')";
                            stm.executeUpdate(x);
                        } else {
                            JOptionPane.showMessageDialog(null, "This number already exists");
                            phoneNumberTextField.setText("");
                            shopTextField.setText("");
                            locationTextField.setText("");
                            urltextField1.setText("");
                            bg.clearSelection();
                        }
                    } else {
                        phoneNumberTextField.setText("");
                        shopTextField.setText("");
                        locationTextField.setText("");
                        urltextField1.setText("");
                        bg.clearSelection();
                    }
                } catch (Exception ae) {
                    ae.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error Occurred");
                    //TODO: Form a relevant INSERT statement.
                    // Make sure The phone number doesn't already exist, using the Main_table
                    // Add to Main_table first (because FK constraint)
                    // Check to see if any spurious data is being entered
                    // If everything is OK, add to the  table
                }}

                //TODO: Form a relevant INSERT statement.
                // Make sure The phone number doesn't already exist, using the Main_table
                // Add to Main_table first (because FK constraint)
                // Check to see if any spurious data is being entered
                // If everything is OK, add to the  table




        });

        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

    }
}
