package repository;

import model.Giay;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class GiayDAO {

    public ArrayList<Giay> getGiay() {
        String sql = "SELECT * FROM Giay";
        ArrayList<Giay> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                list.add(new Giay(rs.getString("id"),rs.getString("ma_giay"),  rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCHelper.closeConnection();
        }
        return null;
    }

    public Integer InsertGiay(Giay giay) {
        String sql = "insert into Giay (id,ma_giay,name) values (newid(),?,?)";
        int row = JDBCHelper.excuteUpdate(sql, giay.getMa(), giay.getName());
        return row;
    }

    public Integer UpdateGiay(Giay giay) {
        String sql = "update Giay set ma_giay=?,name=? where id = ?";
        int row = JDBCHelper.excuteUpdate(sql,giay.getMa(),giay.getName(), giay.getId());
        return row;
    }

//    public Integer DeleteGiay(String id) {
//        String sql = "DELETE FROM Giay WHERE ma_giay = ?";
//        int row = JDBCHelper.excuteUpdate(sql, id);
//        return row;
//    }

//    public Giay SearchHang(String name) {
//        String sql = "SELECT * FROM Giay WHERE ma_giay=?";
//        ResultSet rs = JDBCHelper.executeQuery(sql, name);
//
//        if (rs != null) {
//            try {
//                if (rs.next()) {
//                    return new Giay(rs.getString("ma_giay"), rs.getString("name"));
//                }
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
  public Giay selectById(String id) {
        String sql = "SELECT * FROM Giay WHERE id=?";
        ArrayList<Giay> list = getAll(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    public ArrayList<Giay> selectByKeyword(String keyword) { //tìm kiếm giay theo mã giày
        String sql = "SELECT * FROM Giay WHERE ma_giay LIKE ?";
        return this.getAll(sql, "%" + keyword + "%");
    }

    public ArrayList<Giay> getAll(String sql, Object... args) { //truyền lệnh sql để tìm
        ArrayList<Giay> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Giay giay = new Giay();
                    giay.setId(rs.getString("ID"));
                    giay.setMa(rs.getString("MA_GIAY"));
                    giay.setName(rs.getString("NAME"));
                    list.add(giay);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

}
