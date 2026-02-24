package Service;

import Model.HoaDon;
import Repository.HoaDonRepository;
import Repository.CTHDRepository;
import Repository.SanPhamRepository;
import java.util.List;

public class HoaDonService {

    private HoaDonRepository hoaDonRepo = new HoaDonRepository();
    private CTHDRepository chiTietHoaDonRepo = new CTHDRepository();
    private SanPhamRepository sanPhamRepo = new SanPhamRepository();


    // Lấy tất cả hóa đơn
    public List<HoaDon> getAll() {
        return hoaDonRepo.getAll();
    }

    // Thêm hóa đơn (tổng tiền mặc định 0)
    public boolean them(HoaDon hd) {
        hd.setTongTien(0);  // tổng tiền ban đầu = 0
        return hoaDonRepo.insert(hd);
    }

    // Sửa hóa đơn
    public boolean sua(HoaDon hd) {
        return hoaDonRepo.update(hd);
    }

    // Xóa hóa đơn
    public boolean xoa(String maHD) {
        return hoaDonRepo.delete(maHD);
    }

    public double tinhTongTien(String maHD) {
        return chiTietHoaDonRepo.getTongTien(maHD);
    }

    public void updateTongTien(String maHD) {
        double tong = chiTietHoaDonRepo.getTongTien(maHD);
        hoaDonRepo.updateTongTien(maHD, tong);
    }

    // Lấy giá sản phẩm
    public double getGiaSanPham(String maSP) {
    return sanPhamRepo.getDonGia(maSP);
}

}
