import javax.swing.JPanel;


// ▬ "Navigation Helper" Template
//      → to "Load" the "Panels"
//      → "Smooth Navigation" and
//      → "Panel Loading" Characteristics ▬
public class JPanelLoader {

    // ▬ "Method" ▬
    public  void jPanelLoader(JPanel Main,JPanel setPanel){
        Main.removeAll();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Main);
        Main.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(setPanel
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(setPanel
                        )
        );

        System.gc();
    }
}
