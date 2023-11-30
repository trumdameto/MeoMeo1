package view;

import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import java.awt.Image;
import repobanhang.HoaDnRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
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
    List<HoaDon> paidHoaDon = new ArrayList<>();

    String trangThaiHD = "Đã thanh toán";

    public QuanLyHD() {
        initComponents();
        setOpaque(false);
        hdrepo.getAllHoaDon();
        paidHoaDon = hdrepo.getAllHoaDonByTrangThai("Đã thanh toán");
        dtmHD = (DefaultTableModel) tblHD.getModel();
        dtmHD.setRowCount(soDong);
        dtmHDCT = (DefaultTableModel) tblHDCT.getModel();
        dtmHDCT.setRowCount(soDong);
        configTable();
        fillTable();
    }

    private void configTable() {
        int[] preferredWidths = {75, 75, 75, 135, 90, 90, 80, 80, 110};
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
        listHoaDonChiTiet = hdctrepo.getAllHoaDonChiTietByHoaDonID(id);
        dtmHDCT.setRowCount(0);
        System.out.println("List HDCT: " + listHoaDonChiTiet);
        for (HoaDonChiTiet h : listHoaDonChiTiet) {
            String g = h.getiDhoaDon();
            if (g.equalsIgnoreCase(id)) {
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
        dtmHDCT.fireTableDataChanged();
    }

    private void showAnh(String icon) {
        ImageIcon imageIcon = new ImageIcon(icon);

        int labelWidth = lblAnh.getWidth();
        int labelHeight = lblAnh.getHeight();

        if (labelWidth > 0 && labelHeight > 0) {
            Image newImage = imageIcon.getImage().getScaledInstance(360, 250, Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newImage);
            System.out.println("URL: " + icon);
            lblAnh.setIcon(newIcon);
        } else {
            System.out.println("Kích thước không hợp lý");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainQLHD = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        txtMaNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pnlThongTin = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblMaGiay = new javax.swing.JLabel();
        lblTenGiay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblHang = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblKieuDang = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblMauSac = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblKichCo = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        lblTrang = new javax.swing.JLabel();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        lblErrorSearch = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        btnNext = new javax.swing.JButton();
        jdcTo = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        pnlMainQLHD.setBackground(new java.awt.Color(255, 255, 255));
        pnlMainQLHD.setOpaque(false);

        btnPrev.setBackground(new java.awt.Color(0, 0, 0));
        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrev.setForeground(new java.awt.Color(255, 255, 255));
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        txtMaNV.setBackground(new java.awt.Color(204, 204, 204));
        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaNV.setBorder(null);
        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNVKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("Quản Lý Hóa Đơn");

        pnlThongTin.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlThongTin.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setText("Mã giày ");

        lblMaGiay.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblTenGiay.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("Tên giày");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel18.setText("Hãng");

        lblHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel19.setText("Kiểu dáng");

        lblKieuDang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel9.setText("Danh mục");

        lblDanhMuc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel10.setText("Màu sắc");

        lblMauSac.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel12.setText("Kích cỡ");

        lblKichCo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Thông tin giày");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel13.setText("Mô tả");

        lblMoTa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblMoTa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblMaGiay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblTenGiay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDanhMuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKieuDang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaGiay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(lblMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrang.setText("Trang");

        jdcFrom.setDateFormatString("yyyy-MM-dd");

        lblErrorSearch.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErrorSearch.setForeground(new java.awt.Color(255, 51, 51));

        txtMaKH.setBackground(new java.awt.Color(204, 204, 204));
        txtMaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaKH.setBorder(null);
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKHKeyReleased(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(0, 0, 0));
        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(255, 255, 255));
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

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
        tblHDCT.setRowHeight(30);
        tblHDCT.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblHDCT.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHDCT);
        if (tblHDCT.getColumnModel().getColumnCount() > 0) {
            tblHDCT.getColumnModel().getColumn(0).setResizable(false);
            tblHDCT.getColumnModel().getColumn(1).setResizable(false);
            tblHDCT.getColumnModel().getColumn(2).setResizable(false);
            tblHDCT.getColumnModel().getColumn(3).setResizable(false);
            tblHDCT.getColumnModel().getColumn(4).setResizable(false);
            tblHDCT.getColumnModel().getColumn(5).setResizable(false);
        }

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
        tblHD.setGridColor(new java.awt.Color(51, 51, 51));
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

        btnNext.setBackground(new java.awt.Color(0, 0, 0));
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jdcTo.setDateFormatString("yyyy-MM-dd");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Mã khách hàng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Từ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("đến");

        javax.swing.GroupLayout pnlMainQLHDLayout = new javax.swing.GroupLayout(pnlMainQLHD);
        pnlMainQLHD.setLayout(pnlMainQLHDLayout);
        pnlMainQLHDLayout.setHorizontalGroup(
            pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(lblErrorSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                    .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(576, 576, 576)
                                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNV)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcTo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTim))
                            .addComponent(jLabel8)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainQLHDLayout.setVerticalGroup(
            pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblErrorSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jdcTo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlMainQLHDLayout.createSequentialGroup()
                                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrev)
                            .addComponent(btnNext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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

    private void txtMaNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNVKeyReleased

        String maNV = txtMaNV.getText().trim();
        lblErrorSearch.setText("");

        if (!maNV.isEmpty()) {
            List<HoaDon> hd = hdrepo.getAllPaidHoaDonByNhanVien(maNV);

            if (hd != null && !hd.isEmpty()) {
                paidHoaDon = hd;
                fillTable();
            } else {
                lblErrorSearch.setText("Không tìm thấy hóa đơn");
            }
        } else {
            paidHoaDon = hdrepo.getAllHoaDonByTrangThai(trangThaiHD);
            fillTable();
        }
    }//GEN-LAST:event_txtMaNVKeyReleased

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
            paidHoaDon = hdrepo.getAllHoaDonByTrangThai(trangThaiHD);
            fillTable();

        }
    }//GEN-LAST:event_txtMaKHKeyReleased

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        Date toDate = new Date(jdcTo.getDate().getTime());

        Date fromDate = new Date(jdcFrom.getDate().getTime());

        if (toDate.after(fromDate)) {
            JOPane.showErrorDialog(this, "Ngày bắt đầu không thể sau ngày kết thúc.", "Date time");
            System.out.println("Ngày bắt đầu không thể sau ngày kết thúc.");
            return;
        }

        List<HoaDon> result = hdrepo.getHDByDate(toDate, fromDate);

        if (result != null && !result.isEmpty()) {
            for (HoaDon hoaDon : result) {
                System.out.println("Hóa đơn ID: " + hoaDon.getId());
                paidHoaDon = result;
                fillTable();
            }
            JOPane.showMessageDialog(this, "Đã tìm xong");
        } else {
            JOPane.showMessageDialog(this, "Không có hóa đơn trong khoảng thời gian này.");
            System.out.println("Không có hóa đơn trong khoảng thời gian này.");

        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        int rowHDCT = tblHDCT.getSelectedRow();
        int rowHD = tblHD.getSelectedRow();
        int rowHienTai = (soTrang - 1) * soDong + rowHD;

        paidHoaDon = hdrepo.getAllHoaDonByTrangThai(trangThaiHD);
        HoaDon h = paidHoaDon.get(rowHienTai);
        String idHD = h.getId();
        listHoaDonChiTiet = hdctrepo.getAllHDCTByIDHD(idHD);
        HoaDonChiTiet hdChiTiet = listHoaDonChiTiet.get(rowHDCT);
        hdChiTiet.setiDhoaDon(idHD);
        System.out.println("Index row HDCT = " + rowHDCT + "\nID HD: " + hdChiTiet.getiDhoaDon());

        if (rowHDCT >= 0) {
            //
            String maGiay = hdChiTiet.getGiayChiTiet().getGiay().getMa();
            String tenGiay = hdChiTiet.getGiayChiTiet().getGiay().getName();
            String hang = hdChiTiet.getGiayChiTiet().getHang().getName();
            String kieuDang = hdChiTiet.getGiayChiTiet().getKieuDang().getName();
            String danhMuc = hdChiTiet.getGiayChiTiet().getDanhMuc().getName();
            String mauSac = hdChiTiet.getGiayChiTiet().getMauSac().getName();
            String kichCo = String.valueOf(hdChiTiet.getGiayChiTiet().getKichCo().getSize());
            String anh = hdChiTiet.getGiayChiTiet().getHinhAnh();
            String moTa = hdChiTiet.getGiayChiTiet().getMoTa();
            //show Infor giàyct
            lblMaGiay.setText(maGiay);
            lblTenGiay.setText(tenGiay);
            lblHang.setText(hang);
            lblKieuDang.setText(kieuDang);
            lblDanhMuc.setText(danhMuc);
            lblMauSac.setText(mauSac);
            lblKichCo.setText(kichCo);
            lblMoTa.setText(moTa);
            showAnh(anh);
        }
    }//GEN-LAST:event_tblHDCTMouseClicked

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        int row = tblHD.getSelectedRow();
        int rowHienTai = (soTrang - 1) * soDong + row;
        if (row >= 0) {
            hdrepo.getAllHoaDon();
            HoaDon h = paidHoaDon.get(rowHienTai);
            System.out.println("Index row: " + rowHienTai + " ID HD: " + h.getId());
            String idHoaDon = h.getId();
            showDataGioHang(idHoaDon);
            resetForm();
        } else {
            lblErrorSearch.setText("Chọn một hóa đơn");
        }
    }//GEN-LAST:event_tblHDMouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTo;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblErrorSearch;
    private javax.swing.JLabel lblHang;
    private javax.swing.JLabel lblKichCo;
    private javax.swing.JLabel lblKieuDang;
    private javax.swing.JLabel lblMaGiay;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTenGiay;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JPanel pnlMainQLHD;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
private void resetForm() {
        lblAnh.setIcon(null);
        lblDanhMuc.setText(null);
        lblErrorSearch.setText(null);
        lblHang.setText(null);
        lblKichCo.setText(null);
        lblKieuDang.setText(null);
        lblMaGiay.setText(null);
        lblMauSac.setText(null);
        lblTenGiay.setText(null);
    }
}
