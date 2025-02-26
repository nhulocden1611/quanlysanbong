/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duansanbong;

import Model.DatLichSanBong;
import Model.KhachHang;
import Model.LichSuDat;
import Model.QuanLySanBong;
import Repositories.KhachHangRepo;
import Repositories.QuanLySanRepo;
import Repositories.LichDatRepo;
import Utils.DBConneet;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nguye
 */
public class view_lichdatsan extends javax.swing.JPanel {

    private Connection con;
    private LichDatRepo ld = new LichDatRepo();
    private DefaultTableModel mol = new DefaultTableModel();
    private KhachHangRepo kh = new KhachHangRepo();
    private QuanLySanRepo sb = new QuanLySanRepo();
    private boolean isUpdatingComboBox = false;
    
    public view_lichdatsan() {
        initComponents();
        con = DBConneet.getConnection();// Kết nối cơ sở dữ liệu
        LoadDataToComboBoxSoSan(); 
        this.loadTableLD(ld.getLD());
        Timer timer = new Timer(5000, e -> refreshTable()); // 5 giây
        timer.start();
        refreshTable();
        
        Timer timerComboBox = new Timer(1000, e -> refreshComboBox()); // 1 giây
        timerComboBox.start();
        refreshComboBox();
    }

    @SuppressWarnings("unchecked")

    private void refreshTable() {
        ArrayList<LichSuDat> listLD = ld.getLD();
        loadTableLD(listLD);
    }
    private void refreshComboBox() {
        LoadDataToComboBoxSoSan();
    }
    private DatLichSanBong readFormDV() {
        // Kiểm tra xem các trường có bị trống không
        if (txtIDKhachHang.getText().isEmpty() || cboCaDat.getSelectedItem() == null || txtNgayDat.getDate() == null) {
            return null; // Trả về null nếu không nhập đầy đủ thông tin
        }

        try {
            int idkh = Integer.parseInt(txtIDKhachHang.getText().trim());
            String teSan = cboSoSan.getSelectedItem().toString();
            String caDat = cboCaDat.getSelectedItem().toString().trim();

            // Định dạng lại ngày từ JDateChooser
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayDat = dateFormat.format(txtNgayDat.getDate());

            DatLichSanBong dlsb = new DatLichSanBong(idkh, teSan, ngayDat, caDat);
            return dlsb;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Khách Hàng và ID Sân Bóng phải là số nguyên", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return null; // Trả về null nếu có lỗi định dạng
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null; // Trả về null nếu có lỗi khác
        }
    }
public void LoadDataToComboBoxSoSan() {
    try {
        Statement st = con.createStatement();
        String sqlSoSan = "SELECT TenSan FROM QuanLySanBong;";
        ResultSet re = st.executeQuery(sqlSoSan);

        // Xóa các mục cũ trước khi thêm các mục mới
        cboSoSan.removeAllItems();

        while (re.next()) {
            String name = re.getString("TenSan");
            cboSoSan.addItem(name); // Thêm mục mới vào ComboBox
        }

        re.close();
        st.close();
    } catch (Exception e) {
        e.printStackTrace(); // In lỗi chi tiết để dễ kiểm tra
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel43 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblLD = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSeach = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        txtIDKhachHang = new javax.swing.JTextField();
        txtTimKiemSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboCaDat = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNgayDat = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtDatSan = new javax.swing.JButton();
        btnSeach1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cboSoSan = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();

        jPanel43.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblLD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn", "Tên khách hàng", "SÐT", "Tên sân", "Ca Đặt ", "Ngày Đặt"
            }
        ));
        tblLD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLDMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblLD);

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setText("Lịch đặt sân");

        btnSeach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btnSeach.setText("Seach");
        btnSeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSeach))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel50.setText("ID KH");

        txtIDKhachHang.setEditable(false);
        txtIDKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDKhachHangActionPerformed(evt);
            }
        });

        txtTimKiemSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSDTActionPerformed(evt);
            }
        });

        jLabel7.setText("Ca đặt");

        cboCaDat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 1", "Ca 2", "Ca 3", "Ca 4", "Ca 5", "Ca 6" }));

        jLabel8.setText("Ngày đặt");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel3.setText("Ðặt sân");

        jLabel52.setText("Tên KH");

        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });

        txtDatSan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        txtDatSan.setText("Đặt Sân");
        txtDatSan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatSanActionPerformed(evt);
            }
        });

        btnSeach1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btnSeach1.setText("Seach");
        btnSeach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeach1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tìm số điện thoại khách hàng");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rewind.png"))); // NOI18N
        jButton1.setText("Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel34.setText("Tên Sân");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboSoSan, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addComponent(jLabel50)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                        .addComponent(txtTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSeach1)))
                                .addGroup(jPanel29Layout.createSequentialGroup()
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                            .addComponent(jLabel34)
                                            .addGap(11, 11, 11)))
                                    .addGap(60, 60, 60)
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cboCaDat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                        .addComponent(txtTenKhachHang)))
                                .addGroup(jPanel29Layout.createSequentialGroup()
                                    .addComponent(txtDatSan)
                                    .addGap(63, 63, 63)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel52)))
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(jLabel1))
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addGap(124, 124, 124)
                            .addComponent(jLabel3))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeach1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSoSan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(16, 16, 16)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCaDat, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDatSan)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(182, 182, 182))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(742, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(814, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSDTActionPerformed

    private void txtIDKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKhachHangActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void btnSeach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeach1ActionPerformed

       
        String keyword = txtTimKiemSDT.getText().trim();

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
    try (Connection conn = DBConneet.getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT IdKhachHang, HoTenKH FROM KhachHang WHERE SDT LIKE ?")) {

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
    }//GEN-LAST:event_btnSeach1ActionPerformed

    private void btnSeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeachActionPerformed
        String keyword = txtTimKiem.getText().trim();
        ArrayList<LichSuDat> ketQuaTimKiem = ld.timKiem(keyword);

        if (ketQuaTimKiem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.");
        } else {
            // Load lại bảng hiển thị kết quả tìm kiếm
            loadTableLD(ketQuaTimKiem);
        }
    }//GEN-LAST:event_btnSeachActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblLDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLDMouseClicked

    }//GEN-LAST:event_tblLDMouseClicked

    private void txtDatSanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatSanActionPerformed
        // TODO add your handling code here:
        // Gọi phương thức readFormDV để lấy dữ liệu từ form
        DatLichSanBong dl = readFormDV();
        if (dl != null) {
            int result = ld.Them(dl);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Đặt sân thành công");
                // Có thể cập nhật lại bảng dữ liệu hoặc thực hiện các hành động khác
            } else {
                // Không hiển thị thông báo lỗi thêm vì đã có thông báo trong phương thức Them
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_txtDatSanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtTimKiemSDT.setText("");
        txtIDKhachHang.setText("");
        txtTenKhachHang.setText("");
        cboSoSan.setSelectedIndex(0);
        cboCaDat.setSelectedIndex(0);
        txtNgayDat.setDate(null);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeach;
    private javax.swing.JButton btnSeach1;
    private javax.swing.JComboBox<String> cboCaDat;
    private javax.swing.JComboBox<String> cboSoSan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblLD;
    private javax.swing.JButton txtDatSan;
    private javax.swing.JTextField txtIDKhachHang;
    private com.toedter.calendar.JDateChooser txtNgayDat;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemSDT;
    // End of variables declaration//GEN-END:variables

    private void loadTableLD(ArrayList<LichSuDat> list) {
        mol = (DefaultTableModel) tblLD.getModel();
        mol.setRowCount(0);//xoa dl cu trong bang
        //them dong vao bang
        for (LichSuDat x : list) {
            mol.addRow(x.toDatarow());
        }
    }
}
