package model;

import java.util.Date;

public class NhanVien {

    private String id, ma, ten;
    private String gioitinh;//=0
    private String sdt, diachi;
    private Date ngaysinh;
    private String matkhau, vaitro, trangthai;

    public NhanVien() {
    }

    public NhanVien(String ten, String matkhau, String vaitro) {
        this.ten = ten;
        this.matkhau = matkhau;
        this.vaitro =vaitro ;
    }

    public NhanVien(String ten, String sdt, String diachi, Date ngaysinh, String matkhau, String vaitro, String trangthai) {
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
    }

    
    public NhanVien(String id, String ma, String ten, String gioitinh,String sdt, String diachi, Date ngaysinh, String matkhau, String vaitro, String trangthai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
    }

    public NhanVien(String ma, String ten, String gioitinh,String sdt, String diachi, Date ngaysinh, String matkhau, String vaitro, String trangthai) {
        this.ma = ma;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "NhanVien{" + ", ma=" + ma + ", ten=" + ten + ", gioitinh=" + gioitinh + ", sdt=" + sdt + ", diachi=" + diachi + ", ngaysinh=" + ngaysinh + ", matkhau=" + matkhau + ", vaitro=" + vaitro + ", trangthai=" + trangthai + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
    
}
