/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Utils.DBConneet;
import java.sql.*;
import Model.KhachHang;
import java.util.ArrayList;

/**
 *
 * @author TranDucHung
 */
public class KhachHangRepo {

    private Connection getConnection() throws SQLException {
        return DBConneet.getConnection();
    }

    public ArrayList<KhachHang> getKH() {
        String sql = "SELECT IdKhachHang, HoTenKH, SDT, DiaChiKH, CCCD FROM KhachHang;";
        ArrayList<KhachHang> listkh = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int idKH = rs.getInt("IdKhachHang");
                String tenKH = rs.getString("HoTenKH");
                String sdtKH = rs.getString("SDT");
                String dcKH = rs.getString("DiaChiKH");
                String cccdKH = rs.getString("CCCD");


                KhachHang khachHang = new KhachHang(idKH, tenKH, sdtKH, dcKH, cccdKH);
                listkh.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listkh;
    }

    public int Them(KhachHang kh) {
        String sql = "INSERT INTO KhachHang(HoTenKH,SDT,DiaChiKH,CCCD)VALUES (?,?,?,?)";
        try (Connection con = getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setString(1, kh.getTenKhachHang());
            pr.setString(2, kh.getSDT());
            pr.setString(3, kh.getDiaChiKhachHang());
            pr.setString(4, kh.getCCCD());

            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<KhachHang> timKiem(String keyword) {
        ArrayList<KhachHang> ketQuaTimKiem = new ArrayList<>();
        String sql = "SELECT IdKhachHang, HoTenKH, SDT, DiaChiKH, CCCD FROM KhachHang WHERE LOWER(HoTenKH) LIKE ? OR LOWER(SDT) LIKE ? OR LOWER(CCCD) LIKE ?";
        try (Connection con = getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setString(1, "%" + keyword.toLowerCase() + "%");
            pr.setString(2, "%" + keyword.toLowerCase() + "%");
            pr.setString(3, "%" + keyword.toLowerCase() + "%");

            try (ResultSet rs = pr.executeQuery();) {
                while (rs.next()) {
                    int idKH = rs.getInt("IdKhachHang");
                    String tenKH = rs.getString("HoTenKH");
                    String sdtKH = rs.getString("SDT");
                    String dcKH = rs.getString("DiaChiKH");
                    String cccdKH = rs.getString("CCCD");

                    KhachHang khachHang = new KhachHang(idKH, tenKH, sdtKH, dcKH, cccdKH);
                    ketQuaTimKiem.add(khachHang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQuaTimKiem;
    }

    public int sua(int idKhachHang, KhachHang khachHang) {
        String sql = "UPDATE KhachHang SET HoTenKH = ?, SDT = ?, DiaChiKH  = ?, CCCD = ? WHERE IdKhachHang = ?";
        try (Connection con = getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setString(1, khachHang.getTenKhachHang());
            pr.setString(2, khachHang.getSDT());
            pr.setString(3, khachHang.getDiaChiKhachHang());
            pr.setString(4, khachHang.getCCCD());
            pr.setInt(5, idKhachHang);
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
 public ArrayList<KhachHang> timKiemDatSan(String keyword) {
        ArrayList<KhachHang> ketQuaTimKiem = new ArrayList<>();
        String sql = "SELECT IdKhachHang, HoTenKH, SDT, DiaChiKH, CCCD FROM KhachHang WHERE LOWER(SDT) LIKE ?";
        try (Connection con = getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setString(1, "%" + keyword.toLowerCase() + "%");

            try (ResultSet rs = pr.executeQuery();) {
                while (rs.next()) {
                    int idKH = rs.getInt("IdKhachHang");
                    String tenKH = rs.getString("HoTenKH");
                    String sdtKH = rs.getString("SDT");
                    String dcKH = rs.getString("DiaChiKH");
                    String cccdKH = rs.getString("CCCD");

                    KhachHang khachHang = new KhachHang(idKH, tenKH, sdtKH, dcKH, cccdKH);
                    ketQuaTimKiem.add(khachHang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQuaTimKiem;
    }
   public int xoa(int idKH) {
    String deleteFromHoaDonChiTiet = "DELETE FROM HoaDonChiTiet WHERE IdHoaDon IN (SELECT IdHoaDon FROM HoaDon WHERE IdKhachHang = ?)";
    String deleteFromHoaDon = "DELETE FROM HoaDon WHERE IdKhachHang = ?";
    String deleteFromDatLichSanBong = "DELETE FROM DatLichSanBong WHERE IdKhachHang = ?";
    String deleteFromKhachHang = "DELETE FROM KhachHang WHERE IdKhachHang = ?";

    try (Connection con = getConnection()) {
        con.setAutoCommit(false); // Bắt đầu transaction

        // Xóa các bản ghi trong bảng HoaDonChiTiet trước
        try (PreparedStatement pr = con.prepareStatement(deleteFromHoaDonChiTiet)) {
            pr.setInt(1, idKH);
            pr.executeUpdate();
        }

        // Xóa các bản ghi trong bảng HoaDon
        try (PreparedStatement pr = con.prepareStatement(deleteFromHoaDon)) {
            pr.setInt(1, idKH);
            pr.executeUpdate();
        }

        // Xóa các bản ghi trong bảng DatLichSanBong
        try (PreparedStatement pr = con.prepareStatement(deleteFromDatLichSanBong)) {
            pr.setInt(1, idKH);
            pr.executeUpdate();
        }

        // Xóa bản ghi trong bảng KhachHang
        try (PreparedStatement pr = con.prepareStatement(deleteFromKhachHang)) {
            pr.setInt(1, idKH);
            int result = pr.executeUpdate();

            con.commit(); // Commit transaction
            return result;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}

}
