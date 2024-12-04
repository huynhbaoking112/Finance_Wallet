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

import managerBank.DTOS.PhieuNhapDto;


public class AdminPhieuNhap extends  JFrame{
    JButton dash;
    JButton nhapKho;
    JButton xuatKho;
    JButton PhieuNhap;
    JButton phieuNhap;
    JButton profile;
    JButton logout;
    JTable tongPhieuNhap;
    JScrollPane scrollPane;
    DefaultTableModel model;
    String[] columns = {"Ma Phieu Nhap","Ngay Nhap","Nguoi Nhap", "Tong Hoa Don"};
    List<PhieuNhapDto> listPhieuInfor ;
    
    private static HttpClient client = HttpClient.newHttpClient();

    public void layDuLieu(){
        
        try {
            String uri = "http://" + Network.networkWork+":8080"+"/api/warehouse/getallimportbill";
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                listPhieuInfor = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class,  PhieuNhapDto.class));       

            } else {
                System.err.println("Failed to fetch data. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public AdminPhieuNhap(){
        layDuLieu();
model = new DefaultTableModel(columns, 0);
        tongPhieuNhap = new JTable(model);

        // Tùy chỉnh dữ liệu trong bảng
        tongPhieuNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Font của dữ liệu
        tongPhieuNhap.setRowHeight(30); // Tăng chiều cao từng dòng
        tongPhieuNhap.setForeground(Color.DARK_GRAY); // Màu chữ
        tongPhieuNhap.setBackground(Color.WHITE); // Màu nền của bảng
        tongPhieuNhap.setGridColor(Color.LIGHT_GRAY); // Màu đường kẻ

// Tùy chỉnh header của bảng
JTableHeader header = tongPhieuNhap.getTableHeader();
header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Chữ in đậm
header.setForeground(Color.BLUE); // Màu chữ xanh
header.setBackground(Color.LIGHT_GRAY); // Màu nền header (tuỳ chọn)
header.setReorderingAllowed(false); // Không cho phép kéo đổi thứ tự cột
        scrollPane = new JScrollPane(tongPhieuNhap);
        this.add(scrollPane);
        scrollPane.setBounds(270, 25, 1099, 733);        

        // Căn giữa nội dung các ô trong bảng
DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung trong ô

// Áp dụng renderer cho tất cả các cột
for (int i = 0; i < tongPhieuNhap.getColumnCount(); i++) {
    tongPhieuNhap.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}

        for (PhieuNhapDto phieu : listPhieuInfor){
            model.addRow(new Object[]{phieu.getMa_phieu_nhap(), phieu.getNgay_nhap(), phieu.getNguoi_nhap(), phieu.getTong_hoa_don()});
           
        }

        phieuNhap = new JButton();
        phieuNhap.setBounds(45, 190, 174, 42);
        phieuNhap.setContentAreaFilled(false);
        phieuNhap.setBorderPainted(false);
        phieuNhap.setFocusPainted(false);
        phieuNhap.addActionListener(e -> {
            new AdminPhieuXuat();
            this.setVisible(false);
        });
        
        dash = new JButton();
        dash.setBounds(45, 70, 174, 42);
        dash.setContentAreaFilled(false);
        dash.setBorderPainted(false);
        dash.setFocusPainted(false);
        dash.addActionListener(e -> {
            new Admin();
            this.setVisible(false);
        });

        this.add(dash);
        this.add(phieuNhap);
        init();
    }
    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\trangphieunhap.jpg");
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
        new AdminPhieuNhap();
    }
}
