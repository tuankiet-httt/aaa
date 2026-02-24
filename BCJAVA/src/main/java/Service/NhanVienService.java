/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.NhanVien;
import Repository.NhanVienRepository;
import java.util.List;

public class NhanVienService {

    private NhanVienRepository repo = new NhanVienRepository();

    public List<NhanVien> getAllNV() {
        return repo.getAllNV();
    }

    public List<String> getAllMaNV() {
        return repo.getAllMaNV();
    }

    public boolean them(NhanVien nv) { return repo.insert(nv); }
    public boolean sua(NhanVien nv)  { return repo.update(nv); }
    public boolean xoa(String ma)    { return repo.delete(ma); }
}


