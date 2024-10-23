/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duansanbong;

import java.sql.*;
import Model.DichVu;
import Model.KhachHang;
import Model.ChuongTrinhKhuyenMai;
import Model.QuanLySanBong;
import Model.HoaDon;
import Repositories.KhachHangRepo;
import Repositories.service_chuongtrinkm;
import Repositories.service_dichvu;
import Utils.DBConneet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class view_datsan extends javax.swing.JPanel {

    private Connection con;
    private double tongPhiDVCu = 0.0;
    private double giaTriGiam = 0.0;
    private double tonhgDV = 0;
    private KhachHangRepo kh = new KhachHangRepo();
    private boolean isUpdatingComboBox = false;

    /**
     * Creates new form view_dấtn
     */
    public view_datsan() {
        con = DBConneet.getConnection(); // Kết nối cơ sở dữ liệu
        initComponents();

        LoadDataToComboBoxDichVu();
        cboDichVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isUpdatingComboBox) {
                    addDichVuToTable(); // Call method when selecting a service
                }
            }
        });

        // Thiết lập Timer để cập nhật dữ liệu cho JComboBox mỗi giây
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDataToComboBoxDichVu();
            }
        });
        timer.start();

        LoadDataToComboBoxChuongTrinhKM();
        cboChuongTrinhKM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isUpdatingComboBox) {
                    addChuongTrinhKMToTable(); // Gọi phương thức khi chọn chương trình khuyến mãi
                }
            }
        });

        // Thiết lập Timer để cập nhật dữ liệu cho JComboBox mỗi giây
        Timer timer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDataToComboBoxChuongTrinhKM();
            }
        });
        timer2.start();
    }

    public void LoadDataToComboBoxDichVu() {
        isUpdatingComboBox = true;
        try {
            Statement st = con.createStatement();
            String sqlDichVu = "SELECT TenDV FROM DichVu";
            ResultSet re = st.executeQuery(sqlDichVu);
            // Xóa dữ liệu cũ trong JComboBox (nếu có)
            cboDichVu.removeAllItems();
            while (re.next()) {
                String name = re.getString("TenDV");
                cboDichVu.addItem(name);
            }
            re.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        isUpdatingComboBox = false;
    }

    private List<DichVu> getDichVuList() {
        List<DichVu> serviceList = new ArrayList<>();
        for (Object item : cboDichVu.getSelectedObjects()) {
            String ten = (String) item;
            if (ten != null) {
                try {
                    String sqlDichVu = "SELECT IdDichVu, TenDV, PhiDV, TrangThai FROM DichVu WHERE TenDV = ?";
                    PreparedStatement pst = con.prepareStatement(sqlDichVu);
                    pst.setString(1, ten);
                    ResultSet re = pst.executeQuery();

                    while (re.next()) {
                        int id = re.getInt("IdDichVu");
                        String name = re.getString("TenDV");
                        double price = re.getDouble("PhiDV");
                        String status = re.getString("TrangThai");
                        serviceList.add(new DichVu(id, name, price, status));
                    }
                    re.close();
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi: " + e);
                }
            }
        }
        return serviceList;
    }

    private void addDichVuToTable() {
        tongPhiDVCu = 0.0; // Tổng phí dịch vụ cũ
        List<DichVu> services = getDichVuList();
        DefaultTableModel tableModel = (DefaultTableModel) TblBangDichVu2.getModel();

        // Tính tổng phí dịch vụ cũ
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double phiDV = (double) tableModel.getValueAt(i, 2);
            tongPhiDVCu += phiDV;
        }

        // Thêm các dòng dữ liệu mới
        for (DichVu service : services) {
            tableModel.addRow(new Object[]{
                service.getIdDichVu(),
                service.getTenDV(),
                service.getPhiDV(),
                service.getTrangThai()
            });
            tongPhiDVCu += service.getPhiDV(); // Cộng phí dịch vụ mới vào tổng phí
        }
        txtTongDV.setText(Double.toString(tongPhiDVCu));
    }

//---------------==========================
    public void LoadDataToComboBoxChuongTrinhKM() {
        isUpdatingComboBox = true;
        try {
            Statement st = con.createStatement();
            String sqlChuongTrinhKM = "SELECT TenTruongTrinhKM FROM ChuongTrinhKhuyenMai";
            ResultSet re = st.executeQuery(sqlChuongTrinhKM);
            // Xóa dữ liệu cũ trong JComboBox (nếu có)
            cboChuongTrinhKM.removeAllItems();
            while (re.next()) {
                String name = re.getString("TenTruongTrinhKM");
                cboChuongTrinhKM.addItem(name);
            }
            re.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
        isUpdatingComboBox = false;
    }

    private List<ChuongTrinhKhuyenMai> getChuongTrinhKMList() {
        List<ChuongTrinhKhuyenMai> chuongTrinhKMList = new ArrayList<>();

        // Kiểm tra xem có item nào được chọn không
        if (cboChuongTrinhKM.getSelectedItem() != null) {
            String ten = (String) cboChuongTrinhKM.getSelectedItem();
            String sqlChuongTrinhKM = "SELECT IdKhuyenMai, TenTruongTrinhKM, GiaTriGiam, NgayBatDau, NgayKetThuc FROM ChuongTrinhKhuyenMai";

            // Nếu cần lọc theo tên chương trình khuyến mãi, thêm điều kiện vào câu truy vấn
            if (!ten.isEmpty()) {
                sqlChuongTrinhKM += " WHERE TenTruongTrinhKM = ?";
            }

            try {
                PreparedStatement pst = con.prepareStatement(sqlChuongTrinhKM);

                // Nếu có điều kiện lọc, thiết lập tham số cho PreparedStatement
                if (!ten.isEmpty()) {
                    pst.setString(1, ten);
                }

                ResultSet re = pst.executeQuery();

                while (re.next()) {
                    int id = re.getInt("IdKhuyenMai");
                    String name = re.getString("TenTruongTrinhKM");
                    int price = re.getInt("GiaTriGiam");
                    String ngayBatDau = re.getString("NgayBatDau");
                    String ngayKetThuc = re.getString("NgayKetThuc");

                    // Tạo đối tượng ChuongTrinhKhuyenMai và thêm vào danh sách
                    ChuongTrinhKhuyenMai chuongTrinhKM = new ChuongTrinhKhuyenMai(id, name, price, ngayBatDau, ngayKetThuc);
                    chuongTrinhKMList.add(chuongTrinhKM);
                }
                re.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Lỗi: " + e);
            }
        }

        return chuongTrinhKMList;
    }

   private void addChuongTrinhKMToTable() {
    giaTriGiam = 0.0;
    List<ChuongTrinhKhuyenMai> chuongTrinhKMList = getChuongTrinhKMList();
    DefaultTableModel tableModel = (DefaultTableModel) TblBangCTKM.getModel();
    tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

    // Thêm từng đối tượng ChuongTrinhKhuyenMai vào bảng
    for (ChuongTrinhKhuyenMai chuongTrinhKM : chuongTrinhKMList) {
        tableModel.addRow(new Object[]{
            chuongTrinhKM.getIdKhuyenMai(),
            chuongTrinhKM.getTenTruongTrinhKM(),
            chuongTrinhKM.getGiaTriKM(),
            chuongTrinhKM.getNgayBatDau(),
            chuongTrinhKM.getNgayKetThuc()
        });
        giaTriGiam += chuongTrinhKM.getGiaTriKM(); // Cập nhật tổng giá trị giảm
    }

    // Hiển thị IdKhuyenMai của hàng cuối cùng lên txtID_CTKM_DD
    if (!chuongTrinhKMList.isEmpty()) {
        int idKhuyenMaiCuoi = chuongTrinhKMList.get(chuongTrinhKMList.size() - 1).getIdKhuyenMai();
        txtID_CTKM_DD.setText(Integer.toString(idKhuyenMaiCuoi));
    }
}



    public void XoaTatCa() {
        txtIDKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtID_CTKM_DD.setText("");
        txtNgayDat.setDate(null);
        txtTongDV.setText("");
        txtTongTien.setText("");
        txtTiemKiemKH.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblBangCTKM = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cboChuongTrinhKM = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        txtIDKhachHang = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtTiemKiemKH = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtTongDV = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtID_CTKM_DD = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        txtTongTien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cboGiaThue = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cboTinhTrangThanhToan = new javax.swing.JComboBox<>();
        cboPhuongThucThanhToan = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cboSoNguoi = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNgayDat = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblBangDichVu2 = new javax.swing.JTable();
        btnXoaDichVu2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cboDichVu = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel2.setText("Thanh Toán");

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        TblBangCTKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_KM", "Tên CTKM", "Giá trị giảm", "ngày bắt đầu", "ngày kết thúc"
            }
        ));
        jScrollPane2.setViewportView(TblBangCTKM);

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        jButton2.setText("Xóa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(308, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Tên CTKM");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cboChuongTrinhKM, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboChuongTrinhKM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel50.setText("IDKH");

        txtIDKhachHang.setEditable(false);
        txtIDKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDKhachHangActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setText("Tìm kiếm số điện thoại khách hàng");

        txtTiemKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiemKiemKHActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        jButton1.setText("Seach");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtTongDV.setEditable(false);
        txtTongDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongDVActionPerformed(evt);
            }
        });

        jLabel52.setText("Tổng Dịch Vụ");

        jLabel55.setText("Tên Khách Hàng");

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });

        txtID_CTKM_DD.setEditable(false);

        jLabel53.setText("ID_KM");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addGap(92, 92, 92)
                                .addComponent(txtIDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel53))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTongDV, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                    .addComponent(txtID_CTKM_DD)))))
                    .addComponent(jLabel51)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(txtTiemKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTiemKiemKH))
                .addGap(31, 31, 31)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(30, 30, 30)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 27, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(27, 27, 27)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID_CTKM_DD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(44, 44, 44))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Thành tiền");

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ThanhToanicon.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rewind.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(67, 67, 67)
                        .addComponent(txtTongTien))
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(btnThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        cboGiaThue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "300000", "400000" }));

        jLabel9.setText("Giá thuê");

        jLabel19.setText("Tình trạng thanh toán");

        cboTinhTrangThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã Thanh Toán", "Chưa Thanh Toán" }));

        cboPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt" }));

        jLabel15.setText("Phương thức thanh toán");

        cboSoNguoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "11" }));

        jLabel54.setText("Số người");

        jLabel8.setText("Ngày thanh toán");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(95, 95, 95)
                                .addComponent(cboGiaThue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel19))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboPhuongThucThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTinhTrangThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboSoNguoi, 0, 174, Short.MAX_VALUE)
                            .addComponent(txtNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(cboSoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTinhTrangThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboGiaThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        TblBangDichVu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_DichVu", "Tên DV", "Phi DV", "Trang Thai"
            }
        ));
        jScrollPane3.setViewportView(TblBangDichVu2);

        btnXoaDichVu2.setBackground(new java.awt.Color(51, 153, 255));
        btnXoaDichVu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnXoaDichVu2.setText("Xóa");
        btnXoaDichVu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDichVu2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXoaDichVu2)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaDichVu2)
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cboDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDichVuActionPerformed(evt);
            }
        });

        jLabel12.setText("Tên dịch vụ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cboDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(464, 464, 464)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 424, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int selectedRow = TblBangCTKM.getSelectedRow();

if (selectedRow != -1) {
    // Lấy model của bảng
    DefaultTableModel tableModel = (DefaultTableModel) TblBangCTKM.getModel();

    // Lấy giá trị giảm của hàng được chọn
    Object value = tableModel.getValueAt(selectedRow, 2); // Cột 2 chứa giá trị giảm
    double giaTriGiamCuaHang = 0.0;

    if (value instanceof Integer) {
        giaTriGiamCuaHang = ((Integer) value).doubleValue();
    } else if (value instanceof Double) {
        giaTriGiamCuaHang = (Double) value;
    }

    // Trừ giá trị giảm của hàng được chọn ra khỏi tổng giaTriGiam
    giaTriGiam -= giaTriGiamCuaHang;

    // Xóa hàng đang được chọn khỏi bảng
    tableModel.removeRow(selectedRow);
    txtID_CTKM_DD.setText("");
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtIDKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKhachHangActionPerformed

    private void txtTiemKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiemKiemKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiemKiemKHActionPerformed

    private void txtTongDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongDVActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        int kq = ThemHoaDon();
        if (kq > 0) {
            JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
        } else if (kq == 0) {
            JOptionPane.showMessageDialog(null, "Thanh toán thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        XoaTatCa();
        DefaultTableModel tableModel = (DefaultTableModel) TblBangCTKM.getModel();
        tableModel.setRowCount(0);
        DefaultTableModel tableModel2 = (DefaultTableModel) TblBangDichVu2.getModel();
        tableModel2.setRowCount(0);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        // TODO add your handling code here:
        String keyword = txtTiemKiemKH.getText().trim();

// Kiểm tra nếu ô tìm kiếm trống
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ô tìm kiếm không được để trống.");
            return; // Thoát khỏi phương thức nếu ô tìm kiếm trống
        }

        ArrayList<KhachHang> ketQuaTimKiem = kh.timKiemDatSan(keyword);

        if (ketQuaTimKiem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.");
        } else {
            // Load lại bảng hiển thị kết quả tìm kiếm
            try (Connection conn = DBConneet.getConnection(); PreparedStatement ps = conn.prepareStatement("SELECT IdKhachHang, HoTenKH FROM KhachHang WHERE SDT LIKE ?")) {

                ps.setString(1, "%" + keyword + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int customerId = rs.getInt("IdKhachHang");
                        String name = rs.getString("HoTenKH");

                        txtIDKhachHang.setText(String.valueOf(customerId));
                        txtTenKhachHang.setText(name);
                        // Do something with the customer ID and phone number
                        System.out.println("Customer ID: " + customerId + ", Name: " + name);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXoaDichVu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDichVu2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = TblBangDichVu2.getSelectedRow();

        if (selectedRow != -1) {
            // Lấy phí dịch vụ của hàng đang được chọn
            DefaultTableModel tableModel = (DefaultTableModel) TblBangDichVu2.getModel();
            double phiDV = (double) tableModel.getValueAt(selectedRow, 2);

            // Trừ phí dịch vụ của hàng đã xóa khỏi tổng phí dịch vụ
            tongPhiDVCu -= phiDV;

            // Xóa hàng đang được chọn khỏi bảng
            tableModel.removeRow(selectedRow);

            // Cập nhật giá trị của txtTongDV với tổng phí dịch vụ mới
            txtTongDV.setText(Double.toString(tongPhiDVCu));
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaDichVu2ActionPerformed

    private void cboDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDichVuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblBangCTKM;
    private javax.swing.JTable TblBangDichVu2;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaDichVu2;
    private javax.swing.JComboBox<String> cboChuongTrinhKM;
    private javax.swing.JComboBox<String> cboDichVu;
    private javax.swing.JComboBox<String> cboGiaThue;
    private javax.swing.JComboBox<String> cboPhuongThucThanhToan;
    private javax.swing.JComboBox<String> cboSoNguoi;
    private javax.swing.JComboBox<String> cboTinhTrangThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtIDKhachHang;
    private javax.swing.JTextField txtID_CTKM_DD;
    private com.toedter.calendar.JDateChooser txtNgayDat;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTiemKiemKH;
    private javax.swing.JTextField txtTongDV;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
public int ThemHoaDon() {
        String sql = "INSERT INTO HoaDon (IdKhachHang, IdKhuyenMai, SoLuongNguoi, GiaThue, NgayDat, PhuongThucThanhToan, TinhTrangThanhToan, TongTien) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String idKhachHang = txtIDKhachHang.getText().trim();
        String idKhuyenMai = txtID_CTKM_DD.getText().trim();

        if (idKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tìm kiếm ID khách hàng bằng số điện thoại");
            return 0;
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Thiết lập các giá trị cho lệnh INSERT
            ps.setString(1, idKhachHang);

            // Xử lý trường hợp IdKhuyenMai có thể là null
            if (idKhuyenMai.isEmpty()) {
                ps.setNull(2, java.sql.Types.INTEGER); // Hoặc sử dụng java.sql.Types.VARCHAR nếu IdKhuyenMai là kiểu văn bản
            } else {
                ps.setString(2, idKhuyenMai);
            }

            ps.setString(3, (String) cboSoNguoi.getSelectedItem());
            ps.setString(4, (String) cboGiaThue.getSelectedItem());

            java.util.Date date = txtNgayDat.getDate();
            if (date == null) {
                JOptionPane.showMessageDialog(null, "Ngày đặt không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return -1; // Trả về -1 nếu ngày đặt trống
            }

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            ps.setDate(5, sqlDate);

            ps.setString(6, (String) cboPhuongThucThanhToan.getSelectedItem());
            ps.setString(7, (String) cboTinhTrangThanhToan.getSelectedItem());

            // Tính tổng tiền
            double giaThue = Double.parseDouble((String) cboGiaThue.getSelectedItem());
            double tongTien = tongPhiDVCu + giaThue - giaTriGiam;
            ps.setDouble(8, tongTien);
            txtTongTien.setText(Double.toString(tongTien));

            // Thực thi câu lệnh SQL
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idHoaDon = generatedKeys.getInt(1);
                    // Sử dụng idHoaDon để thêm vào bảng DichVuChiTiet
                    ThemDichVuChiTiet(idHoaDon);
                }
            }

            return 1;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e);
            return 0; // Trả về 0 nếu có lỗi xảy ra
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e);
            JOptionPane.showMessageDialog(null, "Lỗi định dạng số: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return 0; // Trả về 0 nếu có lỗi định dạng số
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e);
            return 0; // Trả về 0 nếu có lỗi khác xảy ra
        }
    }

    public int ThemDichVuChiTiet(int idHoaDon) {
        String sqlCheck = "SELECT COUNT(*) FROM HoaDonChiTiet WHERE IdHoaDon = ? AND IdDichVu = ?";
        String sqlInsert = "INSERT INTO HoaDonChiTiet (IdHoaDon, IdDichVu) VALUES (?, ?)";

        try {
            PreparedStatement psCheck = con.prepareStatement(sqlCheck);
            PreparedStatement psInsert = con.prepareStatement(sqlInsert);

            for (int i = 0; i < TblBangDichVu2.getRowCount(); i++) {
                int idDichVu = (int) TblBangDichVu2.getValueAt(i, 0);
                psCheck.setInt(1, idHoaDon);
                psCheck.setInt(2, idDichVu);
                ResultSet rs = psCheck.executeQuery();
                rs.next();
                int count = rs.getInt(1); // Get the count of existing records

                if (count == 0) { // If no duplicate record exists
                    // Insert new record
                    psInsert.setInt(1, idHoaDon);
                    psInsert.setInt(2, idDichVu);
                    psInsert.executeUpdate();
                } else {
                    // Handle duplicate case (e.g., display a message)
                    System.out.println("Duplicate record detected for IdHoaDon: " + idHoaDon + " and IdDichVu: " + idDichVu);
                }
                rs.close();
            }
            return 1; // Return 1 if successful (at least one record inserted)
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
            return 0;
        }
    }
}
