/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Data.DataConnection;
import Model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienRepository {

    public List<NhanVien> getAllNV() {
    List<NhanVien> list = new ArrayList<>();
    String sql = "SELECT maNV, tenNV, chucVu, sdt FROM NhanVien";

    try (Connection con = DataConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new NhanVien(
                rs.getString("maNV"),
                rs.getString("tenNV"),
                rs.getString("chucVu"),
                rs.getString("sdt")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public List<String> getAllMaNV() {
    List<String> list = new ArrayList<>();
    String sql = "SELECT maNV FROM NhanVien";

    try (Connection con = DataConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            list.add(rs.getString("maNV"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien VALUES (?,?,?,?)";
        try {
            Connection con = DataConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getChucVu());
            ps.setString(4, nv.getSdt());

            int kq = ps.executeUpdate();
            ps.close();
            con.close();

            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET tenNV=?, chucVu=?, sdt=? WHERE maNV=?";
        try {
            Connection con = DataConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getChucVu());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getMaNV());

            int kq = ps.executeUpdate();
            ps.close();
            con.close();

            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV=?";
        try {
            Connection con = DataConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, maNV);

            int kq = ps.executeUpdate();
            ps.close();
            con.close();

            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


