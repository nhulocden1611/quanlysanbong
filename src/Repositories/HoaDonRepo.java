package Repositories;

import Model.HoaDon;
import Utils.DBConneet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonRepo {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public HoaDonRepo() {
        con = DBConneet.getConnection();
    }

    public List<HoaDon> getBangHoaDon() {
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        String query = "SELECT \n"
                + "    HD.IdHoaDon, \n"
                + "    KH.HoTenKH, \n"
                + "    HD.NgayDat, \n"
                + "    DL.TenSan, \n"
                + "    HD.GiaThue, \n"
                + "    KM.TenTruongTrinhKM, \n"
                + "    KM.GiaTriGiam, \n"
                + "    HD.TinhTrangThanhToan, \n"
                + "    HD.TongTien\n"
                + "FROM HoaDon HD\n"
                + "INNER JOIN KhachHang KH ON HD.IdKhachHang = KH.IdKhachHang\n"
                + "INNER JOIN DatLichSanBong DL ON HD.IdKhachHang = DL.IdKhachHang\n"
                + "LEFT JOIN ChuongTrinhKhuyenMai KM ON HD.IdKhuyenMai = KM.IdKhuyenMai\n"
                + "ORDER BY HD.IdHoaDon;;";

        try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idHoaDon = rs.getInt("IdHoaDon");
                String hoTenKhachHang = rs.getString("HoTenKH");
                String ngayDat = rs.getString("NgayDat");
                String tenSan = rs.getString("TenSan");
                double giaThue = rs.getDouble("GiaThue");
                String tenTruongTrinhKM = rs.getString("TenTruongTrinhKM");
                int giaTriGiam = rs.getInt("GiaTriGiam");
                String tinhTrangThanhToan = rs.getString("TinhTrangThanhToan");
                double tongTien = rs.getDouble("TongTien");

                HoaDon hoaDon = new HoaDon(idHoaDon, hoTenKhachHang, ngayDat, tenSan, giaThue, tenTruongTrinhKM, giaTriGiam, tinhTrangThanhToan, tongTien);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }

    public ArrayList<HoaDon> timKiem(String keyword) {
        ArrayList<HoaDon> ketQuaTimKiem = new ArrayList<>();
        String sql = "SELECT HD.IdHoaDon, KH.HoTenKH, HD.NgayDat, DL.TenSan, HD.GiaThue, "
                + "KM.TenTruongTrinhKM, KM.GiaTriGiam, HD.TinhTrangThanhToan, HD.TongTien "
                + "FROM HoaDon HD "
                + "INNER JOIN KhachHang KH ON HD.IdKhachHang = KH.IdKhachHang "
                + "INNER JOIN DatLichSanBong DL ON HD.IdKhachHang = DL.IdKhachHang "
                + "LEFT JOIN ChuongTrinhKhuyenMai KM ON HD.IdKhuyenMai = KM.IdKhuyenMai "
                + "WHERE "
                + "LOWER(CAST(HD.NgayDat AS VARCHAR)) LIKE ? OR LOWER(CAST(KH.HoTenKH AS VARCHAR)) LIKE ?"; // Điều chỉnh để lọc theo ngày

        try (Connection con = DBConneet.getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setString(1, "%" + keyword.toLowerCase() + "%");
            pr.setString(2, "%" + keyword.toLowerCase() + "%");

            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    // Khởi tạo đối tượng HoaDon từ kết quả truy vấn
                    int idHoaDon = rs.getInt("IdHoaDon");
                    String hoTenKH = rs.getString("HoTenKH");
                    String ngayDat = rs.getString("NgayDat");
                    String tenSan = rs.getString("TenSan");
                    double giaThue = rs.getDouble("GiaThue");
                    String tenTruongTrinhKM = rs.getString("TenTruongTrinhKM");
                    double giaTriGiam = rs.getDouble("GiaTriGiam");
                    String tinhTrangThanhToan = rs.getString("TinhTrangThanhToan");
                    double tongTien = rs.getDouble("TongTien");

                    // Tạo đối tượng HoaDon và thêm vào danh sách
                    HoaDon hoaDon = new HoaDon(idHoaDon, hoTenKH, ngayDat, tenSan, giaThue, tenTruongTrinhKM, giaTriGiam, tinhTrangThanhToan, tongTien);
                    ketQuaTimKiem.add(hoaDon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQuaTimKiem;
    }

}
