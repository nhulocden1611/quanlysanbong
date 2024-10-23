package Model;

public class ChuongTrinhKhuyenMai {
    private int idKhuyenMai;
    private String tenTruongTrinhKM;
    private int giaTriKM;
    private String ngayBatDau;
    private String ngayKetThuc;

    // Constructor không tham số
    public ChuongTrinhKhuyenMai() {
    }

    // Constructor có tham số
    public ChuongTrinhKhuyenMai(int idKhuyenMai, String tenTruongTrinhKM, int giaTriKM, String ngayBatDau, String ngayKetThuc) {
        this.idKhuyenMai = idKhuyenMai;
        this.tenTruongTrinhKM = tenTruongTrinhKM;
        this.giaTriKM = giaTriKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    // Getter và Setter cho các thuộc tính
    public int getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(int idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    public String getTenTruongTrinhKM() {
        return tenTruongTrinhKM;
    }

    public void setTenTruongTrinhKM(String tenTruongTrinhKM) {
        this.tenTruongTrinhKM = tenTruongTrinhKM;
    }

    public int getGiaTriKM() {
        return giaTriKM;
    }

    public void setGiaTriKM(int giaTriKM) {
        this.giaTriKM = giaTriKM;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    // Chuyển đổi đối tượng thành mảng để hiển thị trên bảng
    public Object[] toDatarow() {
        return new Object[]{
            this.idKhuyenMai, this.tenTruongTrinhKM, this.giaTriKM, this.ngayBatDau, this.ngayKetThuc
        };
    }
}
