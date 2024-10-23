/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TranDucHung
 */
public class KhachHang {
    private int idKhachHang;
    private String tenKhachHang;
    private String SDT;
    private String diaChiKhachHang;
    private String CCCD;
    

    public KhachHang() {
    }

    public KhachHang(int idKhachHang, String tenKhachHang, String SDT, String diaChiKhachHang, String CCCD) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.SDT = SDT;
        this.diaChiKhachHang = diaChiKhachHang;
        this.CCCD = CCCD;
        
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChiKhachHang() {
        return diaChiKhachHang;
    }

    public void setDiaChiKhachHang(String diaChiKhachHang) {
        this.diaChiKhachHang = diaChiKhachHang;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }


   public Object[] toDatarow(){
        return new Object[]{
            this.idKhachHang, this.tenKhachHang, this.SDT, this.diaChiKhachHang,this.CCCD
        };
    }
    
}
