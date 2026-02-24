/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class SanPham {
    private String maSP;
    private String tenSP;
    private double gia;
    private int soLuongTon;
    private String hinh;

    public SanPham() {}

    public SanPham(String maSP, String tenSP, double gia, int soLuongTon, String hinh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuongTon = soLuongTon;
        this.hinh = hinh;
    }

    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }

    public String getHinh() { return hinh; }
    public void setHinh(String hinh) { this.hinh = hinh; }
}
