package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KichCo;

public class KichCoRepository {

    public List<KichCo> getAll() {
        List<KichCo> list = new ArrayList<>();
        String sql = """
                      SELECT id, size from KichCo
                      """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo kichCo = new KichCo(rs.getString(1), rs.getInt(2));
                list.add(kichCo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean delete(String id) {
        String sql = """
                     DELETE from KichCo
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

    public Boolean update(KichCo kichCo) {
        String sql = """
                     UPDATE DANHMUC SET size = ?
                      WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, kichCo.getSize());
            ps.setObject(2, kichCo.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean add(KichCo kichCo) {
        String sql = """
                     INSERT INTO KichCo
                     (id, size)
                     VALUES (newID(),?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, kichCo.getSize());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
        KichCoRepository kichCoRepository = new KichCoRepository();
        kichCoRepository.add(new KichCo(1));
        List<KichCo> l = kichCoRepository.getAll();
        for (KichCo danhMuc : l) {
            System.out.println(danhMuc);
        }
    }
}
