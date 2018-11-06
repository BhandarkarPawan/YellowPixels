import javax.swing.*;

public class Shops extends JFrame {
    private JPanel menu;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField ShopTextField;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JPanel root;
    private JComboBox shopCategoryCB;

    Shops(){

        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }

}
