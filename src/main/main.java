package main;

import component.Header;
import component.Menu;
import event.EventMenuSelected;
import form.MainForm;

import form.panel2;
import form.panel3;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import swing.CloseFrame;
import view.GiayPanel;
import view.GiayView;
import view.MenuFrame;
import view.QLSP;
import view.QuanLySP;


public class main extends javax.swing.JFrame implements CloseFrame{

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm mainForm;


    public main() {
        initComponents();
        init();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/titleApp.png")));
        setLocationRelativeTo(this);
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        jMain.setLayout(layout);
        menu = new Menu();
        header = new Header();
        mainForm = new MainForm();
       
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIdex, int subMenuIdex) {
//                System.out.println("Menu ");
                if(menuIdex==0){
                   if(subMenuIdex==0){
                        mainForm.showForm(new GiayView());
                   }else if(subMenuIdex==1){
                        mainForm.showForm(new QLSP());
                   }
                }else if(menuIdex==1){
                    if(subMenuIdex==0){
                    
                    }else if(subMenuIdex==1){
                        mainForm.showForm(new panel3());
                    }
                } else if(menuIdex==4){
                     closeFrame(main.this);
                     
                }
            }
        });
        menu.initMenu();
        jMain.add(menu, "w 230!, spany2");
        jMain.add(header, "h 50!, wrap");
        jMain.add(mainForm, "w 100%, h 100%");
      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMain = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 690));

        jMain.setBackground(new java.awt.Color(255, 255, 255));
        jMain.setOpaque(true);
        jMain.setPreferredSize(new java.awt.Dimension(700, 400));

        javax.swing.GroupLayout jMainLayout = new javax.swing.GroupLayout(jMain);
        jMain.setLayout(jMainLayout);
        jMainLayout.setHorizontalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );
        jMainLayout.setVerticalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jMain;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onClose(JFrame frame) {
        frame.dispose();
    }
}
