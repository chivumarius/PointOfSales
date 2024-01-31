/*▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀


 ○ "PreparedStatement"

    • The "PreparedStatement" Interface
        → extends the "Statement" Interface.


     • It "Represents"
        → a "PreCompiled SQL Statement"
        → which can be Executed "Multiple Times".



    • This "Accepts"
        → "Parameterized SQL Quires"
        → and you can "Pass"
        → "0" or "More Parameters"
        → to this "Query".


    • "Initially" this "Statement"
        → uses "Place Holders" as “?”
        → "Instead" of "Parameters",
        → later on you can Pass "Arguments"
        → to these "Dynamically"
        → using the "setXXX()" Methods
        → of the "PreparedStatement" Interface.



 ○ "JSON" Data Type

    • "Array"
        → is "One" of the "Most Common Data Types"
        → in the "world" of "programming",

    • But "MySQL" Actually
        → do "Not Support"
        → "Saving" an "Array" Type
        → "Directly".


  • You can "Not Create"
    → a "Table Column" of "Array" Type
    → in "MySQL".


  • The "Easiest Way"
    → to "Store" an "Array" Data Type
    → in "MySQL"
    → is by "Using"
    → the "JSON" Data Type.


  • The "JSON" Data Type
    → was "First Added"
    → in "MySQL Version 5.7.8",
    → and you can "Use" the "Type"
    → for Storing "JSON Arrays"
    → and "Objects".


  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

// ▬ "Panel 4" ▬
public class Invoice extends JPanel {

    // ♦────────── "Widgets" Declaration ──────────♦
    // ▼ "Labels" & "Text Fields" ▼
    JLabel product_barcode_lbl;
    JTextField product_barcode_field;

    JLabel customer_name_lbl;
    JTextField customer_name_field;

    public JLabel totalPriceLbl;


    // ▼ "Buttons" ▼
    JButton addToCartBtn;
    JButton removeFromCartBtn;
    JButton confirmBtn;
    JButton saveInvoiceBtn;



    // ▼ "Cart" Table ▼
    JTable jTable;
    DefaultTableModel defaultTableModel;


    // ▼ "Cart Array List"
    ArrayList<String> selectedItems;


    // ▼ "Database" ▼
    DB database;



    // ▬ "Constructor" ▬
    public Invoice() {

        // ▼ Instantiating the "Database" Object ▼
        database = new DB();

        // ▼ Instantiating the "Array List" Object ▼
        selectedItems = new ArrayList<>();


        // ♦────────── "Widgets" Initialization ──────────♦
        // ▼ For "Products Table" ▼
        product_barcode_lbl = new JLabel("Product Barcode");
        product_barcode_field = new JTextField();
        product_barcode_field.setOpaque(false);


        // ▼ For "Customers Table" ▼
        customer_name_lbl = new JLabel("Customer Name");
        customer_name_field = new JTextField();
        customer_name_field.setOpaque(false);

        // ▼ For "Total Price" ▼
        totalPriceLbl = new JLabel("Total Price: ");
        totalPriceLbl.setFont(new Font("Serif", Font.PLAIN, 32));

        // ▼ "Buttons" ▼
        addToCartBtn = new JButton("Add to Cart");
        removeFromCartBtn = new JButton("Remove from Cart");
        confirmBtn = new JButton("Display Products Details");
        saveInvoiceBtn = new JButton("Save Invoice");




        // ♦──────────── "Left Panel" ("Grid") ────────────♦
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200,500));
        leftPanel.setLayout(new GridLayout(10,2));


        // ▼ "Adding" the "Widgets" to the "Left Panel" ▼
        leftPanel.add(product_barcode_lbl);
        leftPanel.add(product_barcode_field);
        leftPanel.add(customer_name_lbl);
        leftPanel.add(customer_name_field);
        leftPanel.add(addToCartBtn);
        leftPanel.add(removeFromCartBtn);
        leftPanel.add(confirmBtn);
        leftPanel.add(saveInvoiceBtn);
        leftPanel.add(totalPriceLbl);




        // •──── "Handling" the "Click Events" for the "Left Panel" ────•
        // [1] "Action Listener" → for the "Add to Cart Button":
        addToCartBtn.addActionListener(e -> addToCart());



        // [2] "Action Listener" → for the "Remove from Cart Button":
        removeFromCartBtn.addActionListener(e -> removeFromCart());


        // [3] "Action Listener" → for the "Confirm Button":
        confirmBtn.addActionListener(e -> confirm());


        // [4] "Action Listener" → for the "Save Invoice" Button:
        saveInvoiceBtn.addActionListener(e -> saveInvoice());




        // ♦──────────── "Right Panel" ("Table") ────────────♦
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600,500));
        rightPanel.setLayout(new FlowLayout());



        // ▼ "Initializing" the "Table" ▼
        defaultTableModel = new DefaultTableModel();
        jTable = new JTable(defaultTableModel);
        jTable.setPreferredSize(new Dimension(650, 500));
        jTable.setOpaque(false);


        // ▼ "Loading" the "Table" ▼
        defaultTableModel.addColumn("Product Barcode");
        defaultTableModel.addColumn("Product Name");
        defaultTableModel.addColumn("Product Price");



        // ▼ "Create" a  "Scrollable Table" ▼
        JScrollPane sp = new JScrollPane(jTable);
        rightPanel.add(sp);



        // ♦────────── Adding "Panels" to "Main Panel" ───────────♦
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }





    // ♦──────────── "Method" for "Left Panel" ("Grid") ────────────♦
    // ▬ [1] The "addToCart()" Method
    //      → for "Add to Cart Btn" Action Listener ▬
    private void addToCart() {

        // ▼ Storing the "Product Bar Cde" in the "String" ▼
        String product_barcode = product_barcode_field.getText();

        // ▼ Adding the "Product Bar Cde" to the "Array List" ▼
        selectedItems.add(product_barcode);

        // ▼ "Displaying" the "Selected Items" ▼
        displaySelectedItemsInTable(selectedItems);

        // ▼ "Clearing"/"Resetting" the "Text Field" ▼
        product_barcode_field.setText("");
    }




    // ▬ [2] The "removeFromCart()" Method
    //      → for "Remove from Cart Btn" Action Listener ▬
    public void removeFromCart() {
        // ▼ Storing the "Product Bar Cde" in the "String" ▼
        String product_barcode = product_barcode_field.getText();

        // ▼ Adding the "Product Bar Cde" to the "Array List" ▼
        selectedItems.add(product_barcode);

        // ▼ "Displaying" the "Selected Items" ▼
        displaySelectedItemsInTable(selectedItems);
    }




    // ▬ [3] The "confirm()" Method
    //      → for "Confirm Button" Action Listener ▬
    private void confirm() {

        // ▼ "Resting"/"Clearing" the "Table"
        //      → when the "User Click"
        //      → the "Confirm Button" ▼
        defaultTableModel.setRowCount(0);


        try{

            // ▼ Selecting the "Product Name" & "Price"
            //      → from the "Products" Table
            //      → where the "Bar Code" is equal to the "?" ▼
            String query = "SELECT product_name, price FROM products WHERE bar_code = ?";

            // ▼ "Creating" the "PreparedStatement"
            //      → to execute the "Query" ▼
            PreparedStatement statement = DB.mycon().prepareStatement(query);


            // ▼ "Looping" through the "Array List"
            //      → to "Get All" the "Bar Code" ▼
            for (String barcode: selectedItems){

                // ▼ "Replacing" the "?"
                //      → by the "Bar Code" from the "Array List" ▼
                //      → and "Setting" the "String" Type
                //      → for the "Bar Code"
                //      → and the "Number" for the "Question Mark" Used ("?") ▼
                statement.setString(1, barcode);

                // ▼ Using "Result Set"
                //      → to "Fetch" the "Data"
                //      → in "Form" of "Table" or "Rows" ▼
                ResultSet resultSet = statement.executeQuery();


                // ▼ "Looping" through the "Result Set"
                //      → to "Get" the "Data"
                //      → from the "DB" ▼
                while (resultSet.next()){

                    // ▼ Getting the "Columns" from the "Result Set" ▼
                    System.out.println(resultSet.getString(1));
                    System.out.println(resultSet.getString(2));


                    // ▼ Creating the "Vector" Object ▼
                    Vector<String> vector = new Vector<>();

                    // Get the barcode from the local cart (arraylist)
                    vector.add(barcode);

                    // ▼ "Get" the "Name" and the "Price"
                    //      → of the "Item" from "DB" ▼
                    vector.add(resultSet.getString(1));
                    vector.add(resultSet.getString(2));

                    // ▼ "Adding" the "Row" to the "Table" ▼
                    defaultTableModel.addRow(vector);
                }
            }

            // ▼ "Calling" the "Method" ▼
            calculateTotalPrice();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



    // ▬ [3-1] The "displaySelectedItemsInTable()" Method
    //      → for "Confirm Button" Action Listener ▬
    private void displaySelectedItemsInTable(ArrayList<String> selectedItems) {
        try {
            // ▼ Resetting the "Table" ▼
            defaultTableModel.setRowCount(0);


            // ▼ "Inserting Items"
            //      → from "Array List" to "JTable" ▼
            for (int i = 0; i < selectedItems.size(); i++) {
                // ▼ "Adding" the "Row"
                //      → to the "JTable" ▼
                defaultTableModel.addRow(new Object[]{selectedItems.get(i)});
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    // ▬ [3-2] The "calculateTotalPrice()" Method
    //      → for "Confirm Button" Action Listener ▬
    public double  calculateTotalPrice(){

        // ▼ "Initializing" the "Total Price" Variable ▼
        double totalPrice = 0.0;


        // ▼ "Looping" through the "Table Rows"
        for (int i = 0; i < jTable.getRowCount(); i++){
            // ▼ "Adding" the "Price" to the "Total Price" ▼
            totalPrice = totalPrice + Double.parseDouble(jTable.getValueAt(i,2).toString());
        }

        // ▼ "Displaying" the "Total Price" in the "Label" ▼
        totalPriceLbl.setText("Total Price: " + totalPrice+ " $");

        // ▼ "Returning" the "Total Price" ▼
        return totalPrice;
    }



    // ▬ [4] The "saveInvoice()" Method
    //      → for "Save Invoice Btn" Action Listener ▬
    public void saveInvoice(){

        // ▼ Storing the "Customer Name" in the "String" ▼
        String cus_name = customer_name_field.getText();


        // ▬ 1- "Getting" the "Names" of the "Products"
        //      → in "Form" of "Array" ▬
        ArrayList<String> products_names = new ArrayList<>();

        // ▼ "Looping" through the "Table"
        //      → to "Get All" the "Names" ▼
        for (int i=0; i < jTable.getRowCount(); i++ ){
            products_names.add(jTable.getValueAt(i,1).toString());
        }

        // ▬ 2- "Converting" the "ArrayList" to "String" ▬
        StringBuilder stringBuilder = new StringBuilder();

        // ▼ "Looping" through the "Array List"
        //      → to "Get All" the "Names" ▼
        for (String string : products_names){
            stringBuilder.append(string);
            stringBuilder.append(" , ");
        }



        // ▬ 3- "Getting" the "Current Date" ▬
        Timestamp timestamp = new Timestamp(new Date().getTime());

        // ▬ 4-" Total Price" ▬
        double totalPrice = calculateTotalPrice();


        // ▬ 5- "Executing" the "Query"
        //      → to "Insert" the "Data"
        //      → into the "Table" ▬
        String insertInvoice = "INSERT INTO invoice " +
                "( customer_name , products_sold, date, total_price) VALUES "
                + "('" + cus_name + "','"+ stringBuilder + "'," +
                "'" + timestamp + "','" + totalPrice + "')";


        try{

            // ▼ Checking if the "Fields" are "Not Empty" ▼
            if (!cus_name.equals("") && !selectedItems.isEmpty()) {
                // ▼ "Creating" the "Statement" ▼
                Statement statement = DB.mycon().createStatement();

                //
                statement.executeUpdate(insertInvoice);

            } else {
                // ▼ "Displaying" a "Pop U[ Message" ▼
                JOptionPane.showMessageDialog(null,
                        "One or more fields are empty");
            }
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
    }
}
