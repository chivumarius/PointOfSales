import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


// ▬ "Extends" the "JFrame" Class ▬
public class MainScreen extends JFrame {
    // ♦ "Widgets" Declaration ♦
    // ▼ "J Toggle Button" ▼
    JToggleButton customer_btn;
    JToggleButton employee_btn;
    JToggleButton product_btn;
    JToggleButton sales_btn;
    JToggleButton invoice_btn;


    // ▼ "J Panel Loader" ▼
    JPanelLoader loader = new JPanelLoader();



    // ▬ "Constructor" ▬
    public MainScreen() throws HeadlessException {
        // ▼ "Call" the "Method" ▼
        createNavButtonsPanel();
    }



    // ▬ "Method"
    //      → in which we "Create" the "Panels" ▬
    private void createNavButtonsPanel() {
        // ♦───────────── "Panel 1" ─────────────♦
        JPanel p1 = new JPanel();

        TitledBorder titledBorder1 = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                " Navigation Buttons ",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION
        );

        p1.setBorder(titledBorder1);
        p1.setBounds(15, 50, 150, 250);
        p1.setLayout(new GridLayout(5, 1));

        // ▼ For "JPanel" ▼
        setLayout(null);



        // ───────────── J Toggle Button Initialization ─────────────
        customer_btn = new JToggleButton("Customers");
        employee_btn = new JToggleButton("Employees");
        sales_btn    = new JToggleButton("Sales");
        invoice_btn  = new JToggleButton("Invoice");
        product_btn  = new JToggleButton("Products");



        // ───────────── Button Group ─────────────
        ButtonGroup buttonGroup = new ButtonGroup();

        // ▼ Adding "JToggleButton" to "ButtonGroup" ▼
        buttonGroup.add(customer_btn);
        buttonGroup.add(employee_btn);
        buttonGroup.add(sales_btn);
        buttonGroup.add(invoice_btn);
        buttonGroup.add(product_btn);

        // ▼ Adding "J Toggle Button" to "Panel 1" ▼
        p1.add(customer_btn);
        p1.add(employee_btn);
        p1.add(product_btn);
        p1.add(invoice_btn);
        p1.add(sales_btn);





        // ♦───────────── "Panel 2" ─────────────♦
        JPanel p2 = new JPanel();

        TitledBorder titledBorder2 = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                " Dashboard ",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION
        );

        p2.setBorder(titledBorder2);
        p2.setBounds(200, 50, 900, 580);
        p2.setLayout(new GridLayout(5, 1));



        // •──── "Handling" the "Click Events" for the "Buttons" ────•
        // ▼ [1] "Customer" Button ▼
        customer_btn.addActionListener(e -> {
            // ▼ "Creating" an "Object"/"Instance" ▼
            CustomersPanel customersPanel = new CustomersPanel();
            // ▼ "Load" the "EmployeesPanel" into "Panel 2" ▼
            loader.jPanelLoader(p2, customersPanel);
        });



        // ▼ [2] "Employ" Button ▼
        employee_btn.addActionListener(e -> {
            // ▼ "Creating" an "Object"/"Instance" ▼
            EmployeesPanel employeesPanel = new EmployeesPanel();
            // ▼ "Load" the "EmployeesPanel" into "Panel 2" ▼
            loader.jPanelLoader(p2, employeesPanel);
        });



        // ▼ [3] "Product" Button ▼
        product_btn.addActionListener(e -> {
            // ▼ "Creating" an "Object"/"Instance" ▼
            ProductsPanel productsPanel = new ProductsPanel();
            // ▼ "Load" the "Products Panel" into "Panel 2" ▼
            loader.jPanelLoader(p2, productsPanel);
        });



        // ▼ [4] "Invoice" Button ▼
        invoice_btn.addActionListener(e -> {
            // ▼ "Creating" an "Object"/"Instance" ▼
            Invoice invoice = new Invoice();
            // ▼ "Load" the "Invoice Panel" into "Panel 2" ▼
            loader.jPanelLoader(p2,invoice);
        });



        // ▼ [5] "Sales" Button ▼
        sales_btn.addActionListener(e -> {
            // ▼ "Creating" an "Object"/"Instance" ▼
            SalesPanel salesPanel = new SalesPanel();
            // ▼ "Load" the "Sales Panel" into "Panel 2" ▼
            loader.jPanelLoader(p2, salesPanel);
        });





        // ♦────────── Adding "Panels" to "JPanel" ───────────♦
        add(p1);
        add(p2);
    }





    // ▬ The "First Run Method" - "main()" Method ▬
    public static void main(String[] args) {
        // ▼ Creating a "MainScreen" Instance/Object ▼
        MainScreen mainScreen = new MainScreen();

        // ▼ "Setting" the "Properties" of the "MainScreen" Object ▼
        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setTitle("Point of Sales");
        mainScreen.setBounds(0, 0, 1200, 800);
    }

}
