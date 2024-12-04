package managerBank.DTOS;

import java.util.List;

public class InboundRequest {
    public List<ProductDTO> danhSachSanPham;
    public String tenNguoiNhapHang = "lenhutphuong";
    public int tongHoaDon;
    public List<ProductDTO> getDanhSachSanPham() {
        return danhSachSanPham;
    }
    public void setDanhSachSanPham(List<ProductDTO> danhSachSanPham) {
        this.danhSachSanPham = danhSachSanPham;
    }
    public String getTenNguoiNhapHang() {
        return tenNguoiNhapHang;
    }
    public void setTenNguoiNhapHang(String tenNguoiNhapHang) {
        this.tenNguoiNhapHang = tenNguoiNhapHang;
    }
    public int getTongHoaDon() {
        return tongHoaDon;
    }
    public void setTongHoaDon(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }

    

    
}
