import javax.swing.*;

public class Individual extends JFrame{
    private JPanel JPanel;
    private JPanel searchByP;
    private JPanel details;
    private JTextField LNameTextField;
    private JTextField FNameTextField;
    private JComboBox worksForCB;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JPanel root;
    private JLabel Name;

    Individual(){

        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
