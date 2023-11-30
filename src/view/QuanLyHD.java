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
    List<HoaDon> paidHoaDon = new ArrayList<>();

    public QuanLyHD() {
        initComponents();
        setOpaque(false);
        listHoaDon = hdrepo.getAllHoaDon();
        paidHoaDon =hdrepo.getAllHoaDonByTrangThai("Đã thanh toán");
        dtmHD = (DefaultTableModel) tblHD.getModel();
        dtmHD.setRowCount(soDong);
        dtmHDCT = (DefaultTableModel) tblHDCT.getModel();
        dtmHDCT.setRowCount(soDong);
        configTable();
        fillTable();
    }

    private void configTable() {
        int[] preferredWidths = {75, 70, 75, 155, 90, 90, 80, 80, 110};
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
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblKiemTraDiem = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        lblTienThua1 = new javax.swing.JLabel();

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
        pnlMainQLHD.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 350, 50, -1));

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
        tblHD.setGridColor(new java.awt.Color(0, 0, 0));
        tblHD.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblHD.setOpaque(false);
        tblHD.setRowHeight(30);
        tblHD.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblHD.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHD.getTableHeader().setReorderingAllowed(false);
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);

        pnlMainQLHD.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 127, 840, 210));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm");
        pnlMainQLHD.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 122, 40));

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
        tblHDCT.setGridColor(new java.awt.Color(51, 51, 51));
        tblHDCT.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblHDCT.setRowHeight(30);
        tblHDCT.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblHDCT.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHDCT);

        pnlMainQLHD.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 840, 210));

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
        pnlMainQLHD.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, 50, -1));

        lblErrorSearch.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErrorSearch.setForeground(new java.awt.Color(255, 51, 51));
        pnlMainQLHD.add(lblErrorSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 6, 521, 32));
        pnlMainQLHD.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 140, 40));
        pnlMainQLHD.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 72, 130, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin giày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Nhân Viên");

        lblNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblNhanVien.setText("Null");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Mã hóa đơn");

        lblMaHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Điểm tích lũy");

        lblKiemTraDiem.setText("0");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Tổng tiền");

        lblTongTien.setText("0");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Tiền khách đưa");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Tiền thừa");

        lblTienThua.setText("0");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Hình thức TT");

        lblTongTien1.setText("0");

        lblTienThua1.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblMaHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblKiemTraDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(lblKiemTraDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(lblTongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1)))
                .addGap(263, 263, 263))
        );

        pnlMainQLHD.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 330, 570));

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
            HoaDon h = paidHoaDon.get(rowHienTai);
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
            List<HoaDon> hd = hdrepo.getAllPaidHoaDonByKhachHang(maKH);

            if (hd != null && !hd.isEmpty()) {
                paidHoaDon = hd;
                fillTable();
            } else {
                lblErrorSearch.setText("Không tìm thấy hóa đơn");
            }
        } else {
            paidHoaDon = hdrepo.getAllHoaDonByTrangThai("Đã thanh toán");
            fillTable();
        }
    }//GEN-LAST:event_txtMaKHKeyReleased

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
       
    }//GEN-LAST:event_tblHDCTMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblErrorSearch;
    private javax.swing.JLabel lblKiemTraDiem;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTienThua1;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JPanel pnlMainQLHD;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
