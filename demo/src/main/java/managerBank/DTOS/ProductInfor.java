package managerBank.DTOS;

public class ProductInfor {
    
    private int idProduct;
    private String productName;
    private String category;
    private String donViTinh;
    private int giaBan;
    private int soLuong;
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDonViTinh() {
        return donViTinh;
    }
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    public int getGiaBan() {
        return giaBan;
    }
    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

   
    public static ProductInfor getClone( int idProduct,
     String productName,
     String category,
     String donViTinh,
     int giaBan,
     int soLuong){
        ProductInfor a = new ProductInfor();
        a.setIdProduct(idProduct);
        a.setProductName(productName);
        a.setCategory(category);
        a.setDonViTinh(donViTinh);
        a.setGiaBan(giaBan);
        a.setSoLuong(soLuong);
        return a;
    }

}
