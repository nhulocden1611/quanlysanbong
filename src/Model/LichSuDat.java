/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TranDucHung
 */
public class LichSuDat {
    private int maDon; 
    private String tenKH;
    private String sdtKH;
    private String tenSan;
    private String caDat;
    private String ngayDat;

    public LichSuDat() {
    }

    public LichSuDat(int maDon, String tenKH, String sdtKH, String tenSan, String caDat, String ngayDat) {
        this.maDon = maDon;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.tenSan = tenSan;
        this.caDat = caDat;
        this.ngayDat = ngayDat;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getCaDat() {
        return caDat;
    }

    public void setCaDat(String caDat) {
        this.caDat = caDat;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    
    
    public Object[] toDatarow() {
        return new Object[]{
            this.maDon, this.tenKH, this.sdtKH, this.tenSan, this.caDat, this.ngayDat
        };
    }
}
