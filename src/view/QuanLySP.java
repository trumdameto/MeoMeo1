package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DanhMuc;
import model.Hang;
import model.KichCo;
import model.KieuDang;
import model.MauSac;
import services.DanhMucService;
import services.HangService;
import services.KichCoService;
import services.KieuDangService;
import services.MauSacService;
import services.impl.DanhMucServiceImpl;
import services.impl.HangServiceImpl;
import services.impl.KichCoServiceImpl;
import services.impl.KieuDangServiceImpl;
import services.impl.MauSacServiceImpl;

public class QuanLySP extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<DanhMuc> listDanhMuc = new ArrayList<>();
    private List<KichCo> listKichCo = new ArrayList<>();
    private List<KieuDang> listKieuDang = new ArrayList<>();
    private List<MauSac> listMauSac = new ArrayList<>();
    private List<Hang> listHang = new ArrayList<>();

    private final DanhMucService danhMucService = new DanhMucServiceImpl();
    private final KichCoService kichCoService = new KichCoServiceImpl();
    private final KieuDangService kieuDangService = new KieuDangServiceImpl();
    private final MauSacService mauSacService = new MauSacServiceImpl();
    private final HangService hangService = new HangServiceImpl();

    private Integer currentPage = 1;
    private final Integer rowCountPage = 5;
    private Integer totalPage = 1;

    public QuanLySP() {
        initComponents();
        setSize(825, 530);
        setResizable(false);
        setLocationRelativeTo(null);

        setList();
        dtm = (DefaultTableModel) tblDanhSach.getModel();

        loadDataMauSac(listMauSac, currentPage);
        initLoadTable();
    }

    private void setList() {
        listDanhMuc = danhMucService.getAll();
        listKichCo = kichCoService.getAll();
        listKieuDang = kieuDangService.getAll();
        listMauSac = mauSacService.getAll();
        listHang = hangService.getAll();
    }

    private void loadData() {
        if (rdoDanhMuc.isSelected()) {
            loadDataDanhMuc(listDanhMuc, currentPage);
        } else if (rdoKichThuoc.isSelected()) {
            loadDataKichThuoc(listKichCo, currentPage);
        } else if (rdoKieuDang.isSelected()) {
            loadDataKieuDang(listKieuDang, currentPage);
        } else if (rdoHang.isSelected()) {
            loadDataHang(listHang, currentPage);
        } else {
            loadDataMauSac(listMauSac, currentPage);
        }
    }

    private void loadDataDanhMuc(List<DanhMuc> list, int page) {
        dtm.setRowCount(0);
        int totalItem = list.size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = (totalItem / rowCountPage);
        }
        int limit = page * rowCountPage;
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            DanhMuc get = list.get(start);
            dtm.addRow(new Object[]{
                get.getName()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private void loadDataHang(List<Hang> list, int page) {
        dtm.setRowCount(0);
        int totalItem = list.size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = (totalItem / rowCountPage);
        }
        int limit = page * rowCountPage;
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            Hang get = list.get(start);
            dtm.addRow(new Object[]{
                get.getName()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private void loadDataMauSac(List<MauSac> list, int page) {
        dtm.setRowCount(0);
        int totalItem = list.size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = (totalItem / rowCountPage);
        }
        int limit = page * rowCountPage;
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            MauSac get = list.get(start);
            dtm.addRow(new Object[]{
                get.getName()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private void loadDataKichThuoc(List<KichCo> list, int page) {
        list = kichCoService.getAll();
        dtm.setRowCount(0);
        int totalItem = list.size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = (totalItem / rowCountPage);
        }
        int limit = page * rowCountPage;
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            KichCo get = list.get(start);
            dtm.addRow(new Object[]{
                get.getSize()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private void loadDataKieuDang(List<KieuDang> list, int page) {
        dtm.setRowCount(0);
        int totalItem = list.size();
        if (totalItem % rowCountPage != 0) {
            totalPage = (totalItem / rowCountPage) + 1;
        } else {
            totalPage = (totalItem / rowCountPage);
        }
        int limit = page * rowCountPage;
        lblPage.setText("Trang " + currentPage + "/" + totalPage);
        for (int start = (page - 1) * rowCountPage; start < totalItem; start++) {
            KieuDang get = list.get(start);
            dtm.addRow(new Object[]{
                get.getName()
            });
            if (start + 1 == limit) {
                return;
            }
        }
    }

    private void initLoadTable() {
        rdoKichThuoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtTen.setText("");
                    loadDataKichThuoc(listKichCo, currentPage);
                }
            }
        });

        rdoKieuDang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtTen.setText("");
                    loadDataKieuDang(listKieuDang, currentPage);
                }
            }
        });

        rdoMauSac.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtTen.setText("");
                    loadDataMauSac(listMauSac, currentPage);
                }
            }
        });

        rdoDanhMuc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtTen.setText("");
                    loadDataDanhMuc(listDanhMuc, currentPage);
                }
            }
        });

        rdoHang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtTen.setText("");
                    loadDataHang(listHang, currentPage);
                }
            }
        });
    }

    private Object getForm() {
        String name = txtTen.getText();
        if (name.isBlank()) {
            return null;
        }
        if (rdoDanhMuc.isSelected()) {
            return new DanhMuc(name);
        } else if (rdoKichThuoc.isSelected()) {
            return new KichCo(Integer.parseInt(name));
        } else if (rdoKieuDang.isSelected()) {
            return new KieuDang(name);
        } else if (rdoHang.isSelected()) {
            return new Hang(name);
        } else {
            return new MauSac(name);
        }
    }

    private Object detail() {
        int selectedRow = tblDanhSach.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        int index = selectedRow + (currentPage - 1) * rowCountPage;
        String thongBao;
        if (rdoDanhMuc.isSelected()) {
            txtTen.setText(listDanhMuc.get(index).getName());
            return listDanhMuc.get(index);
        } else if (rdoKichThuoc.isSelected()) {
            txtTen.setText(listKichCo.get(index).getSize().toString());
            return listKichCo.get(index);
        } else if (rdoKieuDang.isSelected()) {
            txtTen.setText(listKieuDang.get(index).getName());
            return listKieuDang.get(index);
        } else if (rdoHang.isSelected()) {
            txtTen.setText(listHang.get(index).getName());
            return listHang.get(index);
        } else {
            txtTen.setText(listMauSac.get(index).getName());
            return listMauSac.get(index);
        }
    }

    private void prevCurrentPage() {
        if (currentPage == 1) {
            currentPage = totalPage;
        } else {
            currentPage--;
        }
    }

    private void nextCurrentPage() {
        if (currentPage == totalPage) {
            currentPage = 1;
        } else {
            currentPage++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        panelDanhSach = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoKieuDang = new javax.swing.JRadioButton();
        rdoDanhMuc = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoHang = new javax.swing.JRadioButton();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Name:");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
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

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelDanhSach.setBackground(new java.awt.Color(255, 255, 255));
        panelDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách"));

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblPage.setText("Trang");

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME"
            }
        ));
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMauSac);
        rdoMauSac.setSelected(true);
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKieuDang);
        rdoKieuDang.setText("Kiểu dáng");
        rdoKieuDang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKieuDangActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDanhMuc);
        rdoDanhMuc.setText("Danh mục");
        rdoDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDanhMucActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKichThuoc);
        rdoKichThuoc.setText("Kich thước");
        rdoKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichThuocActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHang);
        rdoHang.setText("Hãng");

        javax.swing.GroupLayout panelDanhSachLayout = new javax.swing.GroupLayout(panelDanhSach);
        panelDanhSach.setLayout(panelDanhSachLayout);
        panelDanhSachLayout.setHorizontalGroup(
            panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachLayout.createSequentialGroup()
                .addGroup(panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDanhSachLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch))
                    .addGroup(panelDanhSachLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext))
                    .addGroup(panelDanhSachLayout.createSequentialGroup()
                        .addContainerGap(98, Short.MAX_VALUE)
                        .addGroup(panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDanhSachLayout.createSequentialGroup()
                                .addComponent(rdoMauSac)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoKieuDang)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDanhMuc)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKichThuoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoHang))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(99, 99, 99))
        );

        panelDanhSachLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNext, btnPrev});

        panelDanhSachLayout.setVerticalGroup(
            panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMauSac)
                    .addComponent(rdoKieuDang)
                    .addComponent(rdoDanhMuc)
                    .addComponent(rdoKichThuoc)
                    .addComponent(rdoHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(lblPage))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        // TODO add your handling code here:
        detail();
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        nextCurrentPage();
        loadData();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prevCurrentPage();
        loadData();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        String thongBao;
        if (detail() != null) {
            if (rdoDanhMuc.isSelected()) {
                DanhMuc danhMuc = (DanhMuc) detail();
                thongBao = danhMucService.delete(danhMuc.getId());
            } else if (rdoKichThuoc.isSelected()) {
                KichCo kichCo = (KichCo) detail();
                thongBao = kichCoService.delete(kichCo.getId());
            } else if (rdoKieuDang.isSelected()) {
                KieuDang kieuDang = (KieuDang) detail();
                thongBao = kieuDangService.delete(kieuDang.getId());
            } else if (rdoHang.isSelected()) {
                Hang hang = (Hang) detail();
                thongBao = hangService.delete(hang.getId());
            } else {
                MauSac mauSac = (MauSac) detail();
                thongBao = mauSacService.delete(mauSac.getId());
            }
        } else {
            thongBao = "Xóa thất bại";
        }
        setList();
        loadData();
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        String thongBao;
        String name = txtTen.getText();
        if (detail() != null) {
            if (rdoDanhMuc.isSelected()) {
                DanhMuc danhMuc = (DanhMuc) detail();
                danhMuc.setName(name);
                thongBao = danhMucService.update(danhMuc);
            } else if (rdoKichThuoc.isSelected()) {
                KichCo kichCo = (KichCo) detail();
                kichCo.setSize(Integer.parseInt(name));
                thongBao = kichCoService.update(kichCo);
            } else if (rdoKieuDang.isSelected()) {
                KieuDang kieuDang = (KieuDang) detail();
                kieuDang.setName(name);
                thongBao = kieuDangService.update(kieuDang);
            } else if (rdoHang.isSelected()) {
                Hang hang = (Hang) detail();
                hang.setName(name);
                thongBao = hangService.update(hang);
            } else {
                MauSac mauSac = (MauSac) detail();
                mauSac.setName(name);
                thongBao = mauSacService.update(mauSac);
            }
        } else {
            thongBao = "Sửa thất bại";
        }
        setList();
        loadData();
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String thongBao;
        if (getForm() != null) {
            if (rdoDanhMuc.isSelected()) {
                thongBao = danhMucService.add((DanhMuc) getForm());
                loadDataDanhMuc(listDanhMuc, currentPage);
            } else if (rdoKichThuoc.isSelected()) {
                thongBao = kichCoService.add((KichCo) getForm());
                loadDataDanhMuc(listDanhMuc, currentPage);
            } else if (rdoKieuDang.isSelected()) {
                thongBao = kieuDangService.add((KieuDang) getForm());
                loadDataDanhMuc(listDanhMuc, currentPage);
            } else if (rdoHang.isSelected()) {
                thongBao = hangService.add((Hang) getForm());
                loadDataHang(listHang, currentPage);
            } else {
                thongBao = mauSacService.add((MauSac) getForm());
                loadDataDanhMuc(listDanhMuc, currentPage);
            }
        } else {
            thongBao = "Thêm thất bại";
        }
        setList();
        loadData();
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKichThuocActionPerformed

    private void rdoDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDanhMucActionPerformed

    private void rdoKieuDangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKieuDangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKieuDangActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLySP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPage;
    private javax.swing.JPanel panelDanhSach;
    private javax.swing.JRadioButton rdoDanhMuc;
    private javax.swing.JRadioButton rdoHang;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoKieuDang;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
