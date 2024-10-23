/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Model.NhanVien;
import Utils.DBConneet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author THANH NAM
 */
public class NhanVienService {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    private void openConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DBConneet.getConnection();
        }
    }

    public ArrayList<NhanVien> getNhanVien() {
        sql = "select IdNhanVien,HoTenNV,NamSinh,CaLam,Luong from NhanVien";
        ArrayList<NhanVien> listnv = new ArrayList<>();

        try {
            openConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {
                int IdNhanVien = rs.getInt("IdNhanVien");
                String HoTenNV = rs.getString("HoTenNV");
                String NamSinh = rs.getString("NamSinh");
                String CaLam = rs.getString("CaLam");
                String Luong = rs.getString("Luong");


                NhanVien nv = new NhanVien(IdNhanVien, HoTenNV, NamSinh, CaLam, Luong);
                listnv.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return listnv;
    }

    public int them(NhanVien nv) {
        sql = "INSERT INTO NhanVien (HoTenNV,NamSinh,CaLam,Luong) VALUES (?, ?, ?, ?)";
        
        try {
            openConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, nv.getHoTenNV());
            pr.setString(2, nv.getNamSinh());
            pr.setString(3, nv.getCaLam());
            pr.setString(4, nv.getLuong());
           return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return 0;
    }

    public void sua(int IdNhanVien, NhanVien nv) {
        sql = "UPDATE NhanVien SET HoTenNV = ?, NamSinh = ?, CaLam = ?,Luong = ? WHERE IdNhanVien = ?";
        try {
            openConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, nv.getHoTenNV());
            pr.setString(2, nv.getNamSinh());
            pr.setString(3, nv.getCaLam());
            pr.setString(4, nv.getLuong());
            pr.setInt(5, IdNhanVien); // ma là tham số để xác định chương trình khuyến mãi cần sửa
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

   public void xoa(String ma) {
    Connection con = null;
    String sqlDeleteNhanVien = "DELETE FROM NhanVien WHERE IdNhanVien = ?";

    try {
        openConnection();  // Mở kết nối
        con = DBConneet.getConnection();  // Lấy kết nối từ phương thức openConnection

        con.setAutoCommit(false);  // Bắt đầu transaction


        // Xóa bản ghi trong bảng NhanVien
        pr = con.prepareStatement(sqlDeleteNhanVien);
        pr.setString(1, ma);
        pr.executeUpdate();

        con.commit();  // Xác nhận transaction
    } catch (SQLException e) {
        if (con != null) {
            try {
                con.rollback();  // Hủy bỏ transaction nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        closeResources();  // Đóng các tài nguyên
        if (pr != null) {
            try {
                pr.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    public ArrayList<NhanVien> timKiem(String keyword) {
        ArrayList<NhanVien> ketQuaTimKiem = new ArrayList<>();
        try {
            openConnection();
            sql = "SELECT IdNhanVien,HoTenNV,NamSinh,CaLam,Luong FROM NhanVien WHERE LOWER(HoTenNV) LIKE ? OR LOWER(Luong) LIKE ?";
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + keyword.toLowerCase() + "%");
            pr.setString(2, "%" + keyword.toLowerCase() + "%");

            rs = pr.executeQuery();

            while (rs.next()) {
                int IdNhanVien = rs.getInt("IdNhanVien");
                String HoTenNV = rs.getString("HoTenNV");
                String NamSinh = rs.getString("NamSinh");
                String CaLam = rs.getString("CaLam");
                String Luong = rs.getString("Luong");


                NhanVien nv = new NhanVien(IdNhanVien, HoTenNV, NamSinh, CaLam, Luong);
                ketQuaTimKiem.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return ketQuaTimKiem;
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (con != null && !con.isClosed()) {
                con.close(); // Đóng kết nối chỉ nếu nó không bị đóng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
