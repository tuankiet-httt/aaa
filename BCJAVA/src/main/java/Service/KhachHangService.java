/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DoanhThu;
import Repository.DoanhThuRepository;
import java.util.List;

public class KhachHangService {

    private DoanhThuRepository repo = new DoanhThuRepository();

    public List<DoanhThu> getAll() {
        return repo.getAll();
    }

    public boolean them(DoanhThu kh) {
        if (kh.getMaKH().isEmpty() || kh.getTenKH().isEmpty()) {
    return false;
}

        return repo.insert(kh);
    }

    public boolean sua(DoanhThu kh) {
        return repo.update(kh);
    }

    public boolean xoa(String maKH) {
        return repo.delete(maKH);
    }
}

