package com.example.kiemthu3.entity;

public class SinhVien {
    private String maSV;
    private String ten;
    private int tuoi;
    private float diemTrungBinh;
    private int kyHoc;
    private String chuyenNganh;

    public SinhVien() {
    }

    public SinhVien(String maSV, String ten, int tuoi, float diemTrungBinh, int kyHoc, String chuyenNganh) {
        this.maSV = maSV;
        this.ten = ten;
        this.tuoi = tuoi;
        this.diemTrungBinh = diemTrungBinh;
        this.kyHoc = kyHoc;
        this.chuyenNganh = chuyenNganh;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public float getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(float diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    public int getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(int kyHoc) {
        this.kyHoc = kyHoc;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSV='" + maSV + '\'' +
                ", ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                ", diemTrungBinh=" + diemTrungBinh +
                ", kyHoc=" + kyHoc +
                ", chuyenNganh='" + chuyenNganh + '\'' +
                '}';
    }
}
