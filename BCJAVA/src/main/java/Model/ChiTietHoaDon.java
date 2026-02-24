package Model;

public class ChiTietHoaDon {
    private String maHD;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietHoaDon() {}

    // Constructor khi thêm / sửa
    public ChiTietHoaDon(String maHD, String maSP, String tenSP, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    // Constructor khi select từ database
    public ChiTietHoaDon(String maHD, String maSP, String tenSP, int soLuong, double donGia, double thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    // Getter Setter
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { 
        this.soLuong = soLuong;
        this.thanhTien = soLuong * this.donGia;
    }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { 
        this.donGia = donGia;
        this.thanhTien = this.soLuong * donGia;
    }

    public double getThanhTien() { return thanhTien; }
}
