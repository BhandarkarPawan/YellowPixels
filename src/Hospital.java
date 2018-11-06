import javax.swing.*;

public class Hospital extends JFrame {
    private JPanel root;
    private JPanel menu;
    private JLabel image;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField hospitalTextField;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JComboBox specializationCB;

    Hospital(){

        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
