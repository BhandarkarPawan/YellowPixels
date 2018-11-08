import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Individual extends JFrame{
    private JPanel JPanel;
    private JPanel searchByP;
    private JPanel details;
    private JTextField LNameTextField;
    private JTextField FNameTextField;
    private JComboBox worksForCB;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JPanel root;
    private JLabel Name;
    private JButton INSERTButton;
    private ResultSet rs;
    private Statement stm;

    private String PHONE_NUM = "PHONE NUMBER";
    private String FNAME = "FIRST NAME";
    private String LNAME = "LAST NAME";

    private String[] COLUMNS = {PHONE_NUM, FNAME,LNAME};

    Individual() throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                    "root", "root123");
            con.setAutoCommit(false);

            stm = con.createStatement();

            INSERTButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new INSERTIndividual(stm);
                }
            });

            SEARCHButton.addActionListener(e -> {

                DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
                StringBuilder sql = new StringBuilder("SELECT * FROM Individual NATURAL JOIN Main_Table");

                String FName = FNameTextField.getText().trim();
                String LName = LNameTextField.getText().trim();

                System.out.println("First Name: "+  FName + " Last Name: " + LName);

                if(!FName.equals("") || !LName.equals("")){
                    // Check if the user has entered anything in the search fields

                    sql.append(" WHERE");
                    if(!FName.equals("")){
                        sql.append(" FName='").append(FName).append("'");
                        if(!LName.equals("")){
                            sql.append(" AND");
                        }
                    }
                    if(!LName.equals("")){
                        sql.append(" LName ='").append(LName).append("'");
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

                // This will center the values in each cell of the JTable
                TableCellRenderer renderer = resultsTable.getDefaultRenderer(String.class);
                ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
            });


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred.\n Please check the log for details.");
            e.printStackTrace();
        }


        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
