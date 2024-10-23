package Model;

import java.util.Vector;

public class DatLichSanBong {

     // Sử dụng int cho mã đơn
    private int IdKhachHang;
    private String tenSan;
    private String NgayDat;
    private String CaDat;

    public DatLichSanBong() {
    }

    public DatLichSanBong(int IdKhachHang, String tenSan, String NgayDat, String CaDat) {
        this.IdKhachHang = IdKhachHang;
        this.tenSan = tenSan;
        this.NgayDat = NgayDat;
        this.CaDat = CaDat;
    }

    public int getIdKhachHang() {
        return IdKhachHang;
    }

    public void setIdKhachHang(int IdKhachHang) {
        this.IdKhachHang = IdKhachHang;
    }

    public String gettenSan() {
        return tenSan;
    }

    public void settenSan(String IdSanBong) {
        this.tenSan = tenSan;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String NgayDat) {
        this.NgayDat = NgayDat;
    }

    public String getCaDat() {
        return CaDat;
    }

    public void setCaDat(String CaDat) {
        this.CaDat = CaDat;
    }

    
    
}
