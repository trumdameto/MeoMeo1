package view;

import Entity.GiayChiTiet;
import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.KhachHang;
import java.sql.SQLException;
import repobanhang.HoaDnRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import repobanhang.GiayChiTietRepo;
import repobanhang.HoaDonChiTietRepo;
import repobanhang.KhachHangRepo;
import repobanhang.TichDienRepo;
import repository.JOPane;

public class LichSuHoaDon extends javax.swing.JFrame {

    DefaultTableModel dtmHD;
    DefaultTableModel dtmHDCT;
    int soTrang = 1; //số Trang hiện tại
    int soDong = 6;//Số dòng hiển thị mỗi trang của bảng

    HoaDonChiTietRepo hdctrepo = new HoaDonChiTietRepo();
    GiayChiTietRepo gct = new GiayChiTietRepo();
    HoaDnRepo hdrepo = new HoaDnRepo();
    KhachHangRepo khrp = new KhachHangRepo();
    TichDienRepo tdrp = new TichDienRepo();

    List<GiayChiTiet> listGiayChiTiet = new ArrayList<>();
    List<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
    List<HoaDon> listHoaDon = new ArrayList<>();
    List<KhachHang> listKhachHang = new ArrayList<>();

    public LichSuHoaDon() {
        initComponents();
        listHoaDon = hdrepo.getAllHoaDon();
        dtmHD = (DefaultTableModel) tblHD.getModel();
        dtmHD.setRowCount(soDong);
        dtmHDCT = (DefaultTableModel) tblHDCT.getModel();
        dtmHDCT.setRowCount(soDong);
        setActionListener();
        configTable();
        chkShowAll.setSelected(true);
        fillHuy();
        fillAll();
    }

    private void configTable() {
        int[] preferredWidths = {75, 70, 55, 155, 90, 90, 80, 80, 120};

        for (int i = 0; i < preferredWidths.length; i++) {
            this.tblHD.getColumnModel().getColumn(i).setPreferredWidth(preferredWidths[i]);
        }
    }

    private void setActionListener() {
        rdoHuy.addActionListener(e -> fillHuy());
        rdoDaThanhToan.addActionListener(e -> fillHuy());
        chkShowAll.addActionListener(e -> fillAll());
    }

    private void next() {
        int maxPage = (int) Math.ceil((double) listHoaDon.size() / soDong);
        lblTrang.setText("Trang " + soTrang + "/" + maxPage);
        if (soTrang >= maxPage) {
            soTrang = maxPage;
            JOPane.showMessageDialog(this, "Đã ở trang cuối");
        } else {
            soTrang++;
            if (rdoHuy.isSelected() || rdoDaThanhToan.isSelected()) {
                fillHuy();
            } else {
                fillAll();
            }
        }
    }

    private void prev() {
        int maxPage = (int) Math.ceil((double) listHoaDon.size() / soDong);
        lblTrang.setText("Trang " + soTrang + "/" + maxPage);
        if (soTrang > 1) {
            soTrang--;
            if (rdoHuy.isSelected() || rdoDaThanhToan.isSelected()) {
                fillHuy();
            } else {
                fillAll();
            }
        } else {
            soTrang = 1;
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

    private void fillHuy() {
        // Lọc và hiển thị danh sách hóa đơn có trạng thái "Huỷ"
        if (rdoHuy.isSelected()) {
            fillTable(listHoaDon.stream().filter(h -> h.getTrangThai().equalsIgnoreCase("Huỷ")).collect(Collectors.toList()));
        } else if (rdoDaThanhToan.isSelected()) {
            fillTable(listHoaDon.stream().filter(h -> h.getTrangThai().equalsIgnoreCase("Đã thanh toán")).collect(Collectors.toList()));
        }
    }

    private void fillAll() {
        // Gọi fillTable với toàn bộ danh sách hóa đơn
        fillTable(listHoaDon);
    }

    private void fillTable(List<HoaDon> hoaDons) {
        int startIdx = (soTrang - 1) * soDong;
        int endIdx = Math.min(startIdx + soDong, hoaDons.size());
        dtmHD.setRowCount(0);
        List<String> tt = hdrepo.selectAllTrangThaiHoaDon();

        for (int i = startIdx; i < endIdx; i++) {
            HoaDon h = hoaDons.get(i);
            String status = h.getTrangThai();
            if (isConditionMet(status, tt)) {
                addRowToTableModel(h);
            }
        }

        int maxPage = (int) Math.ceil((double) hoaDons.size() / soDong);
        lblTrang.setText("Trang " + soTrang + "/" + maxPage);
        configTable();
    }

    private boolean isConditionMet(String status, List<String> tt) {
        return (rdoHuy.isSelected() && tt.contains(status) && status.equalsIgnoreCase("Huỷ"))
                || (rdoDaThanhToan.isSelected() && tt.contains(status) && status.equalsIgnoreCase("Đã thanh toán"))
                || (chkShowAll.isSelected() || tt.contains(status)
                && (status.equalsIgnoreCase("Hủy") || status.equalsIgnoreCase("Đã thanh toán") || status.equalsIgnoreCase("Chờ thanh toán")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMainQLHD = new javax.swing.JPanel();
        datechooserFrom = new com.toedter.calendar.JDateChooser();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        lblTrang = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        datechooserTo = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        chkShowAll = new javax.swing.JCheckBox();
        txtMaKH = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        rdoHuy = new javax.swing.JRadioButton();
        lblErrorSearch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMainQLHD.setOpaque(false);

        datechooserFrom.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đến", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        datechooserFrom.setForeground(new java.awt.Color(0, 0, 255));
        datechooserFrom.setDateFormatString("dd-MM-yyyy");
        datechooserFrom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        buttonGroup1.add(rdoDaThanhToan);
        rdoDaThanhToan.setText("Đã Thanh Toán");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("Quản Lý Hóa Đơn");

        lblTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang.setText("Trang");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Chọn trạng thái hóa đơn");

        btnPrev.setBackground(new java.awt.Color(0, 0, 0));
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 51));
        jLabel1.setText("Hóa Đơn Chi Tiết");

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
        if (tblHD.getColumnModel().getColumnCount() > 0) {
            tblHD.getColumnModel().getColumn(0).setResizable(false);
            tblHD.getColumnModel().getColumn(1).setResizable(false);
            tblHD.getColumnModel().getColumn(2).setResizable(false);
            tblHD.getColumnModel().getColumn(3).setResizable(false);
            tblHD.getColumnModel().getColumn(4).setResizable(false);
            tblHD.getColumnModel().getColumn(5).setResizable(false);
            tblHD.getColumnModel().getColumn(6).setResizable(false);
            tblHD.getColumnModel().getColumn(7).setResizable(false);
            tblHD.getColumnModel().getColumn(8).setResizable(false);
        }

        datechooserTo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Từ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        datechooserTo.setForeground(new java.awt.Color(0, 0, 255));
        datechooserTo.setDateFormatString("dd-MM-yyyy");
        datechooserTo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tìm");

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

        buttonGroup1.add(chkShowAll);
        chkShowAll.setText("Xem tất cả ?");

        txtMaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaKH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập mã KH", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKHKeyReleased(evt);
            }
        });

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaNV.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập mã NV", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(0, 0, 0));
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHuy);
        rdoHuy.setText("Huỷ ");

        lblErrorSearch.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErrorSearch.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pnlMainQLHDLayout = new javax.swing.GroupLayout(pnlMainQLHD);
        pnlMainQLHD.setLayout(pnlMainQLHDLayout);
        pnlMainQLHDLayout.setHorizontalGroup(
            pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                        .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainQLHDLayout.createSequentialGroup()
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(datechooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(datechooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addGap(0, 36, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainQLHDLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(lblErrorSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHuy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDaThanhToan)))
                .addContainerGap())
        );
        pnlMainQLHDLayout.setVerticalGroup(
            pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(rdoHuy)
                    .addComponent(chkShowAll)
                    .addComponent(jLabel3)
                    .addComponent(lblErrorSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaNV)
                            .addComponent(txtMaKH)
                            .addComponent(datechooserFrom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(datechooserTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrang)
                    .addComponent(btnPrev)
                    .addComponent(btnNext))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        int row = tblHD.getSelectedRow();
        int rowHienTai = (soTrang - 1) * soDong + row;
        if (row > 0) {
            listHoaDon = hdrepo.getAllHoaDon();
            HoaDon h = listHoaDon.get(rowHienTai);
            System.out.println("Index row HD = " + rowHienTai + " ID HD: " + h.getId());
            String idHoaDon = h.getId();
            showDataGioHang(idHoaDon);
            lblErrorSearch.setText("Không có sản phẩm");
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
                fillTable(hd);
            } else {
                lblErrorSearch.setText("Không tìm thấy hóa đơn");
            }
        } else {

            fillAll();
        }
    }//GEN-LAST:event_txtMaKHKeyReleased

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
                if ("Window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LichSuHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LichSuHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkShowAll;
    private com.toedter.calendar.JDateChooser datechooserFrom;
    private com.toedter.calendar.JDateChooser datechooserTo;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrorSearch;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JPanel pnlMainQLHD;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables

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
}
