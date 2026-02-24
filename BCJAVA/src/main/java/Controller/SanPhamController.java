/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.SanPham;
import Model.TaiKhoan;
import Service.SanPhamService;
import View.SanPhamView;
import View.MainView;

import java.io.File;
import java.util.List;
import javax.swing.*;

public class SanPhamController {

    private SanPhamView view;
    private SanPhamService service = new SanPhamService();
    private TaiKhoan taiKhoan;

    public SanPhamController(SanPhamView view, TaiKhoan tk) {
        this.view = view;
        this.taiKhoan = tk;

        loadTable();
        initEvent();
    }

    private void initEvent() {
        view.onThem(e -> them());
        view.onSua(e -> sua());
        view.onXoa(e -> xoa());
        view.onChonHinh(e -> chonHinh());
        view.onTableClick(e -> {
    SanPham sp = view.getSelectedSanPham();
    if (sp != null) {
        view.setForm(sp);
    }
});

        view.onThoat(e -> thoat());
    }

    private void loadTable() {
        List<SanPham> list = service.getAll();
        view.fillTable(list);
    }

    private void them() {
        try {
            SanPham sp = view.getForm();
            if (service.them(sp)) {
                JOptionPane.showMessageDialog(view, "Thêm thành công!");
                loadTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
        }
    }

    private void sua() {
        SanPham sp = view.getForm();
        if (service.sua(sp)) {
            JOptionPane.showMessageDialog(view, "Sửa thành công!");
            loadTable();
        }
    }

    private void xoa() {
        String ma = view.getMaSP();
        if (service.xoa(ma)) {
            JOptionPane.showMessageDialog(view, "Xóa thành công!");
            loadTable();
        }
    }

    private void hienThi() {
        int row = view.getSelectedRow();

        if (row < 0) return;

        SanPham sp = view.getSelectedSanPham();  // ⭐ DÙNG DỮ LIỆU TRONG BẢNG
        view.setForm(sp);
    
    }

    private void chonHinh() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            String fileName = view.saveImage(f);
            view.showImage(fileName);
        }
    }

    private void thoat() {
        view.dispose();
        new MainView(taiKhoan).setVisible(true);  // FIX!
    }
}
