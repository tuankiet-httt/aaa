/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TaiKhoan;
import Service.TaiKhoanService;
import View.DangNhapView;
import View.MainView;

public class DangNhapController {

    private DangNhapView view;
    private TaiKhoanService service = new TaiKhoanService();

    public DangNhapController(DangNhapView view) {
        this.view = view;

        view.actionDangNhap(e -> dangNhap());
        view.actionThoat(e -> view.thoat());
    }

    private void dangNhap() {
        String tk = view.getTenTK();
        String mk = view.getMK();

        TaiKhoan taiKhoan = service.kiemTraDangNhap(tk, mk);

        if (taiKhoan != null) {
            view.thongBao("Đăng nhập thành công");
            new MainView(taiKhoan).setVisible(true);
            view.dispose();
        } else {
            view.thongBao("Sai tài khoản hoặc mật khẩu");
        }
    }
}
