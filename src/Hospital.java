import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Hospital extends JFrame {
    private JPanel root;
    private JPanel menu;
    private JLabel image;
    private JPanel searchByP;
    private JPanel details;
    private JTextField locationTextField;
    private JTextField hospitalTextField;
    private JButton SEARCHButton;
    private JTable resultsTable;
    private JComboBox specializationCB;
    private JButton INSERTButton;
    private JTextField textField1;
    private JLabel label1;
    private JButton map;
    private JTextField textField2;

    private Statement stm;
    private ResultSet rs;

    private String PHONE_NUM = "PHONE NUMBER";
    private String HOSPITAL = "HOSPITAL";

    private String HAS_NEURO = "NEURO?";
    private String HAS_ORTHO = "ORTHO?";
    private String HAS_NEPHRO = "NEPHRO?";
    private String HAS_CARDIO = "CARDIO?";

    private String[] COLUMNS = {PHONE_NUM, HOSPITAL, HAS_NEURO, HAS_ORTHO, HAS_CARDIO, HAS_NEPHRO};

    Hospital() throws Exception{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/YellowPixels",
                "root", "root123");
        con.setAutoCommit(true);

        stm = con.createStatement();

        INSERTButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    new INSERTHospital(stm);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        SEARCHButton.addActionListener(e -> {

            DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
            StringBuilder sql = new StringBuilder("SELECT * FROM Hospital NATURAL JOIN Main_Table");

            String Hospital = hospitalTextField.getText().trim();
            String Location = locationTextField.getText().trim();
            String Specialization = specializationCB.getSelectedItem().toString();


            System.out.println("Hospital: "+  Hospital + "Specialization:" + Specialization+";");

            if(!Hospital.equals("") || !Specialization.equals("Any") || !Location.equals("")){
                // Check if the user has entered anything in the search fields
                sql.append(" WHERE");

                if(!Hospital.equals("")){
                    sql.append(" name='").append(Hospital).append("'");
                    if(!Specialization.equals("Any") || !Location.equals(""))
                        sql.append(" AND");
                }
                if(!Specialization.equals("Any")){

                    switch (Specialization){
                        case "Neurology":
                            sql.append(" has_neuro = true");
                            break;
                        case "Orthopedics":
                            sql.append(" has_ortho = true");
                            break;
                        case "Nephrology":
                            sql.append(" has_nephro = true");
                            break;
                        case "Cardiology":
                            sql.append(" has_cardio = true");
                            break;
                    }
                    if(!Location.equals("")){
                        sql.append(" AND");
                    }
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
                            "There are no Hospitals that match your search");
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

        map.addActionListener(e -> {
                    String text=textField1.getText();
                    String x = "select * from Main_table where category='Hospital' and phone_num="+text;
                    String q = "";
                    try
                    {   rs = stm.executeQuery(x);
                        while(rs.next()) {
                            q = (rs.getString(5));
                            System.out.println(q);
                        }
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(q));
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(this, "Error");
                    }
                }
                );


        setContentPane(root);
        setResizable(false);
        pack();
        setVisible(true);
    }

}
