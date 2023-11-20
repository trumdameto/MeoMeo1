package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KieuDang;

public class KieuDangRepository {

    public List<KieuDang> getAll() {
        List<KieuDang> list = new ArrayList<>();
        String sql = """
                      SELECT id, name from KieuDang
                      """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KieuDang kieuDang = new KieuDang(rs.getString(1), rs.getString(2));
                list.add(kieuDang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean delete(String id) {
        String sql = """
                     DELETE from KieuDang
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

    public Boolean update(KieuDang kieuDang) {
        String sql = """
                     UPDATE DANHMUC SET name = ?
                      WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, kieuDang.getName());
            ps.setObject(2, kieuDang.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean add(KieuDang kieuDang) {
        String sql = """
                     INSERT INTO KieuDang
                     (id, name)
                     VALUES (newID(),?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, kieuDang.getName());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        KieuDangRepository kieuDangRepository = new KieuDangRepository();
        kieuDangRepository.add(new KieuDang("KieuDang 1"));
        List<KieuDang> l = kieuDangRepository.getAll();
        for (KieuDang kieuDang : l) {
            System.out.println(kieuDang);
        }
    }
}
