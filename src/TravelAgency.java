import javax.swing.*;
import java.awt.*;

public class TravelAgency extends JFrame{
    private JPanel root;
    private JTable resultsTable;
    private JPanel menu;
    private JLabel image;
    private JTextField locationTextField;
    private JTextField agencyTextField;
    private JCheckBox busCheckBox;
    private JCheckBox planeCheckBox;
    private JCheckBox trainCheckBox;
    private JCheckBox carCheckBox;
    private JPanel travelModes;
    private JPanel details;
    private JPanel searchByP;
    private JButton SEARCHButton;

    TravelAgency(){
        setContentPane(root);

        image.setHorizontalAlignment(SwingConstants.LEFT);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
