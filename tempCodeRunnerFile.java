import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

// Lớp Product đại diện cho một sản phẩm
class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class ProductTableExample extends JFrame {

    // Constructor tạo và hiển thị bảng sản phẩm
    public ProductTableExample(List<Product> products) {
        // Cột cho JTable
        String[] columnNames = { "Mã sản phẩm", "Tên sản phẩm", "Giá" };

        // Tạo model cho JTable
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Thêm dữ liệu từ danh sách sản phẩm vào model
        for (Product product : products) {
            Object[] row = { product.getId(), product.getName(), product.getPrice() };
            model.addRow(row);
        }

        // Tạo JTable với model
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Thiết lập giao diện JFrame
        this.setTitle("Danh sách sản phẩm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);  // Căn giữa cửa sổ
        this.setVisible(true);
    }

    // Hàm main để chạy chương trình
    public static void main(String[] args) {
        // Tạo danh sách sản phẩm mẫu
        List<Product> products = new ArrayList<>();
        products.add(new Product("P001", "Sản phẩm A", 100.0));
        products.add(new Product("P002", "Sản phẩm B", 200.0));
        products.add(new Product("P003", "Sản phẩm C", 150.0));

        // Hiển thị bảng sản phẩm
        new ProductTableExample(products);
    }
}
