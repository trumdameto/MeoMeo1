
package Entity;

import java.math.BigDecimal;


public class HoaDonChiTiet {
    private String id;
    private GiayChiTiet giayChiTiet;
    private String iDhoaDon;
    private BigDecimal gia;
    private int soLuong;
    private String trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String id, GiayChiTiet giayChiTiet, String iDhoaDon, BigDecimal gia, int soLuong, String trangThai) {
        this.id = id;
        this.giayChiTiet = giayChiTiet;
        this.iDhoaDon = iDhoaDon;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public HoaDonChiTiet(String id, GiayChiTiet giayChiTiet, BigDecimal gia, int soLuong, String trangThai) {
        this.id = id;
        this.giayChiTiet = giayChiTiet;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }
    
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GiayChiTiet getGiayChiTiet() {
        return giayChiTiet;
    }

    public void setGiayChiTiet(GiayChiTiet giayChiTiet) {
        this.giayChiTiet = giayChiTiet;
    }

    public String getiDhoaDon() {
        return iDhoaDon;
    }

    public void setiDhoaDon(String iDhoaDon) {
        this.iDhoaDon = iDhoaDon;
    }

  
    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public BigDecimal tongTien(){
        return gia.multiply(new BigDecimal(soLuong));
    }
    
    
}
