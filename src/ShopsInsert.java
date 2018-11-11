import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class ShopsInsert extends JFrame {
    private JPanel root;
    private JTextField locationTextField;
    private JTextField shopTextField;
    private JButton INSERTButton;
    private JTextField phoneNumberTextField;
    private JTextField urlTextField;
    private JComboBox categoryComboBox;
    private JButton MAPButton;

   ResultSet rs;

    ShopsInsert(Statement stm) {

        // Load the map to point to Mangalore
        MAPButton.addActionListener(e -> {
            try {
                String message="REMEMBER! You must insert the URL for your location.\n"
                        +"You can do it by following these steps :-\n"+
                        "-> Open google maps in your browser\n"+
                        "-> Find your location and drop a pin\n"+
                        "-> Copy the url and paste it in the url text field\n"+
                        "-> The browser opens automatically when you press INSERT button\n"+
                        "-> If your URL contains a single quote character,replace it with two continuous single quotes, ''\n "+
                        "-> You may ignore this step if you don't want your location to be stored";
                JOptionPane.showMessageDialog(null, message);
                java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.google.com/maps/@12.9128295,74.8505745,12.78z"));
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error loading map!");
                e1.printStackTrace();
            }
        });



        INSERTButton.addActionListener(e -> {

            try {
                long phoneNumber = Long.parseLong(phoneNumberTextField.getText());
                String name = shopTextField.getText();
                String location = locationTextField.getText();
                String url = urlTextField.getText();
                String category = Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString();

                int flag = 0;
                int choice = JOptionPane.showConfirmDialog(
                        ShopsInsert.this,
                        "Add details to the database? This action cannot be undone!\n " +
                                "The newly inserted Hospital will be available for all future queries",
                        "Add a new Hospital?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == 0) {
                    // Use chose "YES"
                    String s = "SELECT phone_num FROM Main_table";
                    rs = stm.executeQuery(s);
                    while (rs.next()) {
                        if (phoneNumber == rs.getLong(1)) {
                            System.out.println(rs.getLong(1));
                            flag = 1;
                            break;
                        }
                    }

                    if (flag == 0) {
                        String x = "insert into Main_table values(" + phoneNumber + ", 'Shops', '"
                                + location + "', 'Mangalore', '" + url + "')";
                        stm.executeUpdate(x);
                        x = "insert into Shops values(" + phoneNumber + ", '" + name + "' ,'" + category + "')";
                        stm.executeUpdate(x);
                    } else {
                        JOptionPane.showMessageDialog(null, "This number already exists");
                        phoneNumberTextField.setText("");
                        shopTextField.setText("");
                        locationTextField.setText("");
                        urlTextField.setText("");
                    }
                } else {
                    phoneNumberTextField.setText("");
                    shopTextField.setText("");
                    locationTextField.setText("");
                    urlTextField.setText("");
                }
            } catch (Exception ae) {
                ae.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Occurred");

            }});

        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

    }
}
