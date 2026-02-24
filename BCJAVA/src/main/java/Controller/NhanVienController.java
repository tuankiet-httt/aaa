package Controller;

import Model.NhanVien;
import Model.TaiKhoan;
import View.MainView;
import Service.NhanVienService;
import View.NhanVienView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NhanVienController {

    private NhanVienView view;
    private NhanVienService service;
    private TaiKhoan taiKhoan;

    public NhanVienController(NhanVienView view, TaiKhoan tk) {
        this.view = view;
        this.taiKhoan = tk;
        this.service = new NhanVienService();

        loadTable();

        // Sự kiện click bảng
        view.actionTableClick(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                hienThiLenForm();
            }
        });

        // CRUD
        view.actionThem(e -> them());
        view.actionSua(e -> sua());
        view.actionXoa(e -> xoa());

        // Thoát → quay lại MainView(taiKhoan)
        view.onThoat(e -> {
            new MainView(taiKhoan).setVisible(true);
            view.dispose();
        });
    }

    // ================== LOAD TABLE ==================
    private void loadTable() {
        String[] cols = {"Mã NV", "Tên NV", "Chức vụ", "SĐT"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<NhanVien> list = service.getAllNV();


        for (NhanVien nv : list) {
            model.addRow(new Object[]{
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getChucVu(),
                nv.getSdt()
            });
        }

        view.setTable(model);
    }

    // ================== HIỂN THỊ LÊN FORM ==================
    private void hienThiLenForm() {
        int row = view.getSelectedRow();
        if (row < 0) return;

        view.setForm(
            view.getValueAt(row, 0),
            view.getValueAt(row, 1),
            view.getValueAt(row, 2),
            view.getValueAt(row, 3)
        );
    }

    // ================== THÊM ==================
    private void them() {
        if (view.getMaNV().isEmpty() || view.getTenNV().isEmpty()) {
            view.thongBao("Không được để trống mã hoặc tên nhân viên!");
            return;
        }

        NhanVien nv = new NhanVien(
                view.getMaNV(),
                view.getTenNV(),
                view.getChucVu(),
                view.getSDT()
        );

        if (service.them(nv)) {
            view.thongBao("Thêm nhân viên thành công!");
            loadTable();
        } else {
            view.thongBao("Thêm thất bại (có thể trùng mã)!");
        }
    }

    // ================== SỬA ==================
    private void sua() {
        NhanVien nv = new NhanVien(
                view.getMaNV(),
                view.getTenNV(),
                view.getChucVu(),
                view.getSDT()
        );

        if (service.sua(nv)) {
            view.thongBao("Sửa nhân viên thành công!");
            loadTable();
        } else {
            view.thongBao("Sửa thất bại!");
        }
    }

    // ================== XÓA ==================
    private void xoa() {
        if (view.getMaNV().isEmpty()) {
            view.thongBao("Nhập mã nhân viên cần xóa!");
            return;
        }

        if (service.xoa(view.getMaNV())) {
            view.thongBao("Xóa nhân viên thành công!");
            loadTable();
        } else {
            view.thongBao("Xóa thất bại!");
        }
    }
}
