DROP DATABASE IF EXISTS QLBanhNgot2;

CREATE DATABASE QLBanhNgot2;
GO
USE QLBanhNgot2;
GO

-------------------------
-- BẢNG TÀI KHOẢN
-------------------------
CREATE TABLE TaiKhoan (
    tenDangNhap VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(50) NOT NULL,
    vaiTro NVARCHAR(20) NOT NULL
);

-------------------------
-- BẢNG NHÂN VIÊN
-------------------------
IF OBJECT_ID('SanPham', 'U') IS NOT NULL
    DROP TABLE SanPham;
GO

CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY,
    tenNV NVARCHAR(100) NOT NULL,
    chucVu NVARCHAR(50) NOT NULL,
    sdt VARCHAR(15)
);
GO


-------------------------
-- BẢNG KHÁCH HÀNG
-------------------------
CREATE TABLE KhachHang (
    maKH VARCHAR(10) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
	gioiTinh NVARCHAR (10),
    sdt VARCHAR(15),
    diaChi NVARCHAR(100)
);

-------------------------
-- BẢNG SẢN PHẨM
-------------------------
CREATE TABLE SanPham (
    maSP VARCHAR(10) PRIMARY KEY,
    tenSP NVARCHAR(100) NOT NULL,
    gia FLOAT NOT NULL,
    soLuongTon INT NOT NULL CHECK (soLuongTon >= 0),
    hinh VARCHAR(255)
);

-------------------------
-- BẢNG HÓA ĐƠN
-- (PHÙ HỢP VỚI CODE JAVA CỦA BẠN)
-------------------------
CREATE TABLE HoaDon (
    maHD VARCHAR(10) PRIMARY KEY,
    ngayLap DATE NOT NULL,
    maNV VARCHAR(10),
    maKH VARCHAR(10),
    tongTien FLOAT
);

-------------------------
-- BẢNG CHI TIẾT HÓA ĐƠN
-------------------------

CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(10),
    maSP VARCHAR(10),
    tenSP VARCHAR(255),
    soLuong INT NOT NULL,
    donGia FLOAT NOT NULL,
    PRIMARY KEY (maHD, maSP)
);



-------------------------
-- DỮ LIỆU MẪU
-------------------------
INSERT INTO TaiKhoan VALUES
('admin', '123', N'Quản lý'),
('nv01',  '123', N'Nhân viên'),
('nv02',  '123', N'Nhân viên'),
('nv03',  '123', N'Nhân viên');

INSERT INTO NhanVien (maNV, tenNV, chucVu, sdt) VALUES
('NV01', N'Nguyễn Văn An',     N'Quản lý',  '0912345678'),
('NV02', N'Trần Thị Bình',     N'Nhân viên', '0934567890'),
('NV03', N'Lê Hoàng Minh',     N'Nhân viên', '0987654321');
GO


INSERT INTO KhachHang VALUES
('KH01', N'Phạm Hồng Phúc', '0909123123','Nam', N'Hồ Chí Minh'),
('KH02', N'Võ Ngọc Ánh', '0908334455','Nữ', N'Hà Nội'),
('KH03', N'Hoàng Gia Bảo', '0907555666','Nam', N'Đà Nẵng');

INSERT INTO SanPham VALUES
('SP01', N'Bánh Kem Socola', 150000, 100, NULL),
('SP02', N'Bánh Mì Bơ Sữa', 120000, 100, NULL),
('SP03', N'Bánh Ngọt Dâu Tây', 180000, 100, NULL),
('SP04', N'Bánh Quy Bơ', 25000, 100, NULL);


INSERT INTO HoaDon (maHD, ngayLap, maNV, maKH, tongTien)
VALUES
('HD01', '2024-01-01', 'NV01', 'KH01', 0),
('HD02', '2024-01-02', 'NV01', 'KH01', 0),
('HD03', '2024-01-03', 'NV02', 'KH02', 0),
('HD04', '2024-01-04', 'NV03', 'KH03', 0);


INSERT INTO ChiTietHoaDon (maHD, maSP, tenSP, soLuong, donGia) VALUES
('HD01', 'SP01', 'Sản phẩm 1', 2, 150000),
('HD01', 'SP04', 'Sản phẩm 4', 10, 25000),

('HD02', 'SP02', 'Sản phẩm 2', 1, 120000),
('HD02', 'SP03', 'Sản phẩm 3', 2, 180000),

('HD03', 'SP01', 'Sản phẩm 1', 1, 150000),
('HD03', 'SP03', 'Sản phẩm 3', 3, 180000),

('HD04', 'SP04', 'Sản phẩm 4', 20, 25000);

-------------------------
-- CẬP NHẬT TỔNG TIỀN CHO HÓA ĐƠN
-------------------------
UPDATE HoaDon
SET tongTien = (
    SELECT SUM(soLuong * donGia)
    FROM ChiTietHoaDon
    WHERE ChiTietHoaDon.maHD = HoaDon.maHD
);

SELECT * FROM HoaDon ORDER BY MaHD;
DELETE FROM SanPham WHERE maSP = '';

SELECT maSP FROM SanPham ORDER BY maSP;
SELECT * FROM KhachHang;
DELETE FROM SanPham 
WHERE MaSP = '' OR MaSP IS NULL;


