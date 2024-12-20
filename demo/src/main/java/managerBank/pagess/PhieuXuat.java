package managerBank.pagess;

import java.awt.Color;
import java.awt.Font;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

import managerBank.DTOS.PhieuXuatDto;

public class PhieuXuat extends JFrame {
     JButton dash;
    JButton nhapKho;
    JButton xuatKho;
    JButton phieuXuat;
    JButton phieuNhap;
    JButton profile;
    JButton logout;
    JTable tongPhieuXuat;
    JScrollPane scrollPane;
    DefaultTableModel model;
    String[] columns = {"Ma Phieu Xuat","Ngay Xuat","Nguoi Xuat", "Tong Hoa Don"};
    List<PhieuXuatDto> listPhieuInfor ;
    
    private static HttpClient client = HttpClient.newHttpClient();

    public void layDuLieu(){
        
        try {
            String uri = "http://" + Network.networkWork+":8080"+"/api/warehouse/getallexportbill";
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                listPhieuInfor = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class,  PhieuXuatDto.class));       

            } else {
                System.err.println("Failed to fetch data. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     public PhieuXuat (){

        layDuLieu();

        model = new DefaultTableModel(columns, 0);
        tongPhieuXuat = new JTable(model);
        scrollPane = new JScrollPane(tongPhieuXuat);
        this.add(scrollPane);
        scrollPane.setBounds(270, 25, 1099, 733);        
        for (PhieuXuatDto phieu : listPhieuInfor){
            model.addRow(new Object[]{phieu.getMa_phieu_xuat(), phieu.getNguoi_xuat(), phieu.getNgay_xuat(), phieu.getTong_hoa_don()});
           
        }

        // Tùy chỉnh dữ liệu trong bảng
        tongPhieuXuat.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Font của dữ liệu
        tongPhieuXuat.setRowHeight(30); // Tăng chiều cao từng dòng
        tongPhieuXuat.setForeground(Color.DARK_GRAY); // Màu chữ
        tongPhieuXuat.setBackground(Color.WHITE); // Màu nền của bảng
        tongPhieuXuat.setGridColor(Color.LIGHT_GRAY); // Màu đường kẻ

// Tùy chỉnh header của bảng
JTableHeader header = tongPhieuXuat.getTableHeader();
header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Chữ in đậm
header.setForeground(Color.BLUE); // Màu chữ xanh
header.setBackground(Color.LIGHT_GRAY); // Màu nền header (tuỳ chọn)
header.setReorderingAllowed(false); // Không cho phép kéo đổi thứ tự cột

// Căn giữa nội dung các ô trong bảng
DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung trong ô

// Áp dụng renderer cho tất cả các cột
for (int i = 0; i < tongPhieuXuat.getColumnCount(); i++) {
    tongPhieuXuat.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}


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
           
            new NhapKho();
            this.setVisible(false);
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

        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\phieuxuat.jpg");
        // Image handle_background = pre_background.getImage().getScaledInstance(1000,
        // 800, Image.SCALE_SMOOTH);
        // ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(pre_background);
        jLabel_background.setBounds(0, 0, 1417, 868);
        this.add(jLabel_background);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1450, 900);
        this.setTitle("Dashboard");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new PhieuXuat();
    }
}
