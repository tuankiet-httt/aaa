package Controller;

import Model.HoaDon;
import Repository.CTHDRepository;
import Repository.HoaDonRepository;
import Repository.NhanVienRepository;

import View.ChiTietHoaDonView;
import View.HoaDonView;

public class HoaDonController {

    private HoaDonView view;
    private HoaDonRepository repository;
    private NhanVienRepository nvRepo = new NhanVienRepository();
    private CTHDRepository chiTietRepo = new CTHDRepository();

    public HoaDonController(HoaDonView view) {
        this.view = view;
        this.repository = new HoaDonRepository();

        // Load mã nhân viên
        view.loadMaNV(nvRepo.getAllMaNV());

        // Gán sự kiện nút
        view.getBtnThem().addActionListener(e -> themHoaDon());
        view.getBtnSua().addActionListener(e -> suaHoaDon());
        view.getBtnXoa().addActionListener(e -> xoaHoaDon());
        view.getBtnChiTiet().addActionListener(e -> moCTHD());

        loadHoaDon();
    }

    // ============================
    // LOAD DANH SÁCH HÓA ĐƠN
    // ============================
    public void loadHoaDon() {
        view.loadHoaDon(repository.getAll());
    }

    // ============================
    // THÊM HÓA ĐƠN
    // ============================
    private void themHoaDon() {
        try {
            String maHD = view.getTxtMaHD().getText().trim();
            String ngay = view.getTxtNgayLap().getText().trim();
            String maKH = view.getTxtMaKH().getText().trim();
            String maNV = view.getMaNVSelected();

            HoaDon hd = new HoaDon(maHD, java.sql.Date.valueOf(ngay), maKH, maNV, 0);

            if (repository.insert(hd)) {
                view.showMessage("Thêm hóa đơn thành công!");
                loadHoaDon();
            }
        } catch (Exception e) {
            view.showMessage("Lỗi ngày! Định dạng phải là yyyy-mm-dd");
        }
    }

    // ============================
    // SỬA HÓA ĐƠN
    // ============================
    private void suaHoaDon() {
        try {
            String maHD = view.getTxtMaHD().getText().trim();
            String ngay = view.getTxtNgayLap().getText().trim();
            String maKH = view.getTxtMaKH().getText().trim();
            String maNV = view.getMaNVSelected();

            double tong = chiTietRepo.getTongTien(maHD);

            HoaDon hd = new HoaDon(maHD, java.sql.Date.valueOf(ngay), maKH, maNV, tong);

            if (repository.update(hd)) {
                view.showMessage("Sửa hóa đơn thành công!");
                loadHoaDon();
            }

        } catch (Exception e) {
            view.showMessage("Lỗi sửa hóa đơn!");
        }
    }

    // ============================
    // XÓA HÓA ĐƠN
    // ============================
    private void xoaHoaDon() {
        String maHD = view.getTxtMaHD().getText().trim();

        if (repository.delete(maHD)) {
            view.showMessage("Xóa thành công!");
            loadHoaDon();
        }
    }

    // ============================
    // MỞ CHI TIẾT HÓA ĐƠN
    // ============================
    private void moCTHD() {

        String maHD = view.getTxtMaHD().getText().trim();

        if (maHD.isEmpty()) {
            view.showMessage("Bạn phải chọn hóa đơn trước!");
            return;
        }

        // Mở form CTHD và truyền lại view cha
        ChiTietHoaDonView cthd = new ChiTietHoaDonView(view, maHD);
        cthd.setVisible(true);

        view.setVisible(false); // Ẩn form hóa đơn
    }
}
