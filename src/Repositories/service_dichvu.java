package Repositories;

import Model.DichVu;
import Utils.DBConneet;
import java.sql.*;
import java.util.ArrayList;

public class service_dichvu {
    private Connection getConnection() throws SQLException {
        return DBConneet.getConnection();
    }

    public ArrayList<DichVu> getDV() {
        String sql = "SELECT IdDichVu, TenDV, PhiDV, TrangThai FROM DichVu";
        ArrayList<DichVu> listdv = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pr = con.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {
             
            while (rs.next()) {
                int iddv = rs.getInt("IdDichVu");
                String ten = rs.getString("TenDV");
                double phidv = rs.getDouble("PhiDV");
                String trangthai = rs.getString("TrangThai");
                DichVu dichVu = new DichVu(iddv, ten, phidv, trangthai);
                listdv.add(dichVu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listdv;
    }
    
    public int them(DichVu dv) {
        String sql = "INSERT INTO DichVu (TenDV, PhiDV, TrangThai) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement pr = con.prepareStatement(sql)) {
             
            pr.setString(1, dv.getTenDV());
            pr.setDouble(2, dv.getPhiDV());
            pr.setString(3, dv.getTrangThai());
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(int idDichVu, DichVu dv) {
        String sql = "UPDATE DichVu SET TenDV = ?, PhiDV = ?, TrangThai = ? WHERE IdDichVu = ?";
        try (Connection con = getConnection();
             PreparedStatement pr = con.prepareStatement(sql)) {
             
            pr.setString(1, dv.getTenDV());
            pr.setDouble(2, dv.getPhiDV());
            pr.setString(3, dv.getTrangThai());
            pr.setInt(4, idDichVu); // Sử dụng int cho tham số ID dịch vụ
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoa(int idDichVu) {
        String deleteFromHoaDonChiTiet = "DELETE FROM HoaDonChiTiet WHERE IdDichVu = ?";
        String deleteFromDichVu = "DELETE FROM DichVu WHERE IdDichVu = ?";
        
        try (Connection con = getConnection()) {
            con.setAutoCommit(false); // Bắt đầu transaction
            // Xóa các bản ghi trong bảng HoaDonChiTiet tham chiếu đến IdDichVu
            try (PreparedStatement pr = con.prepareStatement(deleteFromHoaDonChiTiet)) {
                pr.setInt(1, idDichVu);
                pr.executeUpdate();
            }

            // Xóa bản ghi trong bảng DichVu
            try (PreparedStatement pr = con.prepareStatement(deleteFromDichVu)) {
                pr.setInt(1, idDichVu);
                int result = pr.executeUpdate();

                con.commit(); // Commit transaction
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            return 0;
        }
    }

    public ArrayList<DichVu> timKiem(String keyword) {
        ArrayList<DichVu> ketQuaTimKiem = new ArrayList<>();
        String sql = "SELECT IdDichVu, TenDV, PhiDV, TrangThai FROM DichVu WHERE LOWER(TenDV) LIKE ? OR LOWER(TrangThai) LIKE ?";
        try (Connection con = getConnection();
             PreparedStatement pr = con.prepareStatement(sql)) {
             
            pr.setString(1, "%" + keyword.toLowerCase() + "%");
            pr.setString(2, "%" + keyword.toLowerCase() + "%");
            
            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    int iddv = rs.getInt("IdDichVu");
                    String ten = rs.getString("TenDV");
                    double phidv = rs.getDouble("PhiDV");
                    String trangthai = rs.getString("TrangThai");
                    DichVu dichVu = new DichVu(iddv, ten, phidv, trangthai);
                    ketQuaTimKiem.add(dichVu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQuaTimKiem;
    }
}
