import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Hotel extends JFrame{
    private JPanel menu;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField hotelTextField;
    private JButton SEARCHButton;
    private JPanel root;
    private JRadioButton vegRadioButton;
    private JRadioButton nonVegRadioButton;
    private JCheckBox homeDeliveryCheckBox;
    private JTable resultsTable;
    private JButton INSERTButton;
    private Statement stm;
    private ResultSet rs;

    private String PHONE_NUM = "PHONE NUMBER";
    private String HOTEL = "HOTEL";
    private String HAS_NONVEG = "NON-VEG?";
    private String HOME_DELIVERY = "HOMEDELIVERY?";

    private String[] COLUMNS = {PHONE_NUM, HOTEL, HAS_NONVEG, HOME_DELIVERY};

    Hotel() throws Exception{

        ButtonGroup foodGroup = new ButtonGroup();
        foodGroup.add(vegRadioButton);
        foodGroup.add(nonVegRadioButton);
        nonVegRadioButton.setSelected(true);


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        con.setAutoCommit(false);

        stm = con.createStatement();

        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new INSERTHospital(stm);
            }
        });

        SEARCHButton.addActionListener(e -> {

            System.out.println("CLICKED");

            DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
            StringBuilder sql = new StringBuilder("SELECT * FROM Hotel NATURAL JOIN Main_Table WHERE" );
            System.out.println(sql.toString());
            String Hotel = hotelTextField.getText().trim();
            String Location = locationTextField.getText().trim();

             // Check if the user has entered anything in the search fields

                if(!Hotel.equals(""))
                    sql.append(" name='").append(Hotel).append("' AND");
                if(homeDeliveryCheckBox.isSelected())
                    sql.append(" has_homedelivery = true AND");
                if(!Location.equals("")){
                    sql.append(" street LIKE '%").append(Location)
                            .append("%' OR city LIKE '%").append(Location).append("%' AND");
                }
                if (vegRadioButton.isSelected())
                    sql.append(" has_nonveg = false ;");
                else
                    sql.append(" has_nonveg = true ;");



                    System.out.println(sql.toString());
                Object[] row; // This array will hold all the attribute values per row
                try {

                    System.out.println(sql.toString());
                    rs = stm.executeQuery(sql.toString());

                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this,
                                "There are no Hotels that match your search");
                    } else {
                        do {
                            row = new Object[COLUMNS.length];
                            for (int i = 1; i <= COLUMNS.length; i++)
                                row[i - 1] = rs.getObject(i);
                            model.addRow(row);
                        } while (rs.next());
                    }

                    while (rs.next()) {
                        row = new Object[COLUMNS.length];
                        for (int i = 1; i <= COLUMNS.length; i++)
                            row[i - 1] = rs.getObject(i);
                        model.addRow(row);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                model.setColumnIdentifiers(COLUMNS);
                resultsTable.setModel(model);

                // This will center the values in each cell of the JTable
                TableCellRenderer renderer = resultsTable.getDefaultRenderer(String.class);
                ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);

        });


        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
