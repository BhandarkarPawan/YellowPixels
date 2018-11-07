import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

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
    private JCheckBox internationalCheckBox;

    private Statement stm;
    private ResultSet rs;

    private String PHONE_NUM = "PHONE NUMBER";
    private String AGENCY = "AGENCY";

    private String HAS_BUS = "BUS?";
    private String HAS_TRAIN = "TRAIN?";
    private String HAS_CAR = "CAR?";
    private String HAS_PLANE = "PLANE?";
    private String HAS_INTERNATIONAL  = "INTERNATIONAL?";

    private String[] COLUMNS = {PHONE_NUM, AGENCY,HAS_BUS,HAS_TRAIN,HAS_CAR,HAS_PLANE,HAS_INTERNATIONAL};

    TravelAgency() throws Exception{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        stm = con.createStatement();

        SEARCHButton.addActionListener(e -> {

            //TODO: Add code to also search by "Location " -> DATABASE required

            DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
            StringBuilder sql = new StringBuilder("SELECT * FROM Travel_agency");

            String Agency = agencyTextField.getText().trim();
            String Location = locationTextField.getText().trim();
            String TravelModes = null;
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

            if(!Agency.equals("") || !TravelModes.equals("")){
                // Check if the user has entered anything in the search fields
                sql.append(" WHERE");

                if(!Agency.equals("")){
                    sql.append(" name='").append(Agency).append("'");
                    if(!TravelModes.equals(""))
                        sql.append(" AND").append(TravelModes).append(";");
                }
                else
                    sql.append(TravelModes).append(";");
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

                while (rs.next()){
                    row = new Object[COLUMNS.length];
                    for(int i=1; i<=COLUMNS.length;i++)
                        row[i-1] = rs.getObject(i);
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
