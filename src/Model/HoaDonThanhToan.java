/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TranDucHung
 */
public class HoaDonThanhToan {
    private int idKhachHang;
    private int idKhyuenMai;
    private int soLuongNguoi;
    private double giaThue;
    private String PTThanhToan;
    private String tingTrangTT;
    private String ngayDat;
    private double tongTien;

    public HoaDonThanhToan() {
    }

    public HoaDonThanhToan(int idKhachHang, int idKhyuenMai, int soLuongNguoi, double giaThue, String PTThanhToan, String tingTrangTT, String ngayDat, double tongTien) {
        this.idKhachHang = idKhachHang;
        this.idKhyuenMai = idKhyuenMai;
        this.soLuongNguoi = soLuongNguoi;
        this.giaThue = giaThue;
        this.PTThanhToan = PTThanhToan;
        this.tingTrangTT = tingTrangTT;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdKhyuenMai() {
        return idKhyuenMai;
    }

    public void setIdKhyuenMai(int idKhyuenMai) {
        this.idKhyuenMai = idKhyuenMai;
    }

    public int getSoLuongNguoi() {
        return soLuongNguoi;
    }

    public void setSoLuongNguoi(int soLuongNguoi) {
        this.soLuongNguoi = soLuongNguoi;
    }

    public double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(double giaThue) {
        this.giaThue = giaThue;
    }

    public String getPTThanhToan() {
        return PTThanhToan;
    }

    public void setPTThanhToan(String PTThanhToan) {
        this.PTThanhToan = PTThanhToan;
    }

    public String getTingTrangTT() {
        return tingTrangTT;
    }

    public void setTingTrangTT(String tingTrangTT) {
        this.tingTrangTT = tingTrangTT;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    
    
}
