package Service;

import Model.SanPham;
import Repository.SanPhamRepository;

import java.util.List;

public class SanPhamService {

    private SanPhamRepository repo = new SanPhamRepository();

    public List<SanPham> getAll() {
        return repo.getAll();
    }

    public boolean them(SanPham sp) {
        return repo.insert(sp);
    }

    public boolean sua(SanPham sp) {
        return repo.update(sp);
    }

    public boolean xoa(String maSP) {
        return repo.delete(maSP);
    }
    public double getGiaSanPham(String maSP) {
    return repo.getGiaSanPham(maSP);
}

    

}
