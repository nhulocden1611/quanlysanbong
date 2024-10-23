/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt để thay đổi giấy phép này
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java để chỉnh sửa mẫu này
 */
package Model;

/**
 *
 * @tác giả TranDucHung
 */
public class DichVu {
    private int idDichVu;
    private String tenDV;
    private double phiDV; // Sử dụng double để đại diện cho kiểu MONEY
    private String trangThai;

    public DichVu() {
    }

    public DichVu(int idDichVu, String tenDV, double phiDV, String trangThai) {
        this.idDichVu = idDichVu;
        this.tenDV = tenDV;
        this.phiDV = phiDV;
        this.trangThai = trangThai;
    }

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getPhiDV() {
        return phiDV;
    }

    public void setPhiDV(double phiDV) {
        this.phiDV = phiDV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
     public Object[] toDatarow(){
        return new Object[]{
            this.idDichVu, this.tenDV, this.phiDV, this.trangThai
        };
    }
}
