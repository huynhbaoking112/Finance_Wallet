package managerBank.pagess;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse; // Đảm bảo bạn đã import MouseAdapter
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

import managerBank.DTOS.InboundRepond;
import managerBank.DTOS.InboundRequest;
import managerBank.DTOS.ProductDTO;
import managerBank.DTOS.ProductInfor;

public class NhapKho  extends  JFrame{
    JTextField timf;
    JButton tim;
    int tongtien = 0;
    JTextField soluongf;
    JButton them;
    JButton nhapHang;
    JTextField tongTienf;  
    JButton dash;
    JButton nhapKho;
    JButton xuatKho;
    JButton phieuXuat;
    JButton phieuNhap;
    JButton profile;
    JButton logout;
    ProductInfor productInfor ;
    List<ProductInfor> listProductInfor ;
    List<ProductInfor> listProductInforNhap = new ArrayList<>();
    JTable allProduct ;
    JScrollPane scrollPane;
    String[] columnName = {"ID", "ProductName","Category","Unit", "Price","Quantity"};
    DefaultTableModel model;
    JScrollPane scrollPaneNhap;
    DefaultTableModel modelNhap;
    JTable allProductNhap ;
    private static HttpClient client = HttpClient.newHttpClient();
    
    public void layDulieu(){
        try {
            String uri = "http://" + Network.networkWork+":8080"+"/api/products/getall";
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                listProductInfor = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class,  ProductInfor.class));       
               
            } else {
                System.err.println("Failed to fetch data. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NhapKho (){

        layDulieu();
      

        model = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa bất kỳ ô nào
            }
        };
allProduct = new JTable(model);

// Tùy chỉnh dữ liệu trong bảng
allProduct.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Font của dữ liệu
allProduct.setRowHeight(30); // Tăng chiều cao từng dòng
allProduct.setForeground(Color.DARK_GRAY); // Màu chữ
allProduct.setBackground(Color.WHITE); // Màu nền của bảng
allProduct.setGridColor(Color.LIGHT_GRAY); // Màu đường kẻ

// Tùy chỉnh header của bảng
JTableHeader header = allProduct.getTableHeader();
header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Chữ in đậm
header.setForeground(Color.BLUE); // Màu chữ xanh
header.setBackground(Color.LIGHT_GRAY); // Màu nền header (tuỳ chọn)
header.setReorderingAllowed(false); // Không cho phép kéo đổi thứ tự cột

// Cuộn bảng
scrollPane = new JScrollPane(allProduct);
scrollPane.setBounds(270, 164, 562, 514);
this.add(scrollPane);
// Căn giữa nội dung các ô trong bảng
DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung trong ô

// Áp dụng renderer cho tất cả các cột
for (int i = 0; i < allProduct.getColumnCount(); i++) {
    allProduct.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}

// Thêm dữ liệu vào bảng
for (ProductInfor item : listProductInfor) {
    model.addRow(new Object[]{
        item.getIdProduct(), 
        item.getProductName(), 
        item.getCategory(),
        item.getDonViTinh(), 
        item.getGiaBan(), 
        item.getSoLuong()
    });
}


modelNhap = new DefaultTableModel(columnName, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Không cho phép chỉnh sửa bất kỳ ô nào
    }
};
allProductNhap = new JTable(modelNhap);

// Tùy chỉnh dữ liệu trong bảng
allProductNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Font của dữ liệu
allProductNhap.setRowHeight(30); // Tăng chiều cao từng dòng
allProductNhap.setForeground(Color.DARK_GRAY); // Màu chữ
allProductNhap.setBackground(Color.WHITE); // Màu nền của bảng
allProductNhap.setGridColor(Color.LIGHT_GRAY); // Màu đường kẻ

// Tùy chỉnh header của bảng
JTableHeader headerNhap= allProductNhap.getTableHeader();
headerNhap.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Chữ in đậm
headerNhap.setForeground(Color.BLUE); // Màu chữ xanh
headerNhap.setBackground(Color.LIGHT_GRAY); // Màu nền header (tuỳ chọn)
headerNhap.setReorderingAllowed(false); // Không cho phép kéo đổi thứ tự cột

// Cuộn bảng
scrollPaneNhap = new JScrollPane(allProductNhap);
scrollPaneNhap.setBounds(861, 49,542, 629);
this.add(scrollPaneNhap);
// Căn giữa nội dung các ô trong bảng
// DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung trong ô

// Áp dụng renderer cho tất cả các cột
for (int i = 0; i < allProductNhap.getColumnCount(); i++) {
    allProductNhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}



// Thêm sự kiện nhấp chuột vào bảng
allProduct.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = allProduct.getSelectedRow(); // Lấy chỉ số dòng được nhấp
        if (row != -1) {
            // Lấy dữ liệu từ dòng được nhấp (ví dụ: Tên sản phẩm)
            String productName = (String) allProduct.getValueAt(row, 1); // Cột 1 là tên sản phẩm

            productInfor = new ProductInfor();
            productInfor.setIdProduct((int) allProduct.getValueAt(row, 0));
            productInfor.setProductName((String) allProduct.getValueAt(row, 1));
            productInfor.setCategory((String) allProduct.getValueAt(row, 2));
            productInfor.setDonViTinh((String) allProduct.getValueAt(row, 3));
            productInfor.setGiaBan((int) allProduct.getValueAt(row, 4));
            
            // Thực hiện hành động (ví dụ: In ra tên sản phẩm)
            System.out.println("Bạn đã nhấp vào sản phẩm: " + productName);
            
            // Bạn có thể thêm các hành động khác tùy vào yêu cầu của bạn
        }
    }
});

        // Nut tim kiem
        tim = new JButton();
        tim.setBounds(710, 85, 98, 44);
        tim.setContentAreaFilled(false);
        tim.setBorderPainted(false);
        tim.setFocusPainted(false);
        tim.addActionListener(e -> {
           
            model.setRowCount(0);
            if(timf.getText().equals("")){
                for (ProductInfor item : listProductInfor) {
                    model.addRow(new Object[]{
                        item.getIdProduct(), 
                        item.getProductName(), 
                        item.getCategory(),
                        item.getDonViTinh(), 
                        item.getGiaBan(), 
                        item.getSoLuong()
                    });
                }
            }else{
                for(ProductInfor item : listProductInfor){
                
                    if (item.getProductName().toLowerCase().contains(timf.getText().toLowerCase())){
                       model.addRow(new Object[]{
                           item.getIdProduct(), 
                           item.getProductName(), 
                           item.getCategory(),
                           item.getDonViTinh(), 
                           item.getGiaBan(), 
                           item.getSoLuong()
                       });
                   }
               }
            }
            

        });
        // Nut them
        them = new JButton();
        them.setBounds(622, 722, 111, 54);
        them.setContentAreaFilled(false);
        them.setBorderPainted(false);
        them.setFocusPainted(false);
        them.addActionListener(e -> {
            if(productInfor==null){
                JPanel panel = new JPanel();
                panel.setBackground(new Color(255, 223, 186)); // Màu nền nhẹ nhàng (hơi vàng nhạt)
                panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Thêm khoảng cách xung quanh văn bản

                JLabel label = new JLabel("Vui lòng chọn loại hàng nhập", SwingConstants.CENTER); // Đặt văn bản vào giữa
                label.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Đặt font chữ đẹp hơn
                label.setForeground(new Color(255, 87, 34)); // Màu chữ cam (màu nổi bật nhưng dễ nhìn)

                panel.add(label);

                JOptionPane.showMessageDialog(null, panel, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            else if(soluongf.getText().trim().equals("")){
                JPanel panel = new JPanel();
                panel.setBackground(new Color(255, 223, 186)); // Màu nền nhẹ nhàng (hơi vàng nhạt)
                panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Thêm khoảng cách xung quanh văn bản

                JLabel label = new JLabel("Vui lòng nhập số lượng", SwingConstants.CENTER); // Đặt văn bản vào giữa
                label.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Đặt font chữ đẹp hơn
                label.setForeground(new Color(255, 87, 34)); // Màu chữ cam (màu nổi bật nhưng dễ nhìn)

                panel.add(label);

                JOptionPane.showMessageDialog(null, panel, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                
                    int soluong = Integer.parseInt(soluongf.getText().trim()); // Chuyển chuỗi thành số nguyên
                    productInfor.setSoLuong(soluong);

                    boolean check = false;
                    for (ProductInfor item : listProductInforNhap){
                        if(item.getIdProduct()==productInfor.getIdProduct()){
                            System.out.println("da co");
                            System.out.println(item.getSoLuong());
                            System.out.println(productInfor.getSoLuong());
                            check = true;
                            item.setSoLuong(item.getSoLuong()+productInfor.getSoLuong());
                        }
                    }
                    if(!check){ 
                        System.out.println("chua co");
                        listProductInforNhap.add( ProductInfor.getClone(productInfor.getIdProduct(), productInfor.getProductName(), productInfor.getCategory(), productInfor.getDonViTinh(), productInfor.getGiaBan(), productInfor.getSoLuong()) );
                    }
        
                    modelNhap.setRowCount(0);
                    for (ProductInfor item : listProductInforNhap) {
                        modelNhap.addRow(new Object[]{
                            item.getIdProduct(), 
                            item.getProductName(), 
                            item.getCategory(),
                            item.getDonViTinh(), 
                            item.getGiaBan(), 
                            item.getSoLuong()
                        });
                    }
                    soluongf.setText("");

                    
                    for(ProductInfor item : listProductInforNhap){
                        tongtien+=(item.getGiaBan() * item.getSoLuong());
                        tongTienf.setText(tongtien+"");
                    }

                } catch (NumberFormatException ee) {
                    JPanel panel = new JPanel();
                    panel.setBackground(new Color(255, 223, 186)); // Màu nền nhẹ nhàng (hơi vàng nhạt)
                    panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Thêm khoảng cách xung quanh văn bản
    
                    JLabel label = new JLabel("Vui lòng nhập 1 số hợp lệ", SwingConstants.CENTER); // Đặt văn bản vào giữa
                    label.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Đặt font chữ đẹp hơn
                    label.setForeground(new Color(255, 87, 34)); // Màu chữ cam (màu nổi bật nhưng dễ nhìn)
    
                    panel.add(label);
    
                    JOptionPane.showMessageDialog(null, panel, "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }

            layDulieu();

        });

        //Nut nhap
        nhapHang = new JButton();
        nhapHang.setBounds(1206, 716, 174, 42);
        nhapHang.setContentAreaFilled(false);
        nhapHang.setBorderPainted(false);
        nhapHang.setFocusPainted(false);
        nhapHang.addActionListener(e -> {
           try {
            // Tạo đối tượng InboundRequest
            InboundRequest requestPayload = new InboundRequest();

            // Tạo danh sách sản phẩm
            List<ProductDTO> productList = new ArrayList<>();
            
            for(ProductInfor item : listProductInforNhap){
                ProductDTO productDTO = new ProductDTO();
                productDTO.setSoLuong(item.getSoLuong());
                productDTO.setIdProduct(item.getIdProduct());
                productDTO.setProductName(item.getProductName());
                productList.add(productDTO);
            }

            requestPayload.danhSachSanPham = productList;
            requestPayload.tongHoaDon=tongtien;
            // Chuyển đối tượng InboundRequest thành JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestPayload);

            // Gửi POST request
            String uri = "http://" + Network.networkWork + ":8080/api/warehouse/import";
            // HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))  // Đặt body cho POST request
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
               
                objectMapper = new ObjectMapper();
                // InboundRepond inboundRepond = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class,  InboundRepond.class));  
                InboundRepond inboundRepond = objectMapper.readValue(response.body(), InboundRepond.class);
                if(inboundRepond.isSuccess()){
                JPanel panel = new JPanel();
                panel.setBackground(new Color(187, 255, 156)); // Màu nền nhẹ nhàng, xanh lá nhạt
                panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Thêm khoảng cách xung quanh văn bản

                // Tạo JLabel để hiển thị thông báo
                JLabel label = new JLabel("Thành công! Dữ liệu đã được lưu.", SwingConstants.CENTER);
                label.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Đặt font chữ đẹp hơn
                label.setForeground(new Color(0, 128, 0)); // Màu chữ xanh lá cây (thường dùng cho thành công)

                // Thêm JLabel vào JPanel
                panel.add(label);

                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(null, panel, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                layDulieu();
                    model.setRowCount(0);
                    for (ProductInfor item : listProductInfor) {
                        model.addRow(new Object[]{
                            item.getIdProduct(), 
                            item.getProductName(), 
                            item.getCategory(),
                            item.getDonViTinh(), 
                            item.getGiaBan(), 
                            item.getSoLuong()
                        });
                    }
                    listProductInfor = new ArrayList<>();
                    modelNhap.setRowCount(0);
                }

                else{
                    JPanel panel = new JPanel();
                    panel.setBackground(new Color(187, 255, 156)); // Màu nền nhẹ nhàng, xanh lá nhạt
                    panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Thêm khoảng cách xung quanh văn bản

                    // Tạo JLabel để hiển thị thông báo
                    JLabel label = new JLabel("Thất bại! lỗi hệ thống", SwingConstants.CENTER);
                    label.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Đặt font chữ đẹp hơn
                    label.setForeground(new Color(0, 128, 0)); // Màu chữ xanh lá cây (thường dùng cho thành công)

                    // Thêm JLabel vào JPanel
                    panel.add(label);

                    // Hiển thị thông báo thành công
                    JOptionPane.showMessageDialog(null, panel, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    modelNhap.setRowCount(0);
                }

            } else {
                System.err.println("Failed to fetch data. Status code: " + response.statusCode());
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }



        });

        //Field so luong
        soluongf = new JTextField();
        soluongf.setBounds(490, 722, 111, 54);
        soluongf.setFont(new Font("Arial", Font.BOLD, 24));
        //Field tim kiem
        timf = new JTextField();
        timf.setBounds(270, 68, 423, 75);
        timf.setFont(new Font("Arial", Font.BOLD, 24));
        //Field tong tien
        tongTienf = new JTextField();
        tongTienf.setBounds(916, 719, 238, 57);
        tongTienf.setFont(new Font("Arial", Font.BOLD, 24));
        tongTienf.setEditable(false);
        
        dash = new JButton();
        dash.setBounds(45, 70, 174, 42);
        dash.setContentAreaFilled(false);
        dash.setBorderPainted(false);
        dash.setFocusPainted(false);
        dash.addActionListener(e -> {
           
            new Dashboard2();
            this.setVisible(false);
        });
        nhapKho = new JButton();
        nhapKho.setBounds(45, 136, 174, 42);
        nhapKho.setContentAreaFilled(false);
        nhapKho.setBorderPainted(false);
        nhapKho.setFocusPainted(false);
        nhapKho.addActionListener(e -> {
           

        });
        xuatKho = new JButton();
        xuatKho.setBounds(45, 204, 174, 42);
        xuatKho.setContentAreaFilled(false);
        xuatKho.setBorderPainted(false);
        xuatKho.setFocusPainted(false);
        xuatKho.addActionListener(e -> {
           new XuatKho();
           this.setVisible(false);

        });
        phieuXuat = new JButton();
        phieuXuat.setBounds(45, 274, 174, 42);
        phieuXuat.setContentAreaFilled(false);
        phieuXuat.setBorderPainted(false);
        phieuXuat.setFocusPainted(false);
        phieuXuat.addActionListener(e -> {
           new PhieuXuat();
           this.setVisible(false);

        });
        phieuNhap = new JButton();
        phieuNhap.setBounds(45, 341, 174, 42);
        phieuNhap.setContentAreaFilled(false);
        phieuNhap.setBorderPainted(false);
        phieuNhap.setFocusPainted(false);
        phieuNhap.addActionListener(e -> {
           new PhieuNhap();
           this.setVisible(false);

        });
        profile = new JButton();
        profile.setBounds(55, 470, 157, 63);
        profile.setContentAreaFilled(false);
        profile.setBorderPainted(false);
        profile.setFocusPainted(false);
        profile.addActionListener(e -> {
           

        });
        logout = new JButton();
        logout.setBounds(55, 590, 157, 63);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setFocusPainted(false);
        logout.addActionListener(e -> {
           

        });

        
        
        this.add(nhapKho);
        this.add(xuatKho);
        this.add(phieuNhap);
        this.add(phieuXuat);
        this.add(profile);
        this.add(logout);
        this.add(dash);


        this.add(timf);
        this.add(soluongf);
        this.add(tongTienf);
        this.add(nhapHang);
        this.add(them);
        this.add(tim);
        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\nhapkho.jpg");
        // Image handle_background = pre_background.getImage().getScaledInstance(1000,
        // 800, Image.SCALE_SMOOTH);
        // ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(pre_background);
        jLabel_background.setBounds(0, 0, 1417, 800);
        this.add(jLabel_background);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1450, 850);
        this.setTitle("Dashboard");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new NhapKho();
    }
}
