/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Model.QuanLySanBong;
import Utils.DBConneet;
import java.sql.*;
import java.util.ArrayList;
import duansanbong.view_lichdatsan;
/**
 *
 * @author TranDucHung
 */
public class QuanLySanRepo {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;
   // private view_lichdatsan ld = new view_lichdatsan();

    public QuanLySanRepo() {
        con = DBConneet.getConnection();
    }

    public ArrayList<QuanLySanBong> getSanBong() {
        sql = "SELECT * FROM QuanLySanBong";
        ArrayList<QuanLySanBong> listSanBong = new ArrayList<>();
        try {
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int idSanBong = rs.getInt("IdSanBong");
                String tenSanBong = rs.getString("TenSan");
                String ttSanBong = rs.getString("TrangThai");
                int SoNguoi = rs.getInt("SoNguoi");

                QuanLySanBong qlSanBong = new QuanLySanBong(idSanBong, tenSanBong, ttSanBong, SoNguoi);
                listSanBong.add(qlSanBong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pr != null) {
                    pr.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listSanBong;
    }

    public int Them(QuanLySanBong sb) {
        sql = "INSERT INTO QuanLySanBong (TenSan, TrangThai, SoNguoi)\n"
                + "VALUES (?,?,?)";
        try {
            con = DBConneet.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, sb.getTenSan());
            pr.setString(2, sb.getTranhThai());
            pr.setInt(3, sb.getSoNguoi());
            return pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoa(String idSanBong) {
        
        String sqlDeleteSanBong = "DELETE FROM QuanLySanBong WHERE IdSanBong = ?";
        try {
            con = DBConneet.getConnection();
            con.setAutoCommit(false); // Bắt đầu transaction

            // Xóa trong bảng DatLichSanBong
            

            pr = con.prepareStatement(sqlDeleteSanBong);
            pr.setString(1, idSanBong);
            int rowsAffected = pr.executeUpdate();

            con.commit(); // Xác nhận transaction
            return rowsAffected;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return 0;
        } finally {
            // Đóng tài nguyên
            try {
                if (pr != null) {
                    pr.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int sua(int ma, QuanLySanBong sb) {
        String sql = "UPDATE QuanLySanBong SET TenSan = ?, TrangThai = ?, SoNguoi = ? WHERE IdSanBong = ?";
        try {
            con = DBConneet.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, sb.getTenSan());
            pr.setString(2, sb.getTranhThai());
            pr.setInt(3, sb.getSoNguoi());
            pr.setInt(4, ma); // ma là tham số để xác định dịch vụ cần sửa

            return pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<QuanLySanBong> timKiem(String keyword) {
        ArrayList<QuanLySanBong> ketQuaTimKiem = new ArrayList<>();
        try {
            con = DBConneet.getConnection();
            sql = "SELECT IdSanBong, TenSan, TrangThai, SoNguoi FROM QuanLySanBong WHERE LOWER(IdSanBong) LIKE ? OR LOWER(SoNguoi) LIKE ?";
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + keyword.toLowerCase() + "%");
            pr.setString(2, "%" + keyword.toLowerCase() + "%");

            rs = pr.executeQuery();

            while (rs.next()) {
                int iddv = rs.getInt("IdSanBong");
                String ten = rs.getString("TenSan");
                String tthai = rs.getString("TrangThai");
                int SoNguoi = rs.getInt("SoNguoi");
                QuanLySanBong qlsb = new QuanLySanBong(iddv, ten, tthai, SoNguoi);
                ketQuaTimKiem.add(qlsb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pr != null) {
                    pr.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ketQuaTimKiem;
    }
//    public ArrayList<QuanLySanBong> timKiemSanBong(String keyword) {
//        ArrayList<QuanLySanBong> ketQuaTimKiem = new ArrayList<>();
//        try {
//            con = DBConneet.getConnection();
//            sql = "SELECT IdSanBong, TenSan, TrangThai, SDT_KH FROM QuanLySanBong WHERE LOWER(SDT_KH) LIKE ?";
//            pr = con.prepareStatement(sql);
//            pr.setString(1, "%" + keyword.toLowerCase() + "%");
//
//            rs = pr.executeQuery();
//
//            while (rs.next()) {
//                int iddv = rs.getInt("IdSanBong");
//                String ten = rs.getString("TenSan");
//                String tthai = rs.getString("TrangThai");
//                String SdtKH = rs.getString("SDT_KH");
//                QuanLySanBong qlsb = new QuanLySanBong(iddv, ten, tthai, SdtKH);
//                ketQuaTimKiem.add(qlsb);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pr != null) {
//                    pr.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return ketQuaTimKiem;
//    }
}
