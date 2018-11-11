import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.sql.*;
import java.util.ArrayList;

public class TravelAgency extends JFrame{
    private JPanel root;
    private JTable resultsTable;
    private JTextField locationTextField;
    private JTextField agencyTextField;
    private JCheckBox busCheckBox;
    private JCheckBox planeCheckBox;
    private JCheckBox trainCheckBox;
    private JCheckBox carCheckBox;
    private JButton SEARCHButton;
    private JCheckBox internationalCheckBox;
    private JButton INSERTButton;
    private JButton MAPButton;

    private Statement stm;
    private ResultSet rs;

    private String PHONE_NUM = "PHONE NUMBER";
    private String AGENCY = "AGENCY";

//    private String HAS_BUS = "BUS?";
//    private String HAS_TRAIN = "TRAIN?";
//    private String HAS_CAR = "CAR?";
//    private String HAS_PLANE = "PLANE?";
//    private String HAS_INTERNATIONAL  = "INTERNATIONAL?";
    private long numberToLocate;

    private String[] COLUMNS = {PHONE_NUM, AGENCY};//,HAS_BUS,HAS_TRAIN,HAS_CAR,HAS_PLANE,HAS_INTERNATIONAL};

    TravelAgency() throws Exception{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        con.setAutoCommit(true);
        stm = con.createStatement();

        INSERTButton.addActionListener(e -> {
            try {
                new TravelAgencyInsert(stm);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        SEARCHButton.addActionListener(e -> {

            DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
            StringBuilder sql = new StringBuilder("SELECT * FROM Travel_agency NATURAL JOIN Main_Table");

            String Agency = agencyTextField.getText().trim();
            String Location = locationTextField.getText().trim();
            String TravelModes;
            ArrayList<String> conditions = new ArrayList<>();

            if(busCheckBox.isSelected()){
                conditions.add(" has_bus = true ");
            }
            if(trainCheckBox.isSelected()){
                conditions.add(" has_train = true ");
            }
            if(planeCheckBox.isSelected()){
                conditions.add(" has_flight = true ");
            }
            if(carCheckBox.isSelected()){
                conditions.add(" has_car = true ");
            }
            if(internationalCheckBox.isSelected()){
                conditions.add(" has_international = true ");
            }

            TravelModes = String.join("AND", conditions);
            System.out.println(TravelModes);

            System.out.println("Agency: "+  Agency + "TravelModes:" + TravelModes+";");

            if(!Agency.equals("") || !TravelModes.equals("") || !Location.equals("")){
                // Check if the user has entered anything in the search fields
                sql.append(" WHERE");

                if(!Agency.equals("")){
                    sql.append(" name LIKE'%").append(Agency).append("%'");
                    if(!TravelModes.equals("") || !Location.equals(""))
                        sql.append(" AND");
                }
                if(!TravelModes.equals("")){
                    sql.append(TravelModes);
                    if(!Location.equals(""))
                        sql.append(" AND");
                }

                if(!Location.equals("")){
                    sql.append(" street LIKE '%").append(Location)
                            .append("%' OR city LIKE '%").append(Location).append("%';");
                }

            }

            Object[] row; // This array will hold all the attribute values per row
            try {

                System.out.println(sql.toString());
                rs = stm.executeQuery(sql.toString());

                if(!rs.next()){
                    JOptionPane.showMessageDialog(this,
                            "There are no Travel Agencies that match your search");
                }
                else {
                    do {
                        row = new Object[COLUMNS.length];
                        for (int i = 1; i <= COLUMNS.length; i++)
                            row[i - 1] = rs.getObject(i);
                        model.addRow(row);
                    }while (rs.next());
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
                    String query = "select * from Main_table where category='Travel_agency' and phone_num="+numberToLocate;
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
