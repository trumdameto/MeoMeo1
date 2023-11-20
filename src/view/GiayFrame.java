package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Giay;
import services.GiayService;
import services.impl.GiayServiceImpl;

public class GiayFrame extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private GiayService giayService = new GiayServiceImpl();
    private Integer currentPage = 1;
    private Integer rowCountPage = 5;
    private Integer totalPage;
    private List<Giay> list;

    public GiayFrame() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTotalPage();
        dtm = (DefaultTableModel) tblDanhMuc.getModel();
        list = giayService.getAll();
        loadData(list, currentPage);
    }

    private void setTotalPage() {
        int totalItem = giayService.getAll().size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = totalItem / rowCountPage;
        }
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
    }

    private void loadData(List<Giay> list, int page) {
        dtm.setRowCount(0);
        int limit = page * rowCountPage;
        int totalItem = list.size();
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            Giay giay = list.get(start);
            dtm.addRow(new Object[]{
                giay.getMa(),
                giay.getName()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private Giay getForm() {
        String id = null;
        String ma = txtMa.getText();
        String name = txtTen.getText();
        if (name.isBlank()) {
            return null;
        }
        return new Giay(ma, name);
    }

    private Giay getToTable(int selectedRow) {
        int index = (currentPage - 1) * rowCountPage + selectedRow;
        return giayService.getAll().get(index);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        pnlMainGiay = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhMuc = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblPage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        btnPrev = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlMainGiay.setBackground(new java.awt.Color(255, 255, 255));

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã", "Tên"
            }
        ));
        tblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseClicked(evt);
            }
        });
        tblDanhMuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDanhMucKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhMuc);

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Logo.png"))); // NOI18N

        lblPage.setText("Trang");

        jLabel1.setBackground(new java.awt.Color(204, 255, 205));
        jLabel1.setText("Name:");

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnChiTiet.setText("Chi tiết");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });
        jPanel1.add(btnChiTiet);

        jLabel2.setText("Mã:");

        jLabel4.setBackground(new java.awt.Color(204, 255, 205));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Quản lý giày");

        javax.swing.GroupLayout pnlMainGiayLayout = new javax.swing.GroupLayout(pnlMainGiay);
        pnlMainGiay.setLayout(pnlMainGiayLayout);
        pnlMainGiayLayout.setHorizontalGroup(
            pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainGiayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlMainGiayLayout.createSequentialGroup()
                        .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainGiayLayout.createSequentialGroup()
                                .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(331, 331, 331)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(pnlMainGiayLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainGiayLayout.createSequentialGroup()
                                .addComponent(txtSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlMainGiayLayout.createSequentialGroup()
                                .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainGiayLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainGiayLayout.setVerticalGroup(
            pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainGiayLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainGiayLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainGiayLayout.createSequentialGroup()
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainGiayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPage)
                    .addComponent(btnPrev)
                    .addComponent(btnNext))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainGiay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainGiay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (getForm() == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ");
        } else {
            String thongBao = giayService.add(getForm());
            list = giayService.getAll();
            loadData(list, currentPage);
             list.add(0, getForm());
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
            Giay giay = new Giay();
            giay.setId(getToTable(selectedRow).getId());
            giay.setMa(txtMa.getText());
            giay.setName(txtTen.getText());
            String thongBao = giayService.update(giay);
            list = giayService.getAll();
            loadData(list, currentPage);
            JOptionPane.showMessageDialog(this, thongBao);
        }
        setTotalPage();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblDanhMuc.getSelectedRow();
        int index = (currentPage - 1) * rowCountPage + selectedRow;
        String ten = giayService.getAll().get(index).getName();
        String ma = giayService.getAll().get(index).getMa();
        txtTen.setText(ten);
        txtMa.setText(ma);
    }//GEN-LAST:event_tblDanhMucMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String search = txtSearch.getText().toLowerCase();
        List<Giay> searchList = new ArrayList<>();
        for (Giay giay : giayService.getAll()) {
            if (giay.getId().toLowerCase().contains(search) || giay.getName().toLowerCase().contains(search)
                    || giay.getMa().toLowerCase().contains(search)) {
                searchList.add(giay);
            }
        }
        list = searchList;
        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_btnSearchActionPerformed

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

    private void tblDanhMucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDanhMucKeyReleased
        // TODO add your handling code here:
        String column = tblDanhMuc.getColumnName(1);
        int selected = tblDanhMuc.getSelectedRow();

        JOptionPane.showMessageDialog(this, tblDanhMuc.getValueAt(selected, 1));
    }//GEN-LAST:event_tblDanhMucKeyReleased

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        int selectedRow = tblDanhMuc.getSelectedRow();
        int index = (currentPage - 1) * rowCountPage + selectedRow;
        String ma = list.get(index).getMa();
        String ten = list.get(index).getName();
        int confirm = JOptionPane.showConfirmDialog(this, "Quản lý mã: "+ma+" \nTên: "+ten);
        if (confirm == JOptionPane.YES_OPTION) {
            GiayChiTietFrame giayChiTiet = new GiayChiTietFrame(giayService.getAll().get(index));
            giayChiTiet.setVisible(true);
        }
    }//GEN-LAST:event_btnChiTietActionPerformed

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
            java.util.logging.Logger.getLogger(GiayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiayFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSearch;
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
    private javax.swing.JPanel pnlMainGiay;
    private javax.swing.JTable tblDanhMuc;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
