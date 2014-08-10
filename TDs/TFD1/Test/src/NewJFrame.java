
public class NewJFrame extends javax.swing.JFrame {
    public NewJFrame() {
        super("Swing");
        initComponents();
    }
    private void initComponents() {        
        jTextArea1 = new javax.swing.JTextArea(); 
        jScrollPane1 = new javax.swing.JScrollPane(jTextArea1);
        this.getContentPane().add (jScrollPane1);
        pack();
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    private  javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane jScrollPane1;
}