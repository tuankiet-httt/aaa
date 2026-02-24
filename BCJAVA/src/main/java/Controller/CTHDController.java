package Controller;

import Model.ChiTietHoaDon;
import Service.CTHDService;
import View.ChiTietHoaDonView;
import View.HoaDonView;
import java.io.FileWriter;
import java.util.List;

public class CTHDController {

    private final ChiTietHoaDonView view;
    private final CTHDService service;
    private final String maHD;
    private final HoaDonView parent;

    public CTHDController(ChiTietHoaDonView view, String maHD, HoaDonView parent) {
        this.view = view;
        this.maHD = maHD;
        this.parent = parent;
        this.service = new CTHDService();  // ⭐ ĐÃ SỬA Ở ĐÂY
        
        loadData();
        loadMaSP();
        initEvents();
        

    }

    private void loadData() {
        view.loadTable(service.getList(maHD));
    }

    private void loadMaSP() {
    view.getCbMaSP().removeAllItems();
    for (String s : service.getAllMaSP()) {
        view.getCbMaSP().addItem(s);
    }

    // Khi chọn Mã SP → tự load tên + giá
    view.getCbMaSP().addActionListener(e -> {
    String maSP = (String) view.getCbMaSP().getSelectedItem();
    if (maSP != null) {
        view.getTxtTenSP().setText(service.getTenSP(maSP));
        view.getTxtDG().setText(String.valueOf(service.getDonGia(maSP)));
    }
});

}


    private void initEvents() {

        view.onAdd(e -> {
            ChiTietHoaDon c = view.getInput(maHD);
            if (c == null) return;

            if (service.add(c)) {
                view.showMessage("Thêm thành công!");
                loadData();
            }
        });

        view.onUpdate(e -> {
            ChiTietHoaDon c = view.getInput(maHD);
            if (c == null) return;

            if (service.update(c)) {
                view.showMessage("Cập nhật thành công!");
                loadData();
            }
        });

        view.onDelete(e -> {
            String maSP = view.getSelectedMaSP();
            if (maSP == null) {
                view.showMessage("Chọn dòng cần xóa!");
                return;
            }

            if (service.delete(maHD, maSP)) {
                view.showMessage("Xóa thành công!");
                loadData();
            }
        });
        
        view.onPrint(e -> {
    List<ChiTietHoaDon> list = service.getList(maHD);
    xuatHoaDonFile(maHD, list);
});


        view.onExit(e -> {
    if (parent != null) parent.setVisible(true);
    view.dispose();
});

    }
    private void xuatHoaDonCSV(String maHD, List<ChiTietHoaDon> list) {
    try {
        String fileName = "HoaDon_" + maHD + ".csv";
        FileWriter writer = new FileWriter(fileName, false);

        writer.append("Mã SP,Tên SP,Số lượng,Đơn Giá,Thành Tiền\n");

        double tongTien = 0;

        for (ChiTietHoaDon ct : list) {
            double thanhTien = ct.getSoLuong() * ct.getDonGia();
            tongTien += thanhTien;

            writer.append(ct.getMaSP()).append(",");
            writer.append(ct.getTenSP()).append(",");
            writer.append(ct.getSoLuong() + "").append(",");
            writer.append(ct.getDonGia() + "").append(",");
            writer.append(thanhTien + "").append("\n");
        }

        writer.append(",,,Tổng tiền,").append(String.valueOf(tongTien)).append("\n");

        writer.flush();
        writer.close();

        view.showMessage("Xuất hóa đơn thành công!\nFile: " + fileName);

    } catch (Exception ex) {
        ex.printStackTrace();
        view.showMessage("Lỗi khi xuất hóa đơn!");
    }
}
    
    private void xuatHoaDonFile(String maHD, List<ChiTietHoaDon> list) {
    try {
        String fileName = "HoaDon_" + maHD + ".txt";
        FileWriter w = new FileWriter(fileName);

        w.write("===== HÓA ĐƠN BÁN HÀNG =====\n");
        w.write("Mã hóa đơn: " + maHD + "\n");
        w.write("============================\n\n");

        double tongTien = 0;

        for (ChiTietHoaDon c : list) {
            w.write("Sản phẩm: " + c.getTenSP() + "\n");
            w.write("Số lượng: " + c.getSoLuong() + "\n");
            w.write("Đơn giá: " + c.getDonGia() + "\n");
            w.write("Thành tiền: " + c.getThanhTien() + "\n");
            w.write("------------------------------\n");
            tongTien += c.getThanhTien();
        }

        w.write("\nTỔNG CỘNG: " + tongTien + " VND\n");
        w.write("===== CẢM ƠN QUÝ KHÁCH =====\n");

        w.close();

        view.showMessage("Đã xuất hóa đơn: " + fileName);

    } catch (Exception e) {
        view.showMessage("Lỗi in hóa đơn: " + e.getMessage());
    }
}


}
