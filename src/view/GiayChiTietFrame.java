package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DanhMuc;
import model.Giay;
import model.GiayChiTiet;
import model.Hang;
import model.KichCo;
import model.KieuDang;
import model.MauSac;
import services.DanhMucService;
import services.GiayChiTietService;
import services.HangService;
import services.KichCoService;
import services.KieuDangService;
import services.MauSacService;
import services.impl.DanhMucServiceImpl;
import services.impl.GiayChiTietServiceImpl;
import services.impl.HangServiceImpl;
import services.impl.KichCoServiceImpl;
import services.impl.KieuDangServiceImpl;
import services.impl.MauSacServiceImpl;

public class GiayChiTietFrame extends javax.swing.JFrame {

    private final DanhMucFrame danhMucFrame = new DanhMucFrame();
    private final HangFrame hangFrame = new HangFrame();
    private final KieuDangFrame kieuDangFrame = new KieuDangFrame();
    private final MauSacFrame mauSacFrame = new MauSacFrame();
    private final KichCoFrame kichCoFrame = new KichCoFrame();

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<DanhMuc> listDanhMuc;
    private List<KichCo> listKichCo;
    private List<KieuDang> listKieuDang;
    private List<MauSac> listMauSac;
    private List<Hang> listHang;

    private final DanhMucService danhMucService = new DanhMucServiceImpl();
    private final KichCoService kichCoService = new KichCoServiceImpl();
    private final KieuDangService kieuDangService = new KieuDangServiceImpl();
    private final MauSacService mauSacService = new MauSacServiceImpl();
    private final GiayChiTietService giayChiTietService = new GiayChiTietServiceImpl();
    private final HangService hangService = new HangServiceImpl();

    private final Giay giayData;
    private Integer currentPage = 1;
    private final Integer rowCountPage = 5;
    private Integer totalPage;
    private List<GiayChiTiet> list;
    private String urlAnh = "";

    public GiayChiTietFrame(Giay giay) {
        initComponents();

        lblMaTen.setText(giay.getMa() + " - " + giay.getName());
        giayData = giay;
        dtm = (DefaultTableModel) tblDanhSach.getModel();
        setResizable(false);
        setLocation(423, 180);
        setList();
        setDataLocCbo();
        setDataCbo();
        loadCboBtn();
        setDefaultCloseOperation(2);
        list = giayChiTietService.getAllByGiay(giayData.getId());
        loadData(list, currentPage);
    }

    private void loadCboBtn() {
        JComboBox[] combo = new JComboBox[]{
            cboHang, cboKichCo, cboDanhMuc, cboKieuDang, cboMauSac
        };

        for (int i = 0; i < combo.length; i++) {
            int finalI = i;
            combo[i].addMouseListener(new MouseAdapter() {
                private boolean dataUpdated = false;
//Khi người dùng nhấn chuột vào các cbo thì load lại dữ liệu các cbo đó
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!dataUpdated) {
                        setList();
                        switch (finalI) {
                            case 0 ->
                                setDataHang();
                            case 1 ->
                                setDataKichCo();
                            case 2 ->
                                setDataDanhMuc();
                            case 3 ->
                                setDataKieuDang();
                            case 4 ->
                                setDataMauSac();
                        }
                        dataUpdated = true;
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    dataUpdated = false;
                }
            });
        }
    }

    private void setList() {
        list = giayChiTietService.getAllByGiay(giayData.getId());
        listDanhMuc = danhMucService.getAll();
        listKichCo = kichCoService.getAll();
        listKieuDang = kieuDangService.getAll();
        listMauSac = mauSacService.getAll();
        listHang = hangService.getAll();
    }

    private void setDataDanhMuc() {
        cboDanhMuc.removeAllItems();
        for (DanhMuc danhMuc : listDanhMuc) {
            cboDanhMuc.addItem(danhMuc.getName());
        }
    }

    private void setDataHang() {
        cboHang.removeAllItems();
        for (Hang hang : listHang) {
            cboHang.addItem(hang.getName());
        }
    }

    private void setDataKichCo() {
        cboKichCo.removeAllItems();
        for (KichCo kichCo : listKichCo) {
            cboKichCo.addItem(kichCo.getSize().toString());
        }
    }

    private void setDataKieuDang() {
        cboKieuDang.removeAllItems();
        for (KieuDang kieuDang : listKieuDang) {
            cboKieuDang.addItem(kieuDang.getName());
        }
    }

    private void setDataMauSac() {
        cboMauSac.removeAllItems();
        for (MauSac mauSac : listMauSac) {
            cboMauSac.addItem(mauSac.getName());
        }
    }

    private void setDataCbo() {
        setDataDanhMuc();
        setDataKichCo();
        setDataKieuDang();
        setDataMauSac();
        setDataTrangThai();
        setDataHang();
    }

    private void setTotalPage() {
        int totalItem = giayChiTietService.getAllByGiay(giayData.getId()).size();
        if (totalItem % rowCountPage == 0) {
            totalPage = totalItem / rowCountPage;
        } else {
            totalPage = totalItem / rowCountPage + 1;
        }
    }

    private void loadData(List<GiayChiTiet> list, int page) {
        dtm.setRowCount(0);
        setTotalPage();
        int limit = page * rowCountPage;
        int totalItem = list.size();
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            GiayChiTiet giayChiTiet = list.get(start);
            dtm.addRow(new Object[]{
                giayChiTiet.getHang().getName(),
                giayChiTiet.getKieuDang().getName(),
                giayChiTiet.getDanhMuc().getName(),
                giayChiTiet.getMauSac().getName(),
                giayChiTiet.getKichCo().getSize(),
                giayChiTiet.getGia(),
                giayChiTiet.getSoLuong(),
                giayChiTiet.getTrangThai()
            });
            if (start + 1 == limit) {
                return;
            }
        }
        dtm.setColumnIdentifiers(new String[]{
            "Hãng", "Kiểu Dáng", "Danh Mục", "Màu Sắc", "Kích Cỡ", "Giá", "Số Lượng", "Trạng Thái"
        });
    }

    private void setDataTrangThai() {
        cboTrangThai.removeAllItems();
        String[] trangThai = {"Hàng mới", "Hàng cũ", "Hàng..."};
        for (int i = 0; i < trangThai.length; i++) {
            String string = trangThai[i];
            cboTrangThai.addItem(string);
        }
    }

    private GiayChiTiet getDataForm() {
        Hang hang = null;
        KieuDang kieuDang = null;
        DanhMuc danhMuc = null;
        MauSac mauSac = null;
        KichCo kichCo = null;
        String gia = txtGia.getText();
        String soLuong = txtSoLuong.getText();
        String trangThai = cboTrangThai.getSelectedItem().toString();
        String moTa = txtMoTa.getText();
        double dongia = 0;
        int soluong = 0;
        if (gia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá ");
            txtGia.requestFocus();
            txtGia.setBackground(new Color(245, 124, 137));
            lblGia.setForeground(Color.red);
            return null;
        } else {
            try {
                dongia = Double.parseDouble(gia);
                if (dongia <= 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập giá lớn hơn 0");
                    txtGia.setBackground(new Color(245, 124, 137));
                    txtGia.requestFocus();
                    return null;
                } else {
                    txtGia.setBackground(getBackground());
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giá là một số");
                txtGia.setBackground(new Color(245, 124, 137));
                return null;
            }
        }

        if (soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ");
            lblSoLuong.setForeground(Color.red);
            txtSoLuong.setBackground(new Color(245, 124, 137));
            txtSoLuong.requestFocus();
            return null;
        } else {
            try {
                soluong = Integer.parseInt(soLuong);
                if (soluong <= 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                    txtSoLuong.setBackground(new Color(245, 124, 137));
                    txtSoLuong.requestFocus();
                    return null;
                } else {
                    txtSoLuong.setBackground(getBackground());
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số");
                txtSoLuong.setBackground(new Color(245, 124, 137));
                return null;
            }
        }

        for (int i = 0; i < listDanhMuc.size(); i++) {
            DanhMuc get = listDanhMuc.get(i);
            if (i == cboDanhMuc.getSelectedIndex()) {
                danhMuc = get;
            }
        }
        for (int i = 0; i < listKieuDang.size(); i++) {
            KieuDang get = listKieuDang.get(i);
            if (i == cboKieuDang.getSelectedIndex()) {
                kieuDang = get;
            }
        }
        for (int i = 0; i < listMauSac.size(); i++) {
            MauSac get = listMauSac.get(i);
            if (i == cboMauSac.getSelectedIndex()) {
                mauSac = get;
            }
        }
        for (int i = 0; i < listKichCo.size(); i++) {
            KichCo get = listKichCo.get(i);
            if (i == cboKichCo.getSelectedIndex()) {
                kichCo = get;
            }
        }
        for (int i = 0; i < listHang.size(); i++) {
            Hang get = listHang.get(i);
            if (i == cboHang.getSelectedIndex()) {
                hang = get;
            }
        }
        GiayChiTiet giayChiTiet = new GiayChiTiet();
        giayChiTiet.setGiay(giayData);
        giayChiTiet.setDanhMuc(danhMuc);
        giayChiTiet.setHang(hang);
        giayChiTiet.setHinhAnhUrl(urlAnh);
        giayChiTiet.setKichCo(kichCo);
        giayChiTiet.setKieuDang(kieuDang);
        giayChiTiet.setMauSac(mauSac);
        giayChiTiet.setGia(dongia);
        giayChiTiet.setSoLuong(soluong);
        giayChiTiet.setTrangThai(trangThai);
        giayChiTiet.setMoTa(moTa);

        return giayChiTiet;
    }

    private void showAnh(String icon) {
        // Chuyển ImageIcon thành Image
        ImageIcon imageIcon = new ImageIcon(icon);
        Image newImage = imageIcon.getImage().getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImage);
        urlAnh = icon;
        lblAnh.setIcon(newIcon);
    }

    private void fillTableToForm(GiayChiTiet giayChiTiet) {
        txtGia.setText(giayChiTiet.getGia() + "");
        txtMoTa.setText(giayChiTiet.getMoTa());
        txtSoLuong.setText(giayChiTiet.getSoLuong() + "");
        cboDanhMuc.setSelectedItem(giayChiTiet.getDanhMuc().getName());
        cboHang.setSelectedItem(giayChiTiet.getHang().getName());
        cboKichCo.setSelectedItem(giayChiTiet.getKichCo().getSize().toString());
        cboKieuDang.setSelectedItem(giayChiTiet.getKieuDang().getName());
        cboMauSac.setSelectedItem(giayChiTiet.getMauSac().getName());
        cboTrangThai.setSelectedItem(giayChiTiet.getTrangThai());
        if (giayChiTiet.getHinhAnhUrl() == null || giayChiTiet.getHinhAnhUrl() == "") {
            lblAnh.setText("Ảnh lỗi");
        } else {
            showAnh(giayChiTiet.getHinhAnhUrl());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblMaTen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        lblPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboLocHang = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cboLocKieuDang = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cboLocDanhMuc = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cboLocMauSac = new javax.swing.JComboBox<>();
        cboLocKichCo = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cboLocTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboHang = new javax.swing.JComboBox<>();
        btnHang = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cboKieuDang = new javax.swing.JComboBox<>();
        btnKieuDang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboDanhMuc = new javax.swing.JComboBox<>();
        btnDanhMuc = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboKichCo = new javax.swing.JComboBox<>();
        btnKichCo = new javax.swing.JButton();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        lblGia = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblEmty = new javax.swing.JLabel();
        btnMoi = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblMaTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMaTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaTen.setText("Mã - Tên Giày");
        lblMaTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaTenMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblDanhSach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Hang", "KieuDang", "DanhMuc", "Mau", "Size", "Gia", "SoLuong", "TrangThai"
            }
        ));
        tblDanhSach.setRowHeight(30);
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        lblPage.setText("Trang");

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        jLabel2.setText("Tìm kiếm theo giá");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel12.setText("Hãng");

        cboLocHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocHangItemStateChanged(evt);
            }
        });

        jLabel14.setText("Kiểu dáng");

        cboLocKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocKieuDang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocKieuDangItemStateChanged(evt);
            }
        });

        jLabel15.setText("Danh mục");

        cboLocDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocDanhMucItemStateChanged(evt);
            }
        });

        jLabel16.setText("Màu sắc");

        cboLocMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocMauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocMauSacItemStateChanged(evt);
            }
        });

        cboLocKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocKichCo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocKichCoItemStateChanged(evt);
            }
        });

        jLabel17.setText("Kích cỡ");

        jLabel18.setText("Trạng thái");

        cboLocTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLocTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocTrangThaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(cboLocHang, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(cboLocKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(cboLocMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(cboLocKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(526, 526, 526)
                        .addComponent(btnPrev)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNext, btnPrev});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLocHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPage)
                    .addComponent(btnNext)
                    .addComponent(btnPrev))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Hãng");

        cboHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnHang.setText("+");
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        jLabel4.setText("Kiểu dáng");

        cboKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnKieuDang.setText("+");
        btnKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKieuDangActionPerformed(evt);
            }
        });

        jLabel5.setText("Danh mục");

        cboDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDanhMuc.setText("+");
        btnDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhMucActionPerformed(evt);
            }
        });

        jLabel6.setText("Màu sắc");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnMauSac.setText("+");
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        jLabel7.setText("Kích cỡ");

        cboKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnKichCo.setText("+");
        btnKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichCoActionPerformed(evt);
            }
        });

        lblSoLuong.setText("Số lượng(*)");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        jLabel10.setText("Trạng thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Chọn ảnh");
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        lblGia.setText("Giá(*)");

        txtGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaKeyReleased(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Click vào trên để chọn mã khác");

        jButton1.setText("QR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblEmty.setForeground(new java.awt.Color(255, 51, 51));
        lblEmty.setText("(*) Bắt buộc nhập liệu !");

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblEmty, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(lblGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDanhMuc))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnKichCo))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnMauSac))
                                .addComponent(txtGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnKieuDang))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnHang))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(25, 25, 25)
                                        .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSoLuong))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jScrollPane2))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMoi)))
                        .addGap(30, 30, 30)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnHang)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKieuDang)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDanhMuc)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMauSac)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKichCo)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSua)
                                .addComponent(btnThem)
                                .addComponent(jButton1)
                                .addComponent(btnMoi))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGia))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSoLuong))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))
                            .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(11, 11, 11)
                .addComponent(lblEmty)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        hangFrame.setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKieuDangActionPerformed
        kieuDangFrame.setVisible(true);
    }//GEN-LAST:event_btnKieuDangActionPerformed

    private void btnDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhMucActionPerformed
        danhMucFrame.setVisible(true);
    }//GEN-LAST:event_btnDanhMucActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        mauSacFrame.setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichCoActionPerformed
        kichCoFrame.setVisible(true);
    }//GEN-LAST:event_btnKichCoActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        GiayChiTiet giayCT = getDataForm();
        if (giayCT == null) {
            return;
        }
        if (giayCT != null) {
            int check = JOptionPane.showConfirmDialog(this, "Are you sure?", "Meo Meo Confirm", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                list = giayChiTietService.getAllByGiay(giayData.getId());
                giayChiTietService.add(giayCT);
                list.add(0, giayCT); //Lỗi Lấy Id_Qr
                loadData(list, currentPage);
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            }
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int selected = tblDanhSach.getSelectedRow();
        int index = (currentPage - 1) * rowCountPage + selected;
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng trên bảng");
            return;
        } else {
            if (index < list.size()) {
                GiayChiTiet gct = list.get(index);
                fillTableToForm(gct);
            }
        }
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int selected = tblDanhSach.getSelectedRow();
        String thongBao = null;
        if (selected == -1) {
            thongBao = "Bạn cần chọn dữ liệu để sửa";
        } else {
            GiayChiTiet giayChiTiet = getDataForm();
            int index = (currentPage - 1) * rowCountPage + selected;
            if (index < list.size()) {
                giayChiTiet.setId(list.get(index).getId());

                thongBao = giayChiTietService.update(giayChiTiet);
                list = giayChiTietService.getAllByGiay(giayData.getId());
                loadData(list, currentPage);
                JOptionPane.showMessageDialog(this, thongBao);
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

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

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn của file được chọn
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File(imagePath);
                // Kiểm tra kiểu tệp (ví dụ: .jpg, .png)
                String fileExtension = imageFile.getName().substring(imageFile.getName().lastIndexOf("."));
                if (fileExtension.equalsIgnoreCase(".jpg") || fileExtension.equalsIgnoreCase(".png") || fileExtension.equalsIgnoreCase(".gif")) {
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    System.out.println("imageIcon: " + imageIcon);
                    showAnh(imageIcon.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn hình ảnh!", "Thông báo", JOptionPane.OK_OPTION);
                    System.out.println("Tệp không phải là hình ảnh.");
                }
            } else {
                lblAnh.setIcon(null);
            }
        }
    }//GEN-LAST:event_lblAnhMouseClicked

    private void lblMaTenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaTenMouseClicked
        this.dispose();

    }//GEN-LAST:event_lblMaTenMouseClicked

    private void txtGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaKeyReleased
        if (!txtGia.getText().isEmpty()) {
            txtGia.setBackground(getBackground());
            lblGia.setForeground(getForeground());

        }
    }//GEN-LAST:event_txtGiaKeyReleased

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        if (!txtSoLuong.getText().isEmpty()) {
            txtSoLuong.setBackground(getBackground());
            lblSoLuong.setForeground(getForeground());
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int selected = tblDanhSach.getSelectedRow();
        int index = (currentPage - 1) * rowCountPage + selected;
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn một sản phẩm để tạo qr.");
        } else {
            String Qr = list.get(index).getId();
            System.out.println(Qr);
            String path = "C:\\Users\\phuc\\Máy tính\\QR\\" + Qr + ".jpg";
            try {
                BitMatrix matrix = new MultiFormatWriter().encode(Qr, BarcodeFormat.QR_CODE, 200, 200);
                MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
            } catch (WriterException ex) {
                Logger.getLogger(GiayChiTietFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GiayChiTietFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        txtGia.setText("");
        txtSoLuong.setText("");
        txtMoTa.setText("");
        setDataCbo();
        lblAnh.setText("Chọn ảnh");
    }//GEN-LAST:event_btnMoiActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String search = txtTimKiem.getText().toLowerCase();
        List<GiayChiTiet> searchList = new ArrayList<>();
        for (GiayChiTiet giaychitiet : giayChiTietService.getAll()) {
            if (giaychitiet.getGia().toString().toLowerCase().contains(search)) {
                searchList.add(giaychitiet);
            }
        }
        list = searchList;
        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cboLocHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocHangItemStateChanged
        String searchCbo = (String) cboLocHang.getSelectedItem();

        if (searchCbo == null || searchCbo.isEmpty()) {
            list = giayChiTietService.getAllByGiay(giayData.getId());
        } else {
            List<GiayChiTiet> searchList = new ArrayList<>();

            for (GiayChiTiet giaychitiet : giayChiTietService.getAllByGiay(giayData.getId())) {
                Hang hang = giaychitiet.getHang();

                if ((hang != null && hang.getName() != null && hang.getName().equalsIgnoreCase(searchCbo))) {
                    searchList.add(giaychitiet);
                }
            }

            list = searchList;
        }

        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_cboLocHangItemStateChanged

    private void cboLocKieuDangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocKieuDangItemStateChanged
        String searchCbo = (String) cboLocKieuDang.getSelectedItem();

        if (searchCbo == null || searchCbo.isEmpty()) {
            list = giayChiTietService.getAllByGiay(giayData.getId());
        } else {
            List<GiayChiTiet> searchList = new ArrayList<>();

            for (GiayChiTiet giaychitiet : giayChiTietService.getAllByGiay(giayData.getId())) {
                KieuDang hang = giaychitiet.getKieuDang();

                if ((hang != null && hang.getName() != null && hang.getName().equalsIgnoreCase(searchCbo))) {
                    searchList.add(giaychitiet);
                }
            }

            list = searchList;
        }

        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_cboLocKieuDangItemStateChanged

    private void cboLocDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocDanhMucItemStateChanged
         String searchCbo = (String) cboLocDanhMuc.getSelectedItem();

        if (searchCbo == null || searchCbo.isEmpty()) {
            list = giayChiTietService.getAllByGiay(giayData.getId());
        } else {
            List<GiayChiTiet> searchList = new ArrayList<>();

            for (GiayChiTiet giaychitiet : giayChiTietService.getAllByGiay(giayData.getId())) {
                DanhMuc hang = giaychitiet.getDanhMuc();

                if ((hang != null && hang.getName() != null && hang.getName().equalsIgnoreCase(searchCbo))) {
                    searchList.add(giaychitiet);
                }
            }

            list = searchList;
        }

        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_cboLocDanhMucItemStateChanged

    private void cboLocMauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocMauSacItemStateChanged
        String searchCbo = (String) cboLocMauSac.getSelectedItem();

        if (searchCbo == null || searchCbo.isEmpty()) {
            list = giayChiTietService.getAllByGiay(giayData.getId());
        } else {
            List<GiayChiTiet> searchList = new ArrayList<>();

            for (GiayChiTiet giaychitiet : giayChiTietService.getAllByGiay(giayData.getId())) {
                MauSac hang = giaychitiet.getMauSac();

                if ((hang != null && hang.getName() != null && hang.getName().equalsIgnoreCase(searchCbo))) {
                    searchList.add(giaychitiet);
                }
            }

            list = searchList;
        }

        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_cboLocMauSacItemStateChanged

    private void cboLocKichCoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocKichCoItemStateChanged
        String searchCbo = (String) cboLocKichCo.getSelectedItem();

        if (searchCbo == null || searchCbo.isEmpty()) {
            list = giayChiTietService.getAllByGiay(giayData.getId());
        } else {
            List<GiayChiTiet> searchList = new ArrayList<>();

            for (GiayChiTiet giaychitiet : giayChiTietService.getAllByGiay(giayData.getId())) {
                KichCo hang = giaychitiet.getKichCo();

                if ((hang != null && hang.getSize() != null && String.valueOf(hang.getSize()).equalsIgnoreCase(searchCbo))) {
                    searchList.add(giaychitiet);
                }
            }

            list = searchList;
        }

        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_cboLocKichCoItemStateChanged

    private void cboLocTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocTrangThaiItemStateChanged
        String searchCbo = (String) cboLocTrangThai.getSelectedItem();

        if (searchCbo == null || searchCbo.isEmpty()) {
            list = giayChiTietService.getAllByGiay(giayData.getId());
        } else {
            List<GiayChiTiet> searchList = new ArrayList<>();

            for (GiayChiTiet giaychitiet : giayChiTietService.getAllByGiay(giayData.getId())) {

                if ((giaychitiet.getTrangThai() != null && giaychitiet.getTrangThai() != null && String.valueOf(giaychitiet.getTrangThai()).equalsIgnoreCase(searchCbo))) {
                    searchList.add(giaychitiet);
                }
            }

            list = searchList;
        }

        setTotalPage();
        loadData(list, currentPage);
    }//GEN-LAST:event_cboLocTrangThaiItemStateChanged

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
            java.util.logging.Logger.getLogger(GiayChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiayChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiayChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiayChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Giay giay = new Giay();
                new GiayChiTietFrame(giay).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhMuc;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnKichCo;
    private javax.swing.JButton btnKieuDang;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboKieuDang;
    private javax.swing.JComboBox<String> cboLocDanhMuc;
    private javax.swing.JComboBox<String> cboLocHang;
    private javax.swing.JComboBox<String> cboLocKichCo;
    private javax.swing.JComboBox<String> cboLocKieuDang;
    private javax.swing.JComboBox<String> cboLocMauSac;
    private javax.swing.JComboBox<String> cboLocTrangThai;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblEmty;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMaTen;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void setDataLocDanhMuc() {
        cboLocDanhMuc.removeAllItems();
        for (DanhMuc danhMuc : listDanhMuc) {
            cboLocDanhMuc.addItem(danhMuc.getName());
        }
    }

    private void setDataLocHang() {
        cboLocHang.removeAllItems();
        for (Hang hang : listHang) {
            cboLocHang.addItem(hang.getName());
        }
    }

    private void setDataLocKichCo() {
        cboLocKichCo.removeAllItems();
        for (KichCo kichCo : listKichCo) {
            cboLocKichCo.addItem(kichCo.getSize().toString());
        }
    }

    private void setDataLocKieuDang() {
        cboLocKieuDang.removeAllItems();
        for (KieuDang kieuDang : listKieuDang) {
            cboLocKieuDang.addItem(kieuDang.getName());
        }
    }

    private void setDataLocMauSac() {
        cboLocMauSac.removeAllItems();
        for (MauSac mauSac : listMauSac) {
            cboLocMauSac.addItem(mauSac.getName());
        }
    }

    private void setDataLocTrangThai() {
        cboLocTrangThai.removeAllItems();
        String[] trangThai = {"Hàng mới", "Hàng cũ", "Còn hàng", "Hết Hàng"};
        for (String string : trangThai) {
            cboLocTrangThai.addItem(string);
        }
    }

    private void setDataLocCbo() {
        setDataLocDanhMuc();
        setDataLocTrangThai();
        setDataLocKichCo();
        setDataLocKieuDang();
        setDataLocMauSac();
        setDataLocHang();
    }
}
