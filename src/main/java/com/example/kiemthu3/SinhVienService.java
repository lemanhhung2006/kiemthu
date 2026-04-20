package com.example.kiemthu3;

import com.example.kiemthu3.entity.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienService {

    private final List<SinhVien> danhSachSinhVien = new ArrayList<>();

    /**
     * Validate sinh viên - các trường không được để trống.
     * @param sinhVien đối tượng sinh viên cần validate
     * @throws IllegalArgumentException nếu có trường bị trống
     */
    public void validate(SinhVien sinhVien) {
        if (sinhVien == null) {
            throw new IllegalArgumentException("Sinh viên không được null");
        }
        if (sinhVien.getMaSV() == null || sinhVien.getMaSV().trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sinh viên không được để trống");
        }
        if (sinhVien.getTen() == null || sinhVien.getTen().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sinh viên không được để trống");
        }
        if (sinhVien.getTuoi() <= 0) {
            throw new IllegalArgumentException("Tuổi sinh viên phải lớn hơn 0");
        }
        if (sinhVien.getDiemTrungBinh() < 0 || sinhVien.getDiemTrungBinh() > 10) {
            throw new IllegalArgumentException("Điểm trung bình phải từ 0 đến 10");
        }
        if (sinhVien.getKyHoc() <= 0) {
            throw new IllegalArgumentException("Kỳ học phải lớn hơn 0");
        }
        if (sinhVien.getChuyenNganh() == null || sinhVien.getChuyenNganh().trim().isEmpty()) {
            throw new IllegalArgumentException("Chuyên ngành không được để trống");
        }
    }

    /**
     * Thêm một sinh viên mới vào danh sách.
     * @param sinhVien đối tượng sinh viên cần thêm
     * @throws IllegalArgumentException nếu validate không hợp lệ hoặc mã SV đã tồn tại
     */
    public void themSinhVien(SinhVien sinhVien) {
        validate(sinhVien);

        // Kiểm tra mã sinh viên đã tồn tại chưa
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getMaSV().equals(sinhVien.getMaSV())) {
                throw new IllegalArgumentException("Mã sinh viên đã tồn tại: " + sinhVien.getMaSV());
            }
        }

        danhSachSinhVien.add(sinhVien);
    }

    /**
     * Sửa thông tin một sinh viên đã tồn tại (tìm theo maSV).
     * @param sinhVien đối tượng sinh viên chứa thông tin mới
     * @throws IllegalArgumentException nếu validate không hợp lệ hoặc không tìm thấy sinh viên
     */
    public void suaSinhVien(SinhVien sinhVien) {
        validate(sinhVien);

        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getMaSV().equals(sinhVien.getMaSV())) {
                danhSachSinhVien.set(i, sinhVien);
                return;
            }
        }

        throw new IllegalArgumentException("Không tìm thấy sinh viên với mã: " + sinhVien.getMaSV());
    }

    /**
     * Lấy danh sách tất cả sinh viên.
     * @return danh sách sinh viên
     */
    public List<SinhVien> getDanhSachSinhVien() {
        return new ArrayList<>(danhSachSinhVien);
    }

    /**
     * Tìm sinh viên theo mã.
     * @param maSV mã sinh viên cần tìm
     * @return đối tượng sinh viên hoặc null nếu không tìm thấy
     */
    public SinhVien timSinhVienTheoMa(String maSV) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getMaSV().equals(maSV)) {
                return sv;
            }
        }
        return null;
    }
}
