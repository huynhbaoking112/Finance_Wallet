package managerBank.DTOS;

import java.util.List;

public class OutboundRequest {
    public List<ProductDTO> danhSachSanPham;
    public String tenNguoiNhapHang = "lenhutphuong";
    
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

}
