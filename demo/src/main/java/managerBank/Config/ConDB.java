package managerBank.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import managerBank.utils.EmailSender;


public class ConDB {
    
    public Connection connection;
    public Statement statement;

    public ConDB(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletsystem", "root", "");
           // System.out.println("ket noi voi db cua dat thanh cong");
            statement = connection.createStatement();
        }  
        catch (Exception e1) {

          //  System.out.println("Không thể kết nối toi Mysql cua dat, thử kết nối toi mysql cua king");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletSystem", "root", "King_112");
                statement = connection.createStatement();
            } catch (Exception e2) {
                System.out.println("Không thể kết nối với database.");
                EmailSender.sendToDev("", e2.getMessage());
            }
        }
    }
}

// @Transactional(rollbackOn = Exception.class)
//     public OutboundRepond outbound(OutboundRequest outboundRequest) {
//         List<ProductDTO> productList = outboundRequest.getDanhSachSanPham();
//         OutboundRepond outboundRepond = new OutboundRepond();
    
//         try {
//             for (ProductDTO item : productList) {
//                 Product product = findProductDTOById(item.getIdProduct());
    
//                 // Lấy phiên bản và tồn kho hiện tại
//                 String versionSql = "SELECT version FROM khohang WHERE id_san_pham = :idSanPham";
//                 int versionProduct = (int) entityManager.createNativeQuery(versionSql)
//                     .setParameter("idSanPham", item.getIdProduct())
//                     .getSingleResult();
    
//                 String tonKhoSql = "SELECT tong_ton FROM khohang WHERE id_san_pham = :idSanPham";
//                 int tonKho = (int) entityManager.createNativeQuery(tonKhoSql)
//                     .setParameter("idSanPham", item.getIdProduct())
//                     .getSingleResult();
    
//                 // Kiểm tra tồn kho
//                 if (tonKho < item.getSoLuong()) {
//                     throw new RuntimeException("Sản phẩm " + item.getProductName() + " hết hàng. Giao dịch bị hủy.");
//                     // entityManager.getTransaction().setRollbackOnly();

//                     // outboundRepond.setSuccess(false);
//                     // outboundRepond.setMessage("that bai ne");
//                     // return outboundRepond;
//                 }
    
//                 // Thực hiện cập nhật tồn kho
//                 int rowEffect = entityManager.createNativeQuery(
//                     "UPDATE khohang SET tong_xuat = tong_xuat + :soLuong, tong_ton = tong_ton - :soLuong, version = version + 1 WHERE id_san_pham = :idSanPham and version = :versionProduct")
//                     .setParameter("soLuong", item.getSoLuong())
//                     .setParameter("idSanPham", item.getIdProduct())
//                     .setParameter("versionProduct", versionProduct)
//                     .executeUpdate();
    
//                 if (rowEffect == 0) {
//                     entityManager.getTransaction().setRollbackOnly();

//                     throw new RuntimeException("Cập nhật thất bại cho sản phẩm: " + item.getProductName());
//                 }
//             }
    
//             // Nếu tất cả cập nhật thành công
//             outboundRepond.setSuccess(true);
//             outboundRepond.setMessage("Xuất hàng thành công");
//             return outboundRepond;
    
//         } catch (Exception e) {
//             // Rollback giao dịch bằng cách đánh dấu rollback
//             entityManager.getTransaction().setRollbackOnly();

//             // Thiết lập thông báo lỗi trả về
//             outboundRepond.setSuccess(false);
//             outboundRepond.setMessage("Giao dịch thất bại: " + e.getMessage());/-strong/-heart:>:o:-((:-h return outboundRepond;  // Trả về response thay vì ném lại ngoại lệ
//         }
//     }