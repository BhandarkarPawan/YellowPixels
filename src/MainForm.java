import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainForm extends JFrame {

    private JPanel root;
    private JButton GOButton;
    private JComboBox categoryCB;

    MainForm(){

        GOButton.addActionListener(e -> {

            switch (categoryCB.getSelectedItem().toString()){

                case "TRAVEL AGENCY":
                    try {
                        new TravelAgency();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "HOSPITAL":
                    try {
                        new Hospital();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "HOTEL":
                    try {
                        new Hotel();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "SHOPS":
                    new Shops();
                    break;
                case "INDIVIDUAL":
                    try {
                        new Individual();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }


    public static void main(String[] args) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostAddress());
        new MainForm();
    }
}
