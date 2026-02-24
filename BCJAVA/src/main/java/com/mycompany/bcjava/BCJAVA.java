package com.mycompany.bcjava;

import Controller.DangNhapController;
import Controller.HoaDonController;
import View.DangNhapView;
import View.HoaDonView;

public class BCJAVA {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            DangNhapView view = new DangNhapView();
            new DangNhapController(view);
            view.setVisible(true);
        });
//    java.awt.EventQueue.invokeLater(() -> {
//        HoaDonView view = new HoaDonView();
//        new HoaDonController(view);  // <-- QUAN TRỌNG
//        view.setVisible(true);
//    });

    }
}
