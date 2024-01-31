import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


// ▬ "Panel 5" ▬
public class SalesPanel extends JPanel {

    // ♦────────── "Widgets" Declaration ──────────♦
    // ▼ "Label" & "Text Fields" ▼
    JLabel totalSales;

    // ▼ "Button" ▼
    JButton getTotalSalesBtn;

    // ▼ "Table" ▼
    JTable jTable;
    DefaultTableModel defaultTableModel;

    // ▼ "Database" ▼
    DB database;



    // ▬ "Constructor" ▬
    public SalesPanel() {

        // ▼ "Instantiating" the "Database" Object ▼
        database = new DB();

        // ♦────────── "Widgets" Initialization ──────────♦
        totalSales = new JLabel("Total Sales: ");
        totalSales.setFont(new Font("Serif", Font.PLAIN, 30));

        getTotalSalesBtn = new JButton("Calculate Total Sales");



        // ♦──────────── "Left Panel" ("Grid") ────────────♦
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 500));
        leftPanel.setLayout(new GridLayout(6, 2));

        // ▼ "Adding" the "Widgets" to the "Left Panel" ▼
        totalSales = new JLabel("Total Sales: ");
        totalSales.setFont(new Font("Serif", Font.PLAIN, 30));
        getTotalSalesBtn = new JButton("Calculate Total Sales");

        leftPanel.add(totalSales);
        leftPanel.add(getTotalSalesBtn);



        // •──── "Handling" the "Click Events" for the "Left Panel Buttons" ────•
        // [1] "Action Listener" → for "Get Total Sales Btn":
        getTotalSalesBtn.addActionListener(e -> calculateTheTotalSales());




        // ♦──────────── "Right Panel" ("Table") ────────────♦
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600,500));
        rightPanel.setLayout(new FlowLayout());


        // ▼ "Initializing" the "Table" ▼
        defaultTableModel = new DefaultTableModel();
        jTable = new JTable(defaultTableModel);
        jTable.setPreferredSize(new Dimension(650, 500));
        jTable.setOpaque(false);



        // ▼ "Loading" the "Table Columns" ▼
        defaultTableModel.addColumn("Invoice ID");
        defaultTableModel.addColumn("Customer Name");
        defaultTableModel.addColumn("Products Sold");
        defaultTableModel.addColumn("Date");
        defaultTableModel.addColumn("Invoice Price");



        // ♦──────────── "Method" for the "Right Panel" ("Table") ────────────♦
        // [1] "Calling" the "Method":
        getAllInvoicesFromDB();

        // ▼ "Scrolling" the "Table" ▼
        JScrollPane scrollPane = new JScrollPane(jTable);
        rightPanel.add(scrollPane);


        // ♦────────── Adding "Panels" to "Main Panel" ───────────♦
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }




    // ♦──────────── "Method" for "Left Panel" ("Grid") ────────────♦
    // ▬ [1] The "calculateTheSales()" Method
    //      → for "Get Total Sales Btn" Action Listener ▬

    public void calculateTheTotalSales(){

        // ▼ "Initializing" the "Total Price" Variable ▼
        int totalPrice = 0;

        // ▼ "Looping" through the "Table Rows"
        //      → to "Add" the "Price" to the "Total Price" ▼
        for (int i = 0; i < jTable.getRowCount(); i++){
            // ▼ "Adding" the "Price" to the "Total Price" ▼
            totalPrice = totalPrice + Integer
                    .parseInt(jTable.getValueAt(i,4).toString());
        }

        // ▼ "Displaying" the "Total Price" in the "Label" ▼
        totalSales.setText("Total Sales: " + totalPrice + " $");

        System.out.println("");
    }




    // ♦──────────── "Methods" for "Right Panel" ("Table") ────────────♦
    // ▬ [1] The "getAllInvoicesFromDB()" Method
    //      → to "Load All" the "Invoices"
    //      → from the "Database"
    //      → into the "Table" from the "Right Panel" ▬
    private void getAllInvoicesFromDB() {

        // ▼ "Query" to "Select" the "Data"
        //      → from the "DB" ▼
        String query = "SELECT * FROM invoice";


        try {
            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ Using "Result Set"
            //      → to "Fetch" the "Data"
            //      → in "Form" of "Table" or "Rows" ▼
            ResultSet resultSet  = statement.executeQuery(query);


            // ▼ "Looping" through the "Result Set"
            //      → to "Get" the "Data"
            //      → from the "DB" ▼
            while(resultSet.next()){

                // ▼ Creating the "Vector" Object ▼
                Vector<Object> vector = new Vector<>();

                // ▼ "Getting All" the "Columns" from the "Result Set" ▼
                vector.add(resultSet.getInt(1));
                vector.add(resultSet.getString(2));
                vector.add(resultSet.getString(3));
                vector.add(resultSet.getDate(4));
                vector.add(resultSet.getInt(5));

                // ▼ "Display" the "Data" into "Table" ▼
               defaultTableModel.addRow(vector);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
