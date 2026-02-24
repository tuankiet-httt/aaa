package Repository;

import Data.DataConnection;
import Model.ChiTietHoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CTHDRepository {

    // LẤY DANH SÁCH CHI TIẾT HÓA ĐƠN
    public List<ChiTietHoaDon> getAll(String maHD) {
        List<ChiTietHoaDon> list = new ArrayList<>();

        String sql = """
            SELECT c.MaHD, c.MaSP, s.TenSP, c.SoLuong, c.DonGia
            FROM ChiTietHoaDon c 
            JOIN SanPham s ON c.MaSP = s.MaSP
            WHERE c.MaHD = ?
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaSP"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("DonGia")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // THÊM
    public boolean insert(ChiTietHoaDon c) {
        String sql = """
            INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia)
            VALUES (?,?,?,?)
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getMaHD());
            ps.setString(2, c.getMaSP());
            ps.setInt(3, c.getSoLuong());
            ps.setDouble(4, c.getDonGia());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // UPDATE
    public boolean update(ChiTietHoaDon c) {
        String sql = """
            UPDATE ChiTietHoaDon 
            SET SoLuong = ?, DonGia = ?
            WHERE MaHD = ? AND MaSP = ?
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getSoLuong());
            ps.setDouble(2, c.getDonGia());
            ps.setString(3, c.getMaHD());
            ps.setString(4, c.getMaSP());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE
    public boolean delete(String maHD, String maSP) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHD = ? AND MaSP = ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHD);
            ps.setString(2, maSP);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public double getTongTien(String maHD) {
    String sql = """
        SELECT SUM(SoLuong * DonGia) AS TongTien
        FROM ChiTietHoaDon
        WHERE MaHD = ?
    """;

    try (Connection conn = DataConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("TongTien");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}
public boolean exists(String maHD, String maSP) {
    String sql = "SELECT 1 FROM ChiTietHoaDon WHERE MaHD=? AND MaSP=?";
    try (Connection conn = DataConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maHD);
        ps.setString(2, maSP);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

}
