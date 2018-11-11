import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class HospitalInsert extends JFrame {
    private JPanel root;
    private JTextField locationTextField;
    private JTextField hospitalTextField;
    private JButton INSERTButton;
    private JTextField phoneNumberTextField;
    private JCheckBox neurologyCheckBox;
    private JCheckBox cardiologyCheckBox;
    private JCheckBox orthopedicsCheckBox;
    private JCheckBox nephrologyCheckBox;
    private JTextField urlTextField;
    private JButton MAPButton;

    ResultSet rs;
    HospitalInsert(Statement stm) {



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
                        String name = hospitalTextField.getText();
                        String location = locationTextField.getText();
                        String url = urlTextField.getText();

                        int has_nephro = 0;
                        int has_cardio = 0;
                        int has_neuro = 0;
                        int has_ortho = 0;

                        if (nephrologyCheckBox.isSelected())
                            has_nephro = 1;
                        if (cardiologyCheckBox.isSelected())
                            has_cardio = 1;
                        if (neurologyCheckBox.isSelected())
                            has_neuro = 1;
                        if (orthopedicsCheckBox.isSelected())
                            has_ortho = 1;

                        int flag = 0;

                        int choice = JOptionPane.showConfirmDialog(
                                HospitalInsert.this,
                                "Add details to the database? This action cannot be undone!\n " +
                                        "The newly inserted Hospital will be available for all future queries",
                                "Add a new Hospital?",
                                JOptionPane.YES_NO_OPTION);

                        if (choice == 0) {
                            // Use chose "YES"
                            String s = "SELECT phone_num FROM Main_table";
                            rs = stm.executeQuery(s);

                            while (rs.next()) {
                                // Check to see if the phone number already exists
                                if (phoneNumber == rs.getLong(1)) {
                                    System.out.println(rs.getLong(1));
                                    flag = 1;
                                    break;
                                }
                            }

                            if (flag == 0) {
                                String x = "INSERT INTO Main_table VALUES(" + phoneNumber +
                                        ", 'Hospital', '" + location + "', 'Mangalore', '" + url + "')";
                                stm.executeUpdate(x);
                                x = "INSERT INTO Hospital VALUES(" + phoneNumber + ", '" + name + "' ,"
                                        + has_neuro + ", " + has_ortho + ", " + has_nephro + ", " + has_cardio + ")";
                                stm.executeUpdate(x);
                            } else {
                                JOptionPane.showMessageDialog(null, "This number already exists");
                                phoneNumberTextField.setText("");
                                hospitalTextField.setText("");
                                locationTextField.setText("");
                                urlTextField.setText("");
                                nephrologyCheckBox.setSelected(false);
                                cardiologyCheckBox.setSelected(false);
                                neurologyCheckBox.setSelected(false);
                                orthopedicsCheckBox.setSelected(false);
                            }
                        }

                        else {
                            phoneNumberTextField.setText("");
                            hospitalTextField.setText("");
                            locationTextField.setText("");
                            urlTextField.setText("");
                            nephrologyCheckBox.setSelected(false);
                            cardiologyCheckBox.setSelected(false);
                            neurologyCheckBox.setSelected(false);
                            orthopedicsCheckBox.setSelected(false);
                        }
                    } catch (Exception ae) {
                        ae.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error Occurred");
                    }
                });


            setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

    }

    }