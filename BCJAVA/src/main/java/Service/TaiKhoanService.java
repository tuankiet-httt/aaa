/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.TaiKhoan;
import Repository.TaiKhoanRepository;

/**
 *
 * @author HP
 */
public class TaiKhoanService {
    private TaiKhoanRepository repo = new TaiKhoanRepository();

    public TaiKhoan kiemTraDangNhap(String tk, String mk) {
        if (tk.isEmpty() && mk.isEmpty()) {
            return null;
        }
        return repo.dangNhap(tk, mk);
    }
}
