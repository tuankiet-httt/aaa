package Controller;

import Model.DoanhThu;
import Model.TaiKhoan;
import Service.KhachHangService;
import View.KhachHangView;
import View.MainView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHangController {

    private KhachHangView view;
    private KhachHangService service;
    private TaiKhoan taiKhoan;

    public KhachHangController(KhachHangView view, TaiKhoan tk) {
    this.view = view;
    this.taiKhoan = tk;
    this.service = new KhachHangService();

    loadTable();
    initEvent();
}


    private void initEvent() {

        // ===== BUTTON EVENTS =====
        view.actionThem(e -> them());
        view.actionSua(e -> sua());
        view.actionXoa(e -> xoa());

        // ===== CLICK TABLE TO FILL FORM =====
        view.tableClick();

        // ===== THOÁT VỀ TRANG MAIN =====
        view.onThoat(e -> {
    new MainView(taiKhoan).setVisible(true);
    view.dispose();
});

    }

    private void loadTable() {
        String[] cols = {"Mã KH", "Tên KH", "SĐT", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<DoanhThu> list = service.getAll();
        for (DoanhThu kh : list) {
            model.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSdt(),
                kh.getDiaChi()
            });
        }

        view.setTable(model);
    }

    private void them() {
        DoanhThu kh = view.getForm();

        if (kh.getMaKH().isEmpty() || kh.getTenKH().isEmpty()) {
            view.thongBao("Không được để trống!");
            return;
        }

        if (service.them(kh)) {
            view.thongBao("Thêm thành công!");
            loadTable();
        } else {
            view.thongBao("Thêm thất bại!");
        }
    }

    private void sua() {
        DoanhThu kh = view.getForm();

        if (service.sua(kh)) {
            view.thongBao("Sửa thành công!");
            loadTable();
        } else {
            view.thongBao("Sửa thất bại!");
        }
    }

    private void xoa() {
        String maKH = view.getMaKH();

        if (maKH.isEmpty()) {
            view.thongBao("Chưa chọn khách hàng để xóa!");
            return;
        }

        if (service.xoa(maKH)) {
            view.thongBao("Xóa thành công!");
            loadTable();
        } else {
            view.thongBao("Xóa thất bại!");
        }
    }
}
