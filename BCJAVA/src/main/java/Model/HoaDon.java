package Model;

import java.sql.Date;

public class HoaDon {

    private String maHD;
    private Date ngayLap;
    private String maKH;
    private String maNV;
    private double tongTien;

    public HoaDon() {}

    public HoaDon(String maHD, Date ngayLap, String maKH, String maNV, double tongTien) {
    this.maHD = maHD;
    this.ngayLap = ngayLap;
    this.maKH = maKH;
    this.maNV = maNV;
    this.tongTien = tongTien;
}

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

}
