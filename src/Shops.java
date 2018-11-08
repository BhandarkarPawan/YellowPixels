import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Shops extends JFrame {
    private JPanel menu;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField shopTextField;
    private JButton SEARCHButton;
    private JPanel root;
    private JComboBox shopCategoryCB;
    private JTable resultsTable;
    private JButton INSERTButton;

    private Statement stm;
    private ResultSet rs;

    private String PHONE_NUM = "PHONE NUMBER";
    private String HOSPITAL = "SHOP NAME";

    private String SHOP_TYPE = "SHOP TYPE";

    private String[] COLUMNS = {PHONE_NUM, HOSPITAL,SHOP_TYPE};

    Shops() throws Exception{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        con.setAutoCommit(false);

        stm = con.createStatement();

        INSERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new INSERTShops(stm);
            }
        });

        SEARCHButton.addActionListener(e -> {

            DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
            StringBuilder sql = new StringBuilder("SELECT * FROM Shops NATURAL JOIN Main_Table");

            String Shop = shopTextField.getText().trim();
            String Location = locationTextField.getText().trim();
            String ShopCategory = shopCategoryCB.getSelectedItem().toString();


            System.out.println("Shop: "+  Shop + "ShopCategory:" + ShopCategory+";");

            if(!Shop.equals("") || !ShopCategory.equals("Any") || !Location.equals("")){
                // Check if the user has entered anything in the search fields
                sql.append(" WHERE");

                if(!Shop.equals("")){
                    sql.append(" name ='").append(Shop).append("'");
                    if(!ShopCategory.equals("Any"))
                        sql.append(" AND");
                }
                if(!ShopCategory.equals("Any")){
                    sql.append( " shop_type = '" + ShopCategory);
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
