import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel root;
    private JLabel titleLabel;

    MainForm(){

        setContentPane(root);
        String[] categories = {"TRAVEL AGENCY", "HOSPITAL", "HOTEL","SHOPS", "INDIVIDUAL", "ADDRESS"};

        JComboBox<String> categoryCB = new JComboBox<>(categories);
        JLabel categoryL = new JLabel("CATEGORY ");
        JButton goButton = new JButton("GO");
        JPanel categoryP = new JPanel();
        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(30,34));
        space.setBackground(Color.yellow);

        categoryP.setBackground(Color.yellow);

        categoryP.setLayout(new FlowLayout());
        //categoryP.setPreferredSize(new Dimension(600,120));
       // categoryCB.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));
        //categoryCB.setBackground(yellow);

        categoryP.setBorder(BorderFactory.createEmptyBorder(30,0,10,0));

        goButton.setPreferredSize(new Dimension(60,30));
        goButton.setFont(new Font("Arial Black", Font.BOLD,14 ));
       // goButton.setBorder(BorderFactory.createLineBorder(Color.black,2,true));

        //categoryL.setBackground(yellow);

        categoryP.add(categoryL);
        categoryP.add(categoryCB);
        categoryP.add(space);
        categoryP.add(goButton);
        //categoryP.setPreferredSize(new Dimension(350,110));

        categoryL.setFont(new Font("Arial Black", Font.BOLD,18 ));
        categoryCB.setFont(new Font("Arial", Font.BOLD,14 ));

        ImageIcon icon= new ImageIcon("title" + ".png");
        Image playImage = icon.getImage();
        Image newImage = playImage.getScaledInstance( 600, 350,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon(newImage);


        goButton.addActionListener(e -> {
            //TODO: Add code to load selected tables
            new TravelAgency();
            System.out.println(categoryCB.getSelectedItem());
        });

        titleLabel.setLayout(new GridBagLayout());
        titleLabel.setIcon(icon);
        titleLabel.add(categoryP);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
