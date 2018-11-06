import javax.swing.*;

public class Hotel extends JFrame{
    private JPanel menu;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField hotelTextField;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JPanel root;
    private JSlider ratingSlider;
    private JRadioButton vegRadioButton;
    private JRadioButton nonVegRadioButton;

    Hotel(){

        ButtonGroup foodGroup = new ButtonGroup();
        foodGroup.add(vegRadioButton);
        foodGroup.add(nonVegRadioButton);

        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
