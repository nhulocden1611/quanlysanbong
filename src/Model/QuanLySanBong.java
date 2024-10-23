/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TranDucHung
 */
public class QuanLySanBong {
    private int idSanBong;
    private String tenSan;
    private String tranhThai;
    private int soNguoi;

    public QuanLySanBong() {
    }

    public QuanLySanBong(int idSanBong, String tenSan, String tranhThai, int soNguoi) {
        this.idSanBong = idSanBong;
        this.tenSan = tenSan;
        this.tranhThai = tranhThai;
        this.soNguoi = soNguoi;
    }

    public int getIdSanBong() {
        return idSanBong;
    }

    public void setIdSanBong(int idSanBong) {
        this.idSanBong = idSanBong;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getTranhThai() {
        return tranhThai;
    }

    public void setTranhThai(String tranhThai) {
        this.tranhThai = tranhThai;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }
    
    
    public Object[] toDatarow(){
        return new Object[]{
            this.idSanBong, this.tenSan, this.tranhThai, this.soNguoi
        };
    }
    
}
