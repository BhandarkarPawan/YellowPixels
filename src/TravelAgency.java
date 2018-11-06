import javax.swing.*;

public class TravelAgency extends JFrame{
    private JPanel root;
    private JTable table1;
    private JPanel menu;
    private JLabel image;
    private JTextField textField1;
    private JTextField textField2;

    TravelAgency(){
        setContentPane(root);

        image.setHorizontalAlignment(SwingConstants.LEFT);
        pack();
        setVisible(true);
    }
}
