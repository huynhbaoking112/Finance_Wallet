package managerBank.DTOS;

import java.util.List;

import managerBank.pagess.Network;

public class OutboundRequest {
    public List<ProductDTO> danhSachSanPham;
    public String tenNguoiXuatHang = Network.ten;
    public int tongHoaDon;

    public List<ProductDTO> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    public void setDanhSachSanPham(List<ProductDTO> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }

    public String getTenNguoiXuatHang() {
        return tenNguoiXuatHang;
    }

    public void setTenNguoiXuatHang(String tenNguoiXuatHang) {
        this.tenNguoiXuatHang = tenNguoiXuatHang;
    }

    public int getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    
}
