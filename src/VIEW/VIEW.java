/*VIEW_QUANLY
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import duansanbong.view_datsan;
import duansanbong.view_dichvu;
import duansanbong.View_hoadonn;
import duansanbong.view_khuyenmai;
import duansanbong.view_lichdatsan;
import duansanbong.view_qlnv;
import duansanbong.view_thongke;
import duansanbong.view_thongtinkh;
import duansanbong.view_QuanLySanBong;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nguye
 */
public class VIEW extends javax.swing.JFrame {

  view_qlnv a = new view_qlnv();
    view_thongtinkh b = new view_thongtinkh();
    View_hoadonn c = new View_hoadonn();
    view_datsan d = new view_datsan();
    view_lichdatsan e = new view_lichdatsan();
    view_dichvu f = new view_dichvu();
    view_khuyenmai g = new view_khuyenmai();
    view_thongke h = new view_thongke();
    view_QuanLySanBong n = new view_QuanLySanBong();
    public VIEW() {
        initComponents();
           
        
        // Thêm các view vào main panel
        main.setLayout(new java.awt.CardLayout()); // Sử dụng CardLayout để quản lý các panel
        main.add(a, "view_qlnv");
        main.add(b, "view_thongtinkh");
        main.add(c, "view_hoadon");
        main.add(d, "view_datsan");
        main.add(e, "view_lichdatsan");
        main.add(f, "view_dichvu");
        main.add(g, "view_khuyenmai");
        main.add(h, "view_thongke");
        main.add(n, "view_QuanLySanBong");

        // Ẩn tất cả các view ban đầu
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
         setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnqlnv = new javax.swing.JButton();
        btnthongtinkh = new javax.swing.JButton();
        btnHoadon = new javax.swing.JButton();
        btnDatsan = new javax.swing.JButton();
        btnLichdatsan = new javax.swing.JButton();
        btnDichvu = new javax.swing.JButton();
        btnKhuyenmai = new javax.swing.JButton();
        btnThongke = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        main = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 255));
        jLabel2.setText("HỆ THỐNG");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 255, 255));
        jLabel3.setText("QUẢN LÝ");

        btnqlnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NhanVienicon.png"))); // NOI18N
        btnqlnv.setText("       QUẢN LÝ NHÂN VIÊN    ");
        btnqlnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnqlnvActionPerformed(evt);
            }
        });

        btnthongtinkh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/KhachHangicon.png"))); // NOI18N
        btnthongtinkh.setText("THÔNG TIN KHÁCH HÀNG");
        btnthongtinkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongtinkhActionPerformed(evt);
            }
        });

        btnHoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/HoaDonBill-icon.png"))); // NOI18N
        btnHoadon.setText("             HÓA ĐƠN              ");
        btnHoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoadonActionPerformed(evt);
            }
        });

        btnDatsan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ttttt.png"))); // NOI18N
        btnDatsan.setText("       THANH TOÁN            ");
        btnDatsan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatsanActionPerformed(evt);
            }
        });

        btnLichdatsan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Lich_icon.png"))); // NOI18N
        btnLichdatsan.setText("  LỊCH ĐẶT SÂN - ĐẶT SÂN   ");
        btnLichdatsan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichdatsanActionPerformed(evt);
            }
        });

        btnDichvu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/DichVu-icon.png"))); // NOI18N
        btnDichvu.setText("            DỊCH VỤ                 ");
        btnDichvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDichvuActionPerformed(evt);
            }
        });

        btnKhuyenmai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/KhuyenMai-icon.png"))); // NOI18N
        btnKhuyenmai.setText("          KHUYẾN MÃI             ");
        btnKhuyenmai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenmaiActionPerformed(evt);
            }
        });

        btnThongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ThongKe-icon.png"))); // NOI18N
        btnThongke.setText("          THỐNG KÊ              ");
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SanBong.png"))); // NOI18N
        jButton1.setText("        QUẢN LÝ SÂN            ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(67, 67, 67))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnthongtinkh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKhuyenmai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDichvu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLichdatsan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDatsan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnqlnv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnDatsan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLichdatsan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnqlnv, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthongtinkh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(0, 153, 51));
        jPanel2.setPreferredSize(new java.awt.Dimension(1417, 110));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("QUẢN LÝ SÂN BÓNG");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Soccer-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel8)
                .addGap(348, 348, 348)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(508, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1169, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );

        getContentPane().add(main, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnqlnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnqlnvActionPerformed
        a.setVisible(true);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnqlnvActionPerformed

    private void btnthongtinkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongtinkhActionPerformed
        a.setVisible(false);
        b.setVisible(true);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnthongtinkhActionPerformed

    private void btnDatsanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatsanActionPerformed
         a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(true);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnDatsanActionPerformed

    private void btnKhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenmaiActionPerformed
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(true);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnKhuyenmaiActionPerformed

    private void btnHoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoadonActionPerformed
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(true);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnHoadonActionPerformed

    private void btnLichdatsanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichdatsanActionPerformed
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(true);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnLichdatsanActionPerformed

    private void btnDichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDichvuActionPerformed
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(true);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(false);
    }//GEN-LAST:event_btnDichvuActionPerformed

    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
         a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(true);
        n.setVisible(false);
    }//GEN-LAST:event_btnThongkeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        g.setVisible(false);
        h.setVisible(false);
        n.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
 private void showPanel(String panelName) {
        // Lấy layout của main panel và hiển thị panel tương ứng
        CardLayout cl = (CardLayout) (main.getLayout());
        cl.show(main, panelName);
        main.revalidate();
        main.repaint();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatsan;
    private javax.swing.JButton btnDichvu;
    private javax.swing.JButton btnHoadon;
    private javax.swing.JButton btnKhuyenmai;
    private javax.swing.JButton btnLichdatsan;
    private javax.swing.JButton btnThongke;
    private javax.swing.JButton btnqlnv;
    private javax.swing.JButton btnthongtinkh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel main;
    // End of variables declaration//GEN-END:variables
}
