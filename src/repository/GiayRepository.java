package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;
import model.Giay;

public class GiayRepository {

    public List<Giay> getAll() {
        List<Giay> list = new ArrayList<>();
        String sql = """
                      SELECT id, MA_GIAY, name from GIAY
                      """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Giay giay = new Giay(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(giay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean delete(String id) {
        String sql = """
                     DELETE from GIAY
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

    public Boolean update(Giay giay) {
        String sql = """
                     UPDATE GIAY SET MA_GIAY = ?, name = ?
                      WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, giay.getMa());
            ps.setObject(2, giay.getName());
            ps.setObject(3, giay.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean add(Giay giay) {
        String sql = """
                     INSERT INTO GIAY
                     (id, MA_GIAY, name)
                     VALUES (newID(),?,?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, giay.getMa());
            ps.setObject(2, giay.getName());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        GiayRepository danhMucRepository = new GiayRepository();
        danhMucRepository.add(new Giay("GIAY000001","Name 1"));
        List<Giay> l = danhMucRepository.getAll();
        for (Giay danhMuc : l) {
            System.out.println(danhMuc);
        }
    }
}
