package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KichCo;
import services.KichCoService;
import services.MauSacService;
import services.impl.KichCoServiceImpl;
import services.impl.MauSacServiceImpl;

public class KichCoFrame extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private KichCoService kichCoService = new KichCoServiceImpl();
    private Integer currentPage = 1;
    private Integer rowCountPage = 5;
    private Integer totalPage;
    private List<KichCo> list;

    public KichCoFrame() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTotalPage();
        dtm = (DefaultTableModel) tblDanhMuc.getModel();
        list = kichCoService.getAll();
        loadData(list, currentPage);
    }

    private void setTotalPage() {
        int totalItem = kichCoService.getAll().size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = totalItem / rowCountPage;
        }
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
    }

    private void loadData(List<KichCo> list, int page) {
        dtm.setRowCount(0);
        int limit = page * rowCountPage;
        int totalItem = list.size();
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            KichCo mauSac = list.get(start);
            dtm.addRow(new Object[]{
                mauSac.getSize()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private KichCo getForm() {
        String id = null;
        String name = txtTen.getText();
        try {
            Integer.parseInt(name);
        } catch (Exception e) {
            return null;
        }
        return new KichCo(Integer.parseInt(name));
    }

    private KichCo getToTable(int selectedRow) {
        int index = (currentPage - 1) * rowCountPage + selectedRow;
        return kichCoService.getAll().get(index);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPage = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhMuc = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhMuc = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(89, 146, 204));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Close_2.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        lblPage.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPage.setText("Trang");

        txtTen.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Search:");

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        tblDanhMuc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tblDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME"
            }
        ));
        tblDanhMuc.setRowHeight(30);
        tblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhMuc);

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tblDanhMuc.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tblDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME"
            }
        ));
        tblDanhMuc.setRowHeight(30);
        tblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhMuc);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Kích Cỡ");

        txtSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTen)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnNext))))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addComponent(jLabel2))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNext, btnPrev});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(lblPage))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (getForm() == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ");
        } else {
            String thongBao = kichCoService.add(getForm());
            list = kichCoService.getAll();
            loadData(kichCoService.getAll(), currentPage);
            JOptionPane.showMessageDialog(this, thongBao);
        }
        setTotalPage();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblDanhMuc.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn dữ liệu trong bảng để sửa");
        } else {
            KichCo mauSac = new KichCo();
            mauSac.setId(getToTable(selectedRow).getId());
            mauSac.setSize(Integer.parseInt(txtTen.getText()));
            String thongBao = kichCoService.update(mauSac);
            list = kichCoService.getAll();
            loadData(list, currentPage);
            JOptionPane.showMessageDialog(this, thongBao);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblDanhMuc.getSelectedRow();
        int index = (currentPage - 1) * rowCountPage + selectedRow;
        txtTen.setText(kichCoService.getAll().get(index).getSize().toString());
    }//GEN-LAST:event_tblDanhMucMouseClicked

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        setTotalPage();
        if (currentPage == 1) {
            currentPage = totalPage;
            loadData(list, currentPage);
        } else {
            currentPage--;
            loadData(list, currentPage);
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        setTotalPage();
        if (currentPage == totalPage) {
            currentPage = 1;
            loadData(list, currentPage);
        } else {
            currentPage++;
            loadData(list, currentPage);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String search = txtSearch.getText().toLowerCase();
        List<KichCo> searchList = new ArrayList<>();
        for (KichCo mauSac : kichCoService.getAll()) {
            if (mauSac.getId().toLowerCase().contains(search) || mauSac.getSize().toString().toLowerCase().contains(search)) {
                searchList.add(mauSac);
            }
        }
        
        list = searchList;
        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KichCoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KichCoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KichCoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KichCoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KichCoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPage;
    private javax.swing.JTable tblDanhMuc;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
