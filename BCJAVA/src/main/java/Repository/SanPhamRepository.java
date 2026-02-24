package Repository;

import Data.DataConnection;
import Model.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {

    // ================================
    // LẤY TẤT CẢ SẢN PHẨM
    // ================================
    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();

        String sql = "SELECT maSP, tenSP, gia, soLuongTon, hinh FROM SanPham";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SanPham(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("gia"),
                        rs.getInt("soLuongTon"),
                        rs.getString("hinh")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================================
    // LẤY TẤT CẢ MÃ SẢN PHẨM
    // ================================
    public List<String> getAllMaSP() {
        List<String> list = new ArrayList<>();

        String sql = "SELECT maSP FROM SanPham";

        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("maSP"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================================
    // LẤY TÊN SẢN PHẨM THEO MÃ
    // ================================
    public String getTenSP(String maSP) {
        String sql = "SELECT tenSP FROM SanPham WHERE maSP = ?";

        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("tenSP");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================================
    // LẤY GIÁ SẢN PHẨM THEO MÃ
    // ================================
    public double getDonGia(String maSP) {
        String sql = "SELECT gia FROM SanPham WHERE maSP = ?";

        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("gia");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    // ================================
    // CÁC HÀM INSERT / UPDATE / DELETE
    // ================================
    public boolean insert(SanPham sp) {
        String sql = "INSERT INTO SanPham (maSP, tenSP, gia, soLuongTon, hinh) VALUES (?,?,?,?,?)";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setDouble(3, sp.getGia());
            ps.setInt(4, sp.getSoLuongTon());
            ps.setString(5, sp.getHinh());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(SanPham sp) {
        String sql = "UPDATE SanPham SET tenSP=?, gia=?, soLuongTon=?, hinh=? WHERE maSP=?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSP());
            ps.setDouble(2, sp.getGia());
            ps.setInt(3, sp.getSoLuongTon());
            ps.setString(4, sp.getHinh());
            ps.setString(5, sp.getMaSP());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String maSP) {
        String sql = "DELETE FROM SanPham WHERE maSP=?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSP);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
    
    public double getGiaSanPham(String maSP) {
    double gia = 0;

    String sql = "SELECT gia FROM SanPham WHERE maSP = ?";

    try (Connection conn = DataConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maSP);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            gia = rs.getDouble("gia");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return gia;
}

}
