package view;

import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import java.sql.SQLException;
import repobanhang.HoaDnRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import repobanhang.HoaDonChiTietRepo;
import repository.JOPane;

public class QuanLyHD extends javax.swing.JPanel {

    DefaultTableModel dtmHD;
    DefaultTableModel dtmHDCT;
    int soTrang = 1; //số Trang hiện tại
    int soDong = 6;//Số dòng hiển thị mỗi trang của bảng

    HoaDonChiTietRepo hdctrepo = new HoaDonChiTietRepo();
    HoaDnRepo hdrepo = new HoaDnRepo();

    List<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
    List<HoaDon> listHoaDon = new ArrayList<>();
    List<HoaDon> paidHoaDon = hdrepo.getAllHoaDonByTrangThai("Đã thanh toán");

    public QuanLyHD() {
        initComponents();
        listHoaDon = hdrepo.getAllHoaDon();
        dtmHD = (DefaultTableModel) tblHD.getModel();
        dtmHD.setRowCount(soDong);
        dtmHDCT = (DefaultTableModel) tblHDCT.getModel();
        dtmHDCT.setRowCount(soDong);
        configTable();
        fillTable();
    }

    private void configTable() {
        int[] preferredWidths = {75, 70, 75, 155, 90, 90, 80, 80, 120};
        for (int i = 0; i < preferredWidths.length; i++) {
            this.tblHD.getColumnModel().getColumn(i).setPreferredWidth(preferredWidths[i]);
        }
    }

    private void next() {
        int maxPage = (int) Math.ceil((double) hdrepo.getAllHoaDonByTrangThai("Đã thanh toán").size() / soDong);
        if (soTrang < maxPage) {
            soTrang++;
            fillTable();
        } else {
            JOPane.showMessageDialog(this, "Đã ở trang cuối");
        }
    }

    private void prev() {
        if (soTrang > 1) {
            soTrang--;
            fillTable();
        } else {
             JOPane.showMessageDialog(this, "Đã ở trang đầu");
        }
    }

    private void addRowToTableModel(HoaDon h) {
        dtmHD.addRow(new Object[]{
            h.getMaHoaDon(), h.getNhanVien().getMa(), h.getKhachHang().getMa(),
            h.getNgayTao(), h.getTongTien(), h.getTienKhachDua(), h.getTienThua(), h.getHinhThucThanhToan(),
            h.getTrangThai()
        });
    }

    private void fillTable() {
        int startIdx = (soTrang - 1) * soDong;
        int endIdx = Math.min(startIdx + soDong, paidHoaDon.size());
        dtmHD.setRowCount(0);

        for (int i = startIdx; i < endIdx; i++) {
            HoaDon h = paidHoaDon.get(i);
            addRowToTableModel(h);
        }

        int maxPage = (int) Math.ceil((double) paidHoaDon.size() / soDong);
        lblTrang.setText("Trang " + soTrang + "/" + maxPage);
        configTable();
    }


    private void showDataGioHang(String id) {
        try {
            listHoaDonChiTiet = hdctrepo.getAllHoaDonChiTietByHoaDonID(id);
            dtmHDCT.setRowCount(0);

            for (HoaDonChiTiet h : listHoaDonChiTiet) {
                String g = h.getiDhoaDon();

                // Kiểm tra xem chi tiết hoá đơn có thuộc vào ID hoá đơn cần tìm hay không
                if (g.equals(id)) {
                    dtmHDCT.addRow(new Object[]{
                        tblHDCT.getRowCount() + 1,
                        h.getGiayChiTiet().getGiay().getMa(),
                        h.getGiayChiTiet().getGiay().getName(),
                        h.getSoLuong(),
                        h.getGia(),
                        h.tongTien()
                    });

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrTT = new javax.swing.ButtonGroup();
        pnlMainQLHD = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTrang = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        txtMaKH = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        lblErrorSearch = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        pnlMainQLHD.setOpaque(false);
        pnlMainQLHD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("Quản Lý Hóa Đơn");
        pnlMainQLHD.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lblTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang.setText("Trang");
        pnlMainQLHD.add(lblTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 344, 130, -1));

        btnPrev.setBackground(new java.awt.Color(0, 0, 0));
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlMainQLHD.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, 50, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("Hóa Đơn Chi Tiết");
        pnlMainQLHD.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 374, -1, -1));

        tblHD.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HĐ", "Mã NV", "Mã KH", "Ngày tạo", "Tổng tiền", "Tiền khách đưa", "Tiền thừa", "Hình thức TT", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblHD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblHD.setGridColor(new java.awt.Color(204, 204, 255));
        tblHD.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblHD.setOpaque(false);
        tblHD.setRowHeight(30);
        tblHD.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tblHD.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblHD.getTableHeader().setReorderingAllowed(false);
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);

        pnlMainQLHD.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 127, 860, 205));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm");
        pnlMainQLHD.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 73, 122, 40));

        tblHDCT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã giày", "Tên giày", "Số lượng", "Đơn giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDCT.setRowHeight(30);
        jScrollPane2.setViewportView(tblHDCT);

        pnlMainQLHD.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 424, 1171, 203));

        txtMaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập mã KH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKHKeyReleased(evt);
            }
        });
        pnlMainQLHD.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 62, 135, -1));

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập mã NV", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        pnlMainQLHD.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 62, 135, -1));

        btnNext.setBackground(new java.awt.Color(0, 0, 0));
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlMainQLHD.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 50, -1));

        lblErrorSearch.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErrorSearch.setForeground(new java.awt.Color(255, 51, 51));
        pnlMainQLHD.add(lblErrorSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 6, 521, 32));
        pnlMainQLHD.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 140, 40));
        pnlMainQLHD.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 72, 130, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlMainQLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainQLHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        int row = tblHD.getSelectedRow();
        int rowHienTai = (soTrang - 1) * soDong + row;
        if (row >= 0) {
            listHoaDon = hdrepo.getAllHoaDon();
            HoaDon h = listHoaDon.get(rowHienTai);
            System.out.println("Index row HD = " + rowHienTai + " ID HD: " + h.getId());
            String idHoaDon = h.getId();
            showDataGioHang(idHoaDon);
            lblErrorSearch.setText(null);
        } else {
            lblErrorSearch.setText("Không có sản phẩm");
        }
    }//GEN-LAST:event_tblHDMouseClicked

    private void txtMaKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKHKeyReleased

        String maKH = txtMaKH.getText().trim();
        lblErrorSearch.setText("");

        if (!maKH.isEmpty()) {
            List<HoaDon> hd = hdrepo.getAllHoaDonByKhachHang(maKH);

            if (hd != null && !hd.isEmpty()) {
                fillTable();
            } else {
                lblErrorSearch.setText("Không tìm thấy hóa đơn");
            }
        }
    }//GEN-LAST:event_txtMaKHKeyReleased

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGrTT;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrorSearch;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JPanel pnlMainQLHD;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
