/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HÙNG
 */
public class GiayChiTiet {

    private String id;
    private Giay giay;
    private Hang hang;
    private KieuDang kieuDang;
    private DanhMuc danhMuc;
    private MauSac mauSac;
    private KichCo kichCo;
    private String hinhAnhUrl;
    private Double gia;
    private Integer soLuong;
    private String trangThai;
    private String moTa;

    public GiayChiTiet() {
    }

    public GiayChiTiet(Giay giay, Hang hang, KieuDang kieuDang, DanhMuc danhMuc, MauSac mauSac, KichCo kichCo, String hinhAnh, Double gia, Integer soLuong, String trangThai, String moTa) {
        this.giay = giay;
        this.hang = hang;
        this.kieuDang = kieuDang;
        this.danhMuc = danhMuc;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.hinhAnhUrl = hinhAnh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public GiayChiTiet(String id, Giay giay, Hang hang, KieuDang kieuDang, DanhMuc danhMuc, MauSac mauSac, KichCo kichCo, String hinhAnh, Double gia, Integer soLuong, String trangThai, String moTa) {
        this.id = id;
        this.giay = giay;
        this.hang = hang;
        this.kieuDang = kieuDang;
        this.danhMuc = danhMuc;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.hinhAnhUrl = hinhAnh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Giay getGiay() {
        return giay;
    }

    public void setGiay(Giay giay) {
        this.giay = giay;
    }

    public Hang getHang() {
        return hang;
    }

    public void setHang(Hang hang) {
        this.hang = hang;
    }

    public KieuDang getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(KieuDang kieuDang) {
        this.kieuDang = kieuDang;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public KichCo getKichCo() {
        return kichCo;
    }

    public void setKichCo(KichCo kichCo) {
        this.kichCo = kichCo;
    }

    public String getHinhAnhUrl() {
        return hinhAnhUrl;
    }

    public void setHinhAnhUrl(String hinhAnhUrl) {
        this.hinhAnhUrl = hinhAnhUrl;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "GiayChiTiet{" + "id=" + id + ", giay=" + giay + ", hang=" + hang + ", kieuDang=" + kieuDang + ", danhMuc=" + danhMuc + ", mauSac=" + mauSac + ", kichCo=" + kichCo + ", hinhAnhUrl=" + hinhAnhUrl + ", gia=" + gia + ", soLuong=" + soLuong + ", trangThai=" + trangThai + ", moTa=" + moTa + '}';
    }

}
