package Repository;

import Data.DataConnection;
import Model.DoanhThu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuRepository {

    public List<DoanhThu> getAll() {
    List<DoanhThu> list = new ArrayList<>();
    String sql = "SELECT maKH, tenKH, sdt, diaChi FROM KhachHang";


    try (
        Connection con = DataConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        while (rs.next()) {
            DoanhThu kh = new DoanhThu();
            kh.setMaKH(rs.getString("maKH"));
            kh.setTenKH(rs.getString("tenKH"));
            kh.setSdt(rs.getString("sdt"));
            kh.setDiaChi(rs.getString("diaChi"));
            
            list.add(kh);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    public boolean insert(DoanhThu kh) {
        String sql = " INSERT INTO KhachHang (maKH, tenKH, sdt, diaChi) VALUES (?, ?, ?, ?) ";
        try (
            Connection con = DataConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKH());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getDiaChi());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(DoanhThu kh) {
        String sql = " UPDATE KhachHang SET tenKH = ?," + " sdt = ?," + " diaChi = ? WHERE maKH = ? ";

        try (
            Connection con = DataConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, kh.getTenKH());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi()); 
            ps.setString(4, kh.getMaKH());   

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE maKH = ?";

        try (
            Connection con = DataConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, maKH);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
