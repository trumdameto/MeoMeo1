package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import model.Giay;
import model.GiayChiTiet;
import model.Hang;
import model.KichCo;
import model.KieuDang;
import model.MauSac;

public class GiayChiTietRepository {

    public List<GiayChiTiet> getAllByGiay(String idGiay) {
        List<GiayChiTiet> list = new ArrayList<>();
        String sql = """
                      SELECT A.ID, B.*, C.*, D.*, E.*, F.*, G.*, A.HINHANH, A.GIA, A.SOLUONG, A.TRANGTHAI, A.MOTA 
                      FROM GIAYCHITIET A 
                      JOIN GIAY B ON A.ID_GIAY = B.ID
                      JOIN HANG C ON A.ID_HANG = C.ID 
                      JOIN KIEUDANG D ON A.ID_KIEUDANG = D.ID
                      JOIN DANHMUC E ON A.ID_DANHMUC = E.ID 
                      JOIN MAUSAC F ON A.ID_MAUSAC = F.ID
                      JOIN KICHCO G ON A.ID_KICHCO = G.ID 
                      WHERE A.ID_GIAY = ?
                      """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, idGiay);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Giay giay = new Giay(rs.getString(2), rs.getString(3), rs.getString(4));
                Hang hang = new Hang(rs.getString(5), rs.getString(6));
                KieuDang kieuDang = new KieuDang(rs.getString(7), rs.getString(8));
                DanhMuc danhMuc = new DanhMuc(rs.getString(9), rs.getString(10));
                MauSac mauSac = new MauSac(rs.getString(11), rs.getString(12));
                KichCo kichCo = new KichCo(rs.getString(13), rs.getInt(14));
                GiayChiTiet giayChiTiet = new GiayChiTiet(rs.getString(1),
                        giay, hang, kieuDang, danhMuc, mauSac, kichCo,
                        rs.getString(15), rs.getDouble(16), rs.getInt(17),
                        rs.getString(18), rs.getString(19));
                list.add(giayChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<GiayChiTiet> getAll() {
        List<GiayChiTiet> list = new ArrayList<>();
        String sql = """
                      SELECT A.ID, B.*, C.*, D.*, E.*, F.*, G.*, A.HINHANH, A.GIA, A.SOLUONG, A.TRANGTHAI, A.MOTA 
                      FROM GIAYCHITIET A 
                      JOIN GIAY B ON A.ID_GIAY = B.ID
                      JOIN HANG C ON A.ID_HANG = C.ID 
                      JOIN KIEUDANG D ON A.ID_KIEUDANG = D.ID
                      JOIN DANHMUC E ON A.ID_DANHMUC = E.ID 
                      JOIN MAUSAC F ON A.ID_MAUSAC = F.ID
                      JOIN KICHCO G ON A.ID_KICHCO = G.ID 
                      """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Giay giay = new Giay(rs.getString(2), rs.getString(3), rs.getString(4));
                Hang hang = new Hang(rs.getString(5), rs.getString(6));
                KieuDang kieuDang = new KieuDang(rs.getString(7), rs.getString(8));
                DanhMuc danhMuc = new DanhMuc(rs.getString(9), rs.getString(10));
                MauSac mauSac = new MauSac(rs.getString(11), rs.getString(12));
                KichCo kichCo = new KichCo(rs.getString(13), rs.getInt(14));
                GiayChiTiet giayChiTiet = new GiayChiTiet(rs.getString(1),
                        giay, hang, kieuDang, danhMuc, mauSac, kichCo,
                        rs.getString(15), rs.getDouble(16), rs.getInt(17),
                        rs.getString(18), rs.getString(19));
                list.add(giayChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean delete(String id) {
        String sql = """
                     DELETE FROM [dbo].[GIAYCHITIET]
                           WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean update(GiayChiTiet giayChiTiet) {
        String sql = """
                     UPDATE [dbo].[GIAYCHITIET]
                        SET [ID_GIAY] = ?
                           ,[ID_HANG] = ?
                           ,[ID_KIEUDANG] = ?
                           ,[ID_DANHMUC] = ?
                           ,[ID_MAUSAC] = ?
                           ,[ID_KICHCO] = ?
                           ,[HINHANH] = ?
                           ,[GIA] = ?
                           ,[SOLUONG] = ?
                           ,[TRANGTHAI] = ?
                           ,[MOTA] = ?
                      WHERE [ID] = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, giayChiTiet.getGiay().getId());
            ps.setObject(2, giayChiTiet.getHang().getId());
            ps.setObject(3, giayChiTiet.getKieuDang().getId());
            ps.setObject(4, giayChiTiet.getDanhMuc().getId());
            ps.setObject(5, giayChiTiet.getMauSac().getId());
            ps.setObject(6, giayChiTiet.getKichCo().getId());
            ps.setObject(7, giayChiTiet.getHinhAnhUrl());
            ps.setObject(8, giayChiTiet.getGia());
            ps.setObject(9, giayChiTiet.getSoLuong());
            ps.setObject(10, giayChiTiet.getTrangThai());
            ps.setObject(11, giayChiTiet.getMoTa());
            ps.setObject(12, giayChiTiet.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean add(GiayChiTiet giayChiTiet) {
        String sql = """
                     INSERT INTO [dbo].[GIAYCHITIET]
                                ([ID]
                                ,[ID_GIAY]
                                ,[ID_HANG]
                                ,[ID_KIEUDANG]
                                ,[ID_DANHMUC]
                                ,[ID_MAUSAC]
                                ,[ID_KICHCO]
                                ,[HINHANH]
                                ,[GIA]
                                ,[SOLUONG]
                                ,[TRANGTHAI]
                                ,[MOTA])
                          VALUES (newID(),?,?,?,?,?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, giayChiTiet.getGiay().getId());
            ps.setObject(2, giayChiTiet.getHang().getId());
            ps.setObject(3, giayChiTiet.getKieuDang().getId());
            ps.setObject(4, giayChiTiet.getDanhMuc().getId());
            ps.setObject(5, giayChiTiet.getMauSac().getId());
            ps.setObject(6, giayChiTiet.getKichCo().getId());
            ps.setObject(7, giayChiTiet.getHinhAnhUrl());
            ps.setObject(8, giayChiTiet.getGia());
            ps.setObject(9, giayChiTiet.getSoLuong());
            ps.setObject(10, giayChiTiet.getTrangThai());
            ps.setObject(11, giayChiTiet.getMoTa());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        GiayChiTietRepository danhMucRepository = new GiayChiTietRepository();
        List<GiayChiTiet> l = danhMucRepository.getAll();
        for (GiayChiTiet danhMuc : l) {
            System.out.println(danhMuc);
        }
    }
}
