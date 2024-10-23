/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author THANH NAM
 */
public class NhanVien {
    private int IdNhanVien;
    private String HoTenNV;
    private String NamSinh;
    private String CaLam;
    private String Luong;


    public NhanVien() {
    }

    public NhanVien(int IdNhanVien, String HoTenNV, String NamSinh, String CaLam, String Luong) {
        this.IdNhanVien = IdNhanVien;
        this.HoTenNV = HoTenNV;
        this.NamSinh = NamSinh;
        this.CaLam = CaLam;
        this.Luong = Luong;
    }

    public int getIdNhanVien() {
        return IdNhanVien;
    }

    public void setIdNhanVien(int IdNhanVien) {
        this.IdNhanVien = IdNhanVien;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String HoTenNV) {
        this.HoTenNV = HoTenNV;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String NamSinh) {
        this.NamSinh = NamSinh;
    }

    public String getCaLam() {
        return CaLam;
    }

    public void setCaLam(String CaLam) {
        this.CaLam = CaLam;
    }

    public String getLuong() {
        return Luong;
    }

    public void setLuong(String Luong) {
        this.Luong = Luong;
    }

  
    
   
    
     public Object[] toDatarow(){
        return new Object[]{
            this.IdNhanVien, this.HoTenNV, this.NamSinh, this.CaLam,this.Luong
        };
    }
}
