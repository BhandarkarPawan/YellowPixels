import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.sql.*;
import java.util.Objects;

public class Individual extends JFrame{

    private JTextField LNameTextField;
    private JTextField FNameTextField;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JPanel root;
    private JButton INSERTButton;
    private JButton MAPButton;
    private JComboBox worksForCB;
    private ResultSet rs;
    private Statement stm;
    private long numberToLocate;
    private String PHONE_NUM = "PHONE NUMBER";
    private String FNAME = "FIRST NAME";
    private String LNAME = "LAST NAME";

    private String[] COLUMNS = {PHONE_NUM, FNAME,LNAME};

    Individual() throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                    "root", "root123");
            con.setAutoCommit(true);

            stm = con.createStatement();

            INSERTButton.addActionListener(e -> {
                try {
                    new IndividualInsert(stm);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            SEARCHButton.addActionListener(e -> {

                DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
                StringBuilder sql = new StringBuilder("SELECT * FROM Individual NATURAL JOIN Main_Table");

                String FName = FNameTextField.getText().trim();
                String LName = LNameTextField.getText().trim();
                String fieldOfWork = Objects.requireNonNull(worksForCB.getSelectedItem()).toString();
                System.out.println("First Name: "+  FName + " Last Name: " + LName);

                if(!FName.equals("") || !LName.equals("")|| !fieldOfWork.equals("Any")) {
                    // Check if the user has entered anything in the search fields

                    sql.append(" WHERE");
                    if(!FName.equals("")){
                        sql.append(" FName LIKE'%").append(FName).append("%'");
                        if(!LName.equals("")){
                            sql.append(" AND");
                        }
                    }
                    if(!LName.equals("")){
                        if(!LName.equals("")) {
                            sql.append(" LName LIKE'%").append(LName).append("%'");
                        }

                        if(!fieldOfWork.equals("Any")){
                            sql.append(" AND");
                        }
                    }

                    if(!fieldOfWork.equals("Any")){
                        sql.append(" Works_for = '").append(fieldOfWork).append("'");
                    }
                    sql.append(";");
                }

                Object[] row; // This array will hold all the attribute values per row

                try {
                    System.out.println(sql.toString());
                    rs = stm.executeQuery(sql.toString());

                    if(!rs.next()){
                        JOptionPane.showMessageDialog(this,
                                "There are no Individuals that match your search");
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
                        String query = "select * from Main_table where category='Individual' and phone_num="+numberToLocate;
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


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred.\n Please check the log for details.");
            e.printStackTrace();
        }

        SEARCHButton.doClick();
        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
