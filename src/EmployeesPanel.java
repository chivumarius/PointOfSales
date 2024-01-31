import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



// ▬ "Panel 1" ▬
public class EmployeesPanel extends JPanel {
    // ♦────────── "Widgets" Declaration ──────────♦
    // ▼ "Labels" ▼
    JLabel search_lbl;
    JLabel name_lbl;
    JLabel tp_number_lbl;

    // ▼ "Text Fields" ▼
    JTextField search_Field;
    JTextField name_Field;
    JTextField tpNum_Field;
    JTextField searchTable_field;

    // ▼ "Buttons" ▼
    JButton save_Btn;
    JButton search_Btn;
    JButton update_Btn;
    JButton delete_Btn;
    JButton search_table_Btn;

    // "Table"
    JTable table;
    DefaultTableModel defaultTableModel; // ► For "Custom Tables" ◄

    // ▼ "Database" ▼
    DB database;




    // ▬ "Constructor" ▬
    public EmployeesPanel() {

        // ▼ Instantiating the "Database" Object ▼
        database = new DB();


        // ♦────────── "Widgets" Initialization ──────────♦
        // ▼ "Labels" ▼
        search_lbl = new JLabel("Search by Mobile Nb : ");
        name_lbl   = new JLabel("Name: ");
        tp_number_lbl = new JLabel(" Mobile Number: ");

        // ▼ "Text Fields" ▼
        name_Field = new JTextField();
        search_Field = new JTextField();
        tpNum_Field  = new JTextField();
        searchTable_field = new JTextField();

        // ▼ "Buttons" ▼
        save_Btn = new JButton("Save");
        update_Btn = new JButton("Update");
        delete_Btn = new JButton("Delete");
        search_Btn = new JButton("Search");
        search_table_Btn = new JButton("Search Table");




        // ♦──────────── "Left Panel" ("Grid") ────────────♦
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200,300));

        leftPanel.setLayout(new GridLayout(8,2));

        leftPanel.add(search_lbl);
        leftPanel.add(search_Field);
        leftPanel.add(name_lbl);
        leftPanel.add(name_Field);
        leftPanel.add(tp_number_lbl);
        leftPanel.add(tpNum_Field);

        leftPanel.add(save_Btn);
        leftPanel.add(search_Btn);
        leftPanel.add(update_Btn);
        leftPanel.add(delete_Btn);
        leftPanel.add(searchTable_field);
        leftPanel.add(search_table_Btn);



        // •──── "Handling" the "Click Events" for the "Left Panel Buttons" ────•
        // [1] "Action Listener" → for the "Save Button":
        save_Btn.addActionListener(e -> insertEmployeeIntoDB());

        // [2] "Action Listener" → for the "Update Button":
        update_Btn.addActionListener(e -> updateEmployeeInDB());

        // [3-1] "Action Listener" → for the "Delete Button":
        delete_Btn.addActionListener(e -> deleteEmployeeFromDB());

        // [3-2] "Action Listener" → for the "Delete Button":
        delete_Btn.addActionListener(e -> deleteSelectedRow());

        // [4] "Action Listener" → for the "Search Table Button"
        //          → to "Search Employees" by "Name":
        search_table_Btn.addActionListener(e -> searchTableByName());

        // [5] "Action Listener" → for the "Search Button"
        //          → to "Search Employees" by "Mobile Number":
        search_Btn.addActionListener(e -> searchEmployeeByMobileNumber());






        // ♦──────────── "Right Panel" ("Table") ────────────♦
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600,500));
        rightPanel.setLayout(new FlowLayout());


        // ▼ "Initializing" the "Table" ▼
        table  = new JTable();
        table.setPreferredSize(new Dimension(650, 500));


        // [0] "Calling" the "Method":
        loadTable();


        // ▼ "Create" a  "Scrollable Table" ▼
        JScrollPane sp = new JScrollPane(table);
        rightPanel.add(sp);





        // •──── "Handling" the "Click Events" for the "Table Rows" ────•
        // [1] "Action Listener" → for the "Table Rows"
        table.getSelectionModel().addListSelectionListener(e -> tableRowSelected());



        // ♦────────── Adding "Panels" to "Main Panel" ───────────♦
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }






    // ♦──────────── "Methods" for "Left Panel" ("Grid") ────────────♦
    // ▬ [1] The "insertEmployee
    // IntoDB()" Method
    //      → for the "Save Button" Action Listener ▬
    private void insertEmployeeIntoDB() {

        // ▼ Creating the "Variables"
        //      → and "Getting" the "Text" of the "Text Field Widgets" ▼
        String cus_name = name_Field.getText();
        String mobile_num = tpNum_Field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Insert" the "Employee" into the "Database" ▼
        try{
            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ "Making" an "Insertion" by "Executing" the "Query" ▼
            statement.executeUpdate("INSERT INTO employees (employee_name, mobile_number) VALUES ('" + cus_name + "', '" + mobile_num + "')");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }




    // [2] The "updateEmployeeInDB()" Method
    //      → for the "Update Button" Action Listener ▬
    private void updateEmployeeInDB() {

        // ▼ Creating the "Variables"
        //      → and "Getting" the "Text" of the "Text Field Widgets" ▼
        String name = name_Field.getText();
        String tp = tpNum_Field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Update" the "Employee" into the "Database" ▼
        try{
            // ▼ "Creating" the "Statement" ▼
            Statement statement  = database.mycon().createStatement();

            // ▼ "Making" an "Update" by "Executing" the "Query" ▼
            statement.executeUpdate(
                    "UPDATE employees SET employee_name = '" + name + "' , mobile_number = '" + tp + "' WHERE mobile_number = '" + tp+"' ");
            // ▼ "Displaying" a "Pop Up Message" ▼
            JOptionPane.showMessageDialog(null, "Employee Updated!");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }




    // [3-1] The "deleteEmployeeFromDB()" Method
    //      → for the "Delete Button" Action Listener ▬
    private void deleteEmployeeFromDB() {
        // ▼ Creating the "Variable"
        //      → and "Getting" the "Text" of the "Text Field Widget" ▼
        String mobile_num = search_Field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Delete" the "Employee" from the "Database" ▼
        try{
            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();

            // ▼ "Making" a "Delete" by "Executing" the "Query" ▼
            statement.executeUpdate("DELETE FROM employees WHERE mobile_number = '" + mobile_num + "'");

            // ▼ "Displaying" a "Pop Up Message" ▼
            JOptionPane.showMessageDialog(null, "Employee Deleted!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
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
            // ▼ "Getting" the "Value" of the "Mobile Number" ("Column 2") from the "Selected Row" ▼
            String mobileNumber = table.getValueAt(row, 1).toString();

            // ▼ "Executing" the "Query" to "Delete" the "Employee" from the "Database" ▼
            try {
                // ▼ "Creating" the "Statement" ▼
                Statement statement = database.mycon().createStatement();

                // ▼ "Making" a "Delete" by "Executing" the "Query" ▼
                statement.executeUpdate("DELETE FROM employees WHERE mobile_number = '" + mobileNumber + "'");

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





    // [4] The "searchTableByName()" Method
    //      → for the "Search Table Button" Action Listener ▬
    private void searchTableByName() {

        // ▼ Creating the "Variable"
        //      → and "Getting" the "Text" of the "Text Field Widget" ▼
        String name = searchTable_field.getText();


        // ▼ "Executing" the "Query"
        //      → to "Search" by "Name"
        //      → the "Employee" from the "Database" ▼
        try{

            // ▼ "Resetting" the "Row Count" ▼
            defaultTableModel.setRowCount(0);


            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();


            // ▼ Using "Result Set"
            //      → to "Fetch" the "Data"
            //      → in "Form" of "Table" or "Rows" ▼
            // ▼ "Storing" the "Selection"
            //      → in the "ResultSet" Object
            //      → where the "Employee Name"
            //      → is "Similar" to the "Searched Name" ▼
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM employees WHERE employee_name LIKE '%" + name + "%'");


            // ► Getting "All Matching Records"
            //      → from the "Database"
            //      → into "Table" ▼
            while(resultSet.next()){

                // ▼ Creating the "Vector" Object ▼
                Vector<String> vector = new Vector<>();

                // ▼ Getting the "Strings" for "Each Column"
                vector.add(resultSet.getString(1));
                vector.add(resultSet.getString(2));

                // ▼ Adding the "Row" to the "Table" ▼
                defaultTableModel.addRow(vector);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    // [5] The "searchEmployeeByMobileNumber()" Method
    //      → for the "Search Button" Action Listener ▬
    private void searchEmployeeByMobileNumber() {

        // ▼ Creating the "Variable"
        //      → and "Getting" the "Text" of the "Text Field Widget" ▼
        String search = search_Field.getText();


        // ▼ "Search" the "Employee"
        //      → from the "Database"
        //      → by "Mobile Number"
        //      → to Allow the "User"
        //      → to "Reset Employee" ▼
        try{

            // ▼ "Creating" the "Statement" ▼
            Statement statement  = database.mycon().createStatement();

            // ▼ "Storing" the "Selection"
            //      → in the "ResultSet" Object
            //      → where the "Mobile Number"
            //      → is "Similar" to the "Searched Name" ▼
            ResultSet  resultSet = statement.executeQuery(
                    "SELECT * FROM employees WHERE mobile_number = '" + search + "'");


            // ▼ "Checking" if the "ResultSet" is "Not Empty" ▼
            if (resultSet.next()){
                // ▼ "Resetting" the "Text Fields" ▼
                name_Field.setText(resultSet.getString("employee_name"));
                tpNum_Field.setText(resultSet.getString("mobile_number"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ▼ "Calling" the "Method" Method ▼
        loadTable();
    }






    // ♦──────────── "Method" for "Right Panel" ("Grid") ────────────♦
    // ▬ [0] The "loadTable()" Method
    //      → to "Load All" the "Employees Data"
    //      → from the "Database"
    //      → into the "Table" from the "Right Panel" ▬
    public void loadTable()  {

        // ▼ "Getting All" the "Data" from "Database" ▼
        try {
            // ▼ "Initializing" the "Default Table Model" ▼
            defaultTableModel = new DefaultTableModel();
            table = new JTable(defaultTableModel);

            // ▼ Adding "Columns" to "Table" ▼
            defaultTableModel.addColumn("Employee Name");
            defaultTableModel.addColumn("Mobile Number");


            // ▼ "Creating" the "Statement" ▼
            Statement statement = database.mycon().createStatement();


            // ▼ Using "Result Set"
            //      → to "Fetch" the "Data"
            //      → in "Form" of "Table" or "Rows" ▼
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");


            // ► Inserting "All Received Rows/Records"
            //      → from the "Database"
            //      → into "Table" ▼
            while (resultSet.next()) {

                // ▼ Creating the "Vector" Object ▼
                Vector<String> vector = new Vector<>();

                // ▼ Getting the "Strings" for "Each Column"
                vector.add(resultSet.getString(1));
                vector.add(resultSet.getString(2));

                // ▼ Adding the "Row" to the "Table" ▼
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

        // ▼ "Getting" the "Number" of "Selected Row"
        int row = table.getSelectedRow();

        // ▼ "Getting" the "Value" of the "Selected Row" ▼
        String name = table.getValueAt(row,0).toString();
        String mobile_number = table.getValueAt(row,1).toString();

        // ▼ "Resetting" the "Text Fields"
        //      → with the finded "Columns Values" ▼
        name_Field.setText(name);
        tpNum_Field.setText(mobile_number);
//            search_Field.setText(mobile_number);
    }
}

