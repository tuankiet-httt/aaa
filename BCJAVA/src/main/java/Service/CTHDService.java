package Service;

import Model.ChiTietHoaDon;
import Repository.CTHDRepository;
import Repository.SanPhamRepository;

import java.util.List;

public class CTHDService {

    private final CTHDRepository repo = new CTHDRepository();
    private final SanPhamRepository spRepo = new SanPhamRepository();

    // Lấy danh sách chi tiết hóa đơn
    public List<ChiTietHoaDon> getList(String maHD) {
        return repo.getAll(maHD);
    }

    public boolean add(ChiTietHoaDon c) {
    if (repo.exists(c.getMaHD(), c.getMaSP())) {
        return repo.update(c);  // Nếu đã tồn tại → UPDATE
    }
    return repo.insert(c); // Nếu chưa → INSERT
}


    // Sửa
    public boolean update(ChiTietHoaDon c) {
        return repo.update(c);
    }

    // Xóa
    public boolean delete(String maHD, String maSP) {
        return repo.delete(maHD, maSP);
    }

    // Lấy tất cả mã sản phẩm
    public List<String> getAllMaSP() {
        return spRepo.getAllMaSP();
    }

    // Lấy tên sản phẩm
    public String getTenSP(String maSP) {
        return spRepo.getTenSP(maSP);
    }

    // Lấy đơn giá sản phẩm
    public double getDonGia(String maSP) {
        return spRepo.getDonGia(maSP);
    }
}
