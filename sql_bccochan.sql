create database quanlyxenuoc
go
use quanlyxenuoc
go

CREATE TABLE NhanVien (
    MaNV INT IDENTITY PRIMARY KEY,
    TenNV NVARCHAR(100) NOT NULL,
    TaiKhoan VARCHAR(50) UNIQUE NOT NULL,
    MatKhau VARCHAR(50) NOT NULL,
    VaiTro INT NOT NULL -- 1: Admin, 0: NhanVien
);
CREATE TABLE KhachHang (
    MaKH INT IDENTITY PRIMARY KEY,
    TenKH NVARCHAR(100),
    SDT VARCHAR(15)
);
CREATE TABLE LoaiSanPham (
    MaLoai INT IDENTITY PRIMARY KEY,
    TenLoai NVARCHAR(50) NOT NULL
);
CREATE TABLE SanPham (
    MaSP INT IDENTITY PRIMARY KEY,
    TenSP NVARCHAR(100) NOT NULL,
    Gia FLOAT NOT NULL,
    MaLoai INT NOT NULL,
    TrangThai BIT DEFAULT 1,

    FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai)
);
CREATE TABLE Topping (
    MaTop INT IDENTITY PRIMARY KEY,
    TenTop NVARCHAR(100) NOT NULL,
    Gia FLOAT NOT NULL
);
CREATE TABLE DonHang (
    MaDH INT IDENTITY PRIMARY KEY,
    MaNV INT NOT NULL,
    MaKH INT NULL,
    NgayLap DATETIME DEFAULT GETDATE(),
    TongTien FLOAT,

    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);
CREATE TABLE ChiTiet_DonHang (
    MaDH INT,
    MaSP INT,
    SoLuong INT NOT NULL,
    DonGia FLOAT NOT NULL,

    PRIMARY KEY (MaDH, MaSP),
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);
CREATE TABLE ChiTiet_Topping (
    MaDH INT,
    MaTop INT,
    Gia FLOAT NOT NULL,

    PRIMARY KEY (MaDH, MaTop),
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
    FOREIGN KEY (MaTop) REFERENCES Topping(MaTop)
);

INSERT INTO NhanVien (TenNV, TaiKhoan, MatKhau, VaiTro) VALUES
(N'Đặng Tuấn Kiệt', 'admin', '123', 1),
(N'Trần Thị Ngọc Trân', 'trân', '123', 0),
(N'Lê Văn Minh', 'minh', '123', 0);
INSERT INTO KhachHang (TenKH, SDT) VALUES
(N'Nguyễn Anh Tuấn', '0909123456'),
(N'Trần Ngọc Mai', '0988777666'),
(NULL, NULL); -- khách vãng lai
INSERT INTO LoaiSanPham (TenLoai) VALUES
(N'Trà sữa'),
(N'Trà trái cây'),
(N'Latte');
INSERT INTO SanPham (TenSP, Gia, MaLoai) VALUES
-- Trà sữa
(N'Trà sữa trân châu', 30000, 1),
(N'Trà sữa matcha', 35000, 1),
(N'Trà sữa socola', 35000, 1),

-- Trà trái cây
(N'Trà đào cam sả', 35000, 2),
(N'Trà vải', 33000, 2),
(N'Trà dâu', 34000, 2),

-- Latte
(N'Latte truyền thống', 40000, 3),
(N'Matcha latte', 42000, 3),
(N'Caramel latte', 45000, 3);
INSERT INTO Topping (TenTop, Gia) VALUES
(N'Trân châu đen', 5000),
(N'Trân châu trắng', 5000),
(N'Thạch đào', 6000),
(N'Pudding trứng', 7000);
INSERT INTO DonHang (MaNV, MaKH, TongTien) VALUES
(2, 1, 0),
(3, 2, 0);
INSERT INTO ChiTiet_DonHang (MaDH, MaSP, SoLuong, DonGia) VALUES
(1, 1, 2, 30000),
(1, 4, 1, 35000),
(2, 7, 1, 40000);
INSERT INTO ChiTiet_Topping (MaDH, MaTop, Gia) VALUES
(1, 1, 5000),
(1, 3, 6000),
(2, 4, 7000);
