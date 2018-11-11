import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.sql.*;

public class Hotel extends JFrame{

    private JTextField locationTextField;
    private JTextField hotelTextField;
    private JButton SEARCHButton;
    private JPanel root;
    private JRadioButton vegRadioButton;
    private JRadioButton nonVegRadioButton;
    private JCheckBox homeDeliveryCheckBox;
    private JTable resultsTable;
    private JButton INSERTButton;
    private JButton MAPButton;
    private Statement stm;
    private ResultSet rs;
    private  long numberToLocate;

    private String PHONE_NUM = "PHONE NUMBER";
    private String HOTEL = "HOTEL";
    private String HAS_NONVEG = "NON-VEG?";
    //private String HOME_DELIVERY = "HOME DELIVERY?";

    private String[] COLUMNS = {PHONE_NUM, HOTEL, HAS_NONVEG};//, HOME_DELIVERY};

    Hotel() throws Exception{

        ButtonGroup foodGroup = new ButtonGroup();
        foodGroup.add(vegRadioButton);
        foodGroup.add(nonVegRadioButton);
        nonVegRadioButton.setSelected(true);


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        con.setAutoCommit(true);

        stm = con.createStatement();

        INSERTButton.addActionListener(e -> {
            try {
                new HotelInsert(stm);
            } catch (Exception e1) {
                e1.printStackTrace();
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
                    sql.append(" name LIKE'%").append(Hotel).append("%' AND");
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

            resultsTable.getSelectionModel().addListSelectionListener(event -> {
                if (resultsTable.getSelectedRow() > -1) {
                    // print first column value from selected row
                    numberToLocate = Long.parseLong(resultsTable.getValueAt(resultsTable.getSelectedRow(), 0).toString());
                }
            });

            // This will center the values in each cell of the JTable
            TableCellRenderer renderer = resultsTable.getDefaultRenderer(String.class);
            ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);

        });
        MAPButton.addActionListener(e -> {
                    String query = "select * from Main_table where category='Hotel' and phone_num="+numberToLocate;
                    String URL = "";


                    try
                    {   rs = stm.executeQuery(query);
                        if(!rs.next() ){
                            JOptionPane.showMessageDialog(null, "No such entry exists in the database");
                        }
                        rs.previous();

                        while(rs.next()){
                            URL = (rs.getString(5));
                            System.out.println(URL);
                        }
                        if(URL.equals(""))
                            JOptionPane.showMessageDialog(null, "No URL has been set for that location");

                        else{
                            java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
                            numberToLocate = -1;
                        }
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(this, "Error");
                    }
                }
        );


        SEARCHButton.doClick();
        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
