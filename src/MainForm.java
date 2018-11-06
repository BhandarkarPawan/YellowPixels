import javax.swing.*;

public class MainForm extends JFrame {

    private JPanel root;
    private JButton GOButton;
    private JComboBox categoryCB;

    MainForm(){

        GOButton.addActionListener(e -> {

            switch (categoryCB.getSelectedItem().toString()){

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
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        new MainForm();
    }
}
