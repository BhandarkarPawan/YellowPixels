import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainForm extends JFrame {

    private JPanel root;
    private JButton GOButton;
    private JComboBox categoryCB;

    MainForm(){

        GOButton.addActionListener(e -> {

            try {
                switch (categoryCB.getSelectedItem().toString()) {

                    case "TRAVEL AGENCY":
                        new TravelAgency();
                        break;
                    case "HOSPITAL":
                        new Hospital();
                        break;
                    case "HOTEL":
                        new Hotel();
                        break;
                    case "SHOPS":
                        new Shops();
                        break;
                    case "INDIVIDUAL":
                        new Individual();
                        break;
                }
            }catch(Exception e1){
                JOptionPane.showMessageDialog(this, "An Error occurred.");
                e1.printStackTrace();

            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }


    public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostAddress());
        new MainForm();
    }
}
