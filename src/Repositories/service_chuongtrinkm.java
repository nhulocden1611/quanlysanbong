package Repositories;

import Model.ChuongTrinhKhuyenMai;
import Utils.DBConneet;
import java.sql.*;
import java.util.ArrayList;

public class service_chuongtrinkm {
    // Khai báo tài nguyên toàn cục
    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public service_chuongtrinkm() {
        // Không mở kết nối ở đây nếu nó sẽ được quản lý trong các phương thức khác
    }

    private void openConnection() throws SQLException {
        con = DBConneet.getConnection();
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pr != null) pr.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ChuongTrinhKhuyenMai> getCTKM() {
        sql = "SELECT IdKhuyenMai, TenTruongTrinhKM, GiaTriGiam, NgayBatDau, NgayKetThuc FROM ChuongTrinhKhuyenMai";
        ArrayList<ChuongTrinhKhuyenMai> listkm = new ArrayList<>();
        
        try {
            openConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("IdKhuyenMai");
                String ten = rs.getString("TenTruongTrinhKM");
                int giaTri = rs.getInt("GiaTriGiam");
                String ngayBatDau = rs.getString("NgayBatDau");
                String ngayKetThuc = rs.getString("NgayKetThuc");
                
                ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(id, ten, giaTri, ngayBatDau, ngayKetThuc);
                listkm.add(ctkm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        
        return listkm;
    }
    
    public void them(ChuongTrinhKhuyenMai km) {
        sql = "INSERT INTO ChuongTrinhKhuyenMai (TenTruongTrinhKM, GiaTriGiam, NgayBatDau, NgayKetThuc) VALUES (?, ?, ?, ?)";
        
        try {
            openConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, km.getTenTruongTrinhKM());
            pr.setInt(2, km.getGiaTriKM());
            pr.setString(3, km.getNgayBatDau());
            pr.setString(4, km.getNgayKetThuc());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    
    public void sua(int ma, ChuongTrinhKhuyenMai km) {
        sql = "UPDATE ChuongTrinhKhuyenMai SET TenTruongTrinhKM = ?, GiaTriGiam = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE IdKhuyenMai = ?";
        
        try {
            openConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, km.getTenTruongTrinhKM());
            pr.setInt(2, km.getGiaTriKM());
            pr.setString(3, km.getNgayBatDau());
            pr.setString(4, km.getNgayKetThuc());
            pr.setInt(5, ma);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    
    public void xoa(int idKhuyenMai) {
        String deleteFromHoaDonChiTiet = "DELETE FROM HoaDonChiTiet WHERE IdHoaDon IN (SELECT IdHoaDon FROM HoaDon WHERE IdKhuyenMai = ?)";
        String deleteFromHoaDon = "DELETE FROM HoaDon WHERE IdKhuyenMai = ?";
        String deleteFromChuongTrinhKhuyenMai = "DELETE FROM ChuongTrinhKhuyenMai WHERE IdKhuyenMai = ?";

        try {
            openConnection();
            con.setAutoCommit(false); // Bắt đầu transaction

            // Xóa các bản ghi trong bảng HoaDonChiTiet tham chiếu đến IdKhuyenMai
            pr = con.prepareStatement(deleteFromHoaDonChiTiet);
            pr.setInt(1, idKhuyenMai);
            pr.executeUpdate();

            // Xóa các bản ghi trong bảng HoaDon tham chiếu đến IdKhuyenMai
            pr = con.prepareStatement(deleteFromHoaDon);
            pr.setInt(1, idKhuyenMai);
            pr.executeUpdate();

            // Xóa bản ghi trong bảng ChuongTrinhKhuyenMai
            pr = con.prepareStatement(deleteFromChuongTrinhKhuyenMai);
            pr.setInt(1, idKhuyenMai);
            pr.executeUpdate();

            con.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback(); // Rollback transaction nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            closeResources();
        }
    }
    
    public ArrayList<ChuongTrinhKhuyenMai> timKiem(String keyword) {
        ArrayList<ChuongTrinhKhuyenMai> ketQuaTimKiem = new ArrayList<>();
        sql = "SELECT IdKhuyenMai, TenTruongTrinhKM, GiaTriGiam, NgayBatDau, NgayKetThuc FROM ChuongTrinhKhuyenMai WHERE LOWER(TenTruongTrinhKM) LIKE ? OR LOWER(CAST(GiaTriGiam AS VARCHAR)) LIKE ?";
        
        try {
            openConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + keyword.toLowerCase() + "%");
            pr.setString(2, "%" + keyword.toLowerCase() + "%");
        
            rs = pr.executeQuery();
        
            while (rs.next()) {
                int id = rs.getInt("IdKhuyenMai");
                String ten = rs.getString("TenTruongTrinhKM");
                int giaTri = rs.getInt("GiaTriGiam");
                String ngayBatDau = rs.getString("NgayBatDau");
                String ngayKetThuc = rs.getString("NgayKetThuc");
                
                ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(id, ten, giaTri, ngayBatDau, ngayKetThuc);
                ketQuaTimKiem.add(ctkm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        
        return ketQuaTimKiem;
    }
}
