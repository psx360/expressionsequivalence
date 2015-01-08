package ps.expressionsequivalence;

import ps.expressionsequivalence.ui.MainForm;

/**
 *
 * @author Худяков Павел
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }



}
