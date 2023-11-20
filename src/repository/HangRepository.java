package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Hang;

public class HangRepository {

    public List<Hang> getAll() {
        List<Hang> list = new ArrayList<>();
        String sql = """
                      SELECT id, name from HANG
                      """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hang hang = new Hang(rs.getString(1), rs.getString(2));
                list.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean delete(String id) {
        String sql = """
                     DELETE from Hang
                        WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean update(Hang hang) {
        String sql = """
                     UPDATE Hang SET name = ?
                      WHERE id = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, hang.getName());
            ps.setObject(2, hang.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean add(Hang hang) {
        String sql = """
                     INSERT INTO Hang
                     (id, name)
                     VALUES (newID(),?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, hang.getName());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public Boolean search(String name) {
        String sql = "SELECT * FROM Hang WHERE name=?";
        int check =0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 new Hang(rs.getString("id"), rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check>0;
    }

    public static void main(String[] args) {
        HangRepository hangRepository = new HangRepository();
        hangRepository.add(new Hang("Name 1"));
        List<Hang> l = hangRepository.getAll();
        for (Hang danhMuc : l) {
            System.out.println(danhMuc);
        }
    }
}
