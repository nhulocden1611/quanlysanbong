package Model;

public class HoaDon {
    private int idHoaDon;

    private String tenKhachHang;
    private String ngayDat;
    private String tenSan;
    private double giaThue;
    private String tenChuongKm;
    private double giaTriGiam;
    private String tinhTrangThanhToan;
    private double tongTien;

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, String tenKhachHang, String ngayDat, String tenSan, double giaThue, String tenChuongKm, double giaTriGiam, String tinhTrangThanhToan, double tongTien) {
        this.idHoaDon = idHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.tenSan = tenSan;
        this.giaThue = giaThue;
        this.tenChuongKm = tenChuongKm;
        this.giaTriGiam = giaTriGiam;
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        this.tongTien = tongTien;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }


    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(double giaThue) {
        this.giaThue = giaThue;
    }

    public String getTenChuongKm() {
        return tenChuongKm;
    }

    public void setTenChuongKm(String tenChuongKm) {
        this.tenChuongKm = tenChuongKm;
    }

    public double getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(double giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public String getTinhTrangThanhToan() {
        return tinhTrangThanhToan;
    }

    public void setTinhTrangThanhToan(String tinhTrangThanhToan) {
        this.tinhTrangThanhToan = tinhTrangThanhToan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
   

public Object[] toDatarow() {
        return new Object[]{
            this.idHoaDon, this.tenKhachHang, this.ngayDat, this.tenSan, this.giaThue, this.tenChuongKm, this.giaTriGiam,this.tinhTrangThanhToan,this.tongTien
        };
    }
    @Override
    public String toString() {
        return "HoaDon{" +
                "idHoaDon=" + idHoaDon +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", ngayDat='" + ngayDat + '\'' +
                ", tenSan='" + tenSan + '\'' +
                ", giaThue='" + giaThue + '\'' +
                ", tenChuongKm='" + tenChuongKm + '\'' +
                ", giaTriGiam='" + giaTriGiam + '\'' +
                ", tinhTrangThanhToan='" + tinhTrangThanhToan + '\'' +
                ", thanhTien=" + tongTien +
                '}';
    }

  
}
