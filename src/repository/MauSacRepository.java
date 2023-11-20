package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import model.MauSac;

public class MauSacRepository {

    public List<MauSac> getAll() {
        List<MauSac> list = new ArrayList<>();
        String sql = """
                      SELECT id, name from MauSac
                      """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac mauSac = new MauSac(rs.getString(1), rs.getString(2));
                list.add(mauSac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean delete(String id) {
        String sql = """
                     DELETE from MauSac
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

    public Boolean update(MauSac mauSac) {
        String sql = """
                     UPDATE MauSac SET name = ?
                      WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, mauSac.getName());
            ps.setObject(2, mauSac.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean add(MauSac mauSac) {
        String sql = """
                     INSERT INTO MauSac
                     (id, name)
                     VALUES (newID(),?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, mauSac.getName());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        MauSacRepository danhMucRepository = new MauSacRepository();
        danhMucRepository.add(new MauSac("MauSac 1"));
        List<MauSac> l = danhMucRepository.getAll();
        for (MauSac danhMuc : l) {
            System.out.println(danhMuc);
        }
    }
}
