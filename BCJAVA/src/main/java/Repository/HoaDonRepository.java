package Repository;

import Model.HoaDon;
import Data.DataConnection;
import java.sql.*;
import java.util.*;

public class HoaDonRepository {

    // Trong HoaDonRepository.java
public List<HoaDon> getAll() {
    List<HoaDon> list = new ArrayList<>();
    // Viết rõ tên cột để đảm bảo không bị lấy thiếu
    String sql = "SELECT maHD, ngayLap, maKH, maNV, tongTien FROM HoaDon";
    try (Connection con = DataConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            list.add(new HoaDon(
                rs.getString("maHD"),
                rs.getDate("ngayLap"),
                rs.getString("maKH"),
                rs.getString("maNV"),
                rs.getDouble("tongTien")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
public List<Object[]> getHoaDonDayDu() {
    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT 
            hd.maHD,
            hd.ngayLap,
            hd.maKH,
            sp.tenSP,
            cthd.soLuong,
            (cthd.soLuong * cthd.donGia) AS tongTien
        FROM HoaDon hd
        LEFT JOIN ChiTietHoaDon cthd ON hd.maHD = cthd.maHD
        LEFT JOIN SanPham sp ON sp.maSP = cthd.maSP
    """;

    try (Connection con = DataConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[] {
                    rs.getString(1),  // maHD
                    rs.getDate(2),    // ngayLap
                    rs.getString(3),  // maKH
                    rs.getString(4),  // tenSP
                    rs.getInt(5),     // soLuong
                    rs.getDouble(6)   // tongTien
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    public boolean insert(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (maHD, ngayLap, maKH, maNV, tongTien) VALUES (?,?,?,?,?)";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getMaHD());
            ps.setDate(2, hd.getNgayLap());
            ps.setString(3, hd.getMaKH());
            ps.setString(4, hd.getMaNV());
            ps.setDouble(5, hd.getTongTien());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET ngayLap=?, maKH=?, maNV=?, tongTien=? WHERE maHD=?";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, hd.getNgayLap());
            ps.setString(2, hd.getMaKH());
            ps.setString(3, hd.getMaNV());
            ps.setDouble(4, hd.getTongTien());
            ps.setString(5, hd.getMaHD());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String maHD) {
        String sql = "DELETE FROM HoaDon WHERE maHD=?";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
    
   public void updateTongTien(String maHD, double tongTien) {
    try {
        Connection con = DataConnection.getConnection();
        String sql = "UPDATE HoaDon SET tongTien = ? WHERE maHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDouble(1, tongTien);
        ps.setString(2, maHD);

        ps.executeUpdate();

        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
