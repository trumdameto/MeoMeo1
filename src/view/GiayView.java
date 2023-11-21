package view;

import model.Giay;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import repository.GiayDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.JOPane;

public class GiayView extends javax.swing.JPanel {

    private final GiayDAO giayDAO = new GiayDAO();
    private final DefaultTableModel dtm;
    private int soTrang = 1; //số Trang hiện tại
    private final int soDong = 5;//Số dòng hiển thị mỗi trang của bảng
    private ArrayList<Giay> listGiay = new ArrayList<>();

    public GiayView() {
        initComponents();
        setOpaque(false);
        dtm = (DefaultTableModel) tblGiay.getModel();
        listGiay = giayDAO.getGiay();
        dtm.setRowCount(soDong);
        init();

    }

 private void init() {
        clickedRowTable1();
        fillTableByKeyword();
    }
//lấy dữ liệu trêb form

    Giay getForm() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã giày");
            return null;
        }
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên giày");
            return null;
        }
        Giay giay = new Giay(ma, ten);
        return giay;
    }
//Làm mới form

    void clear() {
        txtMa.setText("");
        txtTen.setText("");
        txtTimKiem.setText("");
    }
//Hiển thị từ bảng lên form

    void showTable(Giay giay) {
        txtMa.setText(giay.getMa());
        txtTen.setText(giay.getName());
    }
//ấn vào bảng lên showTable

//    void clickedRowTable() {
//        tblGiay.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (!isSearching) {
//                    if (e.getClickCount() == 2) {
//                        xemChiTiet();
//                    } else {
//                        int row = tblGiay.getSelectedRow();
//                        if (row >= 0) {
//                            int rowHienTai = (soTrang - 1) * soDong + row;
//                            if (rowHienTai < listGiay.size()) {
//                                String ma = (String) tblGiay.getValueAt(rowHienTai, 0);
//                                System.out.println("index" + row);
//                                String id = giayDAO.getGiay().get(rowHienTai).getId();
//
//                                System.out.println("ID: " + id);
//                                Giay g = giayDAO.selectById(ma);
//                                showTable(g);
//                            }
//                        }
//                    }
//                }
//            }
//        });
//    }
    void clickedRowTable1() {
        tblGiay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    xemChiTiet();
                } else {
                    int row = tblGiay.getSelectedRow();
                    if (row >= 0) {
                        int rowHienTai = (soTrang - 1) * soDong + row;
                        if (rowHienTai < listGiay.size()) {
                            String id = giayDAO.getGiay().get(rowHienTai).getId();
                            System.out.println("index Clicked" + row);
                            Giay g = giayDAO.selectById(id);
                            showTable(g);
                            System.out.println("ID clicked: " + id);
                        } else {

                            System.out.println("vị trí ko hợp lệ: " + rowHienTai);
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainQlg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrTblGiay = new javax.swing.JScrollPane();
        tblGiay = new javax.swing.JTable();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        lblTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        pnlMainQlg.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Mã giày:");

        tblGiay.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tblGiay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã giày", "Tên giày"
            }
        ));
        tblGiay.setAlignmentX(1.0F);
        tblGiay.setAlignmentY(1.0F);
        tblGiay.setAutoscrolls(false);
        tblGiay.setOpaque(false);
        tblGiay.setRowHeight(28);
        tblGiay.setRowMargin(2);
        tblGiay.setSelectionBackground(new java.awt.Color(0, 0, 0));
        scrTblGiay.setViewportView(tblGiay);

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Tên giày:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXem.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        btnXem.setForeground(new java.awt.Color(102, 102, 102));
        btnXem.setText("Xem");
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem)
            .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXem))
        );

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btnPrev.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        jPanel3.add(btnPrev);

        lblTrang.setBackground(new java.awt.Color(204, 255, 205));
        lblTrang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTrang.setText("Trang");
        jPanel3.add(lblTrang);

        btnNext.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel3.add(btnNext);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Tìm mã");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logonew.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quản lý giày");

        javax.swing.GroupLayout pnlMainQlgLayout = new javax.swing.GroupLayout(pnlMainQlg);
        pnlMainQlg.setLayout(pnlMainQlgLayout);
        pnlMainQlgLayout.setHorizontalGroup(
            pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainQlgLayout.createSequentialGroup()
                .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainQlgLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainQlgLayout.createSequentialGroup()
                                .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlMainQlgLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(scrTblGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainQlgLayout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainQlgLayout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnlMainQlgLayout.setVerticalGroup(
            pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainQlgLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainQlgLayout.createSequentialGroup()
                        .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlMainQlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addComponent(scrTblGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainQlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainQlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Giay giay = getForm();
        if (giay == null) {
            return;
        } else {
            boolean confirm = JOPane.showConfirmDialog(this, "Are you sure?");
            if (confirm) {
                giayDAO.InsertGiay(giay);
                listGiay.add(0, giay);
                listGiay = giayDAO.getGiay();
                fillTableByKeyword();
                JOPane.showMessageDialog(this, "Thêm thành công!");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
       Giay giay = getForm();
        if (giay == null) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại!");
            System.out.println("getForm() null" + giay);
        } else {
            boolean confirm = JOPane.showConfirmDialog(this, "Are you sure?");
            if (confirm) {
                int row = tblGiay.getSelectedRow();
                if (row >= 0) {
                    int rowHienTai = (soTrang - 1) * soDong + row;
                    String id = giayDAO.getGiay().get(rowHienTai).getId();
                    giay.setId(id);
                    System.out.println("ID: " + giay.getId());
                    System.out.println("Ma Giay: " + giay.getMa());
                    System.out.println("Name: " + giay.getName());
                    int result = giayDAO.UpdateGiay(giay);
                    if (result > 0) {
                        listGiay = giayDAO.getGiay();
                        fillTableByKeyword();
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại!");
                        System.out.println("Resultset " + result);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một giày để sửa!");
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int maxPage = (int) Math.ceil((double) giayDAO.getGiay().size() / soDong);
        if (soTrang < maxPage) {
            soTrang++;
            fillTableByKeyword();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (soTrang > 1) {
            soTrang--;
           fillTableByKeyword();
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        fillTableByKeyword();

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        xemChiTiet();
    }//GEN-LAST:event_btnXemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
        fillTableByKeyword();
    }//GEN-LAST:event_btnMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JPanel pnlMainQlg;
    private javax.swing.JScrollPane scrTblGiay;
    private javax.swing.JTable tblGiay;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
void fillTableByKeyword() {
        String keyword = txtTimKiem.getText().trim();
        ArrayList<Giay> list = giayDAO.selectByKeyword(keyword);

        int maxPage = (int) Math.ceil((double) list.size() / soDong);
        int startIdx = (soTrang - 1) * soDong;
        int endIdx = Math.min(startIdx + soDong, list.size());

        dtm.setRowCount(0);

        for (int i = startIdx; i < endIdx; i++) {
            Giay giay = list.get(i);
            dtm.addRow(new Object[]{
                giay.getMa(), giay.getName()
            });
        }

        dtm.setColumnIdentifiers(new String[]{
            "Mã giày", "Tên giày"
        });
        lblTrang.setText(soTrang + " / " + maxPage);
        if (list.isEmpty()) {
            JOPane.showMessageDialog(this, "Không có mã giày phù hợp");
            clear();
        }
    }

    void xemChiTiet() {

        int row = tblGiay.getSelectedRow();
        if (row >= 0) {
            int rowHienTai = (soTrang - 1) * soDong + row;
            if (rowHienTai < listGiay.size()) {
                String ma = listGiay.get(rowHienTai).getMa();
                String ten = listGiay.get(rowHienTai).getName();
                boolean confirm = JOPane.showConfirmDialog(this, "Xem chi tiết mã: " + ma + "\n Tên: " + ten);
                if (confirm) {
                    Giay giay = listGiay.get(rowHienTai);

                    GiayChiTietFrame chiTietFrame = new GiayChiTietFrame(giay);
                    chiTietFrame.setVisible(true);
                }
            }
        } else {
            JOPane.showMessageDialog(this, "Chọn một sản phẩm để xem các mẫu chi tiết");
        }
    }

}
