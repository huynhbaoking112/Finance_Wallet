package managerBank.DTOS;

import java.sql.Timestamp;

public class PhieuXuatDto {
    private int idPhieuXuat;
    private String nguoiXuat;
    private Timestamp ngayTao;
    private int tongHoaDon;

    public int getIdPhieuXuat() {
        return idPhieuXuat;
    }

    public void setIdPhieuXuat(int idPhieuXuat) {
        this.idPhieuXuat = idPhieuXuat;
    }

    public String getNguoiXuat() {
        return nguoiXuat;
    }

    public void setNguoiXuat(String nguoiXuat) {
        this.nguoiXuat = nguoiXuat;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongHoaDon() {
        return tongHoaDon;
    }

    public void setTongHoaDon(int tongHoaDon) {
        this.tongHoaDon = tongHoaDon;
    }


 
       
}
