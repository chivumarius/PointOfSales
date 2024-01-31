import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


// ▬ "Panel 3" ▬
public class ProductsPanel extends JPanel {

    // ♦────────── "Widgets" Declaration ──────────♦
    // ▼ "Labels" ▼
    JLabel search_lbl;
    JLabel product_name_lbl;
    JLabel barcode_lbl;
    JLabel price_lbl;
    JLabel quantity_lbl;


    // ▼ "Text Fields" ▼
    JTextField search_field;
    JTextField product_name_field;
    JTextField barcode_field;
    JTextField price_field;
    JTextField quantity_field;

    // ▼ "Buttons" ▼
    JButton save_Btn;
    JButton search_Btn;
    JButton update_Btn;
    JButton delete_Btn;


    // "Table"
    JTable table;
    DefaultTableModel defaultTableModel;


    // ▼ "Database" ▼
    DB database;




    // ▬ "Constructor" ▬
    public ProductsPanel() {

        // ♦────────── "Widgets" Initialization ──────────♦
        // ▼ "Labels" ▼
        search_lbl = new JLabel("Search Barcode : ");
        product_name_lbl   = new JLabel("Product Name: ");
        barcode_lbl = new JLabel(" Bar Code: ");
        price_lbl = new JLabel(" Price: ");
        quantity_lbl = new JLabel(" Quantity: ");


        // ▼ "Text Fields" ▼
        product_name_field = new JTextField();
        search_field = new JTextField();
        quantity_field  = new JTextField();
        barcode_field = new JTextField();
        price_field = new JTextField();

        // ▼ "Buttons" ▼
        save_Btn = new JButton("Save");
        update_Btn = new JButton("Update");
        delete_Btn = new JButton("Delete");
        search_Btn = new JButton("Search");



        // ♦──────────── "Left Panel" ("Grid") ────────────♦
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200,300));

        leftPanel.setLayout(new GridLayout(8,2));

        // ▼ "Adding" the "Widgets" to the "Left Panel" ▼
        // ▼ Adding" the "Labels" ▼
        leftPanel.add(search_lbl);
        leftPanel.add(search_field);
        leftPanel.add(product_name_lbl);
        leftPanel.add(product_name_field);
        leftPanel.add(barcode_lbl);
        leftPanel.add(barcode_field);
        leftPanel.add(quantity_lbl);
        leftPanel.add(quantity_field);
        leftPanel.add(price_lbl);
        leftPanel.add(price_field);

        // ▼ "Adding" the "Buttons" ▼
        leftPanel.add(save_Btn);
        leftPanel.add(search_Btn);
        leftPanel.add(update_Btn);
        leftPanel.add(delete_Btn);



        // •──── "Handling" the "Click Events" for the "Left Panel Buttons" ────•
        // [1] "Action Listener" → for the "Save Button":
        save_Btn.addActionListener(e -> insertProductIntoDB());

        // [2] "Action Listener" → for the "Update Button":
        update_Btn.addActionListener(e -> updateProductInDB());

        // [3-1] "Action Listener" → for the "Delete Button":
        delete_Btn.addActionListener(e -> deleteProductFromDB());

        // [3-2] "Action Listener" → for the "Delete Button":
        delete_Btn.addActionListener(e -> deleteSelectedRow());

        // [4] "Action Listener" → for the "Search Button":
        search_Btn.addActionListener(e -> searchProductByBarCodeInDB());





        // ♦──────────── "Right Panel" ("Table") ────────────♦
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600,500));
        rightPanel.setLayout(new FlowLayout());


        // ▼ "Initialing" the "JTable" ▼
        table = new JTable();
        table.setPreferredSize(new Dimension(650,500));


        // [0] Calling the "Method" Method:
        loadTable();


        // ▼ "Create" a  "Scrollable Table" ▼
        JScrollPane sp = new JScrollPane(table);
        rightPanel.add(sp);


        // •──── "Handling" the "Click Events" for the "Table Rows" ────•
        // [1] "Action Listener" → for the "Table Rows"
        table.getSelectionModel().addListSelectionListener(e -> tableRowSelected());


        // ♦────────── Adding "Panels" to "Products Panel" ───────────♦
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }





    // ♦──────────── "Methods" for "Left Panel" ("Grid") ────────────♦
    // ▬ [1] The "insertProductIntoDB()" Method
    //      → for the "Save Button" Action Listener ▬
    private void insertProductIntoDB() {

        // ▼ Creating the "Variables"
        //      → and "Getting" the "Text" of the "Text Field Widgets" ▼
        String bcode = barcode_field.getText();
        String quantity = quantity_field.getText();
        String price = price_field.getText();
        String product_name = product_name_field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Insert" the "Product" into the "Database" ▼
        try{

            // ▼ "Creating" the "Statement" ▼
            Statement statement = DB.mycon().createStatement();

            // ▼ "Making" an "Insertion" by "Executing" the "Query" ▼
            statement.executeUpdate(
                    "INSERT INTO products (bar_code, product_name, price, quantity) " +
                            "VALUES ('" + bcode + "','"+ product_name + "', '" + price + "','" + quantity +"')");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }




    // [2] The "updateProductInDB()" Method
    //      → for the "Update Button" Action Listener ▬
    private void updateProductInDB() {

        // ▼ Creating the "Variables"
        //      → and "Getting" the "Text" of the "Text Field Widgets" ▼
        String bcode = barcode_field.getText();
        String quantity = quantity_field.getText();
        String price = price_field.getText();
        String product_name = product_name_field.getText();
        String bar_code_to_search = search_field.getText();



        // ▼ "Executing" the "Query"
        //      → to "Update" the "Product" into the "Database" ▼
        try{

            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ "Making" an "Update" by "Executing" the "Query" ▼
            statement.executeUpdate("UPDATE products SET bar_code = '" + bcode + "', product_name = '" + product_name + "', price = '" + price + "', quantity = '" + quantity + "' WHERE bar_code = '" + bcode + "' ");

            // ▼ "Displaying" a "Pop Up Message" ▼
            JOptionPane.showMessageDialog(null, "Customer Updated!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }




    // [3-1] The "deleteProductFromDB()" Method
    //      → for the "Delete Button" Action Listener ▬
    private void deleteProductFromDB() {

        // ▼ Creating the "Variables"
        //      → and "Getting" the "Text" of the "Text Field Widgets" ▼
        String bcode = search_field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Delete" the "Product" from the "Database" ▼
        try{

            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ "Making" a "Deletion" by "Executing" the "Query" ▼
            statement.executeUpdate("DELETE FROM products WHERE bar_code = '" + bcode + "'");

            // ▼ "Displaying" a "Pop Up Message" ▼
            JOptionPane.showMessageDialog(null, "Product Deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }




    // ▬ [3-2] The "deleteSelectedRow()" Method
    //      → for the "Delete Button" Action Listener ▬
    private void deleteSelectedRow() {

        // ▼ "Getting" the "Number" of "Selected Row"
        int row = table.getSelectedRow();


        // ▼ "Checking" if a "Row" is "Selected"
        if (row >= 0) {

            // ▼ "Getting" the "Value" of the "Bar Code" ("Column 1") from the "Selected Row" ▼
            String barCode = table.getValueAt(row, 0).toString();

            // ▼ "Executing" the "Query" to "Delete" the "Product" from the "Database" ▼
            try {

                // ▼ "Creating" the "Statement" ▼
                Statement statement = database.mycon().createStatement();

                // ▼ "Making" a "Delete" by "Executing" the "Query" ▼
                statement.executeUpdate("DELETE FROM products WHERE bar_code = '" + barCode + "'");

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // ▼ "Calling" the "Method" to "Reload" the "Table" ▼
            loadTable();
        } else {
            // ▼ "Displaying" a "Pop Up Message" if "No Row" is "Selected" ▼
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }





    // [4] The "searchProductByBarCodeInDB()" Method
    //      → for the "Search Button" Action Listener ▬
    private void searchProductByBarCodeInDB() {

        // ▼ Creating the "Variable"
        String bcode_search = search_field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Search" the "Product"
        //      →  by "Bar Code" from the "Database" ▼
        try{

            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ "Storing" the "Selection"
            //      → in the "ResultSet" Object
            //      → where the "Bar Code"
            //      → is equal to the "Searched Bar Code" ▼
            ResultSet ResultSet = statement.executeQuery("SELECT * FROM products WHERE bar_code = '" + bcode_search + "' ");


            // ▼ "Checking" if the "ResultSet" is "Not Empty" ▼
            if (ResultSet.next()){
                // ▼ "Resetting" the "Text Fields" ▼
                product_name_field.setText(ResultSet.getString("product_name"));
                barcode_field.setText(ResultSet.getString("bar_code"));
                price_field.setText(ResultSet.getString("price"));
                quantity_field.setText(ResultSet.getString("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }







    // ♦──────────── "Methods" for "Right Panel" ("Table") ────────────♦
    // ▬ [0] The "loadTable()" Method
    //      → to "Load All" the "Products"
    //      → from the "Database"
    //      → into the "Table" from the "Right Panel" ▬
    private void loadTable() {

        // ▼ "Getting All" the "Data" from "Database" ▼
        try{
            // ▼ "Initializing" the "Default Table Model" ▼
            defaultTableModel = new DefaultTableModel();
            table = new JTable(defaultTableModel);

            // ▼ Adding "Columns" to "Table" ▼
            defaultTableModel.addColumn("BarCode");
            defaultTableModel.addColumn("Product Name");
            defaultTableModel.addColumn("Price");
            defaultTableModel.addColumn("Quantity");


            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ "Storing" the "Selection" from the "Query" Execution ▼
            ResultSet resultSet  = statement.executeQuery("SELECT * FROM products");


            // ► Inserting "All Received Rows/Records"
            //      → from the "Database"
            //      → into "Table" ▼
            while(resultSet.next()){
                // ▼ "Creating" a "Vector" Object ▼
                Vector<Object> vector = new Vector<>();

                // ▼ Getting the "Strings" for "Each Column" ▼
                vector.add(resultSet.getString(1));
                vector.add(resultSet.getString(2));
                vector.add(resultSet.getInt(3));
                vector.add(resultSet.getInt(4));

                // ▼ "Adding" the "Row" to the "Table" ▼
                defaultTableModel.addRow(vector);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // ▬ [1] The "tableRowSelected()" Method
    //      → for the "Table" Selection
    //      → Action Listener ▬
    private void tableRowSelected() {

        // ▼ "Getting" the "Number" of "Selected Row" ▼
        int row = table.getSelectedRow();


        // ▼ "Checking" if a "Row" is "Selected" ▼
        if (row >= 0) {

            // ▼ "Getting" the "Value" of the "Selected Row" ▼
            String barCode = table.getValueAt(row, 0).toString();
            String p_name = table.getValueAt(row, 1).toString();
            String price = table.getValueAt(row, 2).toString();
            String quantity = table.getValueAt(row, 3).toString();


            // ▼ "Resetting" the "Text Fields"
            //      → with the found "Columns Values" ▼
//            search_field.setText(barCode);
            product_name_field.setText(p_name);
            barcode_field.setText(barCode);
            quantity_field.setText(quantity);
            price_field.setText(price);
        }
    }
}
