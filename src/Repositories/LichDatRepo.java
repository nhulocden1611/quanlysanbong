package Repositories;
import Model.LichSuDat;
import Model.DatLichSanBong;
import Utils.DBConneet;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Repository class for managing "DatLichSanBong" records.
 */
public class LichDatRepo {

    private Connection getConnection() throws SQLException {
        return DBConneet.getConnection();
    }


    public ArrayList<LichSuDat> getLD() {
        String sql = "SELECT \n"
                + "    DatLichSanBong.IdDonDat,\n"
                + "    KhachHang.HoTenKH,\n"
                + "    KhachHang.SDT,\n"
                + "    DatLichSanBong.TenSan,\n"
                + "    DatLichSanBong.CaDat,\n"
                + "    DatLichSanBong.NgayDat\n"
                + "FROM \n"
                + "    DatLichSanBong\n"
                + "INNER JOIN \n"
                + "    KhachHang ON DatLichSanBong.IdKhachHang = KhachHang.IdKhachHang\n";

        ArrayList<LichSuDat> listLD = new ArrayList<>();
        try ( Connection con = getConnection()) {
            PreparedStatement pr = con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int idDL = rs.getInt("IdDonDat");
                String tenKH = rs.getString("HoTenKH");
                String sdtKH = rs.getString("SDT");
                String tenSan = rs.getString("TenSan");
                String caDat = rs.getString("CaDat");
                String ngayDat = rs.getString("NgayDat");

                LichSuDat lichSuDat = new LichSuDat(idDL, tenKH, sdtKH, tenSan, caDat, ngayDat);
                listLD.add(lichSuDat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listLD;
    }

public ArrayList<LichSuDat> timKiem(String keyword) {
    ArrayList<LichSuDat> ketQuaTimKiem = new ArrayList<>();
    String sql = "SELECT \n"
            + "    DatLichSanBong.IdDonDat,\n" // Thay đổi QuanLySanBong.IdDonDat thành DatLichSanBong.IdDonDat
            + "    KhachHang.HoTenKH,\n"
            + "    KhachHang.SDT,\n"
            + "    DatLichSanBong.TenSan,\n"
            + "    DatLichSanBong.CaDat,\n"
            + "    DatLichSanBong.NgayDat\n"
            + "FROM \n"
            + "    DatLichSanBong\n"
            + "INNER JOIN \n"
            + "    KhachHang ON DatLichSanBong.IdKhachHang = KhachHang.IdKhachHang\n"
            + "WHERE \n"
            + "    LOWER(CAST(DatLichSanBong.NgayDat AS VARCHAR)) LIKE ? OR LOWER(CAST(KhachHang.SDT AS VARCHAR)) LIKE ?"; // Chuyển đổi NgayDat và SDT thành chuỗi để sử dụng với LIKE

    try (Connection con = getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {
    pr.setString(1, "%" + keyword.toLowerCase() + "%");
        pr.setString(2, "%" + keyword.toLowerCase() + "%");

        try (ResultSet rs = pr.executeQuery()) {
            while (rs.next()) {
                int idDL = rs.getInt("IdDonDat"); // Đảm bảo IdDonDat tồn tại trong bảng DatLichSanBong
                String tenKH = rs.getString("HoTenKH");
                String sdtKH = rs.getString("SDT");
                String tenSan = rs.getString("TenSan");
                String caDat = rs.getString("CaDat");
                String ngayDat = rs.getString("NgayDat");
                LichSuDat lichSuDat = new LichSuDat(idDL, tenKH, sdtKH, tenSan, caDat, ngayDat);
                ketQuaTimKiem.add(lichSuDat);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ketQuaTimKiem;
}
    
    
    
    
    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public LichDatRepo() {
        con = DBConneet.getConnection();
    }
    
//    public int Them(DatLichSanBong dt) {
//        sql = "INSERT INTO DatLichSanBong (IdKhachHang, TenSan, NgayDat, CaDat)\n"
//                + "VALUES  (?,?,?,?)";
//        try {
//            con = DBConneet.getConnection();
//            pr = con.prepareStatement(sql);
//            pr.setInt(1, dt.getIdKhachHang());
//            pr.setString(2, dt.gettenSan());
//            pr.setString(3, dt.getNgayDat());
//            pr.setString(4, dt.getCaDat());
//            return pr.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
   public int Them(DatLichSanBong dt) {
    // Kiểm tra ca đã được đặt
    if (kiemTraCaTrungLap(dt.gettenSan(), dt.getNgayDat(), dt.getCaDat())) {
        JOptionPane.showMessageDialog(null, "Ca này đã được đặt cho sân này vào ngày này.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return 0;
    }

    sql = "INSERT INTO DatLichSanBong (IdKhachHang, TenSan, NgayDat, CaDat) VALUES (?, ?, ?, ?)";
    try {
        con = DBConneet.getConnection();
        pr = con.prepareStatement(sql);
        pr.setInt(1, dt.getIdKhachHang());
        pr.setString(2, dt.gettenSan());
        pr.setString(3, dt.getNgayDat());
        pr.setString(4, dt.getCaDat());
        return pr.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


    public boolean kiemTraCaTrungLap(String tenSan, String ngayDat, String caDat) {
    String sql = "SELECT COUNT(*) FROM DatLichSanBong WHERE TenSan = ? AND NgayDat = ? AND CaDat = ?";
    try {
        con = DBConneet.getConnection();
        pr = con.prepareStatement(sql);
        pr.setString(1, tenSan);
        pr.setString(2, ngayDat);
        pr.setString(3, caDat);
        rs = pr.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Trả về true nếu ca đã được đặt
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi hoặc ca chưa được đặt
}


}
