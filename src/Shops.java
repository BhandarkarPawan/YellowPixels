import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.sql.*;
import java.util.Objects;

public class Shops extends JFrame {
    private JTextField locationTextField;
    private JTextField shopTextField;
    private JButton SEARCHButton;
    private JPanel root;
    private JComboBox shopCategoryCB;
    private JTable resultsTable;
    private JButton INSERTButton;
    private JButton MAPButton;

    private Statement stm;
    private ResultSet rs;
    private long numberToLocate;

    private String PHONE_NUM = "PHONE NUMBER";
    private String SHOP = "SHOP NAME";

    private String SHOP_TYPE = "SHOP TYPE";

    private String[] COLUMNS = {PHONE_NUM, SHOP,SHOP_TYPE};

    Shops() throws Exception{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        con.setAutoCommit(true);

        stm = con.createStatement();

        INSERTButton.addActionListener(e -> {
            try {
                new ShopsInsert(stm);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        SEARCHButton.addActionListener(e -> {

            DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
            StringBuilder sql = new StringBuilder("SELECT * FROM Shops NATURAL JOIN Main_Table");

            String Shop = shopTextField.getText().trim();
            String Location = locationTextField.getText().trim();
            String ShopCategory = Objects.requireNonNull(shopCategoryCB.getSelectedItem()).toString();


            System.out.println("Shop: "+  Shop + "ShopCategory:" + ShopCategory+";");

            if(!Shop.equals("") || !ShopCategory.equals("Any") || !Location.equals("")){
                // Check if the user has entered anything in the search fields
                sql.append(" WHERE");

                if(!Shop.equals("")){
                    sql.append(" name LIKE'%").append(Shop).append("%'");
                    if(!ShopCategory.equals("Any"))
                        sql.append(" AND");
                }
                if(!ShopCategory.equals("Any")){
                    sql.append(" shop_type = '").append(ShopCategory).append("'");
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
                            "There are no Shops that match your search");
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
                    String query = "select * from Main_table where category='Shops' and phone_num="+numberToLocate;
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
